package com.example.crmgo.register.service.impl;

import com.example.crmgo.register.Enum.ResultEnum;
import com.example.crmgo.register.entity.SecretError;
import com.example.crmgo.register.entity.User;
import com.example.crmgo.register.entity.UserRole;
import com.example.crmgo.register.mapper.UserMapper;
import com.example.crmgo.register.mapper.UserRoleMapper;
import com.example.crmgo.register.results.Result;
import com.example.crmgo.register.service.IRegisterService;
import com.example.crmgo.register.service.ISecretErrorService;
import com.example.crmgo.register.utils.Snowflake.SnowflakeIdWorker;
import com.example.crmgo.register.utils.resultUtil.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nantian
 */
@Slf4j
@Service
public class RegisterServiceImpl implements IRegisterService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private ISecretErrorService secretErrorService;

    /**
     * 密码错误表累计条数
     */
    private static final long LIST_NUM = 1L;
    //机器标识占用的位数
    private static final long MACHINE_NUM = 2L;
    //数据中心占用的位数
    private static final long DATACENTER_NUM = 5L;

    /**
     * 数据库正确操作返回值
     */
    private static final long RETURN_NUM = 1L;

    /**
     * 方法正确返回值
     */
    private static final long CORRECT_RETURN = 1L;

    /**
     * 方法错误返回值
     */
    private static final long INCORRECT_RETURN = -1L;

    /**
     * 注册客户的默认角色:普通工人
     */
    private static final String DEFAULT_ROLEID = "Employer";

    /**
     * 数据库的有效标识位
     */
    private static final int VALID_FLAG = 0;

    /**
     * 客户注册
     * @param user 用户表实体
     * @return 结果集
     */
    @Override
    @Transactional
    public Result<Object> customerReg(User user) {
        if (null == user) {
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        if (user.getCertificatesType().isEmpty() || user.getCertificatesNo().isEmpty()) {
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        List<User> userList = new ArrayList<>(userMapper.selectByOuterKey(user.getCustomerId()));
        if (userMapper.selectEffectiveCount(user.getCertificatesType(), user.getCertificatesNo()) >= LIST_NUM
                || userList.size() >= LIST_NUM) {
            log.info("已存在该用户，无需再次注册");
            return ResultUtil.error(ResultEnum.USER_IS_EXISTS.getCode(), ResultEnum.USER_IS_EXISTS.getMsg());
        }
        if (userMapper.selectInvalidCount(user.getCertificatesType(), user.getCertificatesNo()) >= LIST_NUM) {
            log.info("该用户已注销，重新注册");
            if (RETURN_NUM != userMapper.updateByUnionKey(user)) {
                log.info("更新用户表失败");
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
            }
            log.info("用户表已更新");
            UserRole userRole = new UserRole();
            userRole.setCustomerId(user.getCustomerId());
            userRole.setValidFlag(VALID_FLAG);
            if (RETURN_NUM != userRoleMapper.updateByPrimaryKeySelective(userRole)) {
                log.info("更新用户角色表失败");
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
            }
            log.info("用户角色表已更新");
            return ResultUtil.success();
        }
        long id = new SnowflakeIdWorker(DATACENTER_NUM, MACHINE_NUM).nextId();
        user.setId(id);
        user.setValidFlag(VALID_FLAG);
        if (RETURN_NUM != userMapper.insertSelective(user)) {
            log.info("用户表插入失败");
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
        UserRole userRole = new UserRole();
        userRole.setCustomerId(user.getCustomerId());
        userRole.setRoleId(DEFAULT_ROLEID);
        userRole.setCreateTime(new Date());
        userRole.setUpdateTime(new Date());
        userRole.setValidFlag(VALID_FLAG);
        if (RETURN_NUM != userRoleMapper.insert(userRole)) {
            log.info("用户角色表插入失败");
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
        log.info("注册成功");
        return ResultUtil.success();
    }

    /**
     * 客户登录
     * @param customerId 客户号
     * @param password   密码
     * @param session    Http的session
     * @return 结果集
     */
    @Override
    public Result<Object> customerLogin(String customerId, String password, HttpSession session) {
        if (customerId.isEmpty() || password.isEmpty()) {
            log.info("输入的户名、密码不能为空,customerId#{}、password#{}", customerId, password);
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        log.info("login customerId#{},password#{}", customerId, password);
        List<User> listUser = new ArrayList<>(userMapper.selectByOuterKey(customerId));
        if (listUser.isEmpty()) {
            log.info("输入的户名不存在,请重新输入");
            return ResultUtil.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMsg());
        }
        // 先判断密码是否锁定
        Result<Object> result = secretErrorService.secretErrorQueryLock(listUser.get(0).getCustomerId());
        if (ResultEnum.SUCCESS.getCode() != result.getCode()){
            return result;
        }

        /* lambda表达式  接口类型 引用变量名 = (形参列表) -> { 方法体 } */
        if (!listUser.get(0).getPwd().equals(password)) {
            //密码输入错误
            log.info("输入的密码错误，请重新输入");
            SecretError errorNumQueryResult = (SecretError) secretErrorService
                    .secretErrorNumQuery(listUser.get(0).getCustomerId()).getData();
            if (null == errorNumQueryResult){
                log.info("首次累计");
                return secretErrorService.secretErrorAdd(listUser.get(0).getCustomerId());
            }else {
                log.info("已有累计错误记录，在原数据递增");
                return secretErrorService.secretErrorModify(errorNumQueryResult);
            }
        }else {
            //密码输入正确
            log.info("用户登陆成功");
            //如果客户密码错误表日累计有记录则重置该记录，若无记录则跳过该方法
            Result<Object> secretErrorDelResult = secretErrorService.secretErrorDel(listUser.get(0).getCustomerId());
            if (ResultEnum.SUCCESS.getCode() != secretErrorDelResult.getCode()){
                return secretErrorDelResult;
            }
            //设置session
            if (CORRECT_RETURN == setAttribute(session, customerId, password)) {
                log.info("存储session成功");
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.SESSION_SET_FAIL.getCode(), ResultEnum.SESSION_SET_FAIL.getMsg());
            }
        }
    }

    /**
     * 客户退出
     * @param session http
     * @return 结果集
     */
    @Override
    public Result<Object> customerLogout(HttpSession session) {
        removeAttribute(session);
        log.info("清除session成功");
        return ResultUtil.success();
    }

    /**
     * 修改密码
     * @param customerId 客户id
     * @param pwd        密码
     * @return 结果集
     */
    @Override
    public Result<Object> passwordModify(String customerId, String pwd) {
        if (customerId.isEmpty() || pwd.isEmpty()) {
            log.info("客户号或密码输入为空");
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        User user = new User();
        user.setCustomerId(customerId);
        user.setPwd(pwd);
        user.setUpdateTime(new Date());
        if (RETURN_NUM != userMapper.updateByPrimaryKeySelective(user)) {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
        log.info("密码修改成功");
        return ResultUtil.success();
    }

    /**
     * 生成session
     * @param session    session值
     * @param customerId 客户号
     * @param password   密码
     */
    private long setAttribute(HttpSession session, String customerId, String password) {
        if (session == null) {
            log.info("session is null, customerId{},password{}", customerId, password);
            return INCORRECT_RETURN;
        } else {
            session.setAttribute("customerId", customerId);
            session.setAttribute("password", password);
            return CORRECT_RETURN;
        }
    }

    /**
     * 移除后台session
     * @param session session值
     */
    private void removeAttribute(HttpSession session) {
        if (session != null) {
            session.removeAttribute("customerId");
            session.removeAttribute("password");
        }
    }
}

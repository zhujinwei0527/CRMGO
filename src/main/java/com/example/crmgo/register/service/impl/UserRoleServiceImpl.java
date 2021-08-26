package com.example.crmgo.register.service.impl;

import com.example.crmgo.register.Enum.ResultEnum;
import com.example.crmgo.register.entity.UserRole;
import com.example.crmgo.register.mapper.UserRoleMapper;
import com.example.crmgo.register.results.Result;
import com.example.crmgo.register.service.IUserRoleService;
import com.example.crmgo.register.utils.resultUtil.ResultUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户角色表管理服务
 * @author nantian
 */

@Slf4j
@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 分页大小
     */
    private static final int PAGESIZE = 10;

    /**
     * 客户角色映射增加
     * @param customerId 客户id
     * @param roleId     角色id
     * @return 结果集
     */
    @Override
    public Result<Object> userRoleAdd(String customerId, String roleId){
        if (customerId.isEmpty() || roleId.isEmpty()) {
            log.info("输入有空值,customerId-{},roleId->{}",customerId,roleId);
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        if (userRoleMapper.selectByCustomerId(customerId) == null){
            UserRole userRole = new UserRole();
            userRole.setCustomerId(customerId);
            userRole.setRoleId(roleId);
            userRole.setCreateTime(new Date());
            userRole.setUpdateTime(new Date());
            userRole.setValidFlag(0);
            if (1 == userRoleMapper.insert(userRole)){
                return ResultUtil.success(userRole);
            }else {
                log.info("插入数据库失败");
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMsg());
            }
        }
        log.info("表中有记录");
        UserRole userRole = new UserRole();
        userRole.setCustomerId(customerId);
        userRole.setRoleId(roleId);
        userRole.setUpdateTime(new Date());
        userRole.setValidFlag(0);
        if (1 == userRoleMapper.updateByPrimaryKeySelective(userRole)){
            return ResultUtil.success();
        }else {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }

    /**
     * 客户角色映射修改编辑
     * @param customerId 客户id
     * @param roleId     角色id
     * @return 结果集
     */
    @Override
    public Result<Object> userRoleModify(String customerId, String roleId) {
        if (customerId.isEmpty() || roleId.isEmpty()){
            log.info("输入有空值,customerId-{},roleId->{}",customerId,roleId);
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        UserRole userRole = new UserRole();
        userRole.setCustomerId(customerId);
        userRole.setRoleId(roleId);
        userRole.setUpdateTime(new Date());
        if (1 == userRoleMapper.updateByPrimaryKeySelective(userRole)){
            return ResultUtil.success();
        }else {
            log.info("更新失败");
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }

    /**
     * 客户角色删除
     * @param customerId 客户id
     * @return 结果集
     */
    @Override
    public Result<Object> userRoleDel(String customerId) {
        if (customerId.isEmpty()){
            log.info("输入的参数为空 customerId->{}",customerId);
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        UserRole userRole = new UserRole();
        userRole.setCustomerId(customerId);
        userRole.setUpdateTime(new Date());
        userRole.setValidFlag(1);
        if (1 == userRoleMapper.updateByPrimaryKeySelective(userRole)){
            return ResultUtil.success();
        }else {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }

    /**
     * 查询所有的用户角色记录
     * @return 用户角色list
     */
    @Override
    public Result<Object> userRoleQueryAll(int page) {
        if (page <=0){
            log.info("输入的页码有误，请重新输入 page->{}",page);
            return ResultUtil.error(ResultEnum.INPUT_ERROR.getCode(), ResultEnum.INPUT_ERROR.getMsg());
        }
        //引入分页查询
        //在查询之前传入当前页，然后多少记录
        PageMethod.startPage(page,PAGESIZE);
        PageInfo<UserRole> userRolePageInfo = new PageInfo<>(userRoleMapper.selectAll());
        return ResultUtil.success(userRolePageInfo.getList());
    }

    /**
     * 根据用户id查询用户角色
     * @param customerId 客户id
     * @return 返回userRole对象
     */
    @Override
    public Result<Object> userRoleQuery(String customerId) {
        if (customerId.isEmpty()){
            log.info("输入的customerId为空，customerId->{}",customerId);
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        return ResultUtil.success(userRoleMapper.selectByCustomerId(customerId));
    }
}

package com.example.crmgo.register.service.impl;

import com.example.crmgo.register.Enum.ResultEnum;
import com.example.crmgo.register.entity.SecretError;
import com.example.crmgo.register.mapper.SecretErrorMapper;
import com.example.crmgo.register.results.Result;
import com.example.crmgo.register.service.ISecretErrorService;
import com.example.crmgo.register.utils.resultUtil.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author nantian
 */

@Slf4j
@Service
public class SecretErrorServiceImpl implements ISecretErrorService {

    @Resource
    private SecretErrorMapper secretErrorMapper;

    /**
     * 数据库异常标识位
     */
    private static final int INVALID_FLAG = 1;

    /**
     * 数据库正确返回码
     */
    private static final int RETURN_CORRECT_NUM = 1;

    /**
     * 数据库的有效标识位
     */
    private static final int VALID_FLAG = 0;

    /**
     * 密码日累计最大输入错误次数
     */
    private static final int SECRET_ERROR_MAX = 4;

    /**
     * 密码错误表增加记录
     * @param customerId 客户id
     * @return 数据库返回标识
     */
    @Override
    public Result<Object> secretErrorAdd(String customerId) {
        if (null != customerId){
            SecretError secretError = new SecretError();
            secretError.setCustomerId(customerId);
            secretError.setModifyDate(new Date());
            secretError.setErrorNumDay(1);
            secretError.setErrorNumTotal(1);
            secretError.setValidFlag(VALID_FLAG);
            secretError.setLockFlag(VALID_FLAG);
            if (RETURN_CORRECT_NUM == secretErrorMapper.insertSelective(secretError)){
                log.info("插入数据成功");
                return ResultUtil.success(null);
            }else {
                log.info("插入数据失败");
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
            }
        }else {
            log.info("customerId值为空");
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    /**
     * 密码错误表修改记录
     * @param secretError 密码错误表实体
     * @return 结果集
     */
    @Override
    public Result<Object> secretErrorModify(SecretError secretError) {
        if (null != secretError){
            int newErrorNumDay = secretError.getErrorNumTotal() + 1;
            secretError.setErrorNumTotal(newErrorNumDay);
            secretError.setErrorNumDay(secretError.getErrorNumDay() + 1);
            if (newErrorNumDay < SECRET_ERROR_MAX) {
                secretError.setLockFlag(VALID_FLAG);
            }else {
                secretError.setLockFlag(INVALID_FLAG);
            }
            if (RETURN_CORRECT_NUM == secretErrorMapper.updateByPrimaryKeySelective(secretError)){
                log.info("修改数据成功");
                return ResultUtil.success();
            }else {
                log.info("修改数据失败");
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMsg());
            }
        }else {
            log.info("secretError值为空");
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    /**
     * 密码错误表锁定记录查询
     * @param customerId 客户号
     * @return Result
     */
    @Override
    public Result<Object> secretErrorQueryLock(String customerId) {
        if (customerId.isEmpty()){
            log.info("customerId为空");
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(),ResultEnum.DATA_IS_NULL.getMsg());
        }
        SecretError secretError = secretErrorMapper.selectByCustomerId(customerId);
        if (null == secretError){
            log.info("密码错误表记录为空");
            return ResultUtil.error(ResultEnum.DATABASE_NO_DATA.getCode(),ResultEnum.DATABASE_NO_DATA.getMsg());
        }else {
            if (INVALID_FLAG == secretError.getLockFlag()){
                log.info("密码已锁定，请先解锁密码");
                return ResultUtil.error(ResultEnum.SECRET_LOCKED.getCode(),ResultEnum.SECRET_LOCKED.getMsg());
            }
            else {
                log.info("密码未锁定");
                return ResultUtil.success(null);
            }
        }
    }

    /**
     * 根据客户id搜索密码错误表记录
     * @param customerId 客户id
     * @return 密码错误表记录
     */
    @Override
    public Result<Object> secretErrorNumQuery(String customerId) {
        if (customerId.isEmpty()){
            log.info("customerId为空");
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(),ResultEnum.DATA_IS_NULL.getMsg());
        }
        SecretError secretError = secretErrorMapper.selectByCustomerId(customerId);
        return ResultUtil.success(secretError);
    }

    /**
     * 重置密码错误累计表日累计次数为0
     * @param customerId 客户id
     * @return 结果集
     */
    @Override
    public Result<Object> secretErrorDel(String customerId) {
        if (customerId.isEmpty()){
            log.info("customerId为空");
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(),ResultEnum.DATA_IS_NULL.getMsg());
        }
        SecretError secretError = secretErrorMapper.selectByCustomerId(customerId);
        if (null != secretError){
            secretError.setErrorNumDay(0);
            secretError.setLockFlag(0);
            if (RETURN_CORRECT_NUM != secretErrorMapper.updateByPrimaryKeySelective(secretError)) {
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
            }
            log.info("密码错误表重置成功");
            return ResultUtil.success();
        }else {
            log.info("密码错误表无记录，直接跳过该方法");
            return ResultUtil.success();
        }
    }
}

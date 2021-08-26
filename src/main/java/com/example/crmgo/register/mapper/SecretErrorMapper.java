package com.example.crmgo.register.mapper;

import com.example.crmgo.register.entity.SecretError;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author nantian
 */
public interface SecretErrorMapper {
    /**
     * show 根据客户号删除记录
     * @param customerId 客户号
     * @return 返回结果
     */
    int deleteByCustomerId(String customerId);

    /**
     * show 全表密码插入
     * @param record 全表插入
     * @return 返回结果
     */
    int insert(SecretError record);

    /**
     * show 选择性插入
     * @param record 选择性插入
     * @return 返回成功或失败
     */
    int insertSelective(SecretError record);

    /**
     * show 根据主键刷选
     * @param customerId 客户号
     * @return 返回密码错误记录表对象
     */
    SecretError selectByCustomerId(String customerId);

    /**
     * show 根据输入的记录选择性更新
     * @param record 记录
     * @return 返回结果
     */
    int updateByPrimaryKeySelective(SecretError record);

    /**
     * show 根据输入的记录更新
     * @param record 记录
     * @return 返回结果
     */
    int updateByPrimaryKey(SecretError record);
}
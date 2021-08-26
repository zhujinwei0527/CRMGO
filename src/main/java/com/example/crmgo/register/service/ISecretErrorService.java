package com.example.crmgo.register.service;


import com.example.crmgo.register.entity.SecretError;
import com.example.crmgo.register.results.Result;

/**
 * @author nantian
 */
public interface ISecretErrorService {
    /**
     * 密码错误表增加记录
     * @param customerId 客户id
     * @return 结果集
     */
     Result<Object> secretErrorAdd(String customerId);

    /**
     * 密码错误表修改记录
     * @param secretError  密码错误表实体
     * @return 结果集
     */
     Result<Object> secretErrorModify(SecretError secretError);

    /**
     * 密码错误表查询
     * @param customerId 客户号
     * @return 结果集
     */
     Result<Object> secretErrorQueryLock(String customerId);

    /**
     * 客户密码错误表记录数查询
     * @param customerId 客户id
     * @return 搜索结果集
     */
     Result<Object> secretErrorNumQuery(String customerId);

    /**
     * 重置密码错误累计表日累计次数为0
     * @param customerId 客户id
     * @return 更新结果集
     */
     Result<Object> secretErrorDel(String customerId);
}

package com.example.crmgo.register.service;

import com.example.crmgo.register.results.Result;

/**
 * 客户映射角色管理
 * @author nantian
 */
public interface IUserRoleService {
    /**
     * 客户角色映射增加
     * @param customerId 客户id
     * @param roleId 角色id
     * @return 结果集
     */
    Result<Object> userRoleAdd(String customerId, String roleId);

    /**
     * 客户角色映射修改编辑
     * @param customerId 客户id
     * @param roleId 角色id
     * @return 结果集
     */
    Result<Object> userRoleModify(String customerId, String roleId);

    /**
     * 客户角色删除
     * @param customerId 客户id
     * @return 结果集
     */
    Result<Object> userRoleDel(String customerId);

    /**
     * 查询所有的用户角色记录
     * @param page 页码
     * @return 用户角色list
     */
    Result<Object> userRoleQueryAll(int page);

    /**
     * 根据用户id查询用户角色
     * @param customerId 客户id
     * @return 返回userRole对象
     */
    Result<Object> userRoleQuery(String customerId);
}

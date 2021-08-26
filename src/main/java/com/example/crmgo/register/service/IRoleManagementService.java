package com.example.crmgo.register.service;

import com.example.crmgo.register.results.Result;

/**
 * 角色参数管理
 * @author nantian
 */
public interface IRoleManagementService {
    /**
     * 角色编辑方法
     * @param roleId         角色id
     * @param rolePermission 角色权限
     * @return 返回值
     */
    Result<Object> roleEdit(String roleId, int rolePermission);

    /**
     * 角色增加
     * @param roleId 角色id
     * @param roleName  角色名称
     * @param rolePermission 角色权限
     * @return 返回值
     */
    Result<Object> roleAdd(String roleId, String roleName, int rolePermission);

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 返回值
     */
    Result<Object> roleDelete(String roleId);

    /**
     * 查询所有角色参数列表
     * @param page 页号
     * @return paraRole列表
     */
    Result<Object> roleQuery(int page);
}

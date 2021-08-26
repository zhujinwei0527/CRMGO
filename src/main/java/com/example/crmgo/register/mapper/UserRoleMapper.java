package com.example.crmgo.register.mapper;

import com.example.crmgo.register.entity.UserRole;

import java.util.List;


/**
 * @author nantian
 */
public interface UserRoleMapper {
    /**
     * show 根据客户号删除记录
     * @param customerId 客户id
     * @return 返回结果
     */
    int deleteByCustomerId(String customerId);

    /**
     * show 根据客户角色插入
     * @param userRole 角色记录
     * @return 返回值
     */
    int insert(UserRole userRole);

    /**
     * show 根据客户号搜索记录
     * @param customerId 客户id
     * @return 返回对象
     */
    UserRole selectByCustomerId(String customerId);

    /**
     * 全表查询
     * @return 用户角色表List
     */
    List<UserRole> selectAll();

    /**
     * show 选择性更新记录
     * @param userRole 选择性更新
     * @return 返回值
     */
    int updateByPrimaryKeySelective(UserRole userRole);

    /**
     * show 根据客户号更新
     * @param userRole 更新记录
     * @return 返回值
     */
    int updateByCustomerId(UserRole userRole);
}
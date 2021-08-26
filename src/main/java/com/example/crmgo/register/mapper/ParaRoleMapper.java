package com.example.crmgo.register.mapper;

import com.example.crmgo.register.entity.ParaRole;

import java.util.List;

/**
 * @author nantian
 */
public interface ParaRoleMapper {
    /**
     * show 角色参数表
     * @param roleId 角色id
     * @return 返回结果
     */
    int deleteByRoleId(String roleId);

    /**
     * show 插入新纪录
     * @param record 角色记录
     * @return 返回值
     */
    int insert(ParaRole record);

    /**
     * show 选择性插入
     * @param record 角色记录
     * @return 返回值
     */
    int insertSelective(ParaRole record);

    /**
     * show 根据角色号搜索
     * @param roleId 角色号
     * @return 返回对象值
     */
    ParaRole selectByRoleId(String roleId);

    /**
     * show 查询全部记录
     * @return 返回多条记录
     */
    List<ParaRole> selectAll();

    /**
     * show 根据角色号选择性修改
     * @param record 角色记录
     * @return 返回值
     */
    int updateByRoleIdSelective(ParaRole record);

    /**
     * show 根据角色号全记录修改
     * @param record 角色记录
     * @return 返回值
     */
    int updateByRoleId(ParaRole record);
}
package com.example.crmgo.register.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author nantian
 */
@Data
@ToString
public class ParaRole {
    private Integer id;
    private String roleId;
    private String roleName;
    private Integer rolePermission;
    private Date createTime;
    private Date updateTime;
    private Integer validFlag;
}
package com.example.crmgo.register.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author nantian
 */

@Data
@ToString
public class UserRole {
    private Integer id;
    private String customerId;
    private String roleId;
    private Date createTime;
    private Date updateTime;
    private Integer validFlag;
}
package com.example.crmgo.register.entity;

import lombok.Data;
import lombok.ToString;
import java.util.Date;

/**
 * @author nantian
 */
@Data
@ToString
public class User {
    private Long id;
    private String customerId;
    private String customerName;
    private String certificatesType;
    private String certificatesNo;
    private String pwd;
    private String phoneNo;
    private Integer validFlag;
    private Date createTime;
    private Date updateTime;
}
package com.example.crmgo.register.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author nantian
 */
@Data
@ToString
public class SecretError {
    private long id;
    private String customerId;
    private Date modifyDate;
    private Integer errorNumDay;
    private Integer errorNumTotal;
    private Integer validFlag;
    private Integer lockFlag;
}
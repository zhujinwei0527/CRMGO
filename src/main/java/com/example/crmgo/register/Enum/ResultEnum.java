package com.example.crmgo.register.Enum;

import lombok.Getter;
import lombok.ToString;

/**
 * @author nantian
 */

@Getter
@ToString
public enum ResultEnum {
    //未知错误
    UNKNOWN_ERROR(-1, "未知错误"),
    //交易成功
    SUCCESS(10000, "成功"),
    //用户不存在
    USER_NOT_EXIST(1, "用户不存在"),
    //用户已存在
    USER_IS_EXISTS(2, "用户已存在"),
    //数据为空
    DATA_IS_NULL(3, "输入数据为空"),
    //输入有误
    INPUT_ERROR(4,"输入数据错误"),
    //用户注销
    USER_IS_LOGOUT(5,"用户已注销"),
    //密码错误次数超限
    SECRET_ERROR_DAYMAX(6,"密码错误次数已达上限"),
    //数据库无记录
    DATABASE_NO_DATA(7,"数据库无记录"),
    //session设置失败
    SESSION_SET_FAIL(8,"session设置失败"),
    //密码输入错误
    SECRET_INPUT_ERROR(9,"密码输入错误"),
    //密码已锁
    SECRET_LOCKED(10,"密码已锁定"),
    //密码未锁定
    SECRET_UNLOCK(11,"密码未锁定");

    private final int code;
    private final String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

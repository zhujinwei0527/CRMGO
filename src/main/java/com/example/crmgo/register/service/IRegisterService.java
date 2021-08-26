package com.example.crmgo.register.service;

import com.example.crmgo.register.entity.User;
import com.example.crmgo.register.results.Result;

import javax.servlet.http.HttpSession;

/**
 * 客户注册管理
 * @author nantian
 */
public interface IRegisterService {
    /**
     * show 用户注册
     * @param user 用户表实体
     * @return 返回值
     */
    Result<Object> customerReg(User user);

    /**
     * show 客户登录账号
     * @param customerId 客户号
     * @param pwd 客户密码
     * @param session Http的session
     * @return 成功或失败
     */
    Result<Object> customerLogin(String customerId, String pwd, HttpSession session);

    /**
     * show 客户退出登录
     * @param session http
     * @return 返回值
     */
    Result<Object> customerLogout(HttpSession session);

    /**
     * show 密码修改接口
     * @param customerId 客户id
     * @param pwd 密码
     * @return 返回值
     */
    Result<Object> passwordModify(String customerId, String pwd);
}

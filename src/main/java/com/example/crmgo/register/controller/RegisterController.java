package com.example.crmgo.register.controller;

import com.example.crmgo.register.entity.User;
import com.example.crmgo.register.results.Result;
import com.example.crmgo.register.service.IRegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author nantian
 */
@RestController()
public class RegisterController {
    @Resource
    private IRegisterService registerService;

    @PostMapping(value = "/user/register")
    public Result<Object> registerService(@RequestBody User user) {
        return registerService.customerReg(user);
    }

    @PostMapping(value = "/user/login")
    public Result<Object> customerLogin(@RequestParam String customerId, @RequestParam String pwd, HttpSession session) {
        return registerService.customerLogin(customerId, pwd, session);
    }

    @PostMapping(value = "/user/logout")
    public Result<Object> customerLogout(HttpSession session){
        return registerService.customerLogout(session);
    }

    @PostMapping(value = "/user/passwords")
    public Result<Object> customerModify(@RequestParam String customerId, @RequestParam String pwd){
        return registerService.passwordModify(customerId,pwd);
    }
}

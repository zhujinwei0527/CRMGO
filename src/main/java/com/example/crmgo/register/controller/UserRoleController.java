package com.example.crmgo.register.controller;

import com.example.crmgo.register.results.Result;
import com.example.crmgo.register.service.IUserRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nantian
 */

@RestController
public class UserRoleController {

    @Resource
    private IUserRoleService userRoleService;

    @PostMapping(value = "/userRoleAdd")
    public Result<Object> userRoleAdd(@RequestParam String customerId,@RequestParam String roleId){
        return userRoleService.userRoleAdd(customerId,roleId);
    }

    @PostMapping(value = "/userRoleModify")
    public Result<Object> userRoleModify(@RequestParam String customerId, @RequestParam String roleId){
        return userRoleService.userRoleModify(customerId,roleId);
    }

    @PostMapping(value = "/userRoleDel")
    public Result<Object> userRoleDel(@RequestParam String customerId){
        return userRoleService.userRoleDel(customerId);
    }

    @GetMapping(value = "/userRoleQueryAll")
    public Result<Object> userRoleQueryAll(int page){
        return userRoleService.userRoleQueryAll(page);
    }

    @GetMapping(value = "/userRoleQuery")
    public Result<Object> userRoleQuery(String customerId){
        return userRoleService.userRoleQuery(customerId);
    }
}

package com.example.crmgo.register.controller;

import com.example.crmgo.register.results.Result;
import com.example.crmgo.register.service.IRoleManagementService;
import com.example.crmgo.register.service.ISecretErrorService;
import com.example.crmgo.register.service.IUserRoleService;
import com.example.crmgo.register.utils.resultUtil.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class Test {

    @Resource
    private IRoleManagementService roleManagement;
    @Resource
    private IUserRoleService userRoleService;

    @Resource
    private ISecretErrorService secretErrorService;

    @GetMapping("/test")
    public void test() {
        log.info("拦截器失效");
    }

    @GetMapping(value = "/test/query")
    public Result<Object> roleQry(@RequestParam int page){
        return roleManagement.roleQuery(page);
    }

    @GetMapping(value = "/test1")
    public Result<Object> test1(){
        String str = "Vadodara";
        return ResultUtil.success(str);
    }

    @PostMapping(value = "/test2")
    public Result<Object> test2(String customerId){
        return userRoleService.userRoleQuery(customerId);
    }

    @GetMapping(value = "/test3")
    public Result<Object> test3(@RequestParam String customerId){
        return secretErrorService.secretErrorQueryLock(customerId);
    }
}

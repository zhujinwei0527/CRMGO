package com.example.crmgo.register.controller;

import com.example.crmgo.register.results.Result;
import com.example.crmgo.register.service.IRoleManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nantian
 */

@RestController
public class RoleManageController {

    @Resource
    private IRoleManagementService roleManagement;

    @GetMapping(value = "/roleManage/query")
    public Result<Object> roleQry(@RequestParam int page){
        return roleManagement.roleQuery(page);
    }

    @PostMapping(value = "/roleManage/add")
    public Result<Object> roleAdd(@RequestParam String roleId, @RequestParam String roleName,
                                  @RequestParam int rolePermission){
        return roleManagement.roleAdd(roleId, roleName, rolePermission);
    }

    @PostMapping(value = "/roleManage/delete")
    public Result<Object> roleDel(@RequestParam String roleId){
        return roleManagement.roleDelete(roleId);
    }

    @PostMapping(value = "/roleManage/modify")
    public Result<Object> roleMode(@RequestParam String roleId, @RequestParam int rolePermission){
        return roleManagement.roleEdit(roleId, rolePermission);
    }
}

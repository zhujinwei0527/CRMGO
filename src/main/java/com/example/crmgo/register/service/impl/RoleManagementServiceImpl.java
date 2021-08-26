package com.example.crmgo.register.service.impl;

import com.example.crmgo.register.Enum.ResultEnum;
import com.example.crmgo.register.entity.ParaRole;
import com.example.crmgo.register.mapper.ParaRoleMapper;
import com.example.crmgo.register.results.Result;
import com.example.crmgo.register.service.IRoleManagementService;
import com.example.crmgo.register.utils.resultUtil.ResultUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author nantian
 */

@Slf4j
@Service
public class RoleManagementServiceImpl implements IRoleManagementService {

    @Resource
    private ParaRoleMapper paraRoleMapper;

    //分页大小
    private static final int PAGESIZE = 10;

    //数据库正确操作返回值
    private final static long RETURN_NUM = 1L;

    /**
     * 角色编辑方法
     * @param roleId         角色id
     * @param rolePermission 角色权限
     * @return 返回值
     */
    @Override
    public Result<Object> roleEdit(String roleId, int rolePermission) {
        if (roleId.isEmpty() || rolePermission == 0){
            log.info("输入参数值不能为空，roleId->#{},rolePermission->#{}",roleId,rolePermission);
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        ParaRole paraRole = new ParaRole();
        paraRole.setRoleId(roleId);
        paraRole.setRolePermission(rolePermission);
        paraRole.setUpdateTime(new Date());
        if (RETURN_NUM != paraRoleMapper.updateByRoleIdSelective(paraRole)){
            log.info("数据库执行失败");
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
        log.info("角色表数据修改成功");
        return ResultUtil.success();
    }

    /**
     * 角色增加
     * @param roleId         角色id
     * @param roleName       角色名称
     * @param rolePermission 角色权限
     * @return 返回值
     */
    @Override
    public Result<Object> roleAdd(String roleId, String roleName, int rolePermission) {
        if (roleId.isEmpty() || roleName.isEmpty() || rolePermission == 0) {
            log.info("输入数据不能为空，请核对后再重新输入");
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        ParaRole paraRole = new ParaRole();
        paraRole.setRoleId(roleId);
        paraRole.setRoleName(roleName);
        paraRole.setRolePermission(rolePermission);
        paraRole.setCreateTime(new Date());
        paraRole.setUpdateTime(new Date());
        paraRole.setValidFlag(0);
        if (RETURN_NUM != paraRoleMapper.insert(paraRole)){
            log.info("插入数据失败");
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
        log.info("插入数据成功");
        return ResultUtil.success();
    }

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 返回值
     */
    @Override
    public Result<Object> roleDelete(String roleId) {
        if (roleId.isEmpty()){
            log.info("输入数据不能为空 roleId->{}",roleId);
            ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        if (null == paraRoleMapper.selectByRoleId(roleId)) {
            log.info("无该条记录，新刷新页面");
            return ResultUtil.error(ResultEnum.DATABASE_NO_DATA.getCode(),ResultEnum.DATABASE_NO_DATA.getMsg());
        }
        if (RETURN_NUM != paraRoleMapper.deleteByRoleId(roleId)){
            log.info("数据库删除失败");
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
        return ResultUtil.success();
    }

    /**
     * @return paraRole列表
     */
    @Override
    public Result<Object> roleQuery(int page) {
        if (page <= 0){
            log.info("输入的页码有误，请重新输入 page->{}",page);
            return ResultUtil.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
        //引入分页查询
        //在查询之前传入当前页，然后多少记录
        PageMethod.startPage(page, PAGESIZE);
        PageInfo<ParaRole> paraRolePageInfo = new PageInfo<>(paraRoleMapper.selectAll());
        return ResultUtil.success(paraRolePageInfo.getList());
    }
}

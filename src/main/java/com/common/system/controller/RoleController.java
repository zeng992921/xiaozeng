package com.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.core.web.*;
import com.common.system.entity.Role;
import com.common.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping()
    public String view() {
        return "system/role.html";
    }

    /**
     * 分页查询角色
     */
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Role> page(HttpServletRequest request) {
        PageParam<Role> pageParam = new PageParam<>(request);
        return new PageResult<>(roleService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
    }

    /**
     * 查询全部角色
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Role> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(roleService.list(pageParam.getOrderWrapper()));
    }

    /**
     * 根据id查询角色
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(roleService.getById(id));
    }

    /**
     * 添加角色
     */
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Role role) {
        if (roleService.count(new QueryWrapper<Role>().eq("role_code", role.getRoleCode())) > 0) {
            return JsonResult.error("角色标识已存在");
        }
        if (roleService.count(new QueryWrapper<Role>().eq("role_name", role.getRoleName())) > 0) {
            return JsonResult.error("角色名称已存在");
        }
        if (roleService.save(role)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改角色
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Role role) {
        if (roleService.count(new QueryWrapper<Role>().eq("role_code", role.getRoleCode())
                .ne("role_id", role.getRoleId())) > 0) {
            return JsonResult.error("角色标识已存在");
        }
        if (roleService.count(new QueryWrapper<Role>().eq("role_name", role.getRoleName())
                .ne("role_id", role.getRoleId())) > 0) {
            return JsonResult.error("角色名称已存在");
        }
        if (roleService.updateById(role)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除角色
     */
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (roleService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 批量删除角色
     */
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (roleService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

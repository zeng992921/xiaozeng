package com.common.system.controller;


import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.core.web.*;
import com.common.system.entity.Menu;
import com.common.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单管理
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @RequestMapping()
    public String view() {
        return "system/menu.html";
    }

    /**
     * 分页查询菜单
     */
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Menu> page(HttpServletRequest request) {
        PageParam<Menu> pageParam = new PageParam<>(request);
        pageParam.setDefaultOrder(new String[]{"sort_number"}, null);
        return menuService.listPage(pageParam);
    }

    /**
     * 查询全部菜单
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Menu> pageParam = new PageParam<>(request);
        pageParam.setDefaultOrder(new String[]{"sort_number"}, null);
        return JsonResult.ok().setData(menuService.list(pageParam.getOrderWrapper()));
    }

    /**
     * 根据id查询菜单
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(menuService.getById(id));
    }

    /**
     * 添加菜单
     */
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Menu menu) {
        if (menuService.save(menu)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改菜单
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Menu menu) {
        if (menuService.updateById(menu)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除菜单
     */
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (menuService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 批量添加菜单
     */
    @ResponseBody
    @RequestMapping("/saveBatch")
    public JsonResult saveBatch(@RequestBody List<Menu> menuList) {
        if (menuService.saveBatch(menuList)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 批量删除菜单
     */
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (menuService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

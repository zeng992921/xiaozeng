package com.common.system.controller;

import com.common.core.shiro.CustomizedToken;
import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.system.entity.LoginRecord;
import com.common.system.service.LoginRecordService;
import com.common.system.entity.Menu;
import com.common.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页、登录
 */
@Controller
public class MainController extends BaseController implements ErrorController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private LoginRecordService loginRecordService;

    /**
     * 用户登录
     */
    @ResponseBody
    @PostMapping("/login")
    public JsonResult login(String username, String password, Boolean remember, HttpServletRequest request) {
        if (username == null || username.trim().isEmpty()) return JsonResult.error("请输入账号");
        try {
            if (remember == null) remember = false;
            SecurityUtils.getSubject().login(new CustomizedToken(username, password, remember,"Admin"));
            loginRecordService.saveAsync(username, request);
            return JsonResult.ok("登录成功");
        } catch (IncorrectCredentialsException ice) {
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, "密码错误", request);
            return JsonResult.error("密码错误");
        } catch (UnknownAccountException uae) {
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, "账号不存在", request);
            return JsonResult.error("账号不存在");
        } catch (LockedAccountException e) {
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, "账号被锁定", request);
            return JsonResult.error("账号被锁定");
        } catch (ExcessiveAttemptsException eae) {
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, "操作频繁", request);
            return JsonResult.error("操作频繁，请稍后再试");
        }
    }

    /**
     * 登录页
     */
    @GetMapping("/login")
    public String login() {
        if (getLoginUser() != null) return "redirect:index";
        return "login.html";
    }

    /**
     * 主页
     */
    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        // 左侧菜单
        List<Menu> menus = menuService.getUserMenu(getLoginUserId(), Menu.TYPE_MENU);
        model.addAttribute("menus", menuService.toMenuTree(menus, 0));
        return "index.html";
    }

    /**
     * 主页弹窗页面
     */
    @RequestMapping("/tpl/{name}")
    public String tpl(@PathVariable("name") String name) {
        return "index/" + name + ".html";
    }

    /**
     * 错误页
     */
    @RequestMapping("/error")
    public String error() {
        return "error/404.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}

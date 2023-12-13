package com.common.system.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.core.web.*;
import com.common.system.entity.User;
import com.common.system.service.DictionaryDataService;
import com.common.system.service.RoleService;
import com.common.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private DictionaryDataService dictionaryDataService;
    @Autowired
    private RoleService roleService;
    @RequestMapping()
    public String view(Model model) {
        model.addAttribute("sexList", dictionaryDataService.listByDictCode("sex"));
        model.addAttribute("organizationTypeList", dictionaryDataService.listByDictCode("organization_type"));
        model.addAttribute("rolesJson", JSON.toJSONString(roleService.list()));
        return "system/user.html";
    }

    /**
     * 个人中心
     */
    @RequestMapping("/info")
    public String userInfo(Model model) {
        model.addAttribute("user", userService.getFullById(getLoginUserId()));
        model.addAttribute("sexList", dictionaryDataService.listByDictCode("sex"));
        return "index/user-info.html";
    }

    /**
     * 分页查询用户
     */

    @ResponseBody
    @RequestMapping("/page")
    public PageResult<User> page(HttpServletRequest request) {
        PageParam<User> pageParam = new PageParam<>(request);
        pageParam.setDefaultOrder(null, new String[]{"create_time"});
        return userService.listPage(pageParam);
    }

    /**
     * 查询全部用户
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<User> pageParam = new PageParam<>(request);
        List<User> records = userService.listAll(pageParam.getNoPageParam());
        return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    /**
     * 根据id查询用户
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        PageParam<User> pageParam = new PageParam<>();
        pageParam.put("userId", id);
        List<User> records = userService.listAll(pageParam.getNoPageParam());
        return JsonResult.ok().setData(pageParam.getOne(records));
    }

    /**
     * 添加用户
     */
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(@RequestBody User user) {
        user.setState(0);
        user.setPassword(userService.encodePsw(user.getPassword()));
        if (userService.saveUser(user)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改用户
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(@RequestBody User user) {
        user.setState(null);  // 状态不能修改
        user.setPassword(null);  // 密码不能修改
        user.setUsername(null);  // 账号不能修改
        if (userService.updateUser(user)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除用户
     */
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (userService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }


    /**
     * 批量删除用户
     */

    @ResponseBody
    @RequestMapping("/deleteBatch")
    public JsonResult deleteBatch(@RequestBody List<Integer> ids) {
        if (userService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 修改用户状态
     */
    @ResponseBody
    @RequestMapping("/state/update")
    public JsonResult updateState(Integer id, Integer state) {
        if (state == null || (state != 0 && state != 1)) {
            return JsonResult.error("状态值不正确");
        }
        User user = new User();
        user.setUserId(id);
        user.setState(state);
        if (userService.updateById(user)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }


    /**
     * 重置密码
     */
    @ResponseBody
    @RequestMapping("/psw/reset")
    public JsonResult resetPsw(Integer id, String password) {
        User user = new User();
        user.setUserId(id);
        user.setPassword(userService.encodePsw(password));
        if (userService.updateById(user)) {
            return JsonResult.ok("重置成功");
        } else {
            return JsonResult.error("重置失败");
        }
    }

    /**
     * 修改自己密码
     */
    @ResponseBody
    @RequestMapping("/psw/update")
    public JsonResult updatePsw(String oldPsw, String newPsw) {
        if (StrUtil.hasBlank(oldPsw, newPsw)) {
            return JsonResult.error("参数不能为空");
        }
        if (getLoginUserId() == null) {
            return JsonResult.error("未登录");
        }
        if (!userService.comparePsw(userService.getById(getLoginUserId()).getPassword(), oldPsw)) {
            return JsonResult.error("原密码输入不正确");
        }
        User user = new User();
        user.setUserId(getLoginUserId());
        user.setPassword(userService.encodePsw(newPsw));
        if (userService.updateById(user)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 修改自己资料
     */
    @ResponseBody
    @RequestMapping("/info/update")
    public JsonResult updateInfo(User user) {
        user.setUserId(getLoginUserId());
        // 不能修改的字段
        user.setState(null);
        user.setPassword(null);
        user.setUsername(null);
        user.setOrganizationId(null);
        if (userService.updateById(user)) {
            User loginUser = getLoginUser();
            if (user.getNickName() != null) loginUser.setNickName(user.getNickName());
            if (user.getAvatar() != null) loginUser.setAvatar(user.getAvatar());
            return JsonResult.ok("保存成功");
        }
        return JsonResult.error("保存失败");
    }

}

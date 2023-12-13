package com.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Teacher;
import com.common.system.service.TeacherService;
import com.common.system.entity.User;
import com.common.system.entity.UserRole;
import com.common.system.service.UserRoleService;
import com.common.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 教师管理
 */
@Controller
@RequestMapping("/course/teacher")
public class TeacherController extends BaseController {
	
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;

    @RequestMapping()
    public String view() {
        return "course/teacher.html";
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Teacher> page(HttpServletRequest request) {
        PageParam<Teacher> pageParam = new PageParam<>(request);
        return new PageResult<>(teacherService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return teacherService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 查询全部
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Teacher> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(teacherService.list(pageParam.getOrderWrapper()));
    }
    
    /**
     * 查询全部
     */
    @ResponseBody
    @RequestMapping("/listuser")
    public JsonResult listuser() {
    	List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().eq("role_id", 2));
    	String ids = "";
    	for(UserRole userrole : userRoles) {
    		ids += userrole.getUserId()+",";
    	}
    	ids = ids.substring(0,ids.length() - 1);
    	List<User> users = userService.list(new QueryWrapper<User>().in("user_id", ids).eq("state", 0).eq("deleted", 0));
        return JsonResult.ok().setData(users);
   }

    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(teacherService.getById(id));
    }
    
    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/getById")
    public String getById(Integer id) {
    	Teacher teacher = teacherService.getById(id);
        return teacher.getNickName();
    }
    
    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/userId")
    public String userId(Integer userId) {
    	User user = userService.getById(userId);
        return user.getUsername();
    }

    /**
     *给教师授权
     */
    @ResponseBody
    @RequestMapping("/updateByuserId")
    public JsonResult updateByuserId(Integer id,Integer userId) {
    	Teacher teacher = teacherService.getById(id);
    	teacher.setUserId(userId);
    	if (teacherService.updateById(teacher)) {
            return JsonResult.ok("授权成功");
        }
        return JsonResult.error("授权失败");
    	
    }

    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Teacher teacher) {
        if (teacherService.save(teacher)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Teacher teacher) {
        if (teacherService.updateById(teacher)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (teacherService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 批量删除
     */
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (teacherService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }


}

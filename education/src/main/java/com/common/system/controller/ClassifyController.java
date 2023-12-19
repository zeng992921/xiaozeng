package com.common.system.controller;

import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Classify;
import com.common.system.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 课程分类管理
 */
@Controller
@RequestMapping("/course/classify")
public class ClassifyController extends BaseController {
    @Autowired
    private ClassifyService classifyService;

    @RequestMapping()
    public String view() {
        return "course/classify.html";
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Classify> page(HttpServletRequest request) {
        PageParam<Classify> pageParam = new PageParam<>(request);
        return new PageResult<>(classifyService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return classifyService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 查询全部
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Classify> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(classifyService.list(pageParam.getOrderWrapper()));
    }

    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(classifyService.getById(id));
		// 使用关联查询
        //PageParam<Classify> pageParam = new PageParam<>();
		//pageParam.put("id", id);
        //List<Classify> records = classifyService.listAll(pageParam.getNoPageParam());
        //return JsonResult.ok().setData(pageParam.getOne(records));
    }
    
    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/getById")
    public String getById(Integer id) {
    	Classify classify = classifyService.getById(id);
        return classify.getName();
    }


    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Classify classify) {
        if (classifyService.save(classify)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Classify classify) {
        if (classifyService.updateById(classify)) {
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
        if (classifyService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 批量添加
     */
    @ResponseBody
    @RequestMapping("/saveBatch")
    public JsonResult saveBatch(@RequestBody List<Classify> list) {
        if (classifyService.saveBatch(list)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }


    /**
     * 批量删除
     */
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (classifyService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

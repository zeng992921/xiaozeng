package com.common.system.controller;

import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Release;
import com.common.system.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理
 */
@Controller
@RequestMapping("/course/release")
public class ReleaseController extends BaseController {
    @Autowired
    private ReleaseService releaseService;

    @RequestMapping()
    public String view() {
        return "course/release.html";
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Release> page(HttpServletRequest request) {
        PageParam<Release> pageParam = new PageParam<>(request);
        return new PageResult<>(releaseService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return classifyService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 查询全部
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Release> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(releaseService.list(pageParam.getOrderWrapper()));
        //List<Classify> records = classifyService.listAll(pageParam.getNoPageParam());  // 使用关联查询
        //return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(releaseService.getById(id));
    }

    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Release release) {
        if (releaseService.save(release)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Release release) {
        if (releaseService.updateById(release)) {
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
        if (releaseService.removeById(id)) {
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
        if (releaseService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

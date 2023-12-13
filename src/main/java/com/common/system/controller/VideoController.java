package com.common.system.controller;

import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Video;
import com.common.system.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 视频管理
 */
@Controller
@RequestMapping("/course/video")
public class VideoController extends BaseController {
    @Autowired
    private VideoService videoService;

    @RequestMapping()
    public String view() {
        return "course/video.html";
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Video> page(HttpServletRequest request) {
        PageParam<Video> pageParam = new PageParam<>(request);
        return new PageResult<>(videoService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return classifyService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 查询全部
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Video> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(videoService.list(pageParam.getOrderWrapper()));
    }

    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(videoService.getById(id));
    }

    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Video video) {
    	video.setCreateTime(new Date());
        if (videoService.save(video)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Video video) {
        if (videoService.updateById(video)) {
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
        if (videoService.removeById(id)) {
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
        if (videoService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

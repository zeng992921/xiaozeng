package com.common.system.controller;

import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Notice;
import com.common.system.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 通知管理
 */
@Controller
@RequestMapping("/course/notice")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping()
    public String view() {
        return "course/notice.html";
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Notice> page(HttpServletRequest request) {
        PageParam<Notice> pageParam = new PageParam<>(request);
        return new PageResult<>(noticeService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
        //return classifyService.listPage(pageParam);  // 使用关联查询
    }

    /**
     * 查询全部
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Notice> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(noticeService.list(pageParam.getOrderWrapper()));
    }

    /**
     * 根据id查询
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(noticeService.getById(id));
		// 使用关联查询
        //PageParam<Classify> pageParam = new PageParam<>();
		//pageParam.put("id", id);
        //List<Classify> records = classifyService.listAll(pageParam.getNoPageParam());
        //return JsonResult.ok().setData(pageParam.getOne(records));
    }
    

    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Notice notice) {
    	notice.setCreateTime(new Date());
    	notice.setTeacherId(getLoginUserId());
        if (noticeService.save(notice)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Notice notice) {
        if (noticeService.updateById(notice)) {
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
        if (noticeService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 批量添加
     */
    @ResponseBody
    @RequestMapping("/saveBatch")
    public JsonResult saveBatch(@RequestBody List<Notice> list) {
        if (noticeService.saveBatch(list)) {
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
        if (noticeService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

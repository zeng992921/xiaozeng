package com.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.core.web.*;
import com.common.system.entity.Dictionary;
import com.common.system.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 字典管理
 */
@Controller
@RequestMapping("/sys/dict")
public class DictionaryController extends BaseController {
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping()
    public String view() {
        return "system/dictionary.html";
    }

    /**
     * 分页查询字典
     */
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<Dictionary> page(HttpServletRequest request) {
        PageParam<Dictionary> pageParam = new PageParam<>(request);
        return new PageResult<>(dictionaryService.page(pageParam, pageParam.getWrapper()).getRecords(), pageParam.getTotal());
    }

    /**
     * 查询全部字典
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<Dictionary> pageParam = new PageParam<>(request);
        return JsonResult.ok().setData(dictionaryService.list(pageParam.getOrderWrapper()));
    }

    /**
     * 根据id查询字典
     */

    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        return JsonResult.ok().setData(dictionaryService.getById(id));
    }

    /**
     * 添加字典
     */

    @ResponseBody
    @RequestMapping("/save")
    public JsonResult save(Dictionary dictionary) {
        if (dictionaryService.count(new QueryWrapper<Dictionary>().eq("dict_code", dictionary.getDictCode())) > 0) {
            return JsonResult.error("字典标识已存在");
        }
        if (dictionaryService.count(new QueryWrapper<Dictionary>().eq("dict_name", dictionary.getDictName())) > 0) {
            return JsonResult.error("字典名称已存在");
        }
        if (dictionaryService.save(dictionary)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改字典
     */

    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(Dictionary dictionary) {
        if (dictionaryService.count(new QueryWrapper<Dictionary>().eq("dict_code", dictionary.getDictCode())
                .ne("dict_id", dictionary.getDictId())) > 0) {
            return JsonResult.error("字典代码已存在");
        }
        if (dictionaryService.count(new QueryWrapper<Dictionary>().eq("dict_name", dictionary.getDictName())
                .ne("dict_id", dictionary.getDictId())) > 0) {
            return JsonResult.error("字典名称已存在");
        }
        if (dictionaryService.updateById(dictionary)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除字典
     */

    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (dictionaryService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }


    /**
     * 批量删除字典
     */
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (dictionaryService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

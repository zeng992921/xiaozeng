package com.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.core.web.BaseController;
import com.common.core.web.JsonResult;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.core.web.*;
import com.common.system.entity.DictionaryData;
import com.common.system.service.DictionaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 字典项管理
 */
@Controller
@RequestMapping("/sys/dictdata")
public class DictionaryDataController extends BaseController {
    @Autowired
    private DictionaryDataService dictionaryDataService;

    /**
     * 分页查询字典项
     */
    @ResponseBody
    @RequestMapping("/page")
    public PageResult<DictionaryData> page(HttpServletRequest request) {
        PageParam<DictionaryData> pageParam = new PageParam<>(request);
        return dictionaryDataService.listPage(pageParam);
    }

    /**
     * 查询全部字典项
     */
    @ResponseBody
    @RequestMapping("/list")
    public JsonResult list(HttpServletRequest request) {
        PageParam<DictionaryData> pageParam = new PageParam<>(request);
        List<DictionaryData> records = dictionaryDataService.listAll(pageParam.getNoPageParam());
        return JsonResult.ok().setData(pageParam.sortRecords(records));
    }

    /**
     * 根据id查询字典项
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult get(Integer id) {
        PageParam<DictionaryData> pageParam = new PageParam<>();
        pageParam.put("dictDataId", id);
        List<DictionaryData> records = dictionaryDataService.listAll(pageParam.getNoPageParam());
        return JsonResult.ok().setData(pageParam.getOne(records));
    }

    /**
     * 添加字典项
     */
    @ResponseBody
    @RequestMapping("/save")
    public JsonResult add(DictionaryData dictionaryData) {
        if (dictionaryDataService.count(new QueryWrapper<DictionaryData>()
                .eq("dict_id", dictionaryData.getDictId())
                .eq("dict_data_name", dictionaryData.getDictDataName())) > 0) {
            return JsonResult.error("字典项名称已存在");
        }
        if (dictionaryDataService.count(new QueryWrapper<DictionaryData>()
                .eq("dict_id", dictionaryData.getDictId())
                .eq("dict_data_code", dictionaryData.getDictDataCode())) > 0) {
            return JsonResult.error("字典项标识已存在");
        }
        if (dictionaryDataService.save(dictionaryData)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 修改字典项
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonResult update(DictionaryData dictionaryData) {
        if (dictionaryDataService.count(new QueryWrapper<DictionaryData>()
                .eq("dict_id", dictionaryData.getDictId())
                .eq("dict_data_name", dictionaryData.getDictDataName())
                .ne("dict_data_id", dictionaryData.getDictDataId())) > 0) {
            return JsonResult.error("字典项名称已存在");
        }
        if (dictionaryDataService.count(new QueryWrapper<DictionaryData>()
                .eq("dict_id", dictionaryData.getDictId())
                .eq("dict_data_code", dictionaryData.getDictDataCode())
                .ne("dict_data_id", dictionaryData.getDictDataId())) > 0) {
            return JsonResult.error("字典项标识已存在");
        }
        if (dictionaryDataService.updateById(dictionaryData)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    /**
     * 删除字典项
     */
    @ResponseBody
    @RequestMapping("/remove")
    public JsonResult remove(Integer id) {
        if (dictionaryDataService.removeById(id)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

    /**
     * 批量添加字典项
     */
    @ResponseBody
    @RequestMapping("/saveBatch")
    public JsonResult saveBatch(@RequestBody List<DictionaryData> dictDataList) {
        if (dictionaryDataService.saveBatch(dictDataList)) {
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    /**
     * 批量删除字典项
     */
    @ResponseBody
    @RequestMapping("/removeBatch")
    public JsonResult removeBatch(@RequestBody List<Integer> ids) {
        if (dictionaryDataService.removeByIds(ids)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }

}

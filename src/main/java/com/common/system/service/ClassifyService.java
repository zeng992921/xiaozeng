package com.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Classify;

import java.util.List;
import java.util.Map;

/**
 * 课程分类服务类
 */
public interface ClassifyService extends IService<Classify> {

    /**
     * 分页查询
     */
    PageResult<Classify> listPage(PageParam<Classify> page);

    /**
     * 查询所有
     */
    List<Classify> listAll(Map<String, Object> page);

}

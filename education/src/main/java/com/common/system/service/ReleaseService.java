package com.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.core.web.PageResult;
import com.common.core.web.PageParam;
import com.common.system.entity.Release;

import java.util.List;
import java.util.Map;

/**
 * 发布服务类
 */
public interface ReleaseService extends IService<Release> {

    /**
     * 分页查询
     */
    PageResult<Release> listPage(PageParam<Release> page);

    /**
     * 查询所有
     */
    List<Release> listAll(Map<String, Object> page);

}

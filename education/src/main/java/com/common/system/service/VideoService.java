package com.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Video;

import java.util.List;
import java.util.Map;

/**
 * 视频服务类
 */
public interface VideoService extends IService<Video> {

    /**
     * 分页查询
     */
    PageResult<Video> listPage(PageParam<Video> page);

    /**
     * 查询所有
     */
    List<Video> listAll(Map<String, Object> page);

}

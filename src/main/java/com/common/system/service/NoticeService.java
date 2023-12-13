package com.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Notice;

import java.util.List;
import java.util.Map;

/**
 * 通知服务类
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 分页查询
     */
    PageResult<Notice> listPage(PageParam<Notice> page);

    /**
     * 查询所有
     */
    List<Notice> listAll(Map<String, Object> page);

}

package com.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * 教师服务类
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 分页查询
     */
    PageResult<Teacher> listPage(PageParam<Teacher> page);

    /**
     * 查询所有
     */
    List<Teacher> listAll(Map<String, Object> page);

}

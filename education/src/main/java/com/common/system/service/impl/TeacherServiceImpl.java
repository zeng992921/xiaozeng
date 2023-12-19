package com.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Teacher;
import com.common.system.mapper.TeacherMapper;
import com.common.system.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 教师服务实现类
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public PageResult<Teacher> listPage(PageParam<Teacher> page) {
        List<Teacher> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public List<Teacher> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}

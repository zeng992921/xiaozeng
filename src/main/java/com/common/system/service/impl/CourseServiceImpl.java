package com.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.system.service.CourseService;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Course;
import com.common.system.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 课程服务实现类
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public PageResult<Course> listPage(PageParam<Course> page) {
        List<Course> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public List<Course> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

	@Override
	public List<Course> listGgkc() {
		return baseMapper.listGgkc();
	}

	@Override
	public List<Course> listZykc() {
		return baseMapper.listZykc();
	}

	@Override
	public List<Course> listMfkc() {
		return baseMapper.listMfkc();
	}

	@Override
	public List<Course> listXie() {
		return baseMapper.listXie();
	}

}

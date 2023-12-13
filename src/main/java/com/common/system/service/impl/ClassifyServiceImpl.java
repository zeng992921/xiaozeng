package com.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.system.service.ClassifyService;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Classify;
import com.common.system.mapper.ClassifyMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 课程分类服务实现类
 */
@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements ClassifyService {

    @Override
    public PageResult<Classify> listPage(PageParam<Classify> page) {
        List<Classify> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public List<Classify> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}

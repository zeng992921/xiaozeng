package com.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Release;
import com.common.system.mapper.ReleaseMapper;
import com.common.system.service.ReleaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 发布服务实现类
 */
@Service
public class ReleaseServiceImpl extends ServiceImpl<ReleaseMapper, Release> implements ReleaseService {

    @Override
    public PageResult<Release> listPage(PageParam<Release> page) {
        List<Release> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public List<Release> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}

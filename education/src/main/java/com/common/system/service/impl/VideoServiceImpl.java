package com.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Video;
import com.common.system.mapper.VideoMapper;
import com.common.system.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public PageResult<Video> listPage(PageParam<Video> page) {
        List<Video> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public List<Video> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}

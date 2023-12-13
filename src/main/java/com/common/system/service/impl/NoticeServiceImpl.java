package com.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.Notice;
import com.common.system.mapper.NoticeMapper;
import com.common.system.service.NoticeService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 通知服务实现类
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public PageResult<Notice> listPage(PageParam<Notice> page) {
        List<Notice> records = baseMapper.listPage(page);
        return new PageResult<>(records, page.getTotal());
    }

    @Override
    public List<Notice> listAll(Map<String, Object> page) {
        return baseMapper.listAll(page);
    }

}

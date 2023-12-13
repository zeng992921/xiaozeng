package com.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.core.web.PageParam;
import com.common.system.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通知Mapper接口
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 分页查询
     */
    List<Notice> listPage(@Param("page") PageParam<Notice> page);

    /**
     * 查询全部
     */
    List<Notice> listAll(@Param("page") Map<String, Object> page);

}

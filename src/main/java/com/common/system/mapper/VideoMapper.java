package com.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.core.web.PageParam;
import com.common.system.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 视频Mapper接口
 */
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 分页查询
     */
    List<Video> listPage(@Param("page") PageParam<Video> page);

    /**
     * 查询全部
     */
    List<Video> listAll(@Param("page") Map<String, Object> page);

}

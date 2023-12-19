package com.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.core.web.PageParam;
import com.common.system.entity.Release;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 发布Mapper接口
 */
public interface ReleaseMapper extends BaseMapper<Release> {

    /**
     * 分页查询
     */
    List<Release> listPage(@Param("page") PageParam<Release> page);

    /**
     * 查询全部
     */
    List<Release> listAll(@Param("page") Map<String, Object> page);

}

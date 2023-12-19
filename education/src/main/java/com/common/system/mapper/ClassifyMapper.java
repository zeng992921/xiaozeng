package com.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.core.web.PageParam;
import com.common.system.entity.Classify;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程分类Mapper接口
 */
public interface ClassifyMapper extends BaseMapper<Classify> {

    /**
     * 分页查询
     */
    List<Classify> listPage(@Param("page") PageParam<Classify> page);

    /**
     * 查询全部
     */
    List<Classify> listAll(@Param("page") Map<String, Object> page);

}

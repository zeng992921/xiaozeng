package com.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.core.web.PageParam;
import com.common.system.entity.DictionaryData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 字典项Mapper接口
 */
public interface DictionaryDataMapper extends BaseMapper<DictionaryData> {

    /**
     * 分页查询
     */
    List<DictionaryData> listPage(@Param("page") PageParam<DictionaryData> page);

    /**
     * 查询全部
     */
    List<DictionaryData> listAll(@Param("page") Map<String, Object> page);

}

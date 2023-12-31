package com.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.core.web.PageParam;
import com.common.system.entity.LoginRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 登录日志Mapper接口
 */
public interface LoginRecordMapper extends BaseMapper<LoginRecord> {

    /**
     * 分页查询
     */
    List<LoginRecord> listPage(@Param("page") PageParam<LoginRecord> page);

    /**
     * 查询全部
     */
    List<LoginRecord> listAll(@Param("page") Map<String, Object> page);

}

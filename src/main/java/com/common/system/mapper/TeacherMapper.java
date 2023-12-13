package com.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.core.web.PageParam;
import com.common.system.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教师Mapper接口
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * 分页查询
     */
    List<Teacher> listPage(@Param("page") PageParam<Teacher> page);

    /**
     * 查询全部
     */
    List<Teacher> listAll(@Param("page") Map<String, Object> page);

}

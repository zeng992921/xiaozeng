package com.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.core.web.PageParam;
import com.common.system.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 课程Mapper接口
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 分页查询
     */
    List<Course> listPage(@Param("page") PageParam<Course> page);

    /**
     * 查询全部
     */
    List<Course> listAll(@Param("page") Map<String, Object> page);
    
    List<Course> listGgkc();
    List<Course> listZykc();
    List<Course> listMfkc();
    List<Course> listXie();

}

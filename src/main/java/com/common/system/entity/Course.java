package com.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
@TableName("course")
public class Course implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 类别
     */
    private String type;

    /**
     * 内容
     */
    private String content;

    /**
     * 教师id
     */
    private Integer teacherId;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 分类id
     */
    private Integer classifyId;

    private Integer ggkc;
    
    private Integer zykc;
    
    private Integer mfkc;

}

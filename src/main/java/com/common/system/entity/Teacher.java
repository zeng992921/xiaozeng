package com.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
@TableName("teacher")
public class Teacher implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 真实姓名
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 备注
     */
    private String remark;

    /**
     * yonghu
     */
    private Integer userId;


}

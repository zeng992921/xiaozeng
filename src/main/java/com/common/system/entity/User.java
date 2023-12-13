package com.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户
 */
@TableName("sys_user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱是否验证,0否,1是
     */
    private Integer emailVerified;
    /**
     * 个人简介
     */
    private String introduction;
    /**
     * 机构id
     */
    private Integer organizationId;
    /**
     * 状态，0正常，1冻结
     */
    private Integer state;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 是否删除,0否,1是
     */
    @TableLogic
    private Integer deleted;
    /**
     * 权限列表
     */
    @TableField(exist = false)
    private List<String> authorities;
    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<Role> roles;
    /**
     * 角色id
     */
    @TableField(exist = false)
    private List<Integer> roleIds;
    /**
     * 机构名称
     */
    @TableField(exist = false)
    private String organizationName;
    /**
     * 性别名称
     */
    @TableField(exist = false)
    private String sexName;


}

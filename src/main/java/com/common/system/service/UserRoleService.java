package com.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.system.entity.UserRole;

/**
 * 用户角色服务类
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 查询用户的角色id
     */
    Integer[] getRoleIds(String userId);

}

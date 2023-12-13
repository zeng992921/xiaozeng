package com.common.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.system.mapper.UserRoleMapper;
import com.common.system.entity.UserRole;
import com.common.system.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色服务实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public Integer[] getRoleIds(String userId) {
        List<UserRole> userRoles = baseMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", userId));
        Integer[] roleIds = new Integer[userRoles.size()];
        for (int i = 0; i < userRoles.size(); i++) {
            roleIds[i] = userRoles.get(i).getRoleId();
        }
        return roleIds;
    }
    
}

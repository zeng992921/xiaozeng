package com.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.core.web.PageParam;
import com.common.core.web.PageResult;
import com.common.system.entity.LoginRecord;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 登录日志服务类
 */
public interface LoginRecordService extends IService<LoginRecord> {

    /**
     * 关联分页查询
     */
    PageResult<LoginRecord> listPage(PageParam<LoginRecord> page);

    /**
     * 关联查询所有
     */
    List<LoginRecord> listAll(Map<String, Object> page);

    /**
     * 添加登录日志
     *
     * @param username 用户账号
     * @param type     操作类型
     * @param comments 备注
     * @param request  HttpServletRequest
     */
    void saveAsync(String username, Integer type, String comments, HttpServletRequest request);

    /**
     * 添加登录成功的登录日志
     *
     * @param username 用户账号
     * @param request  HttpServletRequest
     */
    void saveAsync(String username, HttpServletRequest request);

}

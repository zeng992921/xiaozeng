package com.common.core.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 判断的登陆的用户是不是管理员还是普通用户通过token判断
 */
public class CustomizedToken extends UsernamePasswordToken{
	
	//登录类型，判断是普通用户登录，教师登录还是管理员登录
    private String loginType;
 
    public CustomizedToken(final String username,final String password,final Boolean remember, String loginType) {
        super(username,password,remember);
        this.loginType = loginType;
    }
 
    public String getLoginType() {
        return loginType;
    }
 
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}

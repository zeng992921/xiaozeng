package com.common.core.utils;

/**
 * 登录的类型判断
 */
public enum LoginType {
	
	USER("User"),  ADMIN("Admin");
	 
    private String type;
 
    private LoginType(String type) {
        this.type = type;
    }
 
    @Override
    public String toString() {
        return this.type.toString();
    }

}

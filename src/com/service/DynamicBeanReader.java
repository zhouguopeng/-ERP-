package com.service;

import com.model.DynamicBean;

public interface DynamicBeanReader {
	/** 
     * 动态加载bean 
     * @param dynamicBean 
     */  
    public void loadBean(DynamicBean dynamicBean);  
}

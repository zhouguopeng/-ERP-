package com.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.common.DataSourceContextHolder;
import com.common.DynamicDataSource;
import com.model.DataSourceDynamicBean;
import com.service.DynamicBeanReader;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})  
public class DynamicBeanReaderTests extends AbstractJUnit4SpringContextTests{  
    @Autowired  
    private DynamicBeanReader dynamicBeanReader;  
    
    @Test  
    public void testLoadBean(){  
        DataSourceDynamicBean dataSourceDynamicBean = new DataSourceDynamicBean("dataSource1");  
        dataSourceDynamicBean.setDriverClassName("org.gjt.mm.mysql.Driver");  
        dataSourceDynamicBean.setUrl("jdbc:mysql://127.0.0.1:3306/youi2?useUnicode=true&amp;characterEncoding=utf-8");  
        dataSourceDynamicBean.setUsername("youi2");  
        dataSourceDynamicBean.setPassword("youi2");  
          
        dynamicBeanReader.loadBean(dataSourceDynamicBean);//  
        DynamicDataSource dynamicDataSource = (DynamicDataSource) applicationContext.getBean("dynamicdatasource"); 
        DataSource s = (DataSource)applicationContext.getBean("dataSource1");  
        dynamicDataSource.addDataSource("qqq", s);  
        DataSourceContextHolder.setDataSourceType("qqq");  
    }  
}  
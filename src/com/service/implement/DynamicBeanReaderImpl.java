package com.service.implement;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.model.DynamicBean;
import com.service.DynamicBeanReader;
@Service
public class DynamicBeanReaderImpl implements DynamicBeanReader,ApplicationContextAware{  
    private static final Log logger = LogFactory.getLog(DynamicBeanReaderImpl.class);//日记  
      
    private ConfigurableApplicationContext applicationContext = null;    
      
    private XmlBeanDefinitionReader beanDefinitionReader;  
    /*初始化方法*/  
    public void init(){  
        beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)  
                applicationContext.getBeanFactory());    
        beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(applicationContext));    
    }  
      
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {    
        this.applicationContext = (ConfigurableApplicationContext)applicationContext;    
    }  
      
    public void loadBean(DynamicBean dynamicBean){   
        long startTime = System.currentTimeMillis();  
        String beanName = dynamicBean.getBeanName();  
        if(applicationContext.containsBean(beanName)){  
            logger.warn("bean【"+beanName+"】已经加载！");  
            return;  
        }  
        beanDefinitionReader.loadBeanDefinitions(new DynamicResource(dynamicBean));  
        logger.info("初始化bean【"+dynamicBean.getBeanName()+"】耗时"+(System.currentTimeMillis()-startTime)+"毫秒。");  
    }   
}  
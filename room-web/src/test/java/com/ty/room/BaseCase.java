package com.ty.room;

import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/6/2
 * Time: 16:13
 * Description:
 */
public abstract class BaseCase {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    protected static ApplicationContext appContext;

    @BeforeClass
    public static void setUp(){
        long start = System.currentTimeMillis();
        System.out.println("正在加载配置文件...");
        appContext = new ClassPathXmlApplicationContext(new String[]{"spring-context.xml","spring-mvc.xml"});
        System.out.println("配置文件加载完毕,耗时：" + (System.currentTimeMillis() - start));
    }
    @Before
    public void autoSetBean() {
        if (appContext==null) {
            log.error("spring config init error");
            return;
        }
        appContext.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

}

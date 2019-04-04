package com.vsu.webmarket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextProvider implements ApplicationContextAware {

    static {
        ApplicationContextProvider applicationContextProvider
                = new ApplicationContextProvider();
        applicationContextProvider.setApplicationContext(
                new ClassPathXmlApplicationContext("database-config.xml"));
    }

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac)
            throws BeansException {
        context = ac;
    }
}

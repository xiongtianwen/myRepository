package com.xtw.spring.v3.context;

import com.xtw.spring.v3.core.BeanFactory;

public interface ApplicationContextAware extends BeanFactory {
    void setApplicationContext(ApplicationContext applicationContext);
}

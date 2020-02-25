package com.xtw.rmi.zk.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/24 20:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {
    /**
     * 对外发布的服务接口地址
     * @return
     */
    Class<?> value();

    String version() default "";
}

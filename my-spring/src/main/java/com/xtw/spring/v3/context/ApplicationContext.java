package com.xtw.spring.v3.context;

import com.xtw.spring.annotation.Autowired;
import com.xtw.spring.annotation.Controller;
import com.xtw.spring.annotation.Service;
import com.xtw.spring.v3.aop.AopProxy;
import com.xtw.spring.v3.aop.AopProxyConfig;
import com.xtw.spring.v3.aop.AopProxyFactory;
import com.xtw.spring.v3.aop.AopProxyStrategy;
import com.xtw.spring.v3.beans.BeanDefinition;
import com.xtw.spring.v3.beans.BeanPostProcessor;
import com.xtw.spring.v3.beans.BeanWrapper;
import com.xtw.spring.v3.context.support.BeanDefinitionReader;
import com.xtw.spring.v3.core.BeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationContext extends DefaultListableBeanFactory  implements BeanFactory {

    private String[] configLocations;
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private Map<String, BeanDefinition> beanCacheMap = new ConcurrentHashMap<>();
    private BeanDefinitionReader reader;
    //存储所有被代理的对象
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public ApplicationContext(String... locations){
        this.configLocations = locations;
        this.refresh();
    }

    private void refresh(){
        //1、定位
        reader = new BeanDefinitionReader(configLocations);
        //2、加载
        reader.loadBeanDefinition();
        List<String> scanBeanClass = reader.getRegistryBeanClassNames();
        //3、注册
        doRegister(scanBeanClass);
        //4、依赖注入
        doAutowired();
        //手动调用
//        UserController userController = (UserController)beanWrapperMap.get("userController").getWrapperInstance();
//        userController.query(null,null,null);
    }

    private void doAutowired() {
        //注入之前先实例化，然后存入beanDefinitionMap中
        doCreateBeanWrapperInstance();
        for(Map.Entry<String, BeanDefinition> entry: this.beanDefinitionMap.entrySet()){
            String beanName = entry.getKey();
            //lazy-init = false的时候自动注入依赖
            if(!entry.getValue().isLazyInit()){
                Object obj = getBean(beanName);
//                System.out.println(obj.getClass());
            }
        }
    }

    private void doRegister(List<String> scanBeanClass) {
        for(String className : scanBeanClass){
            try {
                //beanName有三种情况
                //1、默认首字母小写
                //2、自定义名字
                //3、接口注入
                Class<?> clazz = Class.forName(className);
                if(clazz.isInterface()){continue;}
                BeanDefinition beanDefinition = this.registerBeanDefinitions(className);
                if(beanDefinition != null){
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                }
                Class<?>[] interfaces = clazz.getInterfaces();
                for(Class<?> i : interfaces){
                    //如果有多个实现类，只能覆盖
                    this.beanDefinitionMap.put(lowerCaseFirstOne(i.getSimpleName()),beanDefinition);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //每注册一个className，就返回一个BeanDefinition
    private BeanDefinition registerBeanDefinitions(String className){
           BeanDefinition beanDefinition = new BeanDefinition();
           beanDefinition.setBeanClassName(className);
           beanDefinition.setFactoryBeanName(lowerCaseFirstOne(className.substring(className.lastIndexOf(".")+1)));
           return beanDefinition;
    }

    private void doCreateBeanWrapperInstance() {
        try {
            for(Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()){
                String beanName = entry.getKey();
                BeanDefinition beanDefinition = entry.getValue();

                //生成通知
                BeanPostProcessor beanPostProcessor = new BeanPostProcessor();
                //创建实例,判断有没有缓存,有则从缓存中取,没有就创建一个实例
                Object instance = instantiateBean(beanDefinition);
                if(instance == null){ continue; }
                //在实例初始化之前调用一次通知
                beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

                BeanWrapper bw = new BeanWrapper(instance);
                //设置aopProxyConfig
                bw.setAopConfig(instantiateAopConfig(beanDefinition));
                //设置通知
                bw.setBeanPostProcessor(beanPostProcessor);
                this.beanWrapperMap.put(beanName,bw);

                //在实例初始化之后调用一次通知
                beanPostProcessor.postProcessAfterInitialization(instance,beanName);
                //如果这个类是实现类，那么将他的接口作为BeanName，传入实例
                Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                if(clazz.isInterface()){ continue; }
                Class<?>[] interfaces = clazz.getInterfaces();
                for(Class<?> i : interfaces){
                    this.beanWrapperMap.put(lowerCaseFirstOne(i.getSimpleName()),bw);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //通过读取BeanDefinition中的信息,通过反射创建一个实例，并返回
    //Spring的做法是，不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式：保留原来的oop关系(is-a)
    @Override
    public Object getBean(String beanName) {
        Object instance = this.beanWrapperMap.get(beanName).getOriginalInstance();
        try{
            //实际上这个方法才是真正注入的方法
            this.populateBean(instance);
        }catch(Exception e){
            e.printStackTrace();
        }
        return this.beanWrapperMap.get(beanName).getWrapperInstance();
    }

    private Object instantiateBean(BeanDefinition beanDefinition){
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        String beanName = lowerCaseFirstOne(className.substring(className.lastIndexOf(".")+1));
        try{
            if(this.beanCacheMap.containsKey(beanName)){
                instance = this.beanCacheMap.get(beanName);
            }else{
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
            }
        }catch(Exception e){e.printStackTrace();}
        return instance;
    }

    private AopProxyConfig instantiateAopConfig(BeanDefinition beanDefinition) throws Exception{
        AopProxyConfig aopProxyConfig = new AopProxyConfig();
        String aopExpressionKey = "pointCutExpression";
        String aopAspectBeforeKey = "aspectBefore";
        String aopAspectAfterKey = "aspectAfter";
        String expression = reader.getConfig().getProperty(aopExpressionKey);
        String[] before = reader.getConfig().getProperty(aopAspectBeforeKey).split("\\s");
        String[] after = reader.getConfig().getProperty(aopAspectAfterKey).split("\\s");

        String className = beanDefinition.getBeanClassName();
        Class<?> clazz = Class.forName(className);
        //需要被切面的类
        Pattern pattern = Pattern.compile(expression);
        //before和after方法的类
        Class aspectClass = Class.forName(before[0]);
        for(Method m : clazz.getMethods()){
//            Matcher matcher = pattern.matcher(m.toString());
//            if(matcher.matches()){
                aopProxyConfig.put(m,aspectClass.newInstance(),aspectClass.getMethod(before[1]),aspectClass.getMethod(after[1]));
//            }
        }
        return aopProxyConfig;
    }

    private void populateBean(Object instance){
        Class<?> clazz = instance.getClass();
        String autowiredBeanName;
        if(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class)){
            try {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (!field.isAnnotationPresent(Autowired.class)) {
                        continue;
                    }
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    if ("".equals(autowired.value())) {
                        autowiredBeanName = lowerCaseFirstOne(field.getType().getSimpleName());
                    } else {
                        autowiredBeanName = autowired.value();
                    }
                    field.setAccessible(true);
                    field.set(instance,this.beanWrapperMap.get(autowiredBeanName).getOriginalInstance());
                }
            }catch (Exception e){e.printStackTrace();}
        }
    }


    //获取所有beanName,返回String[]
    public String[] getBeanDefinitionNames(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    //获取配置文件信息
    public Properties getConfig(){
        return reader.getConfig();
    }

    //首字母转为小写
    public static String lowerCaseFirstOne(String oldStr){
        char[]chars = oldStr.toCharArray();

        chars[0] += 32;

        return String.valueOf(chars);

    }

}

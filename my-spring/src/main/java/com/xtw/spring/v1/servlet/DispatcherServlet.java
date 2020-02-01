package com.xtw.spring.v1.servlet;

import com.xtw.spring.demo.UserController;
import com.xtw.spring.annotation.Autowired;
import com.xtw.spring.annotation.Controller;
import com.xtw.spring.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DispatcherServlet extends HttpServlet {

    private Properties properties = new Properties();

    private Map<String,Object> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<String> classNames = new ArrayList<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        //开始初始化进程

        //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //加载
        doLoadBeanDefinition(properties.getProperty("scanPackage"));
        //注册
        doRegister();
        //自动注入依赖
        doAutowired();

        //手动调用
        UserController userController = (UserController)beanDefinitionMap.get("userController");
        userController.simpleQuery();

        //如果是MVC会多设计一个HandlerMapping
        //将@RequestMapping中配置的Url和一个Method关联上，以便于从浏览器获取用户输入的Url以后能够找到具体执行 的Method通过反射去调用
//        initHandlerMapping();
    }

    private void doAutowired() {
        if(beanDefinitionMap.isEmpty()){return;}
        for(Map.Entry<String,Object> entry :beanDefinitionMap.entrySet()){
            //获取注册了的bean的所有字段
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for(Field field : fields){
                if(!field.isAnnotationPresent(Autowired.class)){
                    continue;
                }
                Autowired autowired = field.getAnnotation(Autowired.class);
                String beanName = autowired.value();
                if("".equals(beanName)){
                    //和前面doRegister()方法中@Service中一样处理
                   beanName = lowerCaseFirstOne(field.getType().getSimpleName());
                }
                field.setAccessible(true);
                try {
                    //字段上有@Autowired注解的，将beanMap容器中实例化的对象赋值给该字段
                    field.set(entry.getValue(),beanDefinitionMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRegister() {
        if(classNames.isEmpty()){
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                //在spring-framework中用的多个子方法处理的
                //spring-framework中并不在这里直接newInstance
                if(clazz.isAnnotationPresent(Controller.class)){
                    String beanName = lowerCaseFirstOne(clazz.getSimpleName());
                    beanDefinitionMap.put(beanName,clazz.newInstance());
                }else if (clazz.isAnnotationPresent(Service.class)){
                    Service service = clazz.getAnnotation(Service.class);
                    String beanName = null;
                    if("".equals(service.value())){
                        beanName = lowerCaseFirstOne(clazz.getSimpleName());
                    }else{
                        beanName = service.value();
                    }
                    Object instance = clazz.newInstance();
                    //不管是获取实现类的Bean还是接口的Bean，得到的都是这个实现类的实例，并且只会实例化一次
                    beanDefinitionMap.put(beanName,instance);
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for(Class<?> inter : interfaces){
                        beanDefinitionMap.put(lowerCaseFirstOne(inter.getSimpleName()),instance);
                    }
                }else{
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doLoadBeanDefinition(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        //遍历扫描目录
        for(File file : classDir.listFiles()){
            if(file.isDirectory()){//如果是文件夹则递归调用
                doLoadBeanDefinition(scanPackage + "." + file.getName());
            }else{
                String realClassName = scanPackage + "." + file.getName().replace(".class","");
//                try {
//                    Class<?> clazz = Class.forName(realClassName);
//                    //如果类是接口就跳过
//                    if(clazz.isInterface()){
//                        continue;
//                    }
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
                classNames.add(realClassName);
            }
        }
    }

    private void doLoadConfig(String location) {
        //在spring-framework中是通过一个Reader查找和定位的
        //这里直接通过properties定位资源
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:",""));
        try {
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String lowerCaseFirstOne(String oldStr){

        char[]chars = oldStr.toCharArray();

        chars[0] += 32;

        return String.valueOf(chars);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("======================调用doGet()===================");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("======================调用doPost()==================");
    }
}

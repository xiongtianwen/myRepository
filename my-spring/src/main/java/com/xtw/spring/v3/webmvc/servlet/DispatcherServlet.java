package com.xtw.spring.v3.webmvc.servlet;

import com.xtw.spring.annotation.Controller;
import com.xtw.spring.annotation.RequestMapping;
import com.xtw.spring.annotation.RequestParam;
import com.xtw.spring.v2.webmvc.ModelAndView;
import com.xtw.spring.v2.webmvc.ViewResolver;
import com.xtw.spring.v3.aop.AopProxy;
import com.xtw.spring.v3.aop.AopProxyFactory;
import com.xtw.spring.v3.aop.AopProxyStrategy;
import com.xtw.spring.v3.aop.AopProxyUtil;
import com.xtw.spring.v3.context.ApplicationContext;
import com.xtw.spring.v3.webmvc.HandlerAdapter;
import com.xtw.spring.v3.webmvc.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatcherServlet extends HttpServlet {
    private final String SERVLET_PARAM = "contextConfigLocation";
    private String templateRoot;
    private List<HandlerMapping> handlerMappings = new ArrayList<>();
    private Map<HandlerMapping, HandlerAdapter> handlerAdapterMap = new HashMap<>();
    private List<ViewResolver> viewResolvers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("======================调用doGet()===================");
        if("/favicon.ico".equals(req.getRequestURI())){
            return ;
        }
        try {
            doDisPatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("<font face='verdana' size='5' color='red'>500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]","").replaceAll("\\s","\r\n") + "</font>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDisPatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]","").replaceAll("\\s","\r\n"));
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            //这里相当于初始化IOC容器
            ApplicationContext context = new ApplicationContext(config.getInitParameter(SERVLET_PARAM));
            initStrategies(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initStrategies(ApplicationContext context) {
        initMultipartResolver(context);//文件上传解析,如果请求的类型是multipart,将通过multipartResolver解析
        initLocaleResolver(context);//本地化解析
        initThemeResolver(context);//主题解析
        initHandlerMappings(context);//通过HandlerMapping将请求映射到处理器(用来保存Controller中配置的RequestMapping和Method的一个对应关系)
        initHandlerAdapters(context);//通过HandlerAdapter进行多类型的动态参数匹配(用来动态匹配Method参数,包括类转换、动态赋值)
        initHandlerExceptionResolvers(context);//如果执行过程中遇到异常，将交给HandlerExceptionResolver解析
        initRequestToViewNameTranslator(context);//直接解析请求到的视图名
        initViewResolvers(context);//通过viewResolver解析逻辑视图得到具体的视图实现
        initFlashMapManager(context);//flash隐射管理器
    }

    private void initFlashMapManager(ApplicationContext context) {}
    private void initRequestToViewNameTranslator(ApplicationContext context) {}
    private void initHandlerExceptionResolvers(ApplicationContext context) {}
    private void initThemeResolver(ApplicationContext context) {}
    private void initLocaleResolver(ApplicationContext context) {}
    private void initMultipartResolver(ApplicationContext context) {}

    private void initHandlerMappings(ApplicationContext context) {
        //常规的思路这里应该是一个Map,一个requestMapping对应一个Method
        //map.put(url,method);
        try {
            //先从容器中拿到beanName
            String[] beanNames = context.getBeanDefinitionNames();
            for(String beanName : beanNames){
                //再通过beanName获取一个实例
                //访问用代理对象,判断用原始对象
                //代理对象
                Object proxy = context.getBean(beanName);
                //原始对象
                Object controller = AopProxyUtil.getProxySourceInstance(proxy);
                Class<?> clazz = controller.getClass();
                if(!clazz.isAnnotationPresent(Controller.class)){continue;}
                String controllerUrl = "";
                if(clazz.isAnnotationPresent(RequestMapping.class)){
                    controllerUrl = clazz.getAnnotation(RequestMapping.class).value();
                }
                //获取所有public方法
                Method[] methods = clazz.getMethods();
                for(Method method : methods){
                    if(!method.isAnnotationPresent(RequestMapping.class)){ continue; }
                    String methodUrl = method.getAnnotation(RequestMapping.class).value();
                    //将*替换为.*,将多个/替换为一个/
                    String regex = controllerUrl + methodUrl.replaceAll("\\*",".*").replaceAll("/+","/");
                    Pattern pattern = Pattern.compile(regex);
                    this.handlerMappings.add(new HandlerMapping(pattern,proxy,method));
                    System.out.println("Mapping:"+handlerMappings.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void initHandlerAdapters(ApplicationContext context) {
        //在初始化阶段,我们能做的就是将这些参数的名称或者类型按一定顺序保存下来
        //因为后面调用的时候,传的形参是一个数组
        //可以通过记录这些参数的位置index,挨个从参数中取值,这样就和参数顺序无关了

        //每一个参数有一个列表,这里保存的是形参列表(参数的value,参数的index)
        Map<String,Integer> paramMap = new HashMap<>();


        for(HandlerMapping handlerMapping : handlerMappings){
            //这里只是处理命名参数(有注解的参数)
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for(int i = 0; i < pa.length; i++){
                for(Annotation an : pa[i]){
                    if(an instanceof RequestParam){
                        String param = ((RequestParam) an).value();
                        if(!"".equals(param)){
                            paramMap.put(param,i);
                        }
                    }
                }
            }
            //接下来处理非命名参数
            //只处理request和response
            Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
            for(int i = 0; i < parameterTypes.length; i++){
                Class<?> type = parameterTypes[i];
                if(type == HttpServletRequest.class || type == HttpServletResponse.class){
                    paramMap.put(type.getName(),i);
                }
            }
            handlerAdapterMap.put(handlerMapping,new HandlerAdapter(paramMap));
        }

    }
    private void initViewResolvers(ApplicationContext context) {
        //解决页面名字和模板文件关联的问题
        this.templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        //递归调用实例化ViewResolver并添加到List中去
        doAddViewResolver(templateRootPath);
    }

    private void doAddViewResolver(String path){
        File templateDir = new File(path);
        if(!templateDir.exists()){ return;}
        for(File templateFile : templateDir.listFiles()){
            if(templateFile.isDirectory()){
                doAddViewResolver(path + templateFile.getName());
            }else{
                viewResolvers.add(new ViewResolver((path + "/" + templateFile.getName()).replaceAll("/+","/"),templateFile));
            }
        }
    }

    private void doDisPatch(HttpServletRequest req, HttpServletResponse resp) throws Exception{
            HandlerMapping handler = getHandler(req);
            if(handler == null){
                resp.getWriter().write("404 Not Found;Detail:com.xtw.spring.v2.webmvc.servlet.DispatcherServlet.getHandler");
            }
            HandlerAdapter ha = getHandlerAdapter(handler);
            ModelAndView mv = ha.handle(req,resp,handler);
            processDispatchResult(resp,mv);
    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) throws Exception{
        //调用ViewResolver的resolveViewName()方法
        if(null == mv || this.viewResolvers.isEmpty()){ return ;}
        String templatePath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        for(ViewResolver resolver : this.viewResolvers){
            if(!mv.getViewName().equals(resolver.getName().replace(templatePath,""))){ continue;}
            String out = resolver.resolverView(mv);
            if(out != null){
                resp.getWriter().write(out);
                break;
            }
        }
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        if(this.handlerAdapterMap.isEmpty()){return null;}
        return this.handlerAdapterMap.get(handler);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        if(this.handlerMappings.isEmpty()){ return null;}
        String uri = req.getRequestURI();
        String contextPatch = req.getContextPath();
        uri = uri.replace(contextPatch,"").replaceAll("/+","/");
        for(HandlerMapping handlerMapping : handlerMappings){
            Matcher matcher = handlerMapping.getPattern().matcher(uri);
            if(!matcher.matches()){ continue; }
            return handlerMapping;
        }
        return null;
    }
}

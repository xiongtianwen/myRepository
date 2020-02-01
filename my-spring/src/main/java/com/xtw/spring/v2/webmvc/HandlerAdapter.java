package com.xtw.spring.v2.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

public class HandlerAdapter  {
    private Map<String,Integer> paramMap;

    public HandlerAdapter(Map<String,Integer> paramMap) {
        this.paramMap = paramMap;
    }

    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) throws Exception{
        //根据用户请求的参数信息,跟method中的参数进行动态匹配
        //resp传进来是为了将其赋值给方法参数

        //1、准备好这个方法的形参列表
        //方法重载：形参的决定因素：参数的个数、参数的类型、参数顺序、方法名字
        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();
        //2、拿到自定义命名参数所在的位置
        //用户通过url传过来的参数列表
        Map<String, String[]> parameterMap = req.getParameterMap();
        //3、构造实参列表
        Object[] paramValues = new Object[parameterTypes.length];
        for(Map.Entry<String,String[]> entry: parameterMap.entrySet()){
            if(!this.paramMap.containsKey(entry.getKey())){ continue; }
            String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]","").replaceAll("\\s","");
            int index = paramMap.get(entry.getKey());
            //复制并且进行类型转换
            paramValues[index] = transformationStringValue(value,parameterTypes[index]);
        }

        if(paramMap.containsKey(HttpServletRequest.class.getName())){
            int reqIndex = paramMap.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }
        if(paramMap.containsKey(HttpServletResponse.class.getName())){
            int respIndex = paramMap.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        //4、从handler中取出controller和method,然后利用反射机制进行调用
        Object result = handler.getMethod().invoke(handler.getController(),paramValues);
        if(result == null){ return null;}
        if(ModelAndView.class == result.getClass()){
            return (ModelAndView)result;
        }else {
            return null;
        }
    }


    private Object transformationStringValue(String value,Class<?> clazz){
        if(clazz == String.class){
            return value;
        }else if(clazz == Integer.class){
            return Integer.valueOf(value);
        }else if(clazz == int.class){
            return Integer.valueOf(value).intValue();
        }else if(clazz == Long.class) {
            return Long.valueOf(value);
        }else if(clazz == long.class){
            return Long.valueOf(value).longValue();
        }else{
            return null;
        }
    }
}

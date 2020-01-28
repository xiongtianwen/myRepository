package com.xtw.spring.v2.context.support;

import com.xtw.spring.v2.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 用于配置文件的查找、读取、解析
 */
public class BeanDefinitionReader {

    //在配置文件中，自动扫描的包名(key)
    private final String SCAN_PACKAGE = "scanPackage";

    private Properties properties = new Properties();

    private List<String> scanBeanClass = new ArrayList<>();

    public BeanDefinitionReader(String... locations){
        //todo 这里暂时只取第一个路径
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));
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
    public List<String> getRegistryBeanClassNames(){
        return this.scanBeanClass;
    }


    public void loadBeanDefinition(){
        //递归扫描所有的相关联的Class，并且保存到一个List中
        doLoadBeanDefinition(properties.getProperty(SCAN_PACKAGE));
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
                scanBeanClass.add(realClassName);
            }
        }
    }

    private Properties getConfig(){
        return this.properties;
    }

}
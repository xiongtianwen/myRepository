package com.example.demo.proxy.custom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String classPath) throws ClassNotFoundException {
        if(classPath != null && !"".equals(classPath)){
            String className = classPath.substring(classPath.lastIndexOf("/")+1,classPath.lastIndexOf("."));
            className = CustomClassLoader.class.getPackage().getName() + "." + className;
            File classFile = new File(classPath);
            if(classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out= null;
                try{
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] b = new byte[1024];
                    int len;
                    //每次读取1024个字节写入到内存
                    while((len = in.read(b)) != -1){
                        out.write(b,0,len);
                    }
                    //将字节码转化为class文件
                    return defineClass(className,out.toByteArray(),0,out.size());
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        if(in != null){
                            in.close();
                        }
                        if(out != null){
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}

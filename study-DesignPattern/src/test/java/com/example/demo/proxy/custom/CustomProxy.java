package com.example.demo.proxy.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CustomProxy {

    public static final String ln = "\n";

    public static Object newProxyInstance(CustomClassLoader loader,Class<?>[] interfaces,CustomInvocationHandler h){
        //1、动态生成.java
        String src = generateSrc(interfaces);
        //2、将.java文件输出到磁盘
//        String javaPath = "C:/Users/Administrator/Desktop/$proxy0.java";
        String javaPath ="D:/work/java/IdeaProjects/demo/src/test/java/com/example/demo/proxy/custom/$proxy0.java";
        File f = new File(javaPath);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

        //3、将生成的.java文件编译成.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
        Iterable iterable = fileManager.getJavaFileObjects(f);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, iterable);
        task.call();
        fileManager.close();
        //4、将编译好的.class文件加载到jvm中
        String classPath = javaPath.substring(0,javaPath.lastIndexOf("."))+".class";
        Class proxyClass = loader.findClass(classPath);
        Constructor constructor = proxyClass.getConstructor(CustomInvocationHandler.class);//调用$proxy0.class的构造方法
        //5、返回字节码重组后的代理对象
            //删除自动生成的java和class文件
            File classFile = new File(classPath);
            f.delete();
            classFile.delete();
            return constructor.newInstance(h);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces){
        StringBuffer sb = new StringBuffer();
        sb.append("package com.example.demo.proxy.custom;").append(ln);
        sb.append("import "+interfaces[0].getName()+";").append(ln);
        sb.append("import java.lang.reflect.Method;").append(ln);
        sb.append("public class $proxy0 implements "+interfaces[0].getName()+"{").append(ln);
        sb.append("CustomInvocationHandler h;").append(ln);
        sb.append("public $proxy0(CustomInvocationHandler h){").append(ln);
        sb.append("this.h = h;").append(ln);
        sb.append("}");
        for(Method m:interfaces[0].getMethods()){
            sb.append("public "+ m.getReturnType() + " " + m.getName() + "(){").append(ln);
            sb.append("try{").append(ln);
            sb.append("Method m = "+interfaces[0].getName()+".class.getMethod(\""+m.getName()+"\");").append(ln);
            sb.append("h.invoke(this,m,null);").append(ln);
            sb.append("}catch(Throwable throwables){throwables.printStackTrace();}").append(ln);
            sb.append("}").append(ln);
        }
        sb.append("}").append(ln);
        return sb.toString();
    }
}

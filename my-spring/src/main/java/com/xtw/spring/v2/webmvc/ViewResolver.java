package com.xtw.spring.v2.webmvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewResolver {
    private String name;
    private File template;

    public ViewResolver(String name, File template) {
        this.name = name;
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getTemplate() {
        return template;
    }

    public void setTemplate(File template) {
        this.template = template;
    }

    public String resolverView(ModelAndView mv) throws Exception{
        StringBuffer sb = new StringBuffer();
        RandomAccessFile ra = new RandomAccessFile(this.template,"r");
        String line = null;
        while (null != (line = ra.readLine())){
            Matcher m = matcher(line);
            while(m.find()){
//                for(int i = 0;i <= m.groupCount();i++){
                    //分组,将${}中间的字符串取出来
                    String paramName = m.group().replaceAll("\\$\\{|\\}","");
                    Object paramValue = mv.getModel().get(paramName);
                    if(paramValue != null){
                        line = line.replaceAll("\\$\\{"+paramName+"\\}",paramValue.toString());
                    }
//                }
            }
            sb.append(line);
        }
        return sb.toString();
    }

    private Matcher matcher(String str){
        String regex = "\\$\\{(.+?)\\}";
//        String regex = "$\\{[^\\}]+\\}";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str);
    }

}


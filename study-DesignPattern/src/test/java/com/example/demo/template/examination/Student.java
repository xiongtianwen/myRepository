package com.example.demo.template.examination;

public class Student extends ExaminationPaperTemplate {
    private String name;

    public String getName(){
        return this.name;
    }

    public Student(String name){
        this.name = name;
        System.out.println("姓名："+name);
    }

}

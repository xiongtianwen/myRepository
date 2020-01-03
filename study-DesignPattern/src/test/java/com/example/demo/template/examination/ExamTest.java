package com.example.demo.template.examination;


public class ExamTest {
    public static void main(String[] args) {
        Student studentA = new Student("张三");
        studentA.question1("C");
        studentA.question2("B");
        studentA.question3("C");

        Student studentB = new Student("李四");
        studentB.question1("C");
        studentB.question2("A");
        studentB.question3("B");

        Student studentC = new Student("王五");
        studentC.question1("C");
        studentC.question2("D");
        studentC.question3("B");

        System.out.println(studentA.getName() + "的成绩为："+studentA.score);
        System.out.println(studentB.getName() + "的成绩为："+studentB.score);
        System.out.println(studentC.getName() + "的成绩为："+studentC.score);

    }
}

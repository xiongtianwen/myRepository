package com.example.demo.template.examination;

public abstract class ExaminationPaperTemplate {

    private String correctAnswer1 = "C";
    private String correctAnswer2 = "D";
    private String correctAnswer3 = "B";

    public int score = 0;
    private int per = 10;

    private void checkAnswer(String correctAnswer,String answer){
        if(correctAnswer.equals(answer)){
            score += per;
            System.out.println("回答正确！+"+per+"分");
        }
    }

    public void question1(String answer){
        System.out.println("1、如何使成员变量 m  被函数 fun() 直接访问:（"+answer+"）");
        System.out.println("A 、将 private int m  改为 protected int m");
        System.out.println("B 、将 private int m  改为  public int m");
        System.out.println("C 、将 private int m  改为  static int m");
        System.out.println("D 、将 private int m  改为  int m");
        checkAnswer(correctAnswer1,answer);
    }

    public void question2(String answer){
        System.out.println("2 、下面哪个函数是 public void example(){...} 的重载函数？（"+answer+"）");
        System.out.println("A 、 private void example( int m){...}");
        System.out.println("B 、 public int example(){...}");
        System.out.println("C 、 public void example2(){...}");
        System.out.println("D 、 public int example ( int m, float f){...}");
        checkAnswer(correctAnswer2,answer);
    }

    public void question3(String answer){
        System.out.println("3.关于Java编译，下面哪一个正确（"+answer+"）");
        System.out.println("A.Java程序经编译后产生machine code");
        System.out.println("B.Java程序经编译后会生产byte code");
        System.out.println("C.Java程序经编译后会产生DLL");
        System.out.println("D、以上答案都不对");
        checkAnswer(correctAnswer3,answer);
    }
}

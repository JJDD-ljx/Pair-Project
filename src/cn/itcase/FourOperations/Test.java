package cn.itcase.FourOperations;

import java.util.Random;
import java.util.Scanner;



public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //输入题目中数值的范围
        System.out.println("请输入题目中数值的范围");
        int range = scanner.nextInt();
        if(range<1) {       //数值范围不能小于1
            System.out.println("数值范围不能小于1，请重新输入");
            range = scanner.nextInt();
        }
        //输入生成题目的数量
        System.out.println("请输入你想生成的题目的数量");
        int questionNum = scanner.nextInt();
        if(questionNum<1) { //题目数目不能小于1
            System.out.println("题目数目不能小于1，请重新输入");
            questionNum = scanner.nextInt();
        }

        for(int i = 0;i<questionNum;i++){
            Random r = new Random();
            //至多4个数值，建立4个分数
            int molecule1 =  r.nextInt(range);         //第1个分子
            int molecule2 =  r.nextInt(range);         //第2个分子
            int molecule3 =  r.nextInt(range);         //第3个分子
            int molecule4 =  r.nextInt(range);         //第4个分子
            int denominator1 = r.nextInt(range)+1;     //第1个分母
            int denominator2 = r.nextInt(range)+1;     //第2个分母
            int denominator3 = r.nextInt(range)+1;     //第3个分母
            int denominator4 = r.nextInt(range)+1;     //第4个分母

            //运算符的数目，最多有3个
            int operatorNum = r.nextInt(3) + 1;
            char[] operator = {'+','-','×','÷'};    //用operator[j]来随机产生+-×÷
            int j1 = r.nextInt(4);
            int j2 = r.nextInt(4);
            int j3 = r.nextInt(4);

            //最多有4个数值，新建4个分数（整数也可看成分数）
            Fraction num1 = new Fraction(molecule1,denominator1);
            Fraction num2 = new Fraction(molecule2,denominator2);
            Fraction num3 = new Fraction(molecule3,denominator3);
            Fraction num4 = new Fraction(molecule4,denominator4);


            //当运算符数目是1个的时候
            if(operatorNum == 1){
                //使用str字符串输出题目
                String question = num1 + " " + operator[j1]+ " " + num2 + " = ";
                //除号后面不能是0
                if(operator[j1] == '÷' && num2.molecule == 0) {
                    i--;
                    continue;
                }
                //使用计算器得题目答案
                Fraction answer=Calculator.calculator.calculate(question);
                //出现负数就重新产生题目
                if(answer.molecule < 0 || answer.denominator < 0) {
                    i--;
                    continue;
                }
                System.out.println("题目" + (i+1) + ": " + question);
                //题目与答案存入目录文件里
                QuestionWrite qw = new QuestionWrite();
                qw.Write(question);
                AnswerWrite aw = new AnswerWrite();
                aw.Write(answer);
            }



            if(operatorNum == 2) {
                //使用str字符串输出题目
                String question = num1 + " " + operator[j1] + " "+ num2 + " " + operator[j2] + " " + num3 + " = ";
                //除号后面不能是0
                if((operator[j1] == '÷' && num2.molecule == 0) || (operator[j2] == '÷' && num3.molecule == 0)) {
                    i--;
                    continue;
                }
                //使用计算器得题目答案
                Fraction answer=Calculator.calculator.calculate(question);
                //出现负数就重新产生题目
                if(answer.molecule < 0 || answer.denominator < 0) {
                    i--;
                    continue;
                }
                System.out.println("题目" + (i+1) + ": " + question);
                QuestionWrite qw = new QuestionWrite();
                qw.Write(question);
                AnswerWrite aw = new AnswerWrite();
                aw.Write(answer);
            }



            if(operatorNum == 3) {
                //使用str字符串输出题目
                String question = num1 + " " + operator[j1] + " " + num2 + " " + operator[j2] + " " + num3 + " " + operator[j3] + " " + num4 + " = ";
                //除号后面不能是0
                if((operator[j1] == '÷' && num2.molecule == 0) || (operator[j2] == '÷' && num3.molecule == 0) || (operator[j3] == '÷' && num4.molecule == 0)) {
                    i--;
                    continue;
                }
                //使用计算器得题目答案
                Fraction answer=Calculator.calculator.calculate(question);
                //出现负数就重新产生题目
                if(answer.molecule < 0 || answer.denominator < 0) {
                    i--;
                    continue;
                }
                System.out.println("题目" + (i+1) + ": " + question);
                QuestionWrite qw = new QuestionWrite();
                qw.Write(question);
                AnswerWrite aw = new AnswerWrite();
                aw.Write(answer);
            }



        }
        QuestionWrite.save();   //保存题目和答案到
        AnswerWrite.save();


    }
}

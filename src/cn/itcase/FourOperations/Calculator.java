package cn.itcase.FourOperations;

import java.util.Stack;


/**
 计算器，识别界面中题目字符串然后计算结果
 **/
public class Calculator {
    final public static Calculator calculator = new Calculator();
    //数字栈,用于存储表达式中的数值
    private Stack<Fraction> fractionStack = null;
    //符号栈,用于存储运算符和括号和带分数号
    private Stack<Character> operationStack = null;

    //解析并计算四则运算表达式，返回计算结果
    public Fraction calculate(String numStr) {
        if(numStr!=null)
            numStr=numStr.replaceAll(" ", "");//去除空格方便处理
        else {
            return new Fraction(0);
        }

        // 如果算术表达式尾部没有‘=’号，则在尾部添加‘=’，表示结束符
        if (numStr.length() >= 1 && !('='==numStr.charAt(numStr.length() - 1))) {
            numStr += "=";
        }

        // 检查表达式是否合法
        if (!isStandard(numStr)) {
            System.out.println(new Throwable().getStackTrace()[0]+":"+numStr);
            return new Fraction(0);
        }
        // 初始化栈
        fractionStack = new Stack<Fraction>();
        operationStack = new Stack<Character>();
        // 用于缓存数字，因为数字可能是多位的
        StringBuffer nowFractionNum = new StringBuffer();
        // 从表达式的第一个字符开始处理
        for (int i = 0; i < numStr.length(); i++) {
            char nowChar = numStr.charAt(i); // 获取一个字符
            if (isNumber(nowChar)) { // 若当前字符是数字
                nowFractionNum.append(nowChar); // 加入到数字缓存中
            } else { // 非数字的情况
                String checkFractionNum = nowFractionNum.toString(); // 将数字缓存转为字符串
                if (!checkFractionNum.isEmpty()) {
                    int num = Integer.parseInt(checkFractionNum); // 将数字字符串转为长整型数
                    fractionStack.push(new Fraction(num)); // 将数字压栈
                    nowFractionNum = new StringBuffer(); // 重置数字缓存
                }
                //若当前运算符优先级小于栈尾的优先级，则应当先处理前面运算符
                while (!comparePri(nowChar) && !operationStack.empty()) {
                    Fraction b = fractionStack.pop(); // 出栈，取出数字，后进先出
                    Fraction a = fractionStack.pop();
                    // 取出栈尾运算符进行相应运算，并把结果压栈用于下一次运算
                    switch (operationStack.pop()) {
                        //处理带分数
                        case '’':
                            fractionStack.push(Fraction.with(a , b));
                            break;
                        //处理普通运算符
                        case '+':
                            fractionStack.push(Fraction.add(a , b));
                            break;
                        case '-':
                            fractionStack.push(Fraction.sub(a , b));
                            break;
                        case '×':
                        case '*':
                            fractionStack.push(Fraction.mul(a ,  b));
                            break;
                        case '/':
                        case '÷':
                            fractionStack.push(Fraction.div(a ,  b));
                            break;
                        default:
                            break;
                    }
                }
                if (nowChar != '=') {
                    operationStack.push(nowChar); // 符号入栈
                    if (nowChar == ')') { // 去括号
                        operationStack.pop();
                        operationStack.pop();
                    }
                }
            }
        }
        return fractionStack.pop(); // 返回计算结果
    }

    //检查是不是合法的表达式
    private boolean isStandard(String numStr) {
        if (numStr == null || numStr.isEmpty()) // 表达式不能为空
            return false;
        Stack<Character> stack = new Stack<Character>(); // 用来保存括号，检查左右括号是否匹配
        boolean haveEq = false; // 用来标记'='符号是否存在多个
        for (int i = 0; i < numStr.length(); i++) {
            char nowChar = numStr.charAt(i);
            // 判断字符是否合法
            if (!(
                    isNumber(nowChar)
                            || '('==nowChar
                            || ')'==nowChar
                            || '+'==nowChar
                            || '-'==nowChar
                            || '*'==nowChar
                            || '/'==nowChar
                            || '='==nowChar
                            || '÷'==nowChar
                            || '×'==nowChar
                            || '’'==nowChar
            )) {
                return false;
            }
            // 将左括号压栈，用来给后面的右括号进行匹配
            if ('('==nowChar) {
                stack.push(nowChar);
            }
            if (')'==nowChar) { // 匹配括号
                if (stack.isEmpty() || !('('==stack.pop())) // 括号是否匹配
                    return false;
            }
            // 检查是否有多个'='号
            if ('='==nowChar) {
                if (haveEq)
                    return false;
                haveEq = true;
            }
        }
        // 可能会有缺少右括号的情况
        if (!stack.isEmpty())
            return false;
        // 检查'='号是否不在末尾
        return '=' == numStr.charAt(numStr.length() - 1);
    }

    //判断字符是否是0-9的数字
    private boolean isNumber(char num) {
        return num >= '0' && num <= '9';
    }
    //比较优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
    private boolean comparePri(char symbol) {
        if (operationStack.empty()) { // 空栈返回ture
            return true;
        }

        /* 符号优先级说明（从高到低）:
         第1级: (
         第2级: * /
         第3级: + -
         第4级: )
        */
        char top = operationStack.peek(); // 查看堆栈顶部的对象，注意不是出栈
        if (top == '(') {
            return true;
        }
        // 比较优先级
        switch (symbol) {
            case '(': // 优先级最高
                return true;
            case '/':
                return true;
            case '’':
                // 优先级比+和-高
                return top == '+' || top == '-' || top == '×' || top == '÷' || top == '*';
            case '×':
            case '*':
            case '÷':
                // 优先级比+和-高
                return top == '+' || top == '-';
            case '+':
            case '-':
                return false;
            case ')': // 优先级最低
                return false;
            case '=': // 结束符
                return false;
            default:
                break;
        }
        return true;
    }
}

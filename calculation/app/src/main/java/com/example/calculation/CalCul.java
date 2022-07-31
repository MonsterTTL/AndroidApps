package com.example.calculation;

import java.util.Stack;

public class CalCul {
    public static float evalRPN(String[] tokens) {
        Stack<Float> stack = new Stack<>();
        int n = tokens.length;
        String temp;
        float cal1;
        float cal2;
        for(int i = 0;i < n;i++)
        {
            temp = tokens[i];
            if(temp.equals("+")||temp.equals("-")||temp.equals("*") || temp.equals("/"))
            {
                cal1 = stack.pop();
                cal2 = stack.pop();
                switch (temp)
                {
                    case "+":
                        stack.push(cal1+cal2);
                        break;
                    case "-":
                        stack.push(cal2-cal1);
                        break;
                    case "*":
                        stack.push(cal1*cal2);
                        break;
                    case "/":
                        stack.push(cal2/cal1);
                        break;
                    default:break;
                }
            }else{
                stack.push(Float.parseFloat(temp));
            }
        }
        return stack.pop();
    }

    public static String[] midToend(String[] mid)
    {
        Stack<String> ret = new Stack<>();
        Stack<String> tok = new Stack<>();
        int n = mid.length;
        for(int i = 0;i < n;i++)
        {
            String temp = mid[i];
            if("+".equals(temp) || "-".equals(temp)||"*".equals(temp)||"/".equals(temp)
                    ||"(".equals(temp)||")".equals(temp))
            {
                if(tok.isEmpty())
                {
                    tok.push(temp);
                    continue;
                }
                switch (temp)
                {
                    case "+":
                        if(tok.peek().equals("*") || tok.peek().equals("/") || tok.peek().equals("+") ||tok.peek().equals("-"))
                        {
                            while(!tok.isEmpty())
                            {
                                ret.push(tok.pop());
                            }
                        }
                        tok.push("+");
                        break;
                    case "-":
                        if(tok.peek().equals("*") || tok.peek().equals("/") || tok.peek().equals("+") ||tok.peek().equals("-"))
                        {
                            while(!tok.isEmpty())
                            {
                                ret.push(tok.pop());
                            }
                        }
                        tok.push("-");
                        break;
                    case "*":
                        if(tok.peek().equals("*") || tok.peek().equals("/") || tok.peek().equals("+") ||tok.peek().equals("-"))
                        {
                            while(!tok.isEmpty())
                            {
                                ret.push(tok.pop());
                            }
                        }
                        tok.push("*");
                        break;
                    case "/":
                        if(tok.peek().equals("*") || tok.peek().equals("/") || tok.peek().equals("+") ||tok.peek().equals("-"))
                        {
                            while(!tok.isEmpty())
                            {
                                ret.push(tok.pop());
                            }
                        }
                        tok.push("/");
                        break;
                    case "(":
                        tok.push("(");
                        break;
                    case ")":
                        while(!tok.peek().equals("("))
                        {
                            ret.push(tok.pop());
                        }
                        tok.pop();
                        break;
                    default:break;
                }
            }else {
                ret.push(temp);
            }
        }
        while(!tok.isEmpty())
        {
            ret.push(tok.pop());
        }
        String[] result = new String[1];
        result = ret.toArray(result);
        return result;
    }


    public static String fmt_prt_double( Float d ) //自定义格式化输出函数
    {
        String s=String.format("%f", d);//将浮点数转为字符串
        int i;
        for( i=s.length()-1;i>=0;i-- ) //从串尾向前检查，遇到非0数据结束循环
        {
            if ( s.charAt(i)=='.' ) //遇到小数点结束，说明是个整数
                break;
            if ( s.charAt(i) != '0' ) //遇到小数中有非0值，结束
            {
                i++;
                break;
            }
        }
        return s.substring(0,i); //返回处理后的子串
    }


}

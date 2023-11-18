package day01;

import java.util.Scanner;

public class _array
{
    public static void main(String[]args)
    {
        /*两个 byte 类型的值相加得到的结果会自动提升为 int 类型。这是因为在 Java 中，
        所有的二进制数字运算都是在 int、long、float、double 这四种基本数据类型中进行的*/
        byte x=10,y=20;
        x+=y;
        System.out.println(x);

        //获取用户键盘数据
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入您的姓名");
        String name=sc.next();

        System.out.println("请输入您的年龄");
        int age=sc.nextInt();

        System.out.println("您的姓名是"+name+",您的年龄是"+age);


        //自动类型转换
        int m=10,n=20;
        float f=m+n;
        System.out.println("m与n的和为:"+f);
        System.out.println("m和n的最大值为"+max(m,n));

    }
    public static int max(int a,int b)
    {
        return a>b?a:b;
    }

}

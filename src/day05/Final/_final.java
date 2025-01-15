package day05.Final;


/*
 * final关键字是最终的意思，可以修饰类、修饰方法、修饰变量
 * 1.final修饰类：该类称为最终类，特点是不能被继承
 * 2.final修饰方法：该方法称之为最终方法，特点是不能被重写。
 * 3.final修饰变量：该变量只能被赋值一次。
 */
public class _final
{
    public final int num=10;

    public final void fun()
    {
        System.out.println("this is a final method");
        System.out.println("this is a final value:"+num);
    }
    public static void main(String[] args)
    {
        _final f=new _final();
        // final变量只能赋值一次
//        f.num=11;
        f.fun();
    }

}

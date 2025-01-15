package day04.Encapsulation;

/*
 * 封装: 把抽象出的属性和方法封装在一起，数据被保护在内部，程序的其他部分只有通过被授权的方法才能对数据进行操作
 * 1. 把属性私有化(private)，不能直接修改属性
 * 2. 提供一个公共(public)的set方法，用于对属性判断并赋值
 * 3. 提供一个公共(public)的get方法，用于获取属性的值
 */
public class _encapsulation
{
    public static void main(String[] args)
    {
        People people=new People("Panda",21,12000,"IT");
        System.out.println(people.PrintInfo());
    }

}

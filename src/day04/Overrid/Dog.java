package day04.Overrid;

public class Dog extends Animal
{
    // 子类方法的返回类型和父类方法的返回类型一样
    public void Cry()
    {
        System.out.println("小狗喊叫...");
    }

    // 子类返回类型是父类返回类型的子类，比如父类返回Object，子类返回String
    public String fun()
    {
        return "汪汪队";
    }

    // 父类的Eat方法是protected，子类是public，没有缩小父类Eat方法的范围
    public void Eat()
    {

    }

    // 父类的Eat方法是protected，子类是private，缩小了父类Eat1方法的范围，编译出错
//    private void Eat1()
//    {
//
//    }
}

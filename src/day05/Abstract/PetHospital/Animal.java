package day05.Abstract.PetHospital;


// 抽象类和普通类比较 记住4个字： 有得有失 得到的是拥有抽象方法的权利 失去的是new的能力
// 只要有抽象方法必定是抽象类
public abstract class Animal
{
    private String name;

    public Animal()
    {
    }

    public Animal(String name)
    {
        this.name = name;
    }

    // 对于动物这个类型来说 ，太抽象 cry方法（叫）不知道怎么实现
    // 所以必须定义成abstract 抽象方法
    public abstract void cry();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}

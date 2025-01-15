package day05.Static;

/*
 * 1.静态变量
 * 定义方式：使用static关键字修饰的变量。
 * 共享性：所有对象共享同一个静态变量，即所有对象共享同一个属性值。
 * 内存分配：静态变量只在内存中存在一份，属于类本身而不是某个特定的对象
 * 2.静态方法
 * 调用方式：可以直接通过类名调用，而不需要创建类的实例。
 * 访问成员：只能访问静态变量和其他静态方法，不能直接访问实例变量或实例方法
 * 内存分配：静态方法属于类本身，所有对象共享同一个静态方法，因此它只在内存中存在一份。
 * 继承特性：静态方法不能被子类重写（虽然可以隐藏），因为静态方法是绑定到类而不是对象上的，因此不能出现this关键字
 * */
public class _static
{
    public static void main(String[] args)
    {
        Student s1 = new Student();
        Student s2 = new Student("张三");
        System.out.println(s1.age);
        System.out.println(s2.age);
        System.out.println(Student.schoolName);
    }
}

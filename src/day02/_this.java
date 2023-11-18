package day02;

class Student
{
    public int age=20;
    public void mehtod()
    {
        int age=5;
        System.out.println(age); //5
        //this的作用：区分局部变量和成员变量,本质是代表方法调用者的地址值
        System.out.println(this.age); //20
    }
}

class StuName
{
    private String name;
    public String GetName()
    {
        return name;
    }
    public  void SetName(String name)
    {
        this.name=name;
    }
}

public class _this {
    public static void main(String[] args)
    {
        Student student1=new Student();
        student1.mehtod();

        StuName stuName=new StuName();
        stuName.SetName("张三");
        System.out.println(stuName.GetName());//张三


    }
}



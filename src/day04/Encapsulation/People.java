package day04.Encapsulation;

public class People
{
    public String name; // 名字公开
    private int age; // 年龄私有化
    private double salary; // 薪资私有化
    private String job; // 工作私有化

    // 含参构造器
    public People(String name,int age,double salary,String job)
    {
        this.name=name;
        this.age=age;
        this.salary=salary;
        this.job=job;
    }

    public String PrintInfo()
    {
        return "information: name="+name+", age="+age+", salary="+salary+", job="+job;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
    }
}

package day04;

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

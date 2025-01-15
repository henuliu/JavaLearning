package day05.Objcet;

import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;

@Data
public class Student
{
    public String Name;
    public String age;

    public Student()
    {
    }

    public Student(String Name, String age)
    {
        this.age = age;
        this.Name = Name;
    }

    @Override
    public String toString()
    {
        String str = "Student{" + this.getName() + "," + this.getAge() + "}";
        return str;
    }

    @Override
    public boolean equals(Object obj)
    {
        // 比较的是对象的引用（即内存地址），如果this和obj引用同一个对象实例，直接返回 true
        if (this == obj)
            return true;
        // 检查对象是否为null或类是否相同
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Student student = (Student) obj;
        return Objects.equals(age, student.age) && Objects.equals(Name, student.Name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(age, Name);
    }

    public static void main(String[] args)
    {
        Student student1 = new Student("Alice", "20");
        Student student2 = new Student("Bob", "21");

        // 直接打印对象
        System.out.println(student1); // 输出: Student{Alice,20}

        // 存储在集合中并打印
        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        System.out.println(students); // 输出: [Student{Alice,20}, Student{Bob,21}]
        
        System.out.println(student1.equals(student2));
    }
}

package day05.Objcet;

import java.util.Objects;

public class Student

{
    public String Name;

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String No;

    public String getNo()
    {
        return No;
    }

    public void setNo(String no)
    {
        No = no;
    }

    public Student()
    {

    }
    public Student(String Name,String No)
    {
        this.No=No;
        this.Name=Name;
    }

    @Override
    public String toString()
    {
        String str="Student{"+this.getName()+","+this.getNo()+"}";
        return str;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return Objects.equals(No, student.No);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(No);
    }
}

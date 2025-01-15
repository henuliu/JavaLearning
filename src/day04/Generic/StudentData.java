package day04.Generic;

import java.util.ArrayList;

//此时确定Data<E>中的E为Student类型，
//接口中add和getByName方法上的T也都会变成Student类型
public class StudentData implements Data<Student>
{
    public void add(Student t)
    {

    }

    public ArrayList<Student> getByName(String name, ArrayList arr)
    {
        ArrayList<Student> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++)
        {
            Student student = (Student) arr.get(i);
            if (student.getName().equals(name))
            {
                arrayList.add(student);
            }
        }
        return arrayList;
    }
}
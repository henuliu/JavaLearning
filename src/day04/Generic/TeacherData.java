package day04.Generic;

import java.util.ArrayList;

/**
 * 这个类是：
 *
 * @author: liu jun hao
 * @date: 2025/1/7 17:01
 * @version: 1.0
 */
//此时确定Data<E>中的E为Teacher类型，
//接口中add和getByName方法上的T也都会变成Teacher类型
public class TeacherData implements Data<Teacher>
{
    public void add(Teacher t)
    {

    }

    public ArrayList<Teacher> getByName(String name, ArrayList arr)
    {
        ArrayList<Teacher> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++)
        {
            Teacher Teacher = (Teacher) arr.get(i);
            if (Teacher.getName().equals(name))
            {
                arrayList.add(Teacher);
            }
        }
        return arrayList;
    }
}

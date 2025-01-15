package day04.Generic;

import java.util.ArrayList;

/*
 * 自定义泛型接口
 * 泛型接口其实指的是在接口中把不确定的数据类型用<类型变量>表示
 * @param <T>
 */
public interface Data<T>
{
    public void add(T t);

    public ArrayList<T> getByName(String name, ArrayList arr);
}
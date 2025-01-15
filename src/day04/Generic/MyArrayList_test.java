package day04.Generic;

import java.util.ArrayList;

public class MyArrayList_test
{

    public static void main(String[] args)
    {
        // ArrayList<E> <E>就是String 也就是只能放String类型的数据
        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("abc");
        arrayList2.add("def");
//        arrayList2.add(100); // 错误 编译期间可以避免放入一些别的类型的数据
        for (String s : arrayList2)
        {
            System.out.println(s);
        }

        //1.确定MyArrayList集合中，元素类型为String类型
        MyArrayList<String> list = new MyArrayList<>();
        //此时添加元素时，只能添加String类型
        list.add("张三");
        list.add("李四");


        //2.确定MyArrayList集合中，元素类型为Integer类型
        MyArrayList<Integer> list1 = new MyArrayList<>();
//        此时添加元素时，只能添加String类型
//        list.add(100);
//        list.add(200);

    }
}

package day05.Generic;

// 泛型类
public class MyArrayList<E>
{
    private Object[] array = new Object[10];
    // 定义一个索引，方便对数组进行操作
    private int index;

    // 添加元素
    public void add(E e)
    {
        array[index] = e;
        index++;
    }

    //获取元素
    public E get(int index)
    {
        return (E) array[index];
    }
}

package day04.Generic;

// 定义一个泛型类，用来表示一个容器
// 容器中存储的数据，它的类型用<E>先代替着，等调用者来确认<E>的具体类型。
public class MyArrayList<E>{
    private Object[] array = new Object[10];
    // 定一个索引，方便对数组进行操作
    private int index;

    // 添加元素
    public void add(E e){
        array[index]=e;
        index++;
    }

    // 获取元素
    public E get(int index){
        return (E)array[index];
    }
}

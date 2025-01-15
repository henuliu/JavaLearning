package day04.Generic;

/*
 * 泛型,指在定义类、接口、方法时，同时声明了一个或者多个类型变量（如：<E>），称为泛型类、泛型接口、泛型方法、它们统称为泛型
 * 1.由于基本数据类型不是引用类型，因此不能直接用作泛型参数。泛型只能使用引用类型（如Integer, Character, Double 等)
 * 2.在实例化泛型类时，通常需要提供构造函数所需的参数，以便初始化对象
 * */
public class _generics<T> {

  T data;

  public _generics(T data) {
    this.data = data;
  }

  public T GetData() {
    return data;
  }

  public void SetData(T data) {
    this.data = data;
  }

  public static void main(String[] args) {
    // 在实例化泛型类时，通常需要提供构造函数所需的参数，以便初始化对象
    // 由于基本数据类型不是引用类型，因此不能直接用作泛型参数。泛型只能使用引用类型
    _generics<Integer> value = new _generics<>(10);

    value.SetData(20);
    System.out.println(value.GetData());
  }
}

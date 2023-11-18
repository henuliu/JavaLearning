package day05.InterfaceDemo;


public class Client {

    public static void main(String[] args) {
        Animal animal1 = new Cat();
        animal1.cry();

        Cat cat = (Cat)animal1;
        cat.catchMouse();

        Animal animal2 = new Dog();
        animal2.cry();

        Dog dog = (Dog)animal2;
        dog.keepHouse();

        catMouseMethod(cat);
        catMouseMethod(dog);
    }

    // 定义一个捉老鼠的方法 定义成Cat 就太窄了，程序扩展性不好 需要的是抓老鼠的能力 不是cat
//    static void catMouseMethod(Cat cat){
//        cat.catMouse();
//    }

    // 定义接口不定义实现 定义抽象不要定义具体 程序扩展性好 多态
    // 同样类型对象 执行同一个方法 表现出来不同的行为特征 是为多态
    static void catMouseMethod(CatchMouseAble catchMouseAble)
    {
        catchMouseAble.catchMouse();
    }
}

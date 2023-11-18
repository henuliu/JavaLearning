package day05.InterfaceDemo;

public class Cat extends Animal implements CatchMouseAble{

    @Override
    public void cry() {
        System.out.println("cat , 喵喵!!!");
    }

    @Override
    // 子类 扩展父类方法 表达自己的个性
    public void catchMouse(){

        System.out.println("猫天生就会抓老鼠");
    }

}

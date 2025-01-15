package day05.Interface;

// 接口代表一种能力 是一种资格考试和认证 所以一般是able结尾
public interface CatchMouseAble
{
    // 在Java中，接口中的方法默认是 public，因此可以省略 public 关键字
    // 接口的方法就是做事的能力 怎么做接口不管的，只是定义标准，怎么实现是厂商的事情
    void catchMouse();

    // 默认是public static final修饰，是不可修改的常量
    String tes = "";
}

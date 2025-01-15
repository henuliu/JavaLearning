package day05.Interface;

/**
 * @author: liu jun hao
 * @date: 2025/1/15 17:06
 * @version: 1.0
 */
public class Test
{
    public static void main(String[] args)
    {
        // Java中，接口不能直接实例化，但可以通过其实现类来创建对象
        CatchMouseAble catchMouseAble = new Dog();
        catchMouseAble.catchMouse();
    }
}

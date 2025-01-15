package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JUnit框架中@test注解的测试
 * 有 @MyTestAnnotation 注解的方法将被执行
 *
 * @author: liu jun hao
 * @date: 2025/1/16 15:32
 * @version: 1.0
 */
public class MyTest
{
    @MyTestAnnotation
    public void test1()
    {
        System.out.println("====test1====");
    }

    @MyTestAnnotation
    public void test2()
    {
        System.out.println("====test2====");
    }

    //    @MyTestAnnotation
    public void test3()
    {
        System.out.println("====test3====");
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException
    {
        MyTest myTest = new MyTest();
        // 1.反射得到Class对象
        Class c = MyTest.class;

        // 2.获取该类中的所有成员方法
        Method[] methods = c.getDeclaredMethods();

        // 3.判断每一个成员方法是否有 @MyTestAnnotation 注解，若存在则触发该方法执行
        for (Method method : methods)
        {
            if (method.isAnnotationPresent(MyTestAnnotation.class))
            {
                method.invoke(myTest);
            }
        }

    }
}

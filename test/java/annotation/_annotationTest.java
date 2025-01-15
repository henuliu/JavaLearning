package annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 解析注解
 *
 * @author: liu jun hao
 * @date: 2025/1/16 14:39
 * @version: 1.0
 */
class _annotationTest
{
    @Test
    void parseClass()
    {
        // 1.反射得到Class对象
        Class c = _annotation.class;

        // 2.解析类上的注解
        // 判断类上是否包含某个注解
        if (c.isAnnotationPresent(Annotation1.class))
        {
            Annotation1 declaredAnnotation = (Annotation1) c.getDeclaredAnnotation(Annotation1.class);
            System.out.println(declaredAnnotation.aaa());
            System.out.println(declaredAnnotation.bbb());
            String string = Arrays.toString(declaredAnnotation.ccc());
            System.out.println(string);
        }
    }

    @Test
    void parseMethod() throws NoSuchMethodException
    {
        // 1.反射得到Class对象
        Class c = _annotation.class;
        Method m = c.getDeclaredMethod("test1");

        // 2.解析方法上的注解
        // 判断方法上是否包含某个注解
        if (m.isAnnotationPresent(Annotation1.class))
        {
            Annotation1 declaredAnnotation = (Annotation1) m.getDeclaredAnnotation(Annotation1.class);
            System.out.println(declaredAnnotation.aaa());
            System.out.println(declaredAnnotation.bbb());
            String string = Arrays.toString(declaredAnnotation.ccc());
            System.out.println(string);
        }
    }
}
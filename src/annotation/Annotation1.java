package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * @author: liu jun hao
 * @date: 2025/1/15 22:11
 * @version: 1.0
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation1
{
    String aaa();

    //default true 表示默认值为true,使用时可以不赋值。
    boolean bbb() default true;

    String[] ccc();
}

// 注解本质上是接口，继承了 Annotation 接口，Annotation1反编译后代码如下所示
//public interface Annotation1 extends Annotation
//{
//    // 注解中的属性本质上是抽象方法
//    public abstract String aaa();
//
//    public abstract boolean bbb();
//
//    public abstract String[] ccc();
//}
package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解: 修饰注解的注解
 *
 * @Target 是用来声明注解只能用在那些位置，比如：类或接口上(type)、方法上(method)、成员变量上(field)等
 * @Retetion 是用来声明注解保留周期，例：源代码时期(source)、字节码时期(class,默认)、运行时期(runtime，开发常用)
 * @author: liu jun hao
 * @date: 2025/1/15 22:11
 * @version: 1.0
 */
@Target({ElementType.TYPE, ElementType.FIELD})  // 当前被修饰的注解只能用在类或接口、成员变量
@Retention(RetentionPolicy.SOURCE)
public @interface Annotation2
{
    String value(); //特殊属性

    int age() default 10;
}
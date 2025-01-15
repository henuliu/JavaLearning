package annotation;

/**
 * 注解
 * 1.Annotation1 注解本质上是接口，每一个注解接口都继承了 Annotation 接口
 * 2.Annotation1 注解中的属性本质上是抽象方法
 * 3.@Annotation1 实际上是实现了 Annotation1 注解接口的匿名内部类
 * 4.@Annotation1(aaa="孙悟空",bbb=false,ccc={"Python","前端","Java"})里面的属性值，可以通过调用aaa()、bbb()、ccc()方法获取到。
 * 5.@AliasFor 是 Spring 框架中的一个注解，主要用于标注两个注解的属性之间存在别名关系
 *
 * @author: liu jun hao
 * @date: 2025/1/15 22:11
 * @version: 1.0
 */
//@Annotation(属性名=属性值，属性名=属性值...)
@Annotation2("孙悟空") // 等价于 @Annotation2(value="孙悟空")
@Annotation1(aaa = "牛魔王", ccc = {"HTML", "Java"})
public class _annotation
{
    @Annotation1(aaa = "铁扇公主", bbb = false, ccc = {"Python", "前端", "Java"})
    public void test1()
    {

    }

    public static void main(String[] args)
    {

    }

}

package day02;

import java.io.Serial;
import java.io.Serializable;

/**
 * JavaBean 是一种符合特定约定的Java类，主要用于封装数据。通常用于表示应用程序中的实体对象（如用户、订单等）
 * JavaBean的设计遵循一定的编码规范，使其易于使用和管理
 * 主要特点：
 * 1.JavaBean 的属性通常是私有的（private），以确保封装性。
 * 2.为每个私有属性提供公共的 getter 和 setter 方法，以便外部代码可以访问和修改这些属性。
 * 3.JavaBean 必须有一个无参构造方法，这使得它可以被序列化和反序列化，并且可以通过反射机制实例化。
 * 4.如果需要将 JavaBean 对象保存到文件或在网络上传输，通常会实现 Serializable 接口。
 * 5.属性名通常采用驼峰命名法（CamelCase），例如 firstName、birthDate 等。
 */
public class _JavaBean implements Serializable
{
    @Serial
    private static final long serialVersionUID = 6136515715310630218L;

    private String name;
    private int age;

    // 无参构造方法
    public _JavaBean()
    {

    }

    // 带参数的构造方法（可选）
    public _JavaBean(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    // Getter 和 Setter 方法
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public static void main(String[] args)
    {


    }
}

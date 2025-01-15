package serializable;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class Student implements Serializable
{

    // @Serial用于标识序列化相关的成员或方法,用于标识序列化相关的成员或方法
    // 有助于提高代码的可读性和维护性，并确保序列化和反序列化的兼容性
    @Serial
    private static final long serialVersionUID = -4181606614948038623L;
    private String name;
    private int age;
    /**
     * Color 类也是需要实现序列化接口的。
     */
    private Color color;//这里如果没有实现序列化接口，那么在 Student 对象序列化时将会报错
}
package serializable;

import lombok.Data;

import java.io.*;

/**
 * Java序列化是指把Java对象转换为字节序列的过程，Java反序列化是指把字节序列恢复为Java对象的过程
 * 1.序列化类的属性没有实现 Serializable接口 那么在序列化就会报错。该接口是一个标记接口，不包含任何方法
 * 2.被 transient关键字修饰的字段不会参与序列化过程
 * 3.静态字段不会被序列化，因为它们不属于某个特定的对象实例
 * 4.如果父类没有实现 Serializable接口，则父类的字段不会被序列化。只有实现了 Serializable接口的父类字段才会被序列化
 * 5.可以通过实现 writeObject和 readObject方法来自定义序列化和反序列化的行为。
 * 6.常见的序列化格式包括 JSON、XML、Protocol Buffers、MessagePack等
 * 7.显式定义 serialVersionUID，在类结构发生变化时序列化和反序列化仍然兼容。避免因默认生成的 serialVersionUID 发生变化而导致的不兼容问题
 * 8.final变量值参与序列化，final transient同时修饰变量，final不会影响 transient，一样不会参与序列化
 * 应用场景（必须实现序列化）：
 * 1.数据存储：将程序中的数据保存到文件或数据库中，以便在以后重新加载和使用。
 * 2.网络通信：在网络上传输数据时，需要将数据序列化为字节流，以便在接收端进行反序列化。
 * 3.分布式系统：在分布式系统中，不同计算节点之间需要通过序列化和反序列化来交换数据。
 * 4.进程间通信：不同进程之间通信时，数据需要在序列化和反序列化之间进行转换。
 */
public class _Serializable
{
    private static final File SAVE_FILE = new File("D:" + File.separator + "demo.Class");

    public static void saveObject(Object object) throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
        oos.writeObject(object); // 序列化
        oos.close();
    }

    public static Object loadObject() throws Exception
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE));
        Object obj = ois.readObject(); // 反序列化
        ois.close();
        return obj;
    }

    public static void main(String[] args) throws Exception
    {
        // 序列化
        saveObject(new MyObject(new Person("panda", 11), "class", 12));
        MyObject c = (MyObject) loadObject();

        // 反序列化
        System.out.println(c);
    }

    // Serializable 用来标识当前类可以被 ObjectOutputStream 序列化，以及被 ObjectInputStream 反序列化
    @Data
    public static class Person implements Serializable
    {

        private String name;
        private int age;

        public Person(String name, int age)
        {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString()
        {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    // Serializable 用来标识当前类可以被 ObjectOutputStream 序列化，以及被 ObjectInputStream 反序列化
    @Data
    public static class MyObject implements Serializable
    {
        @Serial
        private static final long serialVersionUID = -1561314500984035847L;

        private Person person;
        private String name;
        private int age;

        @Override
        public String toString()
        {
            return "Class{" +
                    "person=" + person +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public MyObject(Person person)
        {
            this.person = person;
        }

        public MyObject(Person person, String name, int age)
        {
            this.person = person;
            this.name = name;
            this.age = age;
        }
    }

}


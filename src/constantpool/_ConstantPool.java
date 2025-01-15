package constantpool;

/**
 * 常量池
 * 池化思想: 资源反复利用，用后归还，免去大量建立销毁浪费的时间和资源，建立和销毁占用的资源还是很多的
 * 常量池独立于栈和堆
 *
 * @author: liu jun hao
 * @date: 2025/2/14 14:00
 * @version: 1.0
 */
public class _ConstantPool
{
    public static void main(String[] args)
    {
        // 直接使用双引号定义字符串时，这些字符串会被自动放入字符串常量池中
        // 如果同一个字符串字面量已经存在于常量池中，则不会创建新的字符串对象，而是直接引用已有的对象
        String str1 = "Hello";
        String str2 = "Hello";
        System.out.println(str1 == str2); // ，str1 和 str2 指向的是同一个字符串对象 "Hello",输出：true

        // 使用 new 关键字创建的字符串对象不会自动进入字符串常量池，而是在堆内存中创建一个新的对象
        // 即使字符串的内容相同，也会创建不同的对象
        String str3 = new String("Hello");
        String str4 = new String("Hello");
        System.out.println(str3 == str4); // str3 和 str4 是两个不同的对象,输出：false

    }
}

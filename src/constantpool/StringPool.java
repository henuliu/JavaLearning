package constantpool;

/**
 * 字符串常量池
 *
 * @author: liu jun hao
 * @date: 2025/2/14 21:19
 * @version: 1.0
 */
public class StringPool
{
    public static void main(String[] args)
    {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = "Hel" + "lo";
        String s4 = "Hel" + new String("lo");
        String s5 = new String("Hello");
        String s6 = s5.intern();
        String s7 = "H";
        String s8 = "ello";
        String s9 = s7 + s8;

        System.out.println(s1 == s2);  // true, s1 和 s2 都指向了方法区常量池中的Hello
        System.out.println(s1 == s3);  // true, 做+号的时候，会进行优化，自动生成Hello赋值给s3
        System.out.println(s1 == s4);  // false, 做+的时候会进行动态调用，最后生成的仍然是一个String对象存放在堆中
        System.out.println(s1 == s9);  // false, 动态调用，所以返回的是一个新的String对象
        System.out.println(s4 == s5);  // false
        System.out.println(s1 == s6);  // true, intern方法首先在常量池中查找是否存在一份equal相等的字符串如果有的话就
        // 返回该字符串的引用，没有的话就将它加入到字符串常量池中
    }
}

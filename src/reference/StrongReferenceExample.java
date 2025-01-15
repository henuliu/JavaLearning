package reference;

/**
 * 强引用是Java中最为普遍的引用类型。当一个对象被强引用关联时，垃圾回收器不会回收这个对象，即使系统内存不足也不会回收
 * 只有当该对象的强引用被显式地释放，或者不再被任何引用关联时，该对象才会成为垃圾回收的候选对象。
 */
public class StrongReferenceExample
{
    public static void main(String[] args)
    {
        // 创建一个对象并建立强引用
        Object obj = new Object(); // 强引用

        // 对象仍然存在，可以正常使用
        System.out.println("Object is still accessible.");

        // 解除对对象的强引用
        obj = null;

        // 系统内存充足时，垃圾回收器可能不会立即回收
        // 只有在需要释放内存时，垃圾回收器才会回收不再被引用的对象
    }
}
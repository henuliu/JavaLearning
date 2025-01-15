package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;


/**
 * @author: liu jun hao
 * @date: 2025/1/11 19:48
 * @version: 1.0
 */
class PeopleTest
{
    // 测试通过反射获取全部构造器
    @Test
    public void testGetConstructors()
    {
        // 1.反射第一步：必须先得到这个类的class对象
        Class c = People.class;

        // 2.获取类的构造器(只能获取public修饰的) 不推荐
        // Constructor[] constructors = c.getConstructors();

        // 2.获取类的全部构造器(只要存在就能拿到)
        Constructor[] constructors = c.getDeclaredConstructors();

        // 3.遍历数组中的每个构造器对象
        for (Constructor constructor : constructors)
        {
            System.out.println(constructor.getName() + "--->" + constructor.getParameterCount());
        }
    }

    @Test
    // 测试通过反射获取某个构造器
    public void testGetConstructor() throws NoSuchMethodException
    {
        // 1.反射：获得这个类的class对象
        Class c = People.class;

//        // 2.获取某个构造器：无参构造器 (只能获取public修饰的) 不推荐
//        Constructor constructor = c.getConstructor();

        // 2.获取某个构造器：无参构造器 (只要存在就能拿到)
        Constructor constructor = c.getDeclaredConstructor();
        System.out.println(constructor.getName() + "--->" + constructor.getParameterCount());

        // 3.获取某个构造器：有参构造器 (只要存在就能拿到)
        Constructor constructor1 = c.getDeclaredConstructor(String.class, int.class);
        System.out.println(constructor1.getName() + "--->" + constructor1.getParameterCount());
    }
}
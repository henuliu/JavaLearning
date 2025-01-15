package JUnit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 这个类是：
 *
 * @author: liu jun hao
 * @date: 2025/1/10 16:00
 * @version: 1.0
 */
public class StringUtilTest
{

    @Test
    public void testGetMaxIndex()
    {
        int index1 = StringUtil.getMaxIndex(null);
        System.out.println(index1);

        int index2 = StringUtil.getMaxIndex("admin");
        System.out.println(index2);

        // 断言机制：预测index2的结果
        assertEquals(5, index2);
    }

    @Test
    public void printNumber()
    {
        StringUtil.printNumber("admin");
        StringUtil.printNumber(null);
    }

    @BeforeEach
    public void beforeEachTest()
    {
        System.out.println("--> beforeEachTest BeforeEach 执行了");
    }

    @BeforeAll
    public static void beforeAllTests()
    {
        System.out.println("--> beforeAllTests BeforeAll 执行了");
    }

    @AfterEach
    public void afterEachTest()
    {
        System.out.println("--> afterEachTest AfterEach 执行了");
    }

    @AfterAll
    public static void afterAllTests()
    {
        System.out.println("--> afterAllTests AfterAll 执行了");
    }
}

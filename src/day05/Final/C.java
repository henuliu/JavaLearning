package day05.Final;

public class C
{

    void test()
    {

    }

    // 该方法称之为最终方法，特点是不能被重写
    final void testFinalMethod()
    {

    }

}

class D extends C
{
    @Override
    void test()
    {
        super.test();
    }

    // 'testFinalMethod()' cannot override 'testFinalMethod()' in 'com.gsm.day04.finalDemo.C'; overridden method is final
//    @Override
//    void testFinalMethod()
//    {
//        super.testFinalMethod();
//    }
}

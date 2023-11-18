package day01;

public class _char {
    public static void main(String []args)
    {
        char []string={'h','e','l','l','o'};
        int len=string.length;
        System.out.println(len);

        //Character类提供了一系列方法来操纵字符
        String str="hello ";

        //相同引用
        String str1=str;

        //String 创建的字符串存储在公共池中，而 new 创建的字符串对象在堆上
        String str2=new String("world");

        //字符串连接函数concat(),string1.concat(string2);
        String strconcat=str1.concat(str2);
        System.out.println(strconcat);

        //格式化字符串
        float floatVar=20;
        int intVar=1001;
        String stringVar="liu";
        System.out.printf("浮点型变量的值为 " +
                "%f, 整型变量的值为 " +
                " %d, 字符串变量的值为 " +
                "is %s", floatVar, intVar, stringVar);

        /*在使用 StringBuffer 类时，每次都会对 StringBuffer 对象本身进行操作，而不是生成新的对象，
        所以如果需要对字符串进行修改推荐使用 StringBuffer*/
        StringBuffer strbuffer=new StringBuffer("liujunhao");
        strbuffer.append("age:20");

    }
}

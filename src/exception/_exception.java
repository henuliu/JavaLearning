package exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 自定义异常可能是编译时异常，也可以是运行时异常
 * 1.如果自定义异常类继承Excpetion，则是编译时异常。
 * 特点：方法中抛出的是编译时异常，必须在方法上使用throws声明，强制调用者处理。
 * 2.如果自定义异常类继承RuntimeException，则是运行时异常。
 * 特点：方法中抛出的是运行时异常，不需要在方法上用throws声明。
 */
public class _exception
{
    public static void main(String[] args)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date d = sdf.parse("2024-11-11 10:24:20");
            System.out.println(d);
        } catch (ParseException e)
        {
            System.err.println("解析日期时发生错误: " + e.getMessage());
        }
    }
}

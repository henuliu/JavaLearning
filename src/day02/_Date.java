package day02;

import java.text.SimpleDateFormat;
import java.util.Date;

public class _Date
{
    public static void main(String[] args)
    {
        // 初始化 Date 对象
        Date date = new Date();

        // 使用 toString() 函数显示日期时间
        System.out.println(date);

        SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=ft.format(new Date());
        System.out.println("yyyy-MM-dd HH:mm:ss 格式对应的时间为:"+time);

    }
}

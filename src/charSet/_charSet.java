package charSet;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class _charSet
{
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        String data = "a我b";
        //getBytes()函数将字符串转换成了字节数组
        byte[] bytes = data.getBytes(); // 如果没有指定字符编码，getBytes()方法会使用默认的UTF-8字符编码
        System.out.println(Arrays.toString(bytes)); //UTF-8中一个汉字占3个字节

        // 按照指定字符集进行编码。
        byte[] bytes1 = data.getBytes("GBK");
        System.out.println(Arrays.toString(bytes1));//GBK中1个汉字占用2个字节

        // 2、解码
        String s1 = new String(bytes); // 按照平台默认编码（UTF-8）解码
        System.out.println(s1);

        //如果不按正确字符集解码会出现乱码
        String s2 = new String(bytes1, "GBK");
        System.out.println(s2);
    }
}

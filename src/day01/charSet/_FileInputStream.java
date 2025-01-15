package day01.charSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class _FileInputStream
{
    public static void main(String[] args) throws IOException
    {

        FileInputStream_one();
        System.out.println();
        FileInputStream_more();

    }
    //FileInputStream读取一个字节
    private static void FileInputStream_one() throws IOException
    {
        // 1、创建文件字节输入流管道，与源文件接通。
        InputStream inputStream = new FileInputStream("src/day01/charSet/1.txt");

        // 2、开始读取文件的字节数据。
        // public int read():每次读取一个字节返回，如果没有数据则返回-1
        // 如果文件中有汉字，读一个字节就相当于读了1/3个汉字，此时将这个字节转换为字符是会有乱码的
        int b; // 用于记住读取的字节。
        while ((b = inputStream.read()) != -1)
        {
            System.out.print((char) b);
        }

        //3、流使用完毕之后，必须关闭！释放系统资源！
        inputStream.close();
    }

    //FileInputStream读取多个字节
    private static void FileInputStream_more() throws IOException
    {
        // 1、创建一个字节输入流对象代表字节输入流管道与源文件接通。
        InputStream inputStream = new FileInputStream("src/day01/charSet/1.txt");

        // 2、开始读取文件中的字节数据：每次读取多个字节。
        //  public int read(byte b[]) throws IOException
        //  每次读取多个字节到字节数组中去，返回读取的字节数量，读取完毕会返回-1.

        // 3、使用循环改造。
        byte[] buffer = new byte[3];
        int len; // 记住每次读取了多少个字节。  abc 66
        while ((len = inputStream.read(buffer)) != -1)
        {
            // 注意：读取多少，倒出多少。
            // offset表示了字节数组中的起始位置，从这个位置开始取连续的len个字节来构造字符串
            String rs = new String(buffer, 1 , len-1);
            System.out.println(rs);
        }
        // 性能得到了明显地提升！！
        // 这种方案也不能避免读取汉字输出乱码的问题！！

        inputStream.close(); // 关闭流
    }
}

package JUnit;

public class StringUtil
{
    public static void printNumber(String name)
    {
        if (name == null)
            System.out.println(-1);
        else
            System.out.println("名字长度：" + name.length());
    }

    public static int getMaxIndex(String data)
    {
        if (data == null)
        {
            return -1;
        }
        return data.length();
    }

    public static void main(String[] args)
    {
        
    }
}
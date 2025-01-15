package day01;

import java.util.Scanner;

/**
 * @author panda
 */
public class _arr
{
    /**
     * 了解数组的用法
     * @param args
     */
    public static void main(String[] args)
    {
        // 创建Integer对象数组
        Integer []arr=new Integer[5];

        // 遍历数组输入数据
        for(int i=0;i<arr.length;i++)
        {

            Scanner scanner=new Scanner(System.in);
            arr[i]=scanner.nextInt();

        }

        // 输出数据
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i]);

        }
    }
}

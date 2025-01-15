package day02;

/*
 * 可变参数
 * 1. 如果多个方法名称相同，功能相同，参数个数不同，可以使用可变参数优化
 * 2. 使用可变参数时，可以当做数组来使用
 * 3. 遍历可变参数运算即可
 * 4. 可变参数的实参可以是数组
 * 5. 可变参数可以和普通类型的参数放在一起形成形参列表，但必须保证可变参数在最后
 */
public class _VarParameter
{
    // 计算多个整数的和，可以用可变参数
    public int Sum(int... nums)
    {
        int res=0;
        for(int i=0;i<nums.length;i++)
        {
            // 将可变参数当做数组使用
            res+=nums[i];
        }
        return res;
    }

    // 必须保证可变参数在最后
    public void fun(String str,double... nums)
    {
        System.out.println("str:"+str+" nums:"+nums[0]);
    }

    public static void main(String[] args)
    {
        _VarParameter varParameter=new _VarParameter();
        System.out.println(varParameter.Sum(1,5,100));

        varParameter.fun("hello world",3.14);
    }
}

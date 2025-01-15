package day02;

/**
 * 方法重载
 * @author ljh
 */
public class _Overload
{
    /*
        1. 方法名必须相同，参数列表必须不同(参数类型或个数或顺序，至少有一个不同，参数名无要求)
        2. 返回类型无要求
    */
    public int calculate(int m,int n)
    {
        return m+n;
    }
    // 参数类型不同
    public double calculate(int m,double n)
    {
        return m+n;
    }

    // 参数个数不同
    public int calculate(int m,int n,int l)
    {
        return m+n+l;
    }

    // 参数顺序不同
    public double calculate(double m,int n)
    {
        return m+n;
    }

    public static void main(String[] args)
    {
        _Overload overload=new _Overload();
        int m=1;
        double n=3;
        System.out.println(overload.calculate(n,m));
    }
}

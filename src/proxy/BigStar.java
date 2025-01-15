package proxy;

/**
 * @author: liu jun hao
 * @date: 2025/1/16 16:15
 * @version: 1.0
 */
public class BigStar
{
    private String name;

    public BigStar(String name)
    {
        this.name = name;
    }

    public String sing(String name)
    {
        System.out.println(this.name + "正在唱: " + name);
        return "谢谢！";
    }

    public void dance()
    {
        System.out.println(this.name + "正在跳舞...");
    }
}

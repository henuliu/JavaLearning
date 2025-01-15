package serializable;

import java.io.Serial;
import java.io.Serializable;

public class BlackCat extends Animal implements Serializable
{

    @Serial
    private static final long serialVersionUID = -8649200911234170076L;
    private String name;

    public BlackCat()
    {
        super();
        System.out.println("调用黑猫的无参构造");
    }

    public BlackCat(String color, String name)
    {
        super(color);
        this.name = name;
        System.out.println("调用黑猫有 color 参数的构造");
    }

    @Override
    public String toString()
    {
        return "BlackCat{" +
                "name='" + name + '\'' + super.toString() + '\'' +
                '}';
    }
}
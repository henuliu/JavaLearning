/**
 * @author panda
 * @version 1.0
 */
public class Main
{
    public static void main(String[] args)
    {
        AnnoymousClass annoymousClass = new AnnoymousClass()
        {
            @Override
            public void execute(String name)
            {
                System.out.println("InnerClass: " + name);
            }

            @Override
            public void print(String name)
            {

            }
        };

//        AnnoymousClass annoymousClass = (name) ->
//                System.out.println("lambda: " + name);
    }
}

interface AnnoymousClass
{
    void execute(String name);

    void print(String name);
}

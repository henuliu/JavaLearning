package day05.InnerClass;

public class AnonymousInnerClass
{
    public static void main(String[] args)
    {
        Animal animal=new Animal()
        {
            @Override
            public void cry()
            {
                System.out.println("Animal的匿名内部类");
            }
        };
        animal.cry();
    }
}
abstract class Animal
{
    public abstract void cry();

}
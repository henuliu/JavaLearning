package day05.Enum;

enum Color
{
    white,red,green,yellow
}

public class Client
{
    public static void main(String[] args)
    {
        SexEnum sex = SexEnum.M;
        provideContent(sex);

        Color color=Color.red;
        System.out.println(color);
    }

    public static void provideContent(SexEnum sex)
    {
        switch (sex){
            case M:
                System.out.println("男性,提供对应信息");
                break;
            case F:
                System.out.println("女性,提供对应信息");
                break;
            default:
                System.out.println("没有这个性别");
        }
    }
}

package day01;

public class _break_continue
{
    public static void main(String[] args)
    {
        for(int i = 0 ; i < 5; i++){
            for (int j = 0; j < 5; j++)
            {
                if(j == 4)
                    continue;
                if (i==4)
                    break;
                System.out.println("i="+i+"  j="+j);
            }
        }
    }
}

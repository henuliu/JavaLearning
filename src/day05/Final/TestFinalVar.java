package day05.Final;

public class TestFinalVar {

    public static void main(String[] args)
    {

        int a = 10;

        a = 100;


        // b此时相当于常量
        final int b = 100;

        // Cannot assign a value to final variable 'b'
        // b = 1000;
    }
}

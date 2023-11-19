import java.io.*;
import java.util.HashSet;
import java.util.Hashtable;

public class test
{
    public static void main(String[] args)
    {
        HashSet<Integer>hashSet=new HashSet<>();
        hashSet.add(111);
        hashSet.add(222);
        hashSet.add(333);
        for (Integer h:hashSet)
        {
            System.out.println(h);
        }
    }

}
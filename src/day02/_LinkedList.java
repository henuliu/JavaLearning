package day02;

import java.util.LinkedList;

public class _LinkedList
{
    public static void main(String[] args)
    {
        LinkedList<Integer>linkedList=new LinkedList<>();
        for (int i = 0; i < 5; i++)
        {
            linkedList.add(i);
        }

        for (int i = 0; i < linkedList.size(); i++)
        {
            System.out.println(linkedList.get(i));
        }
    }
}

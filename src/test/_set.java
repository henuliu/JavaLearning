package test;

import day06.v7.Server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class _set
{
    public static void main(String[] args)
    {
        HashMap<Integer,String> hashMap=new HashMap<>();
        hashMap.put(1,"a");
        hashMap.put(2,"b");
        System.out.println(hashMap.keySet());
    }
}

package day02;

import java.util.ArrayList;

public class _ArrayList
{
    public static void main(String[] args)
    {
        //实例化一个集合ArrayList对象,并添加元素,初始索引为0
        ArrayList<String> arrayList=new ArrayList();
        arrayList.add("liu");
        arrayList.add("li");

        //get(index)方法可以根据索引找到值
        System.out.println("添加liu,li后,该集合索引为0的元素是:"+arrayList.get(0)); // liu

        /*
        使用 add 方法在指定的索引位置插入元素时，如果该索引位置已经存在一个元素，则该元素和后面的所有元素都会向右移动一个索引位置。
        新添加的元素将插入到该索引位置上，而不是覆盖现有元素
         */
        arrayList.add(0,"wang");
        System.out.println("在索引为0处加入元素wang后,集合变为:"+arrayList); // liu li wang

        //获得集合的大小
        System.out.println("集合的大小(元素个数)为:"+arrayList.size());

        //remove(index)方法可以根据索引删除元素,还可以返回被删除的元素值
        System.out.println(arrayList.remove(0));;
        System.out.println("集合删除索引为0的元素后变为:"+arrayList);


    }
}

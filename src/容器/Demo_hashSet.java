package 容器;

import java.util.HashSet;


public class Demo_hashSet {

    public static void main(String[] args) {

        HashSet<String> names = new HashSet<>();

        // 加入元素用add方法
        names.add("孙悟空");
        names.add("孙悟空"); // 这里加不进去 因为Hashset 不能存储重复的元素
        names.add("猪八戒");
        names.add("沙和尚");
        names.add("唐僧");

        System.out.println(names.size());

        // 遍历hashset 最简单用增强的for循环 forEach
        for(String name : names)
        {
            System.out.println(name);
        }

        // 删除元素用remove
        names.remove("孙悟空");

        System.out.println(names.size());

    }
}

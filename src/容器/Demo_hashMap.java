package 容器;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *  双列数据结构 用HashMap 例如菜单<菜名 ， 价格>
 *
 *      聊天程序 <name ， Socket>
 */
public class Demo_hashMap {

    public static void main(String[] args) {

        HashMap<String , Integer> menus = new HashMap<>();

        // hashMap 添加用put
        menus.put("鱼香肉丝" , 20);
        menus.put("鱼香肉丝" , 20); // x重复添加也加不上去 key相同就加不上去 hashMap的所有的key是hashSet 还是不重复
        menus.put("地三鲜" , 10);
        menus.put("尖椒肉丝" , 22);
        menus.put("糖醋丸子" , 24);

        // hashMap 遍历 多种遍历方法

        Set<String> strings = menus.keySet();

        for (String str : strings)
        {
            // menus.get(str) hashMap根据key可以获取value
            System.out.println(str + "价格是: " + menus.get(str));
        }

        // hashMap 删除 remove
        menus.remove("鱼香肉丝");

        System.out.println(menus.size());


        // hashMap 更新 也是put 相同的key会覆盖

        menus.put("地三鲜" , 16);

        strings = menus.keySet();

        for (String str : strings)
        {
            // menus.get(str) hashMap根据key可以获取value
            System.out.println(str + "价格是: " + menus.get(str));
        }


    }
}

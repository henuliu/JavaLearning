package day05.Objcet;

import lombok.Data;

@Data
public class User implements Cloneable
{
    private String id; //编号
    private String username; //用户名
    private String password; //密码
    private double[] scores; //分数

    public User()
    {

    }

    public User(String id, String username, String password, double[] scores)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.scores = scores;
    }

    //实现浅克隆方式
    /*
        浅克隆是指创建一个新的对象，然后将原对象的非静态字段复制到新对象中。
        如果字段是基本数据类型，则直接复制其值；
        如果字段是引用类型，则复制引用（即地址），而不是引用的对象本身
        （引用类型拷贝的是地址值）
    */
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    //实现深克隆方式
    /*
        对象中基本类型数据的直接拷贝
        对象中字符串数据拷贝的还是地址
        对象中还包含的其他对象，不会拷贝地址，而是创建新对象
    */
//    @Override
//    protected Object clone() throws CloneNotSupportedException
//    {
//        //先克隆得到一个新对象
//        User u = (User) super.clone();
//        //再将新对象中的引用类型数据，再次克隆
//        u.scores = u.scores.clone();
//        return u;
//
//    }
}

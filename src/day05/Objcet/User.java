package day05.Objcet;

public class User implements Cloneable
{
    private String id; //编号
    private String username; //用户名
    private String password; //密码
    private double[] scores; //分数

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public double[] getScores()
    {
        return scores;
    }

    public void setScores(double[] scores)
    {
        this.scores = scores;
    }

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
    浅克隆的意思：拷贝出来的对象封装的数据与原对象封装的数据一模一样
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

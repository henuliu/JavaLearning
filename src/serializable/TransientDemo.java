package serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class UserInfo
{  //================================注意这里没有实现Serializable接口
    private String name;
    private transient String password;

    public UserInfo(String name, String psw)
    {
        this.name = name;
        this.password = psw;
    }

    @Override
    public String toString()
    {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

public class TransientDemo
{
    public static void main(String[] args)
    {
        UserInfo userInfo = new UserInfo("老王", "123");
        System.out.println("序列化之前信息：" + userInfo);

        try
        {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("userinfo.txt"));
            output.writeObject(new UserInfo("老王", "123"));
            output.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

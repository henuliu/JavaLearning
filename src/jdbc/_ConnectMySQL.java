package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class _ConnectMySQL
{
    public static void main(String[] args) throws Exception
    {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String UserName = "root";
        String PassWord = "2754686220ljh";
        Connection conn = DriverManager.getConnection(url, UserName, PassWord);

        //定义SQL语句
        String cmd = "update student set age=22 where id=1001";

        //获取执行SQL的对象Statement
        Statement statement = conn.createStatement();

        //执行SQL语句
        int result = statement.executeUpdate(cmd);

        if (result > 0)
        {
            System.out.println("执行成功");
        }
    }
}

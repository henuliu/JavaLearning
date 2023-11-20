package swing;

import java.sql.*;

public class ConnectMySQL
{
    private Connection connection;
    public static void main(String[] args) throws SQLException
    {
        String url="jdbc:mysql://localhost:3306/user";
        String uid ="root";
        String passWord="2754686220ljh";
        ConnectMySQL conn=new ConnectMySQL(url,uid,passWord);
        String cmd="select * from login where UserName='admin' and PassWord='1234567'";

        // 执行数据库语句
        conn.queryExecute(cmd);

    }
    // 构造函数用于连接MySQL
    public ConnectMySQL(String url,String uid,String passWord) throws SQLException
    {
        //注册驱动
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        //获取连接
        Connection conn = null;
        try
        {
            this.connection=DriverManager.getConnection(url, uid, passWord);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    // 执行查询操作
    public ResultSet queryExecute(String cmd) throws SQLException
    {
        //获取执行SQL的对象Statement
        Statement statement = connection.createStatement();
        ResultSet resultSet= statement.executeQuery(cmd);
        System.out.println(resultSet.next());

        // 关闭数据库
        statement.close();
        resultSet.close();
        this.connection.close();

        // 返回结果集
        return resultSet;
    }

    // 关闭数据库
    public void closeConnection() throws SQLException
    {
        this.connection.close();

    }
}

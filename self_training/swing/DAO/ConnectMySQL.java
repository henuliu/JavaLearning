package swing.DAO;

import java.sql.*;

public class ConnectMySQL
{
    private Connection connection;

    // 构造函数用于连接MySQL
    public ConnectMySQL() throws SQLException
    {
        //注册驱动
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        //获取连接
        try
        {
            String url = "jdbc:mysql://localhost:3306/chatsystem";
            String uid = "root";
            String passWord = "2754686220ljh";
            this.connection = DriverManager.getConnection(url, uid, passWord);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/chatsystem";
        String uid = "root";
        String passWord = "2754686220ljh";

        ConnectMySQL conn = new ConnectMySQL();
        String cmd="select * from groupinfo where groupAccount in(select groupAccount from groupmembers where memberAccount='111');";
        ResultSet resultSet=conn.queryExecute(cmd);
        try
        {
            if (resultSet.next())
            {
                String result = resultSet.getString("groupAccount");
                System.out.println(result);
            } else
            {
                System.out.println("已经存在好友");
            }
        } catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }

    }

    // 执行查询操作
    public ResultSet queryExecute(String cmd) throws SQLException
    {
        //获取执行SQL的对象Statement
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(cmd);
        // 返回结果集
        return resultSet;
    }

    // 执行插入操作
    public int insertExecute(String cmd) throws SQLException
    {
        // 获取执行SQL的对象Statement
        Statement statement = connection.createStatement();
        int rowsAffected = statement.executeUpdate(cmd);

        // 关闭Statement
        statement.close();

        // 返回受影响的行数
        return rowsAffected;
    }

    // 关闭数据库
    public void closeConnection() throws SQLException
    {
        this.connection.close();

    }
}

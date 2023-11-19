package swing;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ChatServer
{
    // 定义一个双列数据结构HashMap  ，HashMap<String , Socket> String就是代表用户名
    public static HashMap<String, Socket> clientNameAndSockets = new HashMap<>();

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket=new ServerSocket(8888);
        System.out.println("聊天服务器已启动,等待客户端连接");
        serverSocket.accept();

        // 接收多个客户端的连接
        while (true)
        {
            /*
              用accept()方法，它会阻塞程序的执行，直到有客户端连接成功。一旦有客户端连接成功，
              accept()方法会返回一个Socket对象，表示与客户端的连接
             */
            Socket socket=serverSocket.accept();
            // 如果有客户端连接，会打印连接提示
            System.out.println("客户端已连接，IP地址: " + socket.getInetAddress() + "，端口号: " + socket.getPort());


            //服务器为每一个客户端都会创建一条线程 专门为这个客户端服务： 读取这个客户端任意时刻发送过来的数据 并针对性的处理 比如给客户端响应数据
            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();

        }
    }

}

/**
 * 服务器为每一个客户端都会创建一条线程 专门为这个客户端服务
 * 读取这个客户端任意时刻发送过来的数据 并针对性的处理 比如给客户端响应数据
 */
class ServerThread extends Thread
{
    private Socket socket;
    public ServerThread(Socket socket)
    {
        this.socket=socket;
    }

    private boolean validateLogin(String input) throws SQLException
    {
        String []inputs=input.split(",");

        // 将客户端输入的账号密码信息分割
        String UserName=inputs[0];
        String PassWord=inputs[1];

        // 连接MySQL
        String url = "jdbc:mysql://localhost:3306/user";
        String uid = "root";
        String passWord = "2754686220ljh";

        // 用MySQL查询语句进行验证
        ConnectMySQL LoginConn = new ConnectMySQL(url, uid, passWord);
        String cmd = "select * from login where UserName='" + UserName + "' and PassWord='" + PassWord + "'";
        ResultSet resultSet = null;

        // 执行SQL查询语句，返回结果集
        resultSet = LoginConn.queryExecute(cmd);
        if (resultSet.next())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // 验证账号密码是否正确

    private void LoginCheck(String input) throws SQLException, IOException
    {
        // 对接收到的账号密码信息进行验证
        if(validateLogin(input)) // 账号密码正确
        {
            // 发送验证结果到客户端
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            String returnMessage="true";
            printStream.println(returnMessage);
            printStream.flush();
        }
        else // 账号或密码错误
        {
            // 发送验证结果到客户端
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            String returnMessage="false";
            printStream.println(returnMessage);
            printStream.flush();
        }
    }
    @Override
    public void run()
    {
        try
        {
            // 接收账号密码信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input = reader.readLine(); // 客户端发送的是"username,password"格式的字符串
            LoginCheck(input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            // 关闭连接
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

    }
}
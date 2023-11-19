package day06.v1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    public static void main(String[] args) throws IOException
    {

        // 创建一个网络服务器 非常简单 就是new 一个ServerSocket
        // 网络程序需要给一个监听的端口号 是个整数 范围好像是0-65535 ，小的端口号一般给一些系统程序占用了 一般大点
        // 代码是红色波浪线 ，有个编译时异常 Unhandled exception: java.io.IOException
        // java异常体系有个重要知识点，编译时异常和运行时异常，编译时异常就是IDEA会提示，写代码时候必须处理，否则通不过编译
        // 处理编译时异常 ，有2种思路 1是抛出给调用者  2是捕获 try catch
        // 1 创建了服务器，还没有启动
        ServerSocket serverSocket = new ServerSocket(8888);

        System.out.println("我是服务器，我准备开门迎客了，客户端来连我吧...");

        // 2 启动服务器
        // 接收客户端的连接，如果没有客户端连接，线程会阻塞到这里，走不下去的
        Socket socket = serverSocket.accept();

        System.out.println("已经有客户端连接到服务器了");

        // 要读数据 首先要有输入流 通过socket获取 InputStream 细水管 字节流 可以1个字节1个字节读取 太费事
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // 中等水管  字节流转字符流
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    // 粗点水管  可以读取一行数据

        // 读取客户端发送过来的数据
        String name = bufferedReader.readLine();

        System.out.println("我是服务器，我接收到了客户端的数据: " + name);

        // 服务器给客户端写数据 模板代码
        PrintStream ps = new PrintStream(socket.getOutputStream());

        ps.println("hello , " + name);
        ps.flush();

        System.out.println("我是服务器，我给客户端发送了hello,zsc");

    }
}

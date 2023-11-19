package day06.v1;

import java.io.*;
import java.net.Socket;

public class Client
{

    public static void main(String[] args) throws IOException
    {

        // 客户端连接服务器 ，就是创建1个socket ，但是给2个参数 1个服务器的IP地址  还有1个是端口号
        // localhost 或127.0.0.1 就是本机 要改成要连接的服务器IP地址
        Socket socket = new Socket("localhost", 8888);

        System.out.println("我是客户端，我连接ip地址localhost ， 端口号8888 , 网络服务器了");

        // 客户端给服务器发送消息 ，相当与是网络IO Input OutPut 输入输出
        // java里边IO 输入输出流实现的 是有专门的API的 都位于java.io 包下 很多都是模板代码 理解记住常用的API类和方法即可

        // 通过socket 获得输出流 输出流写数据就是写到虚拟链路 也就是写给网络
        OutputStream outputStream = socket.getOutputStream();

        // OutputStream其实就可以往网络写数据了，但是api不是很好用，比如可以理解成一根比较细的水管，水流小 对接一个粗点的水管
        PrintStream ps = new PrintStream(outputStream);

        // 发送了一行数据 ln换行
        ps.println("zsc");
        ps.flush(); // 刷新 ps带缓冲功能，也就是集合一批数据再往目的地发送，flush意思立刻发送

        System.out.println("我是客户端，我给服务器发送了我的名字zsc");


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String reply = bufferedReader.readLine();
        System.out.println("我是客户端，我收到服务器发送给我的消息了: " + reply);
    }
}

package _UDP;

import java.net.InetAddress;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        int port = 9876; // 指定端口号

        UDPReceiver receiver = new UDPReceiver(port);
        // 使用了Lambda表达式来创建一个匿名的Runnable对象，并将其作为参数传递给Thread类的构造函数
        // 调用start()方法会启动新线程，并在新线程中执行方法receiver.startReceiving()来接收数据
        // 由此可以在一个独立的线程中进行数据接收,而不会阻塞主线程的执行。在发送数据的同时，可以并行地接收数据。
        new Thread(() -> receiver.startReceiving()).start();

        // 创建一个IP地址对象,给该IP发送数据
        InetAddress receiverAddress;
        while (true)
        {
            System.out.println("请输入要发送的数据");
            Scanner scanner=new Scanner(System.in);
            String senderInfo=scanner.next();
            try
            {
                receiverAddress = InetAddress.getLocalHost();
                UDPSender sender = new UDPSender();
                sender.send(senderInfo, receiverAddress, port);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
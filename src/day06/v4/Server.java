package day06.v4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server
{

    // 定义一个容器 也是一个动态数组 长度可变的数据 和之前学过的ArrayList类似，但是HashSet不能存储相同的元素 ArrayList可以存储相同元素
    public static HashSet<Socket> clientSockets = new HashSet<Socket>();

    public static void main(String[] args) throws IOException
    {

        ServerSocket serverSocket = new ServerSocket(8888);

        System.out.println("我是服务器, 启动了，客户端快来连接我吧。。。");

        // 接收多个客户端的连接
        while (true)
        {
            Socket socket = serverSocket.accept();
            System.out.println("我是服务器, 有客户端连接到了。。。");

            clientSockets.add(socket);

//          服务器为每一个客户端都会创建一条线程 专门为这个客户端服务： 读取这个客户端任意时刻发送过来的数据 并针对性的处理 比如给客户端响应数据
            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();

////      准备给客户端写数据
//            PrintStream printStream = new PrintStream(socket.getOutputStream());

            // 服务器端读取任意时刻客户端发送过来的数据!!!
//         1 打印到服务器自己的控制台
//         2 给客户端返回去同样的信息
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            String line = null;
//
//            while( (line = bufferedReader.readLine()) != null  )
//            {
//                System.out.println("--------------------------------------------------");
//                System.out.println("我是服务器， 我收到客户端发送数据: " + line);
//
//                printStream.println(line);
//                printStream.flush();
//
//                System.out.println("我是服务器， 我给客户端返回同样的数据: " + line);
//            }


        }

    }
}


/**
 * 服务器为每一个客户端都会创建一条线程 专门为这个客户端服务
 * <p>
 * 读取这个客户端任意时刻发送过来的数据 并针对性的处理 比如给客户端响应数据
 */
class ServerThread extends Thread
{

    private Socket socket;

    public ServerThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {

        try
        {
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = null;

            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println("--------------------------------------------------");
                System.out.println("我是服务器， 我收到客户端发送数据: " + line);

                // 收到任意一个客户端的消息之后，群发给所有客户端
                for (Socket clientSocket : Server.clientSockets)
                {
                    // 群聊消息 不用给自己发 除了自己都发
                    if (socket != clientSocket)
                    {
                        PrintStream clientPrintStream = new PrintStream(clientSocket.getOutputStream());

                        clientPrintStream.println(line);
                        clientPrintStream.flush();

                        System.out.println("我是服务器， 群聊前提下，我给每个客户端返回同样的数据: " + line);
                    }

                }

            }

        } catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

    }
}
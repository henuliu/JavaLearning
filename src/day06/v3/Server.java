package day06.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args)  throws IOException {

        ServerSocket serverSocket = new ServerSocket(8888);

        System.out.println("我是服务器, 启动了，客户端快来连接我吧。。。");

        // 接收多个客户端的连接
        while(true)
        {
            Socket socket = serverSocket.accept();
            System.out.println("我是服务器, 有客户端连接到了。。。");

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
 *  服务器为每一个客户端都会创建一条线程 专门为这个客户端服务
 *
 *  读取这个客户端任意时刻发送过来的数据 并针对性的处理 比如给客户端响应数据
 */
class ServerThread extends Thread
{

    private  Socket socket;

    public ServerThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() {

        try
        {
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = null;

            while( (line = bufferedReader.readLine()) != null  )
            {
                System.out.println("--------------------------------------------------");
                System.out.println("我是服务器， 我收到客户端发送数据: " + line);

                printStream.println(line);
                printStream.flush();

                System.out.println("我是服务器， 我给客户端返回同样的数据: " + line);
            }

        }catch (IOException ioException){
            ioException.printStackTrace();
        }

    }
}
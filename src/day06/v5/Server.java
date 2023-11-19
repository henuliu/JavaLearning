package day06.v5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Server {

    // 定义一个双列数据结构HashMap  ，HashMap<String , Socket> String就是代表用户名
    public static HashMap<String , Socket> clientNameAndSockets = new HashMap<>();

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

                // 定义一个变量 存储服务器准备给客户端返回的信息
                String returnMessage = "";

                // 首先把客户端发送过来的消息 用逗号隔开 返回的是字符串的数组
                // 注册登录 1,zs
                // 群聊 2,message
                // 私聊 3,senderName,receiverName,message
                String[] strings = line.split(",");

                // 注册登录 1,zs 服务器收到客户端的注册登录请求
                if ("1".equals(strings[0]))
                {
                    String name = strings[1];


                    boolean flag = Server.clientNameAndSockets.containsKey(name);// map的containsKey判断这个key

                    if (flag)
                    {
                        returnMessage = "1,nook,name is exist!";
                    }else{
                        returnMessage = "1,ok";

                        // 名字可用，保存到服务器里边 相当于同时也登录成功了
                        Server.clientNameAndSockets.put(name , socket);
                    }

                    printStream.println(returnMessage);
                    printStream.flush();
                }


                // 群聊 2,message
                if ("2".equals(strings[0]))
                {
                    // map的values() 获取所有的value 返回的是一个集合 当中存的是Socket Collection是容器的顶级接口 理解成动态数组
                    Collection<Socket> values = Server.clientNameAndSockets.values();

                    // 收到任意一个客户端的消息之后，群发给所有客户端
                    for(Socket clientSocket : values) // map的values() 获取所有的value
                    {
                        // 群聊消息 不用给自己发 除了自己都发
                        if(socket != clientSocket)
                        {
                            PrintStream clientPrintStream = new PrintStream(clientSocket.getOutputStream());

                            clientPrintStream.println(line);
                            clientPrintStream.flush();

                            System.out.println("我是服务器， 群聊前提下，我给每个客户端返回同样的数据: " + line);
                        }

                    }

                }


                // 私聊 3,senderName,receiverName,message
                if ("3".equals(strings[0]))
                {
                    String senderName = strings[1];
                    String receiverName = strings[2];
                    String message = strings[3];

                    Socket receiverSocket = Server.clientNameAndSockets.get(receiverName);

                    PrintStream receiverPrintStream = new PrintStream(receiverSocket.getOutputStream());
                    receiverPrintStream.println(line);
                    receiverPrintStream.flush();
                }


            }

        }catch (IOException ioException){
            ioException.printStackTrace();
        }

    }
}
package day06.v2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost" , 8888);
        System.out.println("我是客户端, 我连接到服务器了。。。");

//       创建并启动ClientThread ： 接收服务器任意时刻返回来的数据
        ClientThread clientThread = new ClientThread(socket);
        clientThread.start();


//      准备一个输出流 给服务器写数据
        PrintStream printStream = new PrintStream(socket.getOutputStream());

//      接收键盘时输入 之前是利用Scanner next方法
//        Scanner scanner = new Scanner(System.in);
//        scanner.next();

//        客户端读取任意时刻键盘输入的数据，给服务器发送过去!!!
//        输入流 大水管套中水管套小水管 最终读取键盘输入 System.in
        BufferedReader bufferedReaderForSystemIn = new BufferedReader(new InputStreamReader(System.in));

        String line = null;

        while( (line = bufferedReaderForSystemIn.readLine()) != null ) // 读取任意时刻键盘输入 不是null 意思就是读到一行数据 执行循环体 ，如果没读到 一直读 线程阻塞
        {
            System.out.println("--------------------------------------------------");
            System.out.println("我是客户端， 我读取到了键盘输入的数据，准备给服务器发送: " + line);

            printStream.println(line);
            printStream.flush();

            System.out.println("我是客户端， 我给服务器发送数据 " + line);

        }

//       创建并启动ClientThread ： 接收服务器任意时刻返回来的数据 但是放在读取键盘输入之后不行， 因为没机会启动这个线程
//        ClientThread clientThread = new ClientThread(socket);
//        clientThread.start();

//        // 客户端读取任意时刻服务器发送过来的数据!!!
//        BufferedReader bufferedReaderForServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//        String lineForServer = null;
//
//        while( (lineForServer = bufferedReaderForServer.readLine()) != null  )
//        {
//            System.out.println("我是客户端， 我收到服务器发送数据: " + lineForServer);
//        }

    }
}

/**
 *  客户端接收服务器任意时刻返回来的数据
 */
class ClientThread extends Thread
{
    private Socket socket;

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    // 方法重写 有个规则 子类不能抛出异常到父类的方法签名
    @Override
    public void run() /*throws IOException*/{

        // try catch是解决编译时异常的方法 ，制定预案， 如果发生了，怎么做 一般就是打印到控制台 让程序员看到 就知道怎么解决了
        try {
            // 客户端读取任意时刻服务器发送过来的数据!!!
            BufferedReader bufferedReaderForServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String lineForServer = null;

            while( (lineForServer = bufferedReaderForServer.readLine()) != null  )
            {
                System.out.println("我是客户端， 我收到服务器发送数据: " + lineForServer);
            }
        }catch (IOException ioException)
        {
            ioException.printStackTrace();
        }


    }
}

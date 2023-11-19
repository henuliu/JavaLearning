package day06.v6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Client
{

    // Client类 成员 客户端连接服务器生成的socket
    public static Socket socket;

    // Client类 成员 客户端打开的登录注册2合1界面
    public static RegisterAndLoginUI registerAndLoginUI;

    public static ChatRoomUI chatRoomUI;


    public static void main(String[] args) throws IOException
    {

        Socket socket = new Socket("localhost", 8888);
        Client.socket = socket;
        System.out.println("我是客户端, 我连接到服务器了。。。");

//       创建并启动ClientThread ： 接收服务器任意时刻返回来的数据
        ClientThread clientThread = new ClientThread(socket);
        clientThread.start();

        // 显示登录注册2合1界面
        registerAndLoginUI = new RegisterAndLoginUI();
        registerAndLoginUI.initUI();

    }
}

/**
 * 客户端接收服务器任意时刻返回来的数据
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
    public void run() /*throws IOException*/
    {

        // try catch是解决编译时异常的方法 ，制定预案， 如果发生了，怎么做 一般就是打印到控制台 让程序员看到 就知道怎么解决了
        try
        {
            // 客户端读取任意时刻服务器发送过来的数据!!!
            BufferedReader bufferedReaderForServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String lineForServer = null;

            while ((lineForServer = bufferedReaderForServer.readLine()) != null)
            {
                System.out.println("--------------------------------------------------");
                System.out.println("我是客户端， 我收到服务器发送数据: " + lineForServer);

                String[] strings = lineForServer.split(",");

                // 收到服务器关于注册登录的反馈信息
                // 1,ok
                // 1,nook,name is exist!
                if ("1".equals(strings[0]))
                {
                    // 1,ok,name,others
                    if ("ok".equals(strings[1]))
                    {

                        // 关闭打开的登录注册2合1界面
                        Client.registerAndLoginUI.setVisible(false); //只是隐藏 没销毁
                        Client.registerAndLoginUI.dispose();         // 内存中销毁

                        // 打开聊天室主界面
                        ChatRoomUI chatRoomUI = new ChatRoomUI();

                        ArrayList<String> selfAndOtherOnlineNames = new ArrayList<>();

                        for (int i = 2; i < strings.length; i++)
                        {
                            selfAndOtherOnlineNames.add(strings[i]);
                        }

                        chatRoomUI.initUI(selfAndOtherOnlineNames);

                        // 聊天室界面也放到Client的类成员，后续可能别的地方使用
                        Client.chatRoomUI = chatRoomUI;

                    }

                    // 1,nook,name is exist!
                    if ("nook".equals(strings[1]))
                    {

                    }
                }

                // 2,message
                if ("2".equals(strings[0]))
                {

                }

                // 3,senderName,receiverName,message
                if ("3".equals(strings[0]))
                {

                }


            }
        } catch (IOException ioException)
        {
            ioException.printStackTrace();
        }


    }
}

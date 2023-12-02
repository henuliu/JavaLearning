package day06.v7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Client {

    // Client类 成员 客户端连接服务器生成的socket
    public static Socket socket;

    public static String clientMemberName;

    // Client类 成员 客户端打开的登录注册2合1界面
    public static RegisterAndLoginUI registerAndLoginUI;

    public static  ChatRoomUI chatRoomUI; // 对于一个Client而言，只有1个聊天室界面 不是很多 static即可 实例变量不对

    // 因为1个client可以打开多个私聊界面，和多个好友同时私聊 所以用一个双列数据结构保存
    public static HashMap<String , PrivateChatUI> friendName2PrivateChatUI = new HashMap<>();

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost" , 8888);
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
                System.out.println("--------------------------------------------------");
                System.out.println("我是客户端， 我收到服务器发送数据: " + lineForServer);

                String[] strings = lineForServer.split(",");

                // 收到服务器关于注册登录的反馈信息
                // 1,ok
                // 1,nook,name is exist!
                if ("1".equals(strings[0]))
                {
                    // 1,ok,name,others
                    if("ok".equals(strings[1]))
                    {

                        // 关闭打开的登录注册2合1界面
                        Client.registerAndLoginUI.setVisible(false); //只是隐藏 没销毁
                        Client.registerAndLoginUI.dispose();         // 内存中销毁

                        // 打开聊天室主界面
                        ChatRoomUI chatRoomUI = new ChatRoomUI();

                        ArrayList<String> selfAndOtherOnlineNames = new ArrayList<>();

                        for(int i= 2 ; i<strings.length ; i++ )
                        {
                            selfAndOtherOnlineNames.add(strings[i]);
                        }

                        chatRoomUI.initUI(selfAndOtherOnlineNames);

                        Client.clientMemberName = selfAndOtherOnlineNames.get(0);

                        // 聊天室界面也放到Client的类成员，后续可能别的地方使用
                        Client.chatRoomUI = chatRoomUI;

                    }

                    // 1,nook,name is exist!
                    if("nook".equals(strings[1]))
                    {

                    }
                }


                // 收到101开头 就是代表最新的在线会员列表 101,zs,ls,ww,...
                if ("101".equals(strings[0]))
                {
                   // 1 找到自己这个客户端当中存储的聊天室的界面
                    ChatRoomUI chatRoomUI = Client.chatRoomUI;

                    // 2 更新右侧的在线会员列表 (放在了聊天室界面的 jp3 Jpanel当中 )

                    // 2.1 清空原来的jp3 之前的在线用户全部删掉

                    JPanel jp3 = chatRoomUI.getJp3();
                    jp3.removeAll();

                    // 2.2 把最新的会员重新加一遍

                    ArrayList<String> OnlineNames = new ArrayList<>();

                    for(int i = 1 ; i<strings.length ; i++ )
                    {
                        OnlineNames.add(strings[i]);
                    }

                    // 把在线的用户 创建对应的按钮 加入到聊天室右侧的在线列表
                    for(int i=0 ; i < OnlineNames.size() ;i++ )
                    {
                        JButton jButton1 = new JButton(OnlineNames.get(i));

                        final int j = i; // 因为i在匿名内部类当中要用到，要求i必须是final 和 i++冲突 所以给个临时的final j

                        jButton1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                PrivateChatUI privateChatUI = new PrivateChatUI();
                                privateChatUI.initUI(Client.clientMemberName , OnlineNames.get(j));

                                Client.friendName2PrivateChatUI.put(OnlineNames.get(j) , privateChatUI);
                            }
                        });

                        jp3.add(jButton1);
                    }

                    // JPanel当中的内容修改之后 界面发生变化 最后一定调用一个更新方法
                    jp3.updateUI();
                }


                //客户端收到群聊消息 2,zs,message
                if ("2".equals(strings[0]))
                {
                    JPanel jp1 = Client.chatRoomUI.getJp1();

                    JLabel jLabel = new JLabel(new Date().toLocaleString() + strings[1]  + "说: " + strings[2]);
                    jp1.add(jLabel);
                    jp1.updateUI();

                }

                // 3,senderName,receiverName,message
                if ("3".equals(strings[0]))
                {
                    String senderName = strings[1];
                    String receiverName = strings[2];
                    String chatContent = strings[3];

                    // 找到自己客户端当中存储的自己和好友friend的聊天界面
                    PrivateChatUI privateChatUI = Client.friendName2PrivateChatUI.get(senderName);

                    JPanel jp1 = privateChatUI.getJp1();

                    JLabel jLabel = new JLabel(new Date().toLocaleString() + strings[1]  + "说: " + strings[2]);
                    jp1.add(jLabel);
                    jp1.updateUI();
                }


            }
        }catch (IOException ioException)
        {
            ioException.printStackTrace();
        }


    }
}

package test;

import testView.PrivateChatUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ChatRoomWindow extends JFrame {
    // 消息框
    public static JTextArea chatArea;

    // 发送框
    public static JTextArea sendArea;

    public static Socket socket;

    //好友列表panel
    public static JPanel FriendsPanel;

    // 添加PrintStream成员变量
    private PrintStream printStream;

    public ChatRoomWindow() throws IOException
    {
        super("主界面");

        // 设置图标
        Image image = Toolkit.getDefaultToolkit().getImage("self_training/swing/images/QQLogin.png");
        this.setIconImage(image);
        // 初始化聊天界面
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(550, 100, 700, 500);

        //=============== 加入聊天框 ===============
        chatArea = new JTextArea();
        chatArea.setEditable(false); // 设置不可编辑

        JScrollPane scrollPane = new JScrollPane(chatArea); // 添加滚动条
        scrollPane.setBounds(190, 0, 500, 300);
        this.add(scrollPane);

        // 设置字体和大小
        Font font = new Font("微软雅黑", Font.PLAIN, 12);
        chatArea.setFont(font);

        //设置自动换行
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        //=============== 加入发送框 ===============
        sendArea = new JTextArea();

        // 设置字体和大小
        sendArea.setFont(font);

        //设置自动换行
        sendArea.setLineWrap(true);
        sendArea.setWrapStyleWord(true);
        JScrollPane sendScroll = new JScrollPane(sendArea); // 添加滚动条
        sendScroll.setBounds(190, 320, 480, 100);
        this.add(sendScroll);

        // 加入发送消息按钮
        JButton SendMsgButton = new JButton("发送");
        SendMsgButton.setBounds(580, 420, 80, 25);

        this.add(SendMsgButton);

        //=============== 加入好友列表 ===============
        FriendsPanel=new JPanel();
        FriendsPanel.setLayout(null);

        FriendsPanel.setBounds(20,0,150,450);


        JButton button=new JButton("张三");
        button.setBounds(50,50,60,30);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PrivateChatUI privateChatUI=new PrivateChatUI("zs","ls","10001","10002");
            }
        });
        FriendsPanel.add(button);
        this.add(FriendsPanel);

        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException
    {
     ChatRoomWindow chatRoomWindow=new ChatRoomWindow();
    }
}

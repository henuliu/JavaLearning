package test;

import com.google.gson.Gson;
import swing.Client;
import swing.Entity.ClientMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrivateChatUI extends JFrame
{
    // 消息框
    public static JTextArea chatArea;

    public static JTextArea getChatArea()
    {
        return chatArea;
    }

    // 发送框
    public static JTextArea sendArea;

    // 添加PrintStream成员变量
    public static PrintStream printStream;

    public static JButton SendMsgButton;

    public static String self;
    public static String friend;
    public PrivateChatUI(String self,String friend)
    {
        this.setTitle(self+"与"+friend + "的聊天室");

        // 初始化聊天界面
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        SendMsgButton = new JButton("发送");
        SendMsgButton.setBounds(580, 420, 80, 25);

        // 给发送按钮添加监听器
//        SendMsgButton.addActionListener(new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                // 点击发送按钮时执行的操作
//                // 点击发送按钮时执行的操作
//                String msg = sendArea.getText(); // 获取发送框的文本
//                String msg_Json = "{\"type\": \"private_msg\", \"from\": \""+self+"\", \"to\": \""+friend+"\", \"content\": \"" + msg + "\"}";
//                Gson gson = new Gson();
//                ClientMessage message = gson.fromJson(msg_Json, ClientMessage.class);
//                // 如果发送框不为空
//                if (!msg.isEmpty())
//                {
//                    try
//                    {
//                        sendMessage(msg_Json);
//                        sendArea.setText(""); // 清空发送框
//                        appendMessage(Client.UserName,message.getContent());
//                    } catch (IOException ex)
//                    {
//                        throw new RuntimeException(ex);
//                    }
//                }
//            }
//        });

        this.add(SendMsgButton);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        PrivateChatUI privateChatUI=new PrivateChatUI(self,friend);
    }
}

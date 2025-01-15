package swing.view;

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

    public PrivateChatUI(String self, String friend, String selfAccount, String friendAccount)
    {
        this.setTitle(self + "与" + friend + "的聊天室");

        // 初始化聊天界面
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(550, 100, 700, 600);


        // 设置窗口图标
        ImageIcon icon = new ImageIcon("images/QQLogin.png");
        this.setIconImage(icon.getImage());


        // 加入形象
        ImageIcon selfIcon = new ImageIcon("images/QQxiu1.png");
        Image scaledImg01 = selfIcon.getImage().getScaledInstance(170, 245, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon01 = new ImageIcon(scaledImg01);
        JLabel selfLabel = new JLabel(scaledIcon01);
        selfLabel.setBounds(5, 10, 170, 245);
        this.add(selfLabel);

        ImageIcon friendIcon = new ImageIcon("images/QQxiu2.png");
        Image scaledImg02 = friendIcon.getImage().getScaledInstance(170, 245, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon02 = new ImageIcon(scaledImg02);
        JLabel friendLabel = new JLabel(scaledIcon02);
        friendLabel.setBounds(5, 260, 170, 245);
        this.add(friendLabel);

        //添加头像

        ImageIcon img = new ImageIcon("images/touxiang.png");
        Image scaledImg = img.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(190, 5, 70, 70);
        this.add(label);


        // 在两个框之间插入多个小组件图片
        ImageIcon img1 = new ImageIcon("images/xiaolian.png");
        // 缩放图片
        Image scaledImg1 = img1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImg1);
        JLabel label1 = new JLabel(scaledIcon1);
        label1.setBounds(190, 385, 20, 20);
        this.add(label1);

        ImageIcon img2 = new ImageIcon("images/GIF.png");
        Image scaledImg2 = img2.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImg2);
        JLabel label2 = new JLabel(scaledIcon2);
        label2.setBounds(230, 385, 20, 20);
        this.add(label2);

        ImageIcon img3 = new ImageIcon("images/jianqie.png");
        Image scaledImg3 = img3.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImg3);
        JLabel label3 = new JLabel(scaledIcon3);
        label3.setBounds(270, 385, 20, 20);
        this.add(label3);

        ImageIcon img4 = new ImageIcon("images/image.png");
        Image scaledImg4 = img4.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon4 = new ImageIcon(scaledImg4);
        JLabel label4 = new JLabel(scaledIcon4);
        label4.setBounds(310, 385, 20, 20);
        this.add(label4);

        ImageIcon img5 = new ImageIcon("images/jingyin.png");
        Image scaledImg5 = img5.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon5 = new ImageIcon(scaledImg5);
        JLabel label5 = new JLabel(scaledIcon5);
        label5.setBounds(350, 385, 20, 20);
        this.add(label5);


        //=============== 加入聊天框 ===============
        chatArea = new JTextArea();
        chatArea.setEditable(false); // 设置不可编辑

        JScrollPane scrollPane = new JScrollPane(chatArea); // 添加滚动条
        scrollPane.setBounds(190, 80, 500, 300);
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
        sendScroll.setBounds(190, 410, 480, 100);
        this.add(sendScroll);


        // 加入发送消息按钮
        SendMsgButton = new JButton("发送");
        SendMsgButton.setBounds(580, 515, 80, 25);

        // 给发送按钮添加监听器
        SendMsgButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 点击发送按钮时执行的操作
                // 点击发送按钮时执行的操作
                String msg = sendArea.getText(); // 获取发送框的文本
                String msg_Json = "{\"type\": \"private_msg\", \"from\": \"" + selfAccount + "\", \"to\": \"" + friendAccount + "\", \"content\": \"" + msg + "\"}";
                Gson gson = new Gson();
                ClientMessage message = gson.fromJson(msg_Json, ClientMessage.class);
                // 如果发送框不为空
                if (!msg.isEmpty())
                {
                    try
                    {
                        sendMessage(msg_Json);
                        sendArea.setText(""); // 清空发送框
                        appendMessage(Client.UserName, message.getContent());
                    } catch (IOException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        this.add(SendMsgButton);
        this.setVisible(true);
    }

    private void sendMessage(String message) throws IOException
    {
        // 初始化PrintStream
        printStream = new PrintStream(Client.clientSocket.getOutputStream());
        printStream.println(message);
        printStream.flush();
        System.out.println("客户端成功发送消息:" + message);
    }

    private void appendMessage(String sender, String message)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
        String formattedDate = sdf.format(date);

        String newMessage = PrivateChatUI.getChatArea().getText() + sender + " " + formattedDate + "\n" + message + "\n";
        PrivateChatUI.getChatArea().setText(newMessage);
    }

    public static void main(String[] args)
    {
        PrivateChatUI PrivateChatUI = new PrivateChatUI(self, friend, "10001", "10002");
    }
}

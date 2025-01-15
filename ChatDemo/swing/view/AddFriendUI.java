package swing.view;

import swing.Client;
import swing.utils.ModifyIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

public class AddFriendUI extends JFrame
{
    // 验证信息
    public static JTextArea messageTextArea;

    // 备注名称
    public static JTextArea remarkTextArea;

    public static String account;

    public static String nickname;

    public static String avatarPath;

    public static String remark;

    public AddFriendUI(String avatarPath, String account, String nickname)
    {
        AddFriendUI.account = account;
        AddFriendUI.nickname = nickname;
        AddFriendUI.avatarPath = avatarPath;

        setTitle("添加好友");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(500, 200, 470, 370);
        this.setLayout(null);
        //插入背景图片
        ImageIcon backgroundImage = new ImageIcon("images/background4.png");
        Image scaledBackgroundImage = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(scaledBackgroundImage);

        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        getLayeredPane().add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

        ((JPanel) getContentPane()).setOpaque(false);

        // 添加头像
        ImageIcon icon3 = new ImageIcon(avatarPath);
        ModifyIcon modifyIcon = new ModifyIcon();
        ImageIcon Icon = modifyIcon.modify(icon3, 80, 80);

        JLabel iconLabel = new JLabel(Icon);
        iconLabel.setBounds(40, 70, 80, 80);
        this.add(iconLabel);

        // 添加昵称
        JLabel nickNameLabel = new JLabel(nickname);
        nickNameLabel.setBounds(40, 140, 40, 40);
        this.add(nickNameLabel);

        // 添加账号
        JLabel accountLabel = new JLabel(account);
        accountLabel.setBounds(40, 160, 140, 40);
        this.add(accountLabel);

        // 添加性别
        JLabel label1 = new JLabel("性别:");
        label1.setBounds(40, 180, 40, 40);
        JLabel sexLabel = new JLabel("男");
        sexLabel.setBounds(70, 180, 40, 40);
        this.add(label1);
        this.add(sexLabel);

        // 添加年龄
        JLabel label2 = new JLabel("年龄:");
        label2.setBounds(40, 200, 40, 40);
        JLabel ageLabel = new JLabel("20");
        ageLabel.setBounds(70, 200, 40, 40);
        this.add(label2);
        this.add(ageLabel);

        // 添加所在地
        JLabel label3 = new JLabel("所在地:");
        label3.setBounds(40, 220, 40, 40);
        JLabel addressLabel = new JLabel("河南、开封");
        addressLabel.setBounds(85, 220, 100, 40);
        this.add(label3);
        this.add(addressLabel);

        // 添加验证信息框
        JLabel label4 = new JLabel("验证信息:");
        label4.setBounds(170, 58, 60, 40);
        this.add(label4);

        messageTextArea = new JTextArea();
        messageTextArea.setBounds(230, 72, 190, 100);
        messageTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.add(messageTextArea);

        // 添加备注名称
        JLabel label5 = new JLabel("备注姓名:");
        label5.setBounds(170, 170, 60, 40);
        this.add(label5);
        remarkTextArea = new JTextArea();
        remarkTextArea.setBounds(230, 180, 190, 24);
        remarkTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.add(remarkTextArea);

        // 添加分组
        JLabel groupLabel = new JLabel("好友分组：");
        groupLabel.setBounds(170, 200, 100, 40);
        this.add(groupLabel);

        String[] groupOptions = {"我的好友", "朋友", "同学"}; // 下拉框选项
        JComboBox<String> groupComboBox = new JComboBox<>(groupOptions); // 创建下拉框并添加选项
        groupComboBox.setBounds(230, 210, 80, 20);
        this.add(groupComboBox);

        // 确定按钮
        JButton sendButton = new JButton("发送验证");
        sendButton.setBounds(250, 280, 90, 30);
        // 添加发送验证按钮事件监听器
        SendBtnActionListener confirmBtnActionListener = new SendBtnActionListener();
        sendButton.addActionListener(confirmBtnActionListener);
        this.add(sendButton);

        // 取消按钮
        JButton cancelButton = new JButton("关闭");
        cancelButton.setBounds(370, 280, 60, 30);

        //添加取消按钮事件监听器
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 关闭窗口
                dispose();
            }
        });
        this.add(cancelButton);


        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        String avatarPath = "images/head/head1.png";
        String account = "212536805";
        String nickName = "zs";
        AddFriendUI addFriendUI = new AddFriendUI(avatarPath, account, nickName);
    }
}

// 发送验证信息事件监听器
class SendBtnActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // 如果好友备注为空
        if (AddFriendUI.remarkTextArea.getText().isEmpty())
        {
            // 发送加好友验证信息
            String message = "{\"type\":\"addFriend_msg\",\"from\":\"" + Client.UserName + "\",\"to\":\"" + AddFriendUI.account + "\",\"verifyInfo\":\"" + AddFriendUI.messageTextArea.getText() + "\",\"avatarPath\":" +
                    "\"" + Client.avatarPath + "\",\"NickName\": \"" + Client.nickName + "\"}";
            try
            {
                PrintStream printStream = new PrintStream(Client.clientSocket.getOutputStream());
                printStream.println(message);
                printStream.flush();
                System.out.println("客户端成功发送加好友信息" + message);
            } catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }

        }
        // 好友备注不为空
        else
        {
            // 发送加好友验证信息
            String message = "{\"type\":\"addFriend_msg\",\"from\":\"" + Client.UserName + "\",\"to\":\"" + AddFriendUI.account + "\",\"verifyInfo\":\"" + AddFriendUI.messageTextArea.getText() + "\",\"avatarPath\":" +
                    "\"" + Client.avatarPath + "\",\"NickName\": \"" + Client.nickName + "\",\"remark\":\"" + AddFriendUI.remarkTextArea.getText() + "\"}";
            try
            {
                PrintStream printStream = new PrintStream(Client.clientSocket.getOutputStream());
                printStream.println(message);
                printStream.flush();
                System.out.println("客户端成功发送加好友信息" + message);
            } catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    }
}

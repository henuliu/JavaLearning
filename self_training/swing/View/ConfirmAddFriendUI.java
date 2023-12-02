package swing.View;

import swing.Client;
import swing.Entity.Friend;
import swing.utility.ModifyIcon;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

// 确认添加好友界面
public class ConfirmAddFriendUI extends JFrame
{

    // 备注名称
    public static String remark;

    // 同意或拒绝下拉框
    public static JComboBox<String> groupComboBox1;

    // 好友分组下拉框
    public static JComboBox<String> groupComboBox2;
    public static String account;

    public static String nickname;

    public static String avatarPath;

    public static JTextArea verifyInfoArea;
    public ConfirmAddFriendUI(String avatarPath, String account, String nickname,String verifyInfo,String remark)
    {
        ConfirmAddFriendUI.account=account;
        ConfirmAddFriendUI.nickname=nickname;
        ConfirmAddFriendUI.avatarPath=avatarPath;
        ConfirmAddFriendUI.remark =remark;
        setTitle("验证信息");
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
        ModifyIcon modifyIcon=new ModifyIcon();
        ImageIcon Icon = modifyIcon.modify(icon3, 80, 80);

        JLabel iconLabel = new JLabel(Icon);
        iconLabel.setBounds(40,70,80,80);
        this.add(iconLabel);

        // 添加昵称
        JLabel nickNameLabel=new JLabel(nickname);
        nickNameLabel.setBounds(40,140,40,40);
        this.add(nickNameLabel);

        // 添加账号
        JLabel accountLabel = new JLabel(account);
        accountLabel.setBounds(40,160,140,40);
        this.add(accountLabel);

        // 添加性别
        JLabel label1=new JLabel("性别:");
        label1.setBounds(40,180,40,40);
        JLabel sexLabel = new JLabel("男");
        sexLabel.setBounds(70,180,40,40);
        this.add(label1);
        this.add(sexLabel);

        // 添加年龄
        JLabel label2=new JLabel("年龄:");
        label2.setBounds(40,200,40,40);
        JLabel ageLabel = new JLabel("20");
        ageLabel.setBounds(70,200,40,40);
        this.add(label2);
        this.add(ageLabel);

        // 添加所在地
        JLabel label3=new JLabel("所在地:");
        label3.setBounds(40,220,40,40);
        JLabel addressLabel = new JLabel("河南、开封");
        addressLabel.setBounds(85,220,100,40);
        this.add(label3);
        this.add(addressLabel);

        // 添加验证信息框
        JLabel label4=new JLabel("验证信息:");
        label4.setBounds(170,100,60,40);
        this.add(label4);

        verifyInfoArea =new JTextArea();
        verifyInfoArea.setBounds(230,110,190,60);
        verifyInfoArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        verifyInfoArea.setText(verifyInfo);
        verifyInfoArea.setEditable(false);
        this.add(verifyInfoArea);

        // 添加备注名称
        JLabel label5=new JLabel("是否同意:");
        label5.setBounds(170,170,60,40);
        this.add(label5);
        String[] groupOptions1 = {"同意", "拒绝"}; // 下拉框选项
        groupComboBox1 = new JComboBox<>(groupOptions1); // 创建下拉框并添加选项
        groupComboBox1.setBounds(230,182,80,20);
        this.add(groupComboBox1);

        // 添加分组
        JLabel groupLabel = new JLabel("好友分组：");
        groupLabel.setBounds(170,200,100,40);
        this.add(groupLabel);

        String[] groupOptions2 = {"我的好友", "朋友", "同学"}; // 下拉框选项
        groupComboBox2 = new JComboBox<>(groupOptions2); // 创建下拉框并添加选项
        groupComboBox2.setBounds(230,210,80,20);
        this.add(groupComboBox2);

        // 确定按钮
        JButton confirmButton=new JButton("确定");
        confirmButton.setBounds(250,280,90,30);
        // 添加发送验证按钮事件监听器
        ConfirmBtnActionListener confirmBtnActionListener=new ConfirmBtnActionListener();
        confirmButton.addActionListener(confirmBtnActionListener);
        this.add(confirmButton);

        // 取消按钮
        JButton cancelButton=new JButton("关闭");
        cancelButton.setBounds(370,280,60,30);

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
        ConfirmAddFriendUI confirmUI=new ConfirmAddFriendUI("images/head/head1.png","123456789", "张三","你好","张三");
    }
}

// 确定验证信息事件监听器
class ConfirmBtnActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // 是否同意添加好友
        String selectedOption = (String) ConfirmAddFriendUI.groupComboBox1.getSelectedItem();

        // 如果同意
        if(selectedOption.equals("同意"))
        {
            // 创建好友节点
            DefaultMutableTreeNode friendNode1 = null;
            // 如果好友备注为空
            if (ConfirmAddFriendUI.remark.isEmpty())
            {
                // 发送同意申请好友信息
                String message="{\"type\":\"agreeFriend_msg\",\"from\":\""+Client.UserName+"\",\"to\":\""+ConfirmAddFriendUI.account+"\",\"verifyInfo\":\""+ConfirmAddFriendUI.verifyInfoArea.getText()+"\",\"avatarPath\":" +
                        "\""+Client.avatarPath+"\",\"NickName\": \""+ConfirmAddFriendUI.account +"\",\"content\":\"agree\"}";
                try
                {
                    PrintStream printStream=new PrintStream(Client.clientSocket.getOutputStream());
                    printStream.println(message);
                    printStream.flush();
                    System.out.println("客户端发送了同意申请好友信息"+message);
                } catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
            // 好友备注不为空
            else
            {
                String message="{\"type\":\"agreeFriend_msg\",\"from\":\""+Client.UserName+"\",\"to\":\""+ConfirmAddFriendUI.account+"\",\"verifyInfo\":\""+ConfirmAddFriendUI.verifyInfoArea.getText()+"\",\"applyAvatarPath\":" +
                        "\""+ConfirmAddFriendUI.avatarPath+"\",\"applyNickName\": \""+ConfirmAddFriendUI.nickname +"\",\"remark\":\""+ConfirmAddFriendUI.remark+"\",\"appliedAvatarPath\":\""+Client.avatarPath+"\",\"appliedNickName\":\""+Client.nickName+"\",\"content\":\"agree\"}";
                PrintStream printStream= null;
                try
                {
                    printStream = new PrintStream(Client.clientSocket.getOutputStream());
                    printStream.println(message);
                    printStream.flush();
                    System.out.println("客户端发送了同意申请好友信息"+message);
                } catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
        // 拒绝添加好友
        else
        {

        }
    }
}


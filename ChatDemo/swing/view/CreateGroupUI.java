package swing.view;

import swing.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

public class CreateGroupUI extends JFrame
{
    private static JTextField groupNameTextField;

    private static JTextField groupAccountTextField;

    public CreateGroupUI()
    {
        this.setBounds(500, 200, 400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null); // 关闭布局管理器

        JLabel titleLabel = new JLabel("创建群聊");
        titleLabel.setBounds(150, 10, 100, 30);
        this.add(titleLabel);

        JLabel groupIdLabel = new JLabel("群号:");
        groupIdLabel.setBounds(50, 50, 80, 30);
        this.add(groupIdLabel);

        groupAccountTextField = new JTextField();
        groupAccountTextField.setBounds(100, 50, 200, 30);
        this.add(groupAccountTextField);

        JLabel groupNameLabel = new JLabel("群名:");
        groupNameLabel.setBounds(50, 90, 80, 30);
        this.add(groupNameLabel);

        groupNameTextField = new JTextField();
        groupNameTextField.setBounds(100, 90, 200, 30);
        this.add(groupNameTextField);

        // 创建群聊按钮
        JButton createButton = new JButton("创建");
        createButton.setBounds(130, 140, 100, 30);
        // 添加群聊按钮事件监听器
        createButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    String groupAvatar = "images/grouphead/grouphead1.png";
                    String content = CreateGroupUI.groupAccountTextField.getText() + "," + CreateGroupUI.groupNameTextField.getText() + "," + groupAvatar;
                    String message = "{\"type\":\"createGroup_msg\",\"content\":\"" + content + "\",\"groupMember\":\"" + Client.UserName + "\"}";
                    PrintStream printStream = new PrintStream(Client.clientSocket.getOutputStream());
                    printStream.println(message);
                    printStream.flush();
                    System.out.println("客户端成功发送创建群聊消息" + message);

                } catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.add(createButton);

        this.setVisible(true);
    }

    public static void main(String[] args)
    {

    }
}


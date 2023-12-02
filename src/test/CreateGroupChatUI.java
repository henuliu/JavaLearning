package test;

import javax.swing.*;
import java.awt.*;

public class CreateGroupChatUI extends JFrame
{

    public CreateGroupChatUI()
    {
        this.setBounds(500,200,400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null); // 关闭布局管理器

        JLabel titleLabel = new JLabel("创建群聊");
        titleLabel.setBounds(150, 10, 100, 30);
        this.add(titleLabel);

        JLabel groupIdLabel = new JLabel("群号:");
        groupIdLabel.setBounds(50, 50, 80, 30);
        this.add(groupIdLabel);

        JTextField groupAccountTextField = new JTextField();
        groupAccountTextField.setBounds(100, 50, 200, 30);
        this.add(groupAccountTextField);

        JLabel groupNameLabel = new JLabel("群名:");
        groupNameLabel.setBounds(50, 90, 80, 30);
        this.add(groupNameLabel);

        JTextField groupNameTextField = new JTextField();
        groupNameTextField.setBounds(100, 90, 200, 30);
        this.add(groupNameTextField);

        JButton createButton = new JButton("创建");
        createButton.setBounds(130, 140, 100, 30);
        this.add(createButton);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new CreateGroupChatUI();
    }
}

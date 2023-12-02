package swing.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrivateInfoUI extends JFrame
{

    private JLabel accountLabel;
    private JLabel nameLabel;
    private JLabel genderLabel;
    private JLabel ageLabel;
    private JLabel schoolLabel;
    private JLabel classLabel;
    private JLabel phoneLabel;
    private JLabel addressLabel;
    private JLabel signatureLabel;

    public PrivateInfoUI(String Account,String NickName)
    {
        this.setTitle("QQ个人资料");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(600,150,400, 500);

        // 添加背景图片
        JPanel backgroundPanel = new JPanel()
        {
            ImageIcon icon = new ImageIcon("images/2.png");
            Image image = icon.getImage();

            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setOpaque(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel avatarLabel = new JLabel();
        accountLabel = new JLabel("账号: "+Account);
        nameLabel = new JLabel("昵称: "+NickName);
        genderLabel = new JLabel("性别：男");
        ageLabel = new JLabel("年龄：20");
        schoolLabel = new JLabel("学校：河南大学");
        classLabel = new JLabel("班级：信息与计算科学");
        phoneLabel = new JLabel("手机号：13888888888");
        addressLabel = new JLabel("住址：开封龙亭区");
        signatureLabel = new JLabel("个性签名：11111");

        constraints.gridx = 0;
        constraints.gridy = 0;
        contentPanel.add(new JLabel(), constraints);
        constraints.gridx = 1;
        contentPanel.add(avatarLabel, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        contentPanel.add(accountLabel, constraints);
        constraints.gridy = 2;
        contentPanel.add(nameLabel, constraints);
        constraints.gridy = 3;
        contentPanel.add(genderLabel, constraints);
        constraints.gridy = 4;
        contentPanel.add(ageLabel, constraints);
        constraints.gridy = 5;
        contentPanel.add(schoolLabel, constraints);
        constraints.gridy = 6;
        contentPanel.add(classLabel, constraints);
        constraints.gridy = 7;
        contentPanel.add(phoneLabel, constraints);
        constraints.gridy = 8;
        contentPanel.add(addressLabel, constraints);
        constraints.gridy = 9;
        contentPanel.add(signatureLabel, constraints);

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        this.setContentPane(backgroundPanel);

        // 添加关闭按钮
        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);  // 点击关闭按钮时退出程序
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(closeButton);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 设置窗口图标
        ImageIcon icon = new ImageIcon("images/QQLogin.png");
        this.setIconImage(icon.getImage());

        this.setVisible(true);

    }

    public static void main(String[] args)
    {
        PrivateInfoUI privateInfoUI=new PrivateInfoUI("111","张三");
    }
}

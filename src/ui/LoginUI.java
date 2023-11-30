package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

//继承JFrame类，自定义一个创建窗口的子类
public class LoginUI extends JFrame
{
    public static String PassWord;
    public static String UserName;
    private JTextField usernameField;

    private String getUserName()
    {
        return this.usernameField.getText();
    }

    private String getPassword()
    {
        String passWord=new String(this.passwordField.getPassword());
        return passWord;
    }

    private String CheckUser(String UserName,String PassWord)
    {
        String UserAndPass=UserName+","+PassWord;
        return "{\"type\": \"login_check_msg\",\"content\": \""+UserAndPass+"\"}";
    }
    private JPasswordField passwordField;

    public LoginUI(String title, int Weight, int Height) throws IOException
    {

        // =============== 初始化窗口 ===============
        super(title);

        //关闭窗口时退出整个进程
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置窗口初始位置和大小
        this.setBounds(500, 200, Weight, Height);

        // 设置图标
        Image image = Toolkit.getDefaultToolkit().getImage("images/QQLogin.png");
        this.setIconImage(image);

        //创建一个面板作为容器
        JPanel panel = new JPanel();
        this.setContentPane(panel);

        panel.setLayout(null);

        // =============== 在panel中加入组件 ===============
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(65, 135, 80, 30);
        panel.add(userLabel);
        this.usernameField = new JTextField(20);
        this.usernameField.setBounds(145, 135, 165, 30);
        panel.add(this.usernameField);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(65, 175, 80, 30);
        panel.add(passwordLabel);
        this.passwordField = new JPasswordField(20);
        this.passwordField.setBounds(145, 175, 165, 30);
        panel.add(this.passwordField);

        // 加入登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(10, 80, 100, 25);
        JButton LoginButton = new JButton("登录");
        LoginButton.setBounds(230, 225, 100, 30);
        this.add(LoginButton);

        this.setVisible(true);

        //插入注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBounds(10, 80, 100, 25);
        JButton RegisterButton = new JButton("注册");
        RegisterButton.setBounds(115, 225, 100, 30);
        this.add(RegisterButton);

        //插入背景图片

        ImageIcon backgroundImage = new ImageIcon("images/background1.png");
        Image scaledBackgroundImage = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(scaledBackgroundImage);

        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 00, getWidth(), getHeight());
        getLayeredPane().add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

        ((JPanel) getContentPane()).setOpaque(false);


    }

    public static void main(String[] args) throws IOException
    {
        LoginUI loginUI=new LoginUI("test",450,330);

    }
}

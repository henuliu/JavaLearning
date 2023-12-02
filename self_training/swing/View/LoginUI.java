package swing.View;

import swing.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

//继承JFrame类，自定义一个创建窗口的子类
public class LoginUI extends JFrame
{
    public static RegistrationUI registrationUI;
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
        JLabel userLabel = new JLabel("账号:");
        userLabel.setBounds(65, 110, 80, 30);
        panel.add(userLabel);
        this.usernameField = new JTextField(20);
        this.usernameField.setBounds(145, 110, 165, 30);
        panel.add(this.usernameField);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(65, 150, 80, 30);
        panel.add(passwordLabel);
        this.passwordField = new JPasswordField(20);
        this.passwordField.setBounds(145, 150, 165, 30);
        panel.add(this.passwordField);

        // 加入登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(10, 60, 100, 25);
        JButton LoginButton = new JButton("登录");
        LoginButton.setBounds(230, 205, 100, 30);
        LoginButtonListener loginButtonListener=new LoginButtonListener();
        LoginButton.addActionListener(loginButtonListener);
        this.add(LoginButton);

        this.setVisible(true);

        //插入注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setBounds(10, 60, 100, 25);
        JButton RegisterButton = new JButton("注册");
        RegisterButton.setBounds(115, 205, 100, 30);
        RegisterButtonListener registerButtonListener=new RegisterButtonListener();
        RegisterButton.addActionListener(registerButtonListener);
        this.add(RegisterButton);

        //插入背景图片

        ImageIcon backgroundImage = new ImageIcon("images/3.png");
        Image scaledBackgroundImage = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledBackgroundIcon = new ImageIcon(scaledBackgroundImage);

        JLabel backgroundLabel = new JLabel(scaledBackgroundIcon);
        backgroundLabel.setBounds(0, 00, getWidth(), getHeight());
        getLayeredPane().add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));

        ((JPanel) getContentPane()).setOpaque(false);
    }

    // 登录按钮事件监听器
    private class LoginButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String UserName= LoginUI.this.getUserName();
            String PassWord= LoginUI.this.getPassword();
            if (!UserName.isEmpty() && !PassWord.isEmpty())
            {
                try
                {
                    System.out.println("客户端已经成功连接服务器");
                    // 客户端给服务端发送表单数据进行验证
                    PrintStream printStream = new PrintStream(Client.clientSocket.getOutputStream());
                    LoginUI.UserName=UserName;
                    LoginUI.PassWord=PassWord;
                    Random random=new Random();
                    int i=random.nextInt(1,14);
                    // 发送登录信息
                    String loginMsg="{\"type\": \"login_check_msg\",\"content\": \""+UserName+","+PassWord+"\"}";
                    printStream.println(loginMsg);
                    printStream.flush();
                    System.out.println("客户端已发送登录信息:"+loginMsg);
                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(null, "无法连接到服务器：服务器可能已关闭");
                    ex.printStackTrace();
                }
            }
            // 账号密码为空
            else
            {
                JOptionPane.showMessageDialog((Component) null, "账号或密码不能为空");
                System.out.println("账号或密码为空");
            }
        }
    }

    // 注册按钮事件监听器
    private class RegisterButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            LoginUI.registrationUI=new RegistrationUI();
        }
    }

    public static void main(String[] args) throws IOException
    {
        LoginUI loginUI=new LoginUI("test",450,350);
    }
}

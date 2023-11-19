package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

//继承JFrame类，自定义一个创建窗口的子类
public class LoginWindow extends JFrame
{
    private static Socket socket;
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
        return UserName+","+PassWord;
    }
    private JPasswordField passwordField;

    public LoginWindow(String title, int Weight, int Height) throws IOException
    {

        // =============== 初始化窗口 ===============
        super(title);

        // 创建Socket并连接服务器
        socket = new Socket("localhost", 8888);
        //关闭窗口时退出整个进程
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置窗口初始位置和大小
        this.setBounds(500, 200, Weight, Height);

        //创建一个面板作为容器
        JPanel panel = new JPanel();
        this.setContentPane(panel);

        panel.setLayout(null);

        // =============== 在panel中加入组件 ===============
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);
        this.usernameField = new JTextField(20);
        this.usernameField.setBounds(100, 20, 165, 25);
        panel.add(this.usernameField);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);
        this.passwordField = new JPasswordField(20);
        this.passwordField.setBounds(100, 50, 165, 25);
        panel.add(this.passwordField);

        // 加入登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(10, 80, 80, 25);
        JButton LoginButton = new JButton("登录");
        LoginButton.setBounds(120, 120, 80, 30);
        this.add(LoginButton);

        // =============== 连接服务器并验证数据 ===============
        // 给登录按钮添加事件监听器,在服务端验证账号密码是否正确
        LoginButtonListener loginButtonListener = new LoginButtonListener();
        LoginButton.addActionListener(loginButtonListener);
        this.setVisible(true);
    }

    private class LoginButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String UserName=LoginWindow.this.getUserName();
            String PassWord=LoginWindow.this.getPassword();
            if (!UserName.isEmpty() && !PassWord.isEmpty())
            {
                try
                {
                    Socket socket = new Socket("localhost", 8888);
                    LoginWindow.socket = socket;
                    System.out.println("客户端已经成功连接服务器");
                    // 客户端给服务端发送表单数据进行验证
                    PrintStream printStream = new PrintStream(LoginWindow.socket.getOutputStream());
                    printStream.println(CheckUser(UserName,PassWord));
                    printStream.flush();
                    System.out.println("客户端已发送数据");

                    // 客户端读取服务器发送过来的数据
                    BufferedReader bufferedReaderForServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String result=bufferedReaderForServer.readLine();
                    System.out.println("客户端成功接收验证数据");
                    if (result.equals("true"))
                    {
                        JOptionPane.showMessageDialog(null, "登录成功");
                        System.out.println("登录密码为：" + PassWord);
                        ChatRoomWindow main = new ChatRoomWindow();
                        main.setVisible(true);
                        // 关闭登录界面
                        LoginWindow.this.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog((Component) null, "用户名或密码错误!请重试");
                    }
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
}

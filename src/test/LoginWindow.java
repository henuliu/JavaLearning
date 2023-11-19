package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

//继承JFrame类，自定义一个创建窗口的子类
public class LoginWindow extends JFrame
{
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginWindow(String title, int Weight, int Height)
    {
        // =============== 初始化窗口 ===============
        super(title);

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

        // =============== 进行登录验证 ===============
        // 给登录按钮添加事件监听器,用MySQL验证账号密码是否正确
        LoginButtonListener loginButtonListener = new LoginButtonListener();
        LoginButton.addActionListener(loginButtonListener);
        this.setVisible(true);
    }

    private class LoginButtonListener implements ActionListener
    {
        private LoginButtonListener()
        {
        }

        public void actionPerformed(ActionEvent e)
        {
            // 连接MySQL
            String url = "jdbc:mysql://localhost:3306/user";
            String uid = "root";
            String passWord = "2754686220ljh";

            // 用MySQL查询语句进行验证
            ConnectMySQL LoginConn = new ConnectMySQL(url, uid, passWord);
            String UserName = LoginWindow.this.usernameField.getText();
            String PassWord = new String(LoginWindow.this.passwordField.getPassword());
            String cmd = "select * from login where UserName='" + UserName + "' and PassWord='" + PassWord + "'";
            ResultSet resultSet = null;
            try
            {
                if (!UserName.isEmpty() && !PassWord.isEmpty())
                {
                    try
                    {
                        resultSet = LoginConn.queryExecute(cmd);
                    }
                    catch (SQLException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                    try
                    {
                        if (resultSet.next())
                        {
                            JOptionPane.showMessageDialog((Component) null, "登录成功");
                            System.out.println("登录密码为：" + PassWord);
                            MainWindow main = new MainWindow();
                            main.setVisible(true);
                            LoginWindow.this.dispose();
                        } else
                        {
                            JOptionPane.showMessageDialog((Component) null, "用户名或密码错误!请重试");
                        }
                    }
                    catch (SQLException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog((Component) null, "账号或密码不能为空");
                    System.out.println("账号或密码为空");
                }
            }
            finally
            {
                if (resultSet != null)
                {
                    try
                    {
                        resultSet.close();
                    } catch (SQLException var17)
                    {
                        throw new RuntimeException(var17);
                    }
                }
            }
        }
    }
}

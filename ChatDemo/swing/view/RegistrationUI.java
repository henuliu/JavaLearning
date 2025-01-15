package swing.view;

import swing.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

public class RegistrationUI
{
    //昵称文本框,账号文本框,签名文本框
    private static JTextField nickNameText, accountText, signatureText;

    // 密码文本框,确认密码文本框
    private static JPasswordField passwordText, confirmPasswordText;

    public RegistrationUI()
    {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("用户注册");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 设置窗口图标
        ImageIcon icon = new ImageIcon("images/QQLogin.png");
        frame.setIconImage(icon.getImage());

        // 创建面板 JPanel 实例
        JPanel panel = new JPanel()
        {
            // 绘制背景图片
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Image bg = new ImageIcon("images/4.png").getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);

            }
        };
        frame.add(panel);

        // 调用方法设置组件
        placeComponents(panel);

        // 设置窗口居中显示
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        // 设置窗口可见
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel)
    {
        // 设置布局为 null
        panel.setLayout(null);


        // 创建用户名标签并设置位置
        JLabel userLabel = new JLabel("昵称:");
        userLabel.setBounds(55, 30, 80, 25);
        panel.add(userLabel);

        // 创建用户名文本框并设置位置
        nickNameText = new JTextField(20);
        nickNameText.setBounds(155, 30, 160, 25);
        panel.add(nickNameText);

        // 创建账号标签及文本框
        JLabel userAccount = new JLabel("账号：");
        userAccount.setBounds(55, 70, 80, 25);
        panel.add(userAccount);

        accountText = new JTextField(20);
        accountText.setBounds(155, 70, 160, 25);
        panel.add(accountText);


        // 创建密码标签并设置位置
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(55, 110, 80, 25);
        panel.add(passwordLabel);

        // 创建密码文本框并设置位置
        passwordText = new JPasswordField(20);
        passwordText.setBounds(155, 110, 160, 25);
        panel.add(passwordText);

        // 创建确认密码标签并设置位置
        JLabel confirmPasswordLabel = new JLabel("确认密码:");
        confirmPasswordLabel.setBounds(55, 150, 80, 25);
        panel.add(confirmPasswordLabel);

        // 创建确认密码文本框并设置位置
        confirmPasswordText = new JPasswordField(20);
        confirmPasswordText.setBounds(155, 150, 160, 25);
        panel.add(confirmPasswordText);

        // 创建签名标签并设置位置
        JLabel signatureLabel = new JLabel("签名:");
        signatureLabel.setBounds(55, 190, 80, 25);
        panel.add(signatureLabel);

        // 创建签名文本框并设置位置
        signatureText = new JTextField(20);
        signatureText.setBounds(155, 190, 160, 25);
        panel.add(signatureText);

        // 创建注册按钮并设置位置
        JButton registerButton = new JButton("注册");
        registerButton.setBounds(150, 240, 80, 25);
        panel.add(registerButton);

        // 给注册按钮添加事件监听器
        RegisterButtonListener registerButtonListener = new RegisterButtonListener();
        registerButton.addActionListener(registerButtonListener);
        // 创建重置按钮并设置位置
        JButton resetButton = new JButton("重置");
        resetButton.setBounds(245, 240, 80, 25);

        panel.add(resetButton);

        // 重置按钮点击事件处理
        resetButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 清空所有文本框
                nickNameText.setText("");
                passwordText.setText("");
                confirmPasswordText.setText("");
                signatureText.setText("");
                accountText.setText("");
            }
        });
    }

    // 注册按钮事件
    private class RegisterButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // 随机生成头像
            Random random = new Random();
            int i = random.nextInt(1, 15);
            String avatarPath = "images/head/head" + i + ".png";
            String registerMsg = "{\"type\": \"register_msg\",\"account\":\"" + accountText.getText() + "\",\"password\":" +
                    "\"" + new String(passwordText.getPassword()) + "\",\"NickName\": \"" + nickNameText.getText() +
                    "\",\"signature\": \"" + signatureText.getText() + "\",\"avatarPath\":\"" + avatarPath + "\"}";
            if (!accountText.getText().isEmpty() && !passwordText.getPassword().toString().isEmpty())
            {
                //使用 getPassword() 方法获取到密码字符数组时，这个数组可能会在内存中被修改。这意味着如果直接使用
                // == 运算符或者 equals() 方法进行比较，实际比较的是两个数组的引用，而不是数组中的内容
                if (Arrays.equals(passwordText.getPassword(), confirmPasswordText.getPassword()))
                {
                    // 客户端给服务端发送注册数据进行验证
                    PrintStream printStream = null;
                    try
                    {
                        printStream = new PrintStream(Client.clientSocket.getOutputStream());
                        printStream.println(registerMsg);
                        printStream.flush();
                        System.out.println("客户端已发送注册信息" + registerMsg);
                    } catch (IOException ex)
                    {
                        throw new RuntimeException(ex);
                    }

                } else
                {
                    System.out.println(passwordText.getPassword());
                    System.out.println(confirmPasswordText.getPassword());
                    JOptionPane.showMessageDialog(null, "两次输入的密码不一致,请重试");
                }
            } else
            {
                JOptionPane.showMessageDialog(null, "账号和密码信息不能为空");
            }
        }
    }

    public static void main(String[] args)
    {
        RegistrationUI registrationUI = new RegistrationUI();
    }
}

package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationApp {
    public static void main(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("用户注册");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口图标
        ImageIcon icon = new ImageIcon("images/QQLogin.png");
        frame.setIconImage(icon.getImage());

        // 创建面板 JPanel 实例
        JPanel panel = new JPanel() {
            // 绘制背景图片
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg = new ImageIcon("images/background2.png").getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);

            }
        };
        frame.add(panel);

        // 调用方法设置组件
        placeComponents(panel, frame);

        // 设置窗口居中显示
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        // 设置窗口可见
        frame.setVisible(true);
    }

        private static void placeComponents(JPanel panel, JFrame frame) {
            // 设置布局为 null
            panel.setLayout(null);


            // 创建用户名标签并设置位置
            JLabel userLabel = new JLabel("昵称:");
            userLabel.setBounds(55, 30, 80, 25);
            panel.add(userLabel);

            // 创建用户名文本框并设置位置
            JTextField userText = new JTextField(20);
            userText.setBounds(155, 30, 160, 25);
            panel.add(userText);

            // 创建账号标签及文本框
            JLabel userAccount = new JLabel("账号：");
            userAccount.setBounds(55, 70, 80, 25);
            panel.add(userAccount);

            JTextField accountText = new JTextField(20);
            accountText.setBounds(155, 70, 160, 25);
            panel.add(accountText);


            // 创建密码标签并设置位置
            JLabel passwordLabel = new JLabel("密码:");
            passwordLabel.setBounds(55, 110, 80, 25);
            panel.add(passwordLabel);

            // 创建密码文本框并设置位置
            JPasswordField passwordText = new JPasswordField(20);
            passwordText.setBounds(155, 110, 160, 25);
            panel.add(passwordText);

            // 创建确认密码标签并设置位置
            JLabel confirmPasswordLabel = new JLabel("确认密码:");
            confirmPasswordLabel.setBounds(55, 150, 80, 25);
            panel.add(confirmPasswordLabel);

            // 创建确认密码文本框并设置位置
            JPasswordField confirmPasswordText = new JPasswordField(20);
            confirmPasswordText.setBounds(155, 150, 160, 25);
            panel.add(confirmPasswordText);

            // 创建签名标签并设置位置
            JLabel signatureLabel = new JLabel("签名:");
            signatureLabel.setBounds(55, 190, 80, 25);
            panel.add(signatureLabel);

            // 创建签名文本框并设置位置
            JTextField signatureText = new JTextField(20);
            signatureText.setBounds(155, 190, 160, 25);
            panel.add(signatureText);

            // 创建注册按钮并设置位置
            JButton registerButton = new JButton("注册");
            registerButton.setBounds(150, 240, 80, 25);
            panel.add(registerButton);

            // 创建重置按钮并设置位置
            JButton resetButton = new JButton("重置");
            resetButton.setBounds(245, 240, 80, 25);
            panel.add(resetButton);

            // 注册按钮点击事件处理
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 获取用户名、密码和确认密码输入
                    String username = userText.getText();
                    String password = new String(passwordText.getPassword());
                    String confirmPassword = new String(confirmPasswordText.getPassword());

                    // 检查密码和确认密码是否匹配
                    if (!password.equals(confirmPassword)) {
                        // 弹出密码不匹配的错误消息框
                        JOptionPane.showMessageDialog(frame, "密码和确认密码不匹配，请重新输入！");
                        return;
                    }

                    // 获取签名输入
                    String signature = signatureText.getText();

                    // 进行注册逻辑处理，这里可以添加具体的注册逻辑

                    // 弹出注册成功消息框
                    JOptionPane.showMessageDialog(frame, "注册成功！欢迎加入。");

                    // 清空所有文本框
                    userText.setText("");
                    passwordText.setText("");
                    confirmPasswordText.setText("");
                    signatureText.setText("");
                }
            });

            // 重置按钮点击事件处理
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 清空所有文本框
                    userText.setText("");
                    passwordText.setText("");
                    confirmPasswordText.setText("");
                    signatureText.setText("");
                }
            });
    }
}

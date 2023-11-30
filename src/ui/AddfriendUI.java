package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddfriendUI {
    public static void main(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("添加好友/加入群聊");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口图标
        ImageIcon icon = new ImageIcon("images/QQLogin.png");
        frame.setIconImage(icon.getImage());

        // 创建面板 JPanel 实例，并设置为透明
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 加载背景图片
                ImageIcon imageIcon = new ImageIcon("images/background.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, this);
            }
        };
        panel.setOpaque(false); // 设置为透明
        frame.add(panel);


        // 调用方法设置组件
        placeComponents(panel, frame);

        // 设置窗口居中显示
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        // 设置窗口可见
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, JFrame frame) {
        // 设置布局为 null
        panel.setLayout(null);

        // 创建标签及文本框
        JLabel searchLabel = new JLabel("搜索账号或名称：");
        searchLabel.setBounds(50, 50, 150, 25);
        panel.add(searchLabel);

        JTextField searchField = new JTextField(20);
        searchField.setBounds(200, 50, 150, 25);
        panel.add(searchField);

        // 创建按钮
        JButton addFriendButton = new JButton("添加好友");
        addFriendButton.setBounds(90, 120, 100, 25);
        panel.add(addFriendButton);

        JButton joinGroupButton = new JButton("加入群聊");
        joinGroupButton.setBounds(210, 120, 100, 25);
        panel.add(joinGroupButton);

        // 添加按钮点击事件监听器
        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                // 在此处执行搜索账号或名称并添加好友的逻辑

                JOptionPane.showMessageDialog(frame, "执行添加好友操作：" + searchTerm, "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        joinGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                // 在此处执行搜索账号或名称并加入群聊的逻辑

                JOptionPane.showMessageDialog(frame, "执行加入群聊操作：" + searchTerm, "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}

package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchUI {
    public static void main(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("搜索好友/群聊");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建面板 JPanel 实例
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("images/background.jpg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
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
        // 创建搜索选项卡
        JPanel searchOptionPanel = new JPanel();
        searchOptionPanel.setLayout(new BoxLayout(searchOptionPanel, BoxLayout.X_AXIS));

        ButtonGroup searchOptionGroup = new ButtonGroup();

        JRadioButton friendSearchRadio = new JRadioButton("搜索好友", true);
        searchOptionGroup.add(friendSearchRadio);
        searchOptionPanel.add(friendSearchRadio);

        JRadioButton groupSearchRadio = new JRadioButton("搜索群聊");
        searchOptionGroup.add(groupSearchRadio);
        searchOptionPanel.add(groupSearchRadio);

        panel.add(searchOptionPanel, BorderLayout.NORTH);

        // 创建搜索面板
        JPanel searchPanel = new JPanel(new BorderLayout());
        panel.add(searchPanel, BorderLayout.CENTER);

        // 创建搜索结果面板
        JPanel resultPanel = new JPanel(new GridLayout(0, 3));
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        searchPanel.add(scrollPane, BorderLayout.CENTER);

        // 创建搜索按钮及文本框
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("搜索");

        JPanel searchControlPanel = new JPanel();
        searchControlPanel.setLayout(new BoxLayout(searchControlPanel, BoxLayout.X_AXIS));
        searchControlPanel.add(searchField);
        searchControlPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchControlPanel.add(searchButton);

        searchPanel.add(searchControlPanel, BorderLayout.NORTH);

        // 添加按钮点击事件监听器
        // 添加按钮点击事件监听器
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();

                // 在此处执行搜索逻辑，并将结果添加到resultPanel中
                // 每个结果块可以通过创建一个FriendPanel来实现，并将其添加到resultPanel中

                // 示例代码：生成随机的搜索结果
                for (int i = 0; i < 9; i++) {
                    String avatarPath = "images/avatar.png";  // 好友头像路径
                    String account = "Account" + i;  // 好友账号

                    FriendPanel friendPanel = new FriendPanel(avatarPath, account);
                    resultPanel.add(friendPanel);

                    // 获取加好友按钮并添加点击事件监听器
                    JButton addButton = friendPanel.getAddButton();
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 在此处处理点击加好友按钮的逻辑
                        }
                    });
                }

                scrollPane.getVerticalScrollBar().setValue(0); // 滚动到顶部
                frame.revalidate(); // 刷新界面
            }
        });

    }
}

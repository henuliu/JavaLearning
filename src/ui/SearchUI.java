package ui;

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
        JPanel panel = new JPanel(new BorderLayout());
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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();

                // 在此处执行搜索逻辑，并将结果添加到resultPanel中
                // 每个结果块可以通过创建一个JPanel来实现，并将其添加到resultPanel中

                // 示例代码：生成随机的搜索结果
                for (int i = 0; i < 9; i++) {
                    JPanel resultBlock = new JPanel();
                    resultBlock.setLayout(new BoxLayout(resultBlock, BoxLayout.Y_AXIS));
                    resultBlock.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                    JLabel avatarLabel = new JLabel("头像");
                    avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    resultBlock.add(avatarLabel);

                    JLabel nicknameLabel = new JLabel("昵称：" + "Nickname" + i);
                    nicknameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    resultBlock.add(nicknameLabel);

                    JLabel accountLabel = new JLabel("账号：" + "Account" + i);
                    accountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    resultBlock.add(accountLabel);

                    resultPanel.add(resultBlock);
                }

                scrollPane.getVerticalScrollBar().setValue(0); // 滚动到顶部
            }
        });
    }
}

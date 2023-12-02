package testView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchFriendApp extends JFrame {

    private JTextField searchField;
    private JTextArea resultArea; // 用于显示搜索结果
    private JButton prevButton; // 上一页按钮
    private JButton nextButton; // 下一页按钮
    private int currentPage = 1; // 当前页数

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SearchFriendApp frame = new SearchFriendApp();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Search Friend");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // 将窗口居中显示

        // 设置窗口图标
        ImageIcon icon = new ImageIcon("images/QQLogin.png");
        frame.setIconImage(icon.getImage());

        // 创建"好友"和"群聊"按钮
        JButton friendButton = new JButton("好友");
        friendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理点击"好友"按钮的逻辑
            }
        });

        JButton groupChatButton = new JButton("群聊");
        groupChatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理点击"群聊"按钮的逻辑
            }
        });

        // 创建搜索输入框和按钮
        frame.searchField = new JTextField();
        frame.searchField.setColumns(15);
        JButton searchButton = new JButton("搜索");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.searchFriend();
            }
        });

        // 添加背景图片
        ImageIcon backgroundImage = new ImageIcon("images/2.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);

        // 创建显示搜索结果的文本区域
        frame.resultArea = new JTextArea();
        frame.resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(frame.resultArea);
        scrollPane.setPreferredSize(new Dimension(500, 220)); // 调整滚动条的大小

        // 创建上一页和下一页按钮
        frame.prevButton = new JButton("上一页");
        frame.prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.showPrevPage();
            }
        });
        frame.nextButton = new JButton("下一页");
        frame.nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.showNextPage();
            }
        });

        // 创建布局
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false); // 设置为透明，让背景图片显示出来
        buttonPanel.add(friendButton);
        buttonPanel.add(groupChatButton);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setOpaque(false); // 设置为透明，让背景图片显示出来
        searchPanel.add(frame.searchField);
        searchPanel.add(searchButton);

        JPanel pagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pagePanel.setOpaque(false); // 设置为透明，让背景图片显示出来
        pagePanel.add(frame.prevButton);
        pagePanel.add(frame.nextButton);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setOpaque(false); // 设置为透明，让背景图片显示出来
        resultPanel.add(scrollPane, BorderLayout.CENTER); // 将滚动条放在中央
        resultPanel.add(pagePanel, BorderLayout.SOUTH); // 将上一页和下一页按钮放在南部

        // 设置布局管理器
        frame.setLayout(new BorderLayout());
        frame.setContentPane(backgroundLabel);
        frame.getContentPane().setLayout(new BorderLayout()); // 设置内容面板的布局管理器
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(searchPanel, BorderLayout.CENTER);
        frame.getContentPane().add(resultPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void searchFriend() {
        String searchText = searchField.getText();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入搜索内容！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 处理搜索好友的逻辑
        // 更新搜索结果到resultArea
    }

    private void showPrevPage() {
        if (currentPage == 1) {
            JOptionPane.showMessageDialog(this, "当前已经是第一页！", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        currentPage--;
        // 更新搜索结果到resultArea
    }

    private void showNextPage() {
        if (searchField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "请先进行搜索！", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        currentPage++;
        updatePageLabel();
        // 更新搜索结果到resultArea
    }

    private void updatePageLabel() {
    }
}

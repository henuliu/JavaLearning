package test;

import javax.swing.*;
import java.awt.*;

public class AddFriendVerificationUI extends JFrame {
    private JTextField nicknameField;
    private JTextArea messageArea;

    public AddFriendVerificationUI() {
        setTitle("添加好友");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10)); // 将布局改为3行2列，用来添加好友分组下拉框
        mainPanel.add(formPanel, BorderLayout.CENTER);

        JLabel nicknameLabel = new JLabel("昵称：");
        nicknameField = new JTextField();
        formPanel.add(nicknameLabel);
        formPanel.add(nicknameField);

        JLabel groupLabel = new JLabel("好友分组：");
        String[] groupOptions = {"家人", "朋友", "同事"}; // 下拉框选项
        JComboBox<String> groupComboBox = new JComboBox<>(groupOptions); // 创建下拉框并添加选项
        formPanel.add(groupLabel);
        formPanel.add(groupComboBox);

        JLabel messageLabel = new JLabel("验证消息：");
        messageArea = new JTextArea();
        messageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(messageArea);
        formPanel.add(messageLabel);
        formPanel.add(scrollPane);

        JButton sendButton = new JButton("发送验证");
        sendButton.addActionListener(e -> {
            String nickname = nicknameField.getText();
            String group = (String) groupComboBox.getSelectedItem(); // 获取选择的分组
            String message = messageArea.getText();
            // 在这里处理发送验证的逻辑
            JOptionPane.showMessageDialog(AddFriendVerificationUI.this, "已发送好友验证");
        });
        mainPanel.add(sendButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            AddFriendVerificationUI ui = new AddFriendVerificationUI();
            ui.setVisible(true);
        });
    }
}

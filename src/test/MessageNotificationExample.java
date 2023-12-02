package test;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MessageNotificationExample {
    public static void main(String[] args) {
        // 设置JOptionPane的默认字体大小为18
        UIManager.put("OptionPane.messageFont", new Font("宋体", Font.PLAIN, 18));

        // 在这里检查是否有新消息，如果有就显示消息提醒
        boolean hasNewMessage = true;
        if (hasNewMessage) {
            JDialog dialog = new JDialog();
            dialog.setUndecorated(true); // 去除窗口边框（可选）
            JLabel label = new JLabel("您有一条新消息");
            label.setFont(new Font("宋体", Font.BOLD, 20)); // 设置标签字体
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(dialog, "这是一条重要消息", "消息详情", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            dialog.add(label);
            dialog.pack();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();
            int dialogWidth = dialog.getWidth();
            int dialogHeight = dialog.getHeight();
            int x = screenWidth - dialogWidth - 20; // 可根据实际需要微调位置
            int y = screenHeight - dialogHeight - 40; // 可根据实际需要微调位置
            dialog.setLocation(x, y);

            dialog.setVisible(true);
        }
    }
}

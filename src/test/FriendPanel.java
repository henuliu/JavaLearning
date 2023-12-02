package test;

import javax.swing.*;
import java.awt.*;

public class FriendPanel extends JPanel {
    private JLabel avatarLabel;
    private JLabel accountLabel;
    private JButton addButton;

    public FriendPanel(String avatarPath, String account) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        avatarLabel = new JLabel(new ImageIcon(avatarPath));
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(avatarLabel);

        accountLabel = new JLabel("账号：" + account);
        accountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(accountLabel);

        addButton = new JButton("加好友");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(addButton);
    }

    public JButton getAddButton() {
        return addButton;
    }
}

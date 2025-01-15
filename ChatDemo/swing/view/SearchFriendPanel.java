package swing.view;

import swing.utils.ModifyIcon;

import javax.swing.*;
import java.awt.*;

public class SearchFriendPanel extends JPanel
{
    // 头像
    private JLabel avatarLabel;
    // QQ号
    private JLabel accountLabel;
    // 昵称
    private JLabel nickNameLabel;
    // 添加好友
    private JLabel addFriendLabel;

    public SearchFriendPanel(String avatarPath, String account, String nickname)
    {
        setLayout(new GridBagLayout());

        // 创建头像标签并添加到面板
        ImageIcon imageIcon = new ImageIcon(avatarPath);
        Image image = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        avatarLabel = new JLabel(new ImageIcon(image));
        GridBagConstraints gbcAvatar = new GridBagConstraints();
        gbcAvatar.gridx = 0;
        gbcAvatar.gridy = 0;
        gbcAvatar.gridheight = 2;
        gbcAvatar.fill = GridBagConstraints.BOTH;
        gbcAvatar.weightx = 0.3;
        add(avatarLabel, gbcAvatar);

        // 创建信息面板并添加到面板
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        accountLabel = new JLabel("账号：" + account);
        nickNameLabel = new JLabel("昵称：" + nickname);
        addFriendLabel = new JLabel();
        ImageIcon addIcon = new ImageIcon("images/addFriend5.png");
        ModifyIcon MI = new ModifyIcon();
        ImageIcon modifiedAddIcon = MI.modify(addIcon, 40, 40);
        addFriendLabel.setPreferredSize(new Dimension(40, 40)); // 设置宽高

        addFriendLabel.setIcon(modifiedAddIcon);


        infoPanel.add(accountLabel);
        infoPanel.add(nickNameLabel);
        infoPanel.add(addFriendLabel);
        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 0;
        gbcInfo.gridheight = 2;
        gbcInfo.fill = GridBagConstraints.BOTH;
        gbcInfo.weightx = 0.7;
        add(infoPanel, gbcInfo);
    }

    public JLabel getAddFriendLabel()
    {
        return addFriendLabel;
    }
}
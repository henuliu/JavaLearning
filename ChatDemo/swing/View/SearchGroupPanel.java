package swing.View;

import swing.utility.ModifyIcon;

import javax.swing.*;
import java.awt.*;

public class SearchGroupPanel extends JPanel
{
    // 群聊头像
    private JLabel groupAvatarLabel;
    // 群号
    private JLabel groupAccountLabel;
    // 群名
    private JLabel groupNameLabel;
    // 加入群聊
    private JLabel joinGroupLabel;

    public SearchGroupPanel(String avatarPath, String account, String nickname)
    {
        setLayout(new GridBagLayout());

        // 创建头像标签并添加到面板
        ImageIcon imageIcon = new ImageIcon(avatarPath);
        Image image = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        groupAvatarLabel = new JLabel(new ImageIcon(image));
        GridBagConstraints gbcAvatar = new GridBagConstraints();
        gbcAvatar.gridx = 0;
        gbcAvatar.gridy = 0;
        gbcAvatar.gridheight = 2;
        gbcAvatar.fill = GridBagConstraints.BOTH;
        gbcAvatar.weightx = 0.3;
        add(groupAvatarLabel, gbcAvatar);

        // 创建信息面板并添加到面板
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        groupAccountLabel = new JLabel("群号：" + account);
        groupNameLabel = new JLabel("群名：" + nickname);
        joinGroupLabel = new JLabel();
        ImageIcon addIcon=new ImageIcon("images/addGroup2.png");
        ModifyIcon MI=new ModifyIcon();
        ImageIcon modifiedAddIcon=MI.modify(addIcon,40,40);
        joinGroupLabel.setSize(40,40);

        joinGroupLabel.setIcon(modifiedAddIcon);

        infoPanel.add(groupAccountLabel);
        infoPanel.add(groupNameLabel);
        infoPanel.add(joinGroupLabel);
        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 0;
        gbcInfo.gridheight = 2;
        gbcInfo.fill = GridBagConstraints.BOTH;
        gbcInfo.weightx = 0.7;
        add(infoPanel, gbcInfo);
    }

    public JLabel getJoinGroupLabel()
    {
        return joinGroupLabel;
    }
}

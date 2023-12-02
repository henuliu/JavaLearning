package test;

import testView.PrivateChatUI;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class QQFriendList extends JFrame
{

    private static String UserName;
    public QQFriendList(String UserName)
    {
        setTitle("QQ好友列表");
        setBounds(500, 150, 300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建根节点
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("我的好友");

        // 创建好友节点
        for (int i = 1; i <= 10; i++)
        {
            Friend friend = new Friend(i, "好友" + i, "images/QQLogin.png","zs");
            DefaultMutableTreeNode friendNode = new DefaultMutableTreeNode(friend);
            root.add(friendNode);
        }

        // 创建树
        JTree tree = new JTree(root);
        tree.setCellRenderer(new FriendListRenderer());
        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);

        // 添加树节点选择监听器
        tree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null && selectedNode.getUserObject() instanceof Friend)
                {
                    Friend friend = (Friend) selectedNode.getUserObject();
                    openChatWindow(friend);
                }
            }
        });
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new QQFriendList("zs");
    }

    private void openChatWindow(Friend friend)
    {
        // 在这里实现打开私聊窗口的逻辑
        // 可以创建一个新的窗口对象，并将好友信息传递给该窗口
        // 这里只是示例，展示弹出一个消息框来显示好友信息
        //JOptionPane.showMessageDialog(this, "开始与好友 " + friend.getNickname() + " 进行私聊");
        PrivateChatUI privateChatUI = new PrivateChatUI(UserName, friend.friendName,"10001","10002");
    }
}

// 定义好友类
class Friend
{
    public String friendName;
    public int qqNumber;
    public String nickname;
    public String avatarUrl;

    public Friend(int qqNumber, String nickname, String avatarUrl,String friendName)
    {
        this.qqNumber = qqNumber;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.friendName=friendName;
    }

    public int getQqNumber()
    {
        return qqNumber;
    }

    public String getNickname()
    {
        return nickname;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }
}

// 定义好友列表渲染器
class FriendListRenderer implements TreeCellRenderer
{

    private JLabel label;

    public FriendListRenderer()
    {
        label = new JLabel();
        label.setOpaque(true);
        label.setIconTextGap(5); // 设置文本与图标之间的距离
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object userObject = node.getUserObject();

        if (userObject instanceof Friend)
        {
            Friend friend = (Friend) userObject;
            label.setText(friend.getNickname());
            label.setIcon(new ImageIcon(friend.getAvatarUrl()));
        }
        else
        {
            label.setText(userObject.toString());
            if (node.getChildCount() > 0)
            {
                if (expanded)
                {
                    label.setIcon(new ImageIcon("images/open.png"));
                } else
                {
                    label.setIcon(new ImageIcon("images/closed.png"));
                }
            } else
            {
                label.setIcon(new ImageIcon("images/leaf.png"));
            }
        }

        if (selected)
        {
            Color selectionBackground = UIManager.getColor("Tree.selectionBackground");
            Color selectionForeground = UIManager.getColor("Tree.selectionForeground");
            label.setBackground(selectionBackground);
            label.setForeground(selectionForeground);
        } else
        {
            Color background = UIManager.getColor("Tree.textBackground");
            Color foreground = UIManager.getColor("Tree.textForeground");
            label.setBackground(background);
            label.setForeground(foreground);
        }

        return label;
    }

}

package swing.View;

import swing.Client;
import swing.Entity.Friend;
import swing.Entity.Group;
import swing.utility.ModifyIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class MainUI extends JFrame
{
    private JScrollPane jspMyBuddy,jspNew,jspGroup;

    public static JTree friendTree,groupTree;
    //好友分组
    public static DefaultMutableTreeNode mybuddyRoot;
    //朋友分组
    public static DefaultMutableTreeNode friendRoot;
    // 同学分组
    public static DefaultMutableTreeNode classMateRoot;

    // 群聊分组
    public static DefaultMutableTreeNode groupRoot;

    public MainUI(String account,String nickName,String avatarPath)
    {
        // 设置窗口图标
        ImageIcon icon = new ImageIcon("images/head/QQ.png");
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.setIconImage(image);
        setBounds(500, 150, 300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //=====================设置主页个人信息=====================

        // 创建顶部面板
        // 创建顶部面板
        JPanel topPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                try
                {
                    Image image = ImageIO.read(new File("images/background2.png"));
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        };

        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(getWidth(), 100));


        // 添加账号昵称标签
        JLabel accountLabel = new JLabel(nickName+"("+account+")");
        accountLabel.setBounds(85, 20, 100, 20);
        topPanel.add(accountLabel);

        //=====================设置头像=====================
        // 添加头像图标
        // 加载头像图片，并获取Image对象
        ImageIcon headIcon = new ImageIcon(avatarPath);
        Client.avatarPath=avatarPath;
        Client.nickName =nickName;
        // 将缩放后的Image对象转换为ImageIcon对象，并设置到JLabel上
        JLabel avatarLabel = new JLabel(modifyIcon(headIcon, 60, 60));
        avatarLabel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                PrivateInfoUI privateInfoUI=new PrivateInfoUI(account,nickName);
            }
        });
        avatarLabel.setBounds(0, 0, 80, 80);
        topPanel.add(avatarLabel);

        //=====================添加其他图标=====================
        // 添加邮箱图标
        ImageIcon icon1 = new ImageIcon("images/letter2.png");
        ImageIcon modifyIcon1 = modifyIcon(icon1, 20, 20);
        JLabel iconLabel1 = new JLabel(modifyIcon1);
        iconLabel1.setBounds(80, 40, 30, 30);
        topPanel.add(iconLabel1);

        // 添加QQ空间图标
        ImageIcon icon2 = new ImageIcon("images/space2.png");
        ImageIcon modifyIcon2 = modifyIcon(icon2, 25, 25);
        JLabel iconLabel2 = new JLabel(modifyIcon2);
        iconLabel2.setBounds(120, 40, 30, 30);
        topPanel.add(iconLabel2);

        // 添加天气图标
        ImageIcon icon3 = new ImageIcon("images/weather2.png");
        ImageIcon modifyIcon3 = modifyIcon(icon3, 70, 70);
        JLabel iconLabel3 = new JLabel(modifyIcon3);
        iconLabel3.setBounds(180, 10, 70, 70);
        topPanel.add(iconLabel3);
        // 将顶部面板添加到主窗口
        this.add(topPanel, BorderLayout.NORTH);

        //=====================选项卡面板=====================
        JTabbedPane tabbedPane = new JTabbedPane();
        this.add(tabbedPane, BorderLayout.CENTER);

        //=====================好友列表=====================
        //============分组=============
        // 创建根节点
        DefaultMutableTreeNode Root = new DefaultMutableTreeNode("好友");
        // 创建我的好友分组
        mybuddyRoot = new DefaultMutableTreeNode("我的好友");
        // 创建朋友分组
        friendRoot = new DefaultMutableTreeNode("朋友");
        // 创建同学分组
        classMateRoot=new DefaultMutableTreeNode("同学");

        Root.add(mybuddyRoot);
        Root.add(friendRoot);
        Root.add(classMateRoot);

        // 创建好友树列表
        friendTree = new JTree(Root);

        // 给根节点添加渲染器
        friendTree.setCellRenderer(new FriendListRenderer());

        jspMyBuddy = new JScrollPane(friendTree);
        jspMyBuddy.setBorder(null);
        jspMyBuddy.setBounds(0, 0, 300, 300);

        tabbedPane.add("联系人", jspMyBuddy);

        jspNew = new JScrollPane();
        tabbedPane.add("最近联系人", jspNew);

        // 添加好友树节点选择监听器
        friendTree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) friendTree.getLastSelectedPathComponent();
                if (selectedNode != null && selectedNode.getUserObject() instanceof Friend)
                {
                    Friend friend = (Friend) selectedNode.getUserObject();
                    try
                    {
                        openPrivateWindow(friend);
                    } catch (IOException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        //=====================群聊列表=====================
        // 创建群聊分组
        groupRoot = new DefaultMutableTreeNode("群聊");

        groupTree=new JTree(groupRoot);
        groupTree.setCellRenderer(new GroupListRenderer());

        jspGroup = new JScrollPane(groupTree);
        jspGroup.setBorder(null);
        jspGroup.setBounds(0, 0, 300, 300);

        tabbedPane.add("群聊", jspGroup);

        // 添加群聊树节点选择监听器
        groupTree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) groupTree.getLastSelectedPathComponent();
                if (selectedNode != null && selectedNode.getUserObject() instanceof Group)
                {
                    Group group = (Group) selectedNode.getUserObject();
                    try
                    {
                        openGroupWindow(group);
                    } catch (IOException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

//        // 创建群聊节点
//        Group group = new Group("1001", "群聊", "images/grouphead/grouphead1.png");
//        DefaultMutableTreeNode groupNode1 = new DefaultMutableTreeNode(group);
//        groupRoot.add(groupNode1);

        //=====================搜索好友和群聊=====================
        // 创建底部窗口所在的面板
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setPreferredSize(new Dimension(getWidth(), 50));
        bottomPanel.setBackground(Color.WHITE);

        // 添加搜索按钮组件
        JButton searchButton = new JButton();
        searchButton.setPreferredSize(new Dimension(25, 25));
        ImageIcon searchIcon = new ImageIcon("images/search3.jpg");
        searchButton.setIcon(searchIcon);

        // 添加搜索按钮事件监听器
        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client.searchUI=new SearchUI();
            }
        });
        bottomPanel.add(searchButton);

        // 创建群聊按钮
        JButton createGroupButton = new JButton();
        createGroupButton.setPreferredSize(new Dimension(25, 25));
        ImageIcon groupIcon = new ImageIcon("images/addGroup2.png");
        ModifyIcon modifyIcon=new ModifyIcon();
        groupIcon=modifyIcon.modify(groupIcon,22,22);
        createGroupButton.setIcon(groupIcon);
        createGroupButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CreateGroupUI createGroupUI=new CreateGroupUI();
            }
        });
        bottomPanel.add(createGroupButton);

        // 将底部面板添加到主窗口中
        this.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args)
    {
        new MainUI("10001","zs","images/head/head1.png");

    }

    private ImageIcon modifyIcon(ImageIcon icon, int width, int height)
    {
        Image img = icon.getImage();
        // 对Image对象进行平滑缩放,调整宽和高
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // 将缩放后的Image对象转换为ImageIcon对象
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        return scaledIcon;
    }

    private void openPrivateWindow(Friend friend) throws IOException
    {
        // 在这里实现打开私聊窗口的逻辑
        // 可以创建一个新的窗口对象，并将好友信息传递给该窗口
        PrivateChatUI privateChatUI = new PrivateChatUI(Client.UserName, friend.nickName,Client.UserName, friend.getQQNumber());
        Client.friendName2PrivateChatUI.put(friend.friendName, privateChatUI);
    }
    private void openGroupWindow(Group group) throws IOException
    {
        // 在这里实现打开群聊窗口的逻辑
        // 可以创建一个新的窗口对象
        GroupChatUI groupUI=new GroupChatUI(group.groupAccount,group.groupName,group.groupAvatar);
        Client.groupName2GroupChatUI.put(group.groupAccount,groupUI);

        // 发送更新群成员信息
        String message="{\"type\":\"updateGroup_msg\",\"model\":\"groupChat\",\"groupAccount\":\""+group.groupAccount+"\"}";
        PrintStream printStream=new PrintStream(Client.clientSocket.getOutputStream());
        printStream.println(message);
        printStream.flush();
        System.out.println("客户端发送更新群成员信息:"+message);
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
            label.setText(friend.getNickName()+"("+friend.getQQNumber()+")");
            ImageIcon friendHead = new ImageIcon(friend.getAvatarPath());

            Image img = friendHead.getImage();

            // 定义新的宽度和高度
            int width = 30;
            int height = 30;

            // 对Image对象进行平滑缩放
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // 将缩放后的Image对象转换为ImageIcon对象，并设置到JLabel上
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            label.setIcon(scaledIcon);
        } else
        {
            label.setText(userObject.toString());
            if (node.getChildCount() > 0)
            {
                if (expanded)
                {
                    label.setIcon(new ImageIcon("images/open.jpg"));
                } else
                {
                    label.setIcon(new ImageIcon("images/close.jpg"));
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
// 定义群聊列表渲染器
class GroupListRenderer implements TreeCellRenderer
{

    private JLabel label;

    public GroupListRenderer()
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

        if (userObject instanceof Group)
        {
            Group group = (Group) userObject;
            label.setText(group.groupName+"("+group.getGroupAccount()+")");
            ImageIcon groupHead = new ImageIcon(group.groupAvatar);

            Image img = groupHead.getImage();

            // 定义新的宽度和高度
            int width = 30;
            int height = 30;

            // 对Image对象进行平滑缩放
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // 将缩放后的Image对象转换为ImageIcon对象，并设置到JLabel上
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            label.setIcon(scaledIcon);
        } else
        {
            label.setText(userObject.toString());
            if (node.getChildCount() > 0)
            {
                if (expanded)
                {
                    label.setIcon(new ImageIcon("images/open.jpg"));
                } else
                {
                    label.setIcon(new ImageIcon("images/close.jpg"));
                }
            }
            else
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

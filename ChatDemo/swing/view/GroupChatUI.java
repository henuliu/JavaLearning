package swing.view;

import swing.Client;
import swing.Entity.Friend;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

public class GroupChatUI extends JFrame
{
    private JScrollPane jspMyBuddy;
    public static JTree memberTree;

    //群分组
    public static DefaultMutableTreeNode myGroupRoot;

    // 群成员节点
    public static DefaultMutableTreeNode memberNode;
    // 消息框
    public static JTextArea chatArea;

    public static JTextArea getChatArea()
    {
        return chatArea;
    }

    // 发送框
    public static JTextArea sendArea;


    // 添加PrintStream成员变量
    public static PrintStream printStream;

    public static JButton SendMsgButton;

    public GroupChatUI(String groupAccount, String groupName, String groupAvatar)
    {
        // 设置窗口图标
        ImageIcon icon = new ImageIcon("images/head/QQ.png");
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        this.setIconImage(image);
        // 初始化聊天界面
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(550, 100, 700, 500);

        ///=============== 加入聊天框 ===============
        chatArea = new JTextArea();
        chatArea.setEditable(false); // 设置不可编辑

        JScrollPane scrollPane = new JScrollPane(chatArea); // 添加滚动条
        scrollPane.setBounds(200, 0, 500, 300);
        this.add(scrollPane);

        // 设置字体和大小
        Font font = new Font("微软雅黑", Font.PLAIN, 12);
        chatArea.setFont(font);

        //设置自动换行
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        //=============== 加入发送框 ===============
        sendArea = new JTextArea();
        // 设置字体和大小
        sendArea.setFont(font);

        //设置自动换行
        sendArea.setLineWrap(true);
        sendArea.setWrapStyleWord(true);
        JScrollPane sendScroll = new JScrollPane(sendArea); // 添加滚动条
        sendScroll.setBounds(200, 300, 450, 100);
        this.add(sendScroll);

        // 加入发送消息按钮
        SendMsgButton = new JButton("发送");
        SendMsgButton.setBounds(580, 420, 80, 25);

        // 给发送按钮添加监听器
        SendMsgButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 遍历群成员节点，拼接所有成员的账号
                StringBuilder groupMember = new StringBuilder();
                for (int i = 0; i < myGroupRoot.getChildCount(); i++)
                {
                    DefaultMutableTreeNode memberNode = (DefaultMutableTreeNode) myGroupRoot.getChildAt(i);
                    if (memberNode.getUserObject() instanceof Friend)
                    {
                        Friend friend = (Friend) memberNode.getUserObject();
                        groupMember.append(friend.getQQNumber()).append(",");
                    }
                }
                if (groupMember.length() > 0)
                {
                    groupMember.deleteCharAt(groupMember.length() - 1);
                }
                System.out.println(groupMember);
                // 点击发送按钮时执行的操作
                String msg = sendArea.getText(); // 获取发送框的文本
                String msg_Json = "{\"type\": \"group_msg\", \"from\": \"" + Client.UserName + "\", \"to\": \"all\", " +
                        "\"content\": \"" + msg + "\",\"groupMember\":\"" + groupMember + "\"}";

                // 如果发送框不为空
                if (!msg.isEmpty())
                {
                    try
                    {
                        sendMessage(msg_Json);
                        System.out.println("客户端发送了群聊消息:" + msg_Json);
                        sendArea.setText(""); // 清空发送框
                    } catch (IOException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        this.add(SendMsgButton);
        //==================创建群成员列表==================
        myGroupRoot = new DefaultMutableTreeNode("群成员");

        // 创建树列表
        memberTree = new JTree(myGroupRoot);

        // 给根节点添加渲染器
        memberTree.setCellRenderer(new FriendListRenderer1());

        jspMyBuddy = new JScrollPane(memberTree);
        jspMyBuddy.setBorder(null);
        jspMyBuddy.setBounds(0, 0, 180, 600);
        // 设置面板的边界线
        jspMyBuddy.setBorder(BorderFactory.createLineBorder(Color.RED));
        // 添加好友树节点选择监听器
        memberTree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) memberTree.getLastSelectedPathComponent();
                if (selectedNode != null && selectedNode.getUserObject() instanceof Friend)
                {
                    Friend friend = (Friend) selectedNode.getUserObject();
                    openChatWindow(friend);
                }
            }
        });

        //============添加群成员节点=============
        // 创建群成员节点
//        Friend friend1 = new Friend("1001", "张三", "images/head/head1.png");
//        DefaultMutableTreeNode friendNode1 = new DefaultMutableTreeNode(friend1);

        // 添加群成员节点
//        for (int i = 1; i < 14; i++)
//        {
//            Friend friend = new Friend("10000"+i, "好友"+i, "images/head/head"+i+".png");
//            memberNode = new DefaultMutableTreeNode(friend);
//            myGroupRoot.add(memberNode);
//        }
        // 创建一个面板，并将树添加到面板中
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(jspMyBuddy);
        panel.setBounds(0, 0, 200, 400);
        panel.setPreferredSize(new Dimension(200, 400));
        panel.setMinimumSize(new Dimension(200, 400));

        // 将面板添加到窗口中
        this.add(panel);

        setVisible(true);
    }

    public static void main(String[] args)
    {
        new GroupChatUI("10001", "群聊", "images/grouphead/grouphead1.png");
    }

    private void openChatWindow(Friend friend)
    {
        // 在这里实现打开私聊窗口的逻辑
        // 可以创建一个新的窗口对象，并将好友信息传递给该窗口
        PrivateChatUI privateChatUI = new PrivateChatUI(Client.UserName, friend.nickName, Client.UserName, friend.getQQNumber());
        Client.friendName2PrivateChatUI.put(friend.friendName, privateChatUI);
    }

    // 发送消息
    private void sendMessage(String message) throws IOException
    {
        // 初始化PrintStream
        GroupChatUI.printStream = new PrintStream(Client.clientSocket.getOutputStream());
        GroupChatUI.printStream.println(message);
        GroupChatUI.printStream.flush();
        System.out.println("客户端成功发送消息:" + message);
    }
}

// 定义好友列表渲染器
class FriendListRenderer1 implements TreeCellRenderer
{

    private JLabel label;

    public FriendListRenderer1()
    {
        label = new JLabel();
        label.setOpaque(true);
        label.setSize(50, 20);
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
            label.setText(friend.getNickName() + "(" + friend.getQQNumber() + ")");
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

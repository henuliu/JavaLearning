package testView;

import swing.Client;
import swing.Entity.Friend;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class testGroupChatUI extends JFrame
{
    public static JTree friendTree;
    private JScrollPane jspMyBuddy;
    // 消息框
    public static JTextArea chatArea;

    public static JTextArea getChatArea()
    {
        return chatArea;
    }
    // 发送框
    public static JTextArea sendArea;

    //好友列表panel
    public static JPanel FriendsPanel;

    // 添加PrintStream成员变量
    public static PrintStream printStream;

    public static JButton SendMsgButton;
//    private void openPriateChat(Friend friend)
//    {
//        // 在这里实现打开私聊窗口的逻辑
//        // 可以创建一个新的窗口对象，并将好友信息传递给该窗口
//        PrivateChatUI privateChatUI = new PrivateChatUI(Client.UserName, friend.nickName,Client.UserName, friend.getQQNumber());
//        Client.friendName2PrivateChatUI.put(friend.friendName, privateChatUI);
//    }
    public void Init(ArrayList<String> selfAndOtherOnlineNames)
    {
        this.setTitle(selfAndOtherOnlineNames.get(0) + "的聊天室");

        // 初始化聊天界面
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(550, 100, 700, 500);

        //=============== 加入聊天框 ===============
        chatArea = new JTextArea();
        chatArea.setEditable(false); // 设置不可编辑

        JScrollPane scrollPane = new JScrollPane(chatArea); // 添加滚动条
        scrollPane.setBounds(190, 0, 500, 300);
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
        sendScroll.setBounds(190, 320, 480, 100);
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
                // 点击发送按钮时执行的操作
                String msg = sendArea.getText(); // 获取发送框的文本
                String msg_Json = "{\"type\": \"group_msg\", \"from\": \""+ Client.UserName+"\", \"to\": \"all\", \"content\": \"" + msg + "\"}";

                // 如果发送框不为空
                if (!msg.isEmpty())
                {
                    try
                    {
                        sendMessage(msg_Json);
                        sendArea.setText(""); // 清空发送框
                    } catch (IOException ex)
                    {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        this.add(SendMsgButton);

        //=============== 左侧群成员列表 ===============
        FriendsPanel=new JPanel();
        FriendsPanel.setSize(150,450);
        FriendsPanel.setLocation(20,0);

        // 创建好友根节点
        DefaultMutableTreeNode Root = new DefaultMutableTreeNode("好友");
        friendTree = new JTree(Root);

        // 给根节点添加渲染器
//        friendTree.setCellRenderer(new FriendListRenderer());
        jspMyBuddy = new JScrollPane(friendTree);
        jspMyBuddy.setBorder(null);
        jspMyBuddy.setBounds(0, 0, 300, 300);


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
//                    openPriateChat(friend);
                }
            }
        });
        friendTree = new JTree(Root);

        // 给根节点添加渲染器
//        friendTree.setCellRenderer(new FriendListRenderer());
        jspMyBuddy = new JScrollPane(friendTree);
        jspMyBuddy.setBorder(null);
        jspMyBuddy.setBounds(0, 0, 300, 300);

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
//                    openPriateChat(friend);
                }
            }
        });
        // 把在线的用户 创建对应的按钮 加入到聊天室右侧的在线列表
        for (int i = 0; i < selfAndOtherOnlineNames.size(); i++)
        {
            String privateChatFriend=selfAndOtherOnlineNames.get(i);
            JButton jButton1 = new JButton(privateChatFriend);
            jButton1.setSize(50,50);
            // 给每个在线好友按钮设置私聊功能
            jButton1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    PrivateChatUI privateChatUI=new PrivateChatUI(selfAndOtherOnlineNames.get(0),privateChatFriend,"1001","1002");
                }
            });
            FriendsPanel.add(jButton1);
        }

        //============添加节点=============
//        // 创建好友节点
//        Friend friend1 = new Friend("1001", "张三", "images/head/head1.png");
//        DefaultMutableTreeNode friendNode1 = new DefaultMutableTreeNode(friend1);
//
//        for (int i = 1; i < 14; i++)
//        {
//            Friend friend = new Friend("10000"+i, "好友"+i, "images/head/head"+i+".png");
//            friendNode = new DefaultMutableTreeNode(friend);
//            mybuddyRoot.add(friendNode);
//        }
//        // 给我的好友分组添加好友节点
//        friendRoot.add(friendNode1);
        // 更新界面
        FriendsPanel.updateUI();

        this.add(FriendsPanel);

        this.setVisible(true);
    }

    public void UpdateFriendsList(ArrayList<String> selfAndOtherOnlineNames)
    {
        // 先清除当前在线好友列表
        FriendsPanel.removeAll();

        // 把在线的用户创建对应的按钮 加入到聊天室左侧的在线列表
        for (int i=0;i<selfAndOtherOnlineNames.size();i++)
        {
            String friend=selfAndOtherOnlineNames.get(i);
            String self=Client.UserName;
            JButton jButton1 = new JButton(friend);
            jButton1.setSize(50, 50);
            jButton1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //PrivateChatUI privateChatUI=new PrivateChatUI(self,friend);
                }
            });
            FriendsPanel.add(jButton1);
        }
        FriendsPanel.updateUI();
    }

    // 发送消息
    private void sendMessage(String message) throws IOException
    {
        // 初始化PrintStream
        printStream = new PrintStream(Client.clientSocket.getOutputStream());
        printStream.println(message);
        printStream.flush();
        System.out.println("客户端成功发送消息:" + message);
    }

    public static void main(String[] args)
    {
        testGroupChatUI testGroupChatUI =new testGroupChatUI();
        ArrayList<String> selfAndOtherOnlineNames=new ArrayList<>();
        selfAndOtherOnlineNames.add("张三");
        selfAndOtherOnlineNames.add("李四");
        selfAndOtherOnlineNames.add("王五");
        testGroupChatUI.Init(selfAndOtherOnlineNames);

    }
}

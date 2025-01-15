package swing.view;

import swing.Client;
import swing.Entity.Friend;
import swing.Entity.Group;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SearchUI
{
    private static JRadioButton friendSearchRadio;
    private static JRadioButton groupSearchRadio;
    private static JPanel resultPanel;

    public SearchUI()
    {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("添加");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 创建面板 JPanel 实例
        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        // 调用方法设置组件
        placeComponents(panel, frame);

        // 设置窗口居中显示
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        // 设置窗口可见
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        HashMap hashMap = new HashMap<>();
        hashMap.put("111", "111");
        hashMap.put("222", "222");
        SearchUI searchUI = new SearchUI();

    }

    private static void placeComponents(JPanel panel, JFrame frame)
    {
        // 创建搜索选项卡
        JPanel searchOptionPanel = new JPanel();
        searchOptionPanel.setLayout(new BoxLayout(searchOptionPanel, BoxLayout.X_AXIS));

        ButtonGroup searchOptionGroup = new ButtonGroup();

        friendSearchRadio = new JRadioButton("搜索好友", true);
        searchOptionGroup.add(friendSearchRadio);
        searchOptionPanel.add(friendSearchRadio);

        groupSearchRadio = new JRadioButton("搜索群聊");
        searchOptionGroup.add(groupSearchRadio);
        searchOptionPanel.add(groupSearchRadio);

        panel.add(searchOptionPanel, BorderLayout.NORTH);

        // 创建搜索面板
        JPanel searchPanel = new JPanel(new BorderLayout());
        panel.add(searchPanel, BorderLayout.CENTER);

        // 创建搜索结果面板
        resultPanel = new JPanel(new GridLayout(0, 3));
        JScrollPane scrollPane = new JScrollPane(resultPanel);
        searchPanel.add(scrollPane, BorderLayout.CENTER);

        // 创建搜索按钮及文本框
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("搜索");

        JPanel searchControlPanel = new JPanel();
        searchControlPanel.setLayout(new BoxLayout(searchControlPanel, BoxLayout.X_AXIS));
        searchControlPanel.add(searchField);
        searchControlPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchControlPanel.add(searchButton);

        searchPanel.add(searchControlPanel, BorderLayout.NORTH);

        // 添加按钮点击事件监听器
        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 先清空搜索界面结果
                resultPanel.removeAll();
                frame.revalidate(); // 刷新界面
                String searchContent = searchField.getText();
                // 搜索框内容不为空
                if (!(searchContent.isEmpty()))
                {
                    // 客户端给服务端发送注册数据进行验证
                    PrintStream printStream = null;
                    // 如果选择搜索好友
                    if (friendSearchRadio.isSelected())
                    {
                        String searchFriendMsg = "{\"type\":\"searchFriend_msg\",\"from\":\"" + Client.UserName + "\",\"content\": \"" + searchContent + "\"}";
                        try
                        {
                            printStream = new PrintStream(Client.clientSocket.getOutputStream());
                            printStream.println(searchFriendMsg);
                            printStream.flush();
                            System.out.println("客户端已发送搜索好友信息" + searchFriendMsg);

                            // 接收搜索好友结果
                            try
                            {
                                TimeUnit.SECONDS.sleep(1); // 暂停1秒钟
                                // 遍历所有键值对
                                for (Friend friend : Client.accountNicknameMap)
                                {
                                    String avatarPath = friend.avatarPath;
                                    String Account = friend.Account;
                                    String nickName = friend.nickName;
                                    // 在此处执行搜索逻辑，并将结果添加到resultPanel中
                                    SearchFriendPanel friendPanel = new SearchFriendPanel(avatarPath, Account, nickName);
                                    resultPanel.add(friendPanel);

                                    // 获取加好友按钮并添加点击事件监听器
                                    JLabel addFriendLabel = friendPanel.getAddFriendLabel();
                                    addFriendLabel.addMouseListener(new MouseInputAdapter()
                                    {
                                        @Override
                                        public void mouseClicked(MouseEvent e)
                                        {
                                            // 在这里处理鼠标点击事件，弹出加好友窗口
                                            // 实现加好友窗口的逻辑
                                            Client.addFriendUI = new AddFriendUI(avatarPath, Account, nickName);
                                        }
                                    });
                                }
                            } catch (InterruptedException ex)
                            {
                                throw new RuntimeException(ex);
                            }
                        } catch (IOException ex)
                        {
                            throw new RuntimeException(ex);
                        }

                    }
                    // 如果选择搜索群聊
                    else if (groupSearchRadio.isSelected())
                    {
                        String searchGroupMsg = "{\"type\":\"searchGroup_msg\",\"from\":\"" + Client.UserName + "\",\"content\": \"" + searchContent + "\"}";
                        try
                        {
                            printStream = new PrintStream(Client.clientSocket.getOutputStream());
                            printStream.println(searchGroupMsg);
                            printStream.flush();
                            System.out.println("客户端已发送搜索群聊信息" + searchGroupMsg);

                            // 接收搜索群聊结果
                            try
                            {
                                TimeUnit.SECONDS.sleep(1); // 暂停5秒钟
                                // 遍历所有键值对
                                for (Map.Entry<String, String> entry : Client.groupAccountNameMap.entrySet())
                                {
                                    // 创建一个 Random 对象
                                    Random random = new Random();

                                    // 生成一个范围在 [1, 15) 之间的随机整数
                                    int i = random.nextInt(1, 15);
                                    String groupAvatar = "images/grouphead/grouphead1.png";
                                    ;
                                    String groupAccount = entry.getKey();
                                    String groupName = entry.getValue();
                                    // 在此处执行搜索逻辑，并将结果添加到resultPanel中
                                    // 每个结果块可以通过创建一个groupPanel来实现，并将其添加到resultPanel中
                                    SearchGroupPanel groupPanel = new SearchGroupPanel(groupAvatar, groupAccount, groupName);
                                    resultPanel.add(groupPanel);

                                    // 获取加群聊按钮并添加点击事件监听器
                                    JLabel joinGroupLabel = groupPanel.getJoinGroupLabel();
                                    joinGroupLabel.addMouseListener(new MouseInputAdapter()
                                    {
                                        @Override
                                        public void mouseClicked(MouseEvent e)
                                        {
                                            // 在这里处理鼠标点击事件，弹出加好友窗口
                                            // 实现加好友窗口的逻辑
                                            Group group = new Group(groupAccount, groupName, groupAvatar);
                                            DefaultMutableTreeNode groupNode1 = new DefaultMutableTreeNode(group);
                                            Client.mainUI.groupRoot.add(groupNode1);
                                            Client.mainUI.groupTree.updateUI();
                                            String addGroupMsg = "{\"type\":\"joinGroup_msg\",\"groupAccount\":\"" + groupAccount + "\",\"groupMember\":\"" + Client.UserName + "\"}";
                                            try
                                            {
                                                PrintStream printStream1 = new PrintStream(Client.clientSocket.getOutputStream());
                                                printStream1.println(addGroupMsg);
                                                printStream1.flush();
                                                System.out.println("客户端已发送加入群聊消息:" + addGroupMsg);
                                            } catch (IOException ex)
                                            {
                                                throw new RuntimeException(ex);
                                            }

                                        }
                                    });

                                }
                            } catch (InterruptedException ex)
                            {
                                throw new RuntimeException(ex);
                            }
                        } catch (IOException ex)
                        {
                            throw new RuntimeException(ex);
                        }
                    }
                }

                scrollPane.getVerticalScrollBar().setValue(0); // 滚动到顶部
                frame.revalidate(); // 刷新界面
                if (Client.accountNicknameMap != null)
                {
                    Client.accountNicknameMap.clear();
                }
                if (Client.groupAccountNameMap != null)
                {
                    Client.groupAccountNameMap.clear();
                }
            }
        });
    }
}

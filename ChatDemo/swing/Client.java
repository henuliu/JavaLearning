package swing;

import com.google.gson.Gson;
import swing.Entity.ClientMessage;
import swing.Entity.Friend;
import swing.Entity.Group;
import swing.View.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Client
{
    // 心跳间隔时间，单位：毫秒
    public static final int HEARTBEAT_INTERVAL = 5000;
    public static Socket clientSocket;
    public static String UserName;
    public static LoginUI loginUI;
    public static AddFriendUI addFriendUI;
    public static MainUI mainUI;
    public static GroupChatUI groupChatUI;
    public  static SearchUI searchUI;
    public static ConfirmAddFriendUI confirmAddFriendUI;

    // 因为1个client可以打开多个私聊界面，和多个好友同时私聊 所以用一个双列数据结构保存
    public static HashMap<String, PrivateChatUI> friendName2PrivateChatUI = new HashMap<>();

    public static HashMap<String, GroupChatUI> groupName2GroupChatUI = new HashMap<>();
    public static String Json_Msg;
    // 私聊消息
    public static String privateMsg;

    // 群聊消息
    public static String groupMsg;

    // 个人头像
    public static String avatarPath;

    // 个人昵称
    public static String nickName;

    //搜索好友信息 包括账号、昵称、头像
    public static ArrayList<Friend> accountNicknameMap;

    // 搜索群聊消息
    public static HashMap<String, String> groupAccountNameMap;

    public static String getGroupMsg()
    {
        return groupMsg;
    }

    // 给服务端发送心跳包
    public static void sendHeartbeat()
    {
        try
        {
            // 客户端最后一次发送心跳包的时间戳
            long lastHeartbeatTime = System.currentTimeMillis();
            PrintWriter writer = new PrintWriter(Client.clientSocket.getOutputStream(), true);
            writer.println("{\"type\": \"heartBeat_msg\", \"lastHeartbeatTime\": \""+lastHeartbeatTime+"\"}");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void startHeartbeatThread()
    {
        Thread heartbeatThread = new Thread(() ->
        {
            while (true)
            {
                sendHeartbeat();
                try
                {
                    Thread.sleep(Client.HEARTBEAT_INTERVAL);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        heartbeatThread.start();
    }
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("localhost", 8888);
        clientSocket = socket;
        loginUI = new LoginUI("登录", 450,350);
        ClientListenThread listenThread = new ClientListenThread(socket);
        listenThread.start();

        // 启动心跳线程
        // startHeartbeatThread();
    }
}

class ClientListenThread extends Thread
{
    private Socket socket;

    public ClientListenThread(Socket socket)
    {
        this.socket = socket;
    }

    // 解析json消息
    private static ClientMessage parseMessage(String json)
    {
        Gson gson = new Gson();
        ClientMessage message = gson.fromJson(json, ClientMessage.class);
        return message;
    }


    // 在群聊消息框中添加新消息
    private void appendGroupMessage(String sender, String message)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
        String formattedDate = sdf.format(date);
        String newMessage = GroupChatUI.getChatArea().getText() + sender + " " + formattedDate + "\n" + message + "\n";
        GroupChatUI.getChatArea().setText(newMessage);
    }

    // 在私聊消息框中添加新消息
    private void appendPrivateMessage(String sender, String message)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
        String formattedDate = sdf.format(date);
        PrivateChatUI privateChatUI = Client.friendName2PrivateChatUI.get(sender);
        String newMessage = privateChatUI.getChatArea().getText() + sender + " " + formattedDate + "\n" + message + "\n";
        privateChatUI.getChatArea().setText(newMessage);
    }

    @Override
    public void run()
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = null;

            while ((msg = bufferedReader.readLine()) != null)
            {
                System.out.println("客户端收到服务端的消息" + msg);
                ClientMessage getMsg = parseMessage(msg);

                // 处理登录结果
                if (getMsg.getType().equals("login_result"))
                {
                    if (getMsg.getContent().equals("true"))
                    {
                        JOptionPane.showMessageDialog(null, "登录成功");
                        System.out.println(LoginUI.UserName+"登录成功");

                        ArrayList<String> selfAndOtherOnlineNames = new ArrayList<>();
                        selfAndOtherOnlineNames.add(LoginUI.UserName);
                        System.out.println(LoginUI.UserName + "已被添加到在线列表");

                        Client.UserName = LoginUI.UserName;
                        // 初始化主界面用户名和头像
                        String account=Client.UserName;
                        String nickName=getMsg.getNickName();
                        String avatarPath=getMsg.getAvatarPath();
                        Client.mainUI = new MainUI(account,nickName,avatarPath);

                        PrintStream printStream=new PrintStream(Client.clientSocket.getOutputStream());
                        // 发送更新好友列表信息
                        String updateFriendMsg="{\"type\": \"updateFriend_msg\",\"account\": \""+Client.UserName+"\"}";
                        printStream.println(updateFriendMsg);
                        printStream.flush();
                        System.out.println("客户端已发送更新好友列表信息:"+updateFriendMsg);

                        // 发送更新群聊列表信息
                        String updateGroupMsg="{\"type\": \"updateGroup_msg\",\"model\":\"main\",\"account\":\""+Client.UserName+"\"}";
                        printStream.println(updateGroupMsg);
                        printStream.flush();
                        System.out.println("客户端已发送更新群聊列表信息:"+updateGroupMsg);
                        // 关闭登录界面
                        Client.loginUI.dispose();
                    }
                    else if (getMsg.getContent().equals("havaLogined"))
                    {
                        JOptionPane.showMessageDialog((Component) null, "该账号已登录");

                    } else if (getMsg.getContent().equals("false"))
                    {
                        JOptionPane.showMessageDialog((Component) null, "用户名或密码错误!请重试");
                    }
                    // 处理心跳消息
                    else if (getMsg.getType().equals("heartBeat"))
                    {
                        continue; // 忽略心跳消息，继续监听下一条消息
                    }
                }

                // 处理注册结果
                else if (getMsg.getType().equals("register_result"))
                {
                    if (getMsg.getContent().equals("true"))
                    {
                        JOptionPane.showMessageDialog((Component) null, "注册成功");
                    }
                    else if (getMsg.getContent().equals("haveRegistered"))
                    {
                        JOptionPane.showMessageDialog((Component) null, "该账号已注册");

                    } else
                    {
                        JOptionPane.showMessageDialog((Component) null, "注册失败");
                    }
                }
                // 更新好友列表
                else if (getMsg.getType().equals("updateFriend_msg"))
                {
                    // 如果用户有好友
                    if (!(getMsg.getContent().isEmpty()))
                    {
                        String[] friends = getMsg.getContent().split(";");
                        for (String friend : friends)
                        {
                            // 按逗号分割元素
                            String[] elements = friend.split(",");

                            String Account = elements[0];
                            String nickName = elements[1];
                            String avatarPath = elements[2];
                            Friend member = new Friend(Account, nickName, avatarPath);

                            // 加入好友
                            DefaultMutableTreeNode memberNode = new DefaultMutableTreeNode(member);
                            Client.mainUI.mybuddyRoot.add(memberNode);
                        }
                    }
                    // 刷新界面
                    Client.mainUI.friendTree.updateUI();
                }

                // 更新群成员列表
                else if (getMsg.getType().equals("updateGroup_msg"))
                {
                    if (getMsg.getModel().equals("main"))
                    {
                        // 如果有群聊
                        if (!(getMsg.getContent().isEmpty()))
                        {
                            // 按";"分割成组
                            String[] groups = getMsg.getContent().split(";");
                            for (String group : groups)
                            {
                                // 按逗号分割元素
                                String[] elements = group.split(",");

                                String groupAccount=elements[0];
                                String groupName=elements[1];
                                String groupAvatar=elements[2];
                                Group member=new Group(groupAccount,groupName,groupAvatar);

                                // 添加群成员
                                DefaultMutableTreeNode memberNode = new DefaultMutableTreeNode(member);
                                Client.mainUI.groupRoot.add(memberNode);
                            }
                        }
                        Client.mainUI.groupTree.updateUI();
                    }

                    else if (getMsg.getModel().equals("groupChat"))
                    {
                        System.out.println("客户端收到了服务端的更新群成员信息"+msg);
                        // 如果有群聊
                        if (!(getMsg.getContent().isEmpty()))
                        {
                            System.out.println(getMsg.getContent());
                            // 按";"分割成组
                            String[] groups = getMsg.getContent().split(";");
                            for (String group : groups)
                            {
                                // 按逗号分割元素
                                String[] elements = group.split(",");

                                String Account=elements[0];
                                String NickName=elements[1];
                                String avatarPath=elements[2];
                                Friend member=new Friend(Account,NickName,avatarPath);

                                // 添加群成员
                                DefaultMutableTreeNode memberNode = new DefaultMutableTreeNode(member);
                                Client.groupChatUI.myGroupRoot.add(memberNode);
                            }
                        }

                        Client.groupChatUI.memberTree.updateUI();
                    }
                }


                // 处理创建群聊结果
                else if (getMsg.getType().equals("createGroup_msg"))
                {
                    if (getMsg.getContent().equals("true"))
                    {
                        String groupAccount=getMsg.getGroupAccount();
                        String groupName=getMsg.getGroupName();
                        String groupAvatar=getMsg.getGroupAvatar();

                        Group group = new Group(groupAccount, groupName, groupAvatar);
                        DefaultMutableTreeNode groupNode1 = new DefaultMutableTreeNode(group);
                        Client.mainUI.groupRoot.add(groupNode1);
                        Client.mainUI.groupTree.updateUI();
                        JOptionPane.showMessageDialog((Component) null, "创建成功");
                    } else if (getMsg.getContent().equals("haveCreated"))
                    {
                        JOptionPane.showMessageDialog((Component) null, "该群号已存在");
                    }
                    else if (getMsg.getContent().equals("false"))
                    {
                        JOptionPane.showMessageDialog((Component) null, "创建失败");
                    }
                }

                // 处理加入群聊结果
                else if (getMsg.getType().equals("joinGroup_msg"))
                {
                    if (getMsg.getContent().equals("true"))
                    {
                        JOptionPane.showMessageDialog((Component) null, "加入成功");
                    }
                    else if (getMsg.getContent().equals("true"))
                    {
                        JOptionPane.showMessageDialog((Component) null, "您已在群聊中");
                    }
                }
                // 处理群聊消息
                else if (getMsg.getType().equals("group_msg"))
                {
                    System.out.println(Client.UserName + "成功接收服务端发来的群聊消息:" + msg);
                    appendGroupMessage(getMsg.getFrom(), getMsg.getContent());
                }

                // 处理私聊消息
                else if (getMsg.getType().equals("private_msg"))
                {
                    System.out.println(Client.UserName + "成功接收服务端发来的私聊消息:" + msg);
                    appendPrivateMessage(getMsg.getFrom(), getMsg.getContent());
                }

                // 处理搜索好友结果
                else if (getMsg.getType().equals("searchFriend_msg"))
                {
                    System.out.println(Client.UserName + "成功接收服务端发来的搜索好友消息:" + msg);
                    String searchResult=getMsg.getContent();
                    String[] resultArray = searchResult.split(";");
                    ArrayList<Friend> accountNicknameMap = new ArrayList<>();
                    // 处理每对 "Account,NickName"
                    for (String result : resultArray)
                    {
                        String[] pair = result.split(",");
                        if (pair.length == 3)
                        {
                            String account = pair[0];
                            String nickname = pair[1];
                            String avatarPath=pair[2];
                            Friend friend=new Friend(account,nickname,avatarPath);
                            accountNicknameMap.add(friend);
                        }
                    }
                    Client.accountNicknameMap=accountNicknameMap;
                }

                // 处理搜索群聊结果
                else if (getMsg.getType().equals("searchGroup_msg"))
                {
                    System.out.println(Client.UserName + "成功接收服务端发来的搜索群聊消息:" + msg);
                    String searchResult=getMsg.getContent();
                    String[] resultArray = searchResult.split(";");
                    HashMap<String, String> groupAccountNameMap = new HashMap<>();
                    // 处理每对 "groupAccount,groupName"
                    for (String result : resultArray)
                    {
                        String[] pair = result.split(",");
                        if (pair.length == 2)
                        {
                            String account = pair[0];
                            String nickname = pair[1];
                            groupAccountNameMap.put(account, nickname);
                        }
                    }
                    Client.groupAccountNameMap=groupAccountNameMap;
                }

                // 处理添加好友结果
                else if (getMsg.getType().equals("addFriend_msg"))
                {
                    if (getMsg.getContent().equals("true"))
                    {
                        System.out.println(Client.UserName + "成功接收服务端发来的添加好友请求:" + msg);
                        String avatarPath=getMsg.getAvatarPath();
                        String account=getMsg.getFrom();
                        String nickname=getMsg.getNickName();
                        String verifyInfo=getMsg.getVerifyInfo();
                        String remark=getMsg.getRemark();
                        Client.confirmAddFriendUI=new ConfirmAddFriendUI(avatarPath,account,nickname,verifyInfo,remark);
                    }
                    else if (getMsg.getContent().equals("haveAdded"))
                    {
                        JOptionPane.showMessageDialog((Component) null, "好友已存在");
                    }
                }

                // 处理同意或拒绝申请好友结果
                else if (getMsg.getType().equals("agreeFriend_msg"))
                {
                    // 如果同意申请好友
                    if (getMsg.getContent().equals("agree"))
                    {
                        Friend friend1 = new Friend(getMsg.getFrom(), getMsg.getNickName(), getMsg.getAvatarPath());
                        DefaultMutableTreeNode friendNode1 = new DefaultMutableTreeNode(friend1);
                        Client.mainUI.friendRoot.add(friendNode1);
                        Client.mainUI.friendTree.updateUI();
                    }
                    // 如果拒绝申请好友
                    else
                    {

                    }
                }
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}



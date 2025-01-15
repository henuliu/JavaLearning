package swing;

import com.google.gson.Gson;
import swing.Entity.ServerMessage;
import swing.dao.ConnectMySQL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChatServer
{
    // 定义一个双列数据结构HashMap,HashMap<String , Socket> String就是代表用户名
    public static HashMap<String, Socket> clientNameAndSockets = new HashMap<>();

    // 心跳间隔时间为5秒
    public static final long HEARTBEAT_INTERVAL = 5000;

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("聊天服务器已启动,等待客户端连接");

        // 接收多个客户端的连接
        while (true)
        {
            Socket socket = serverSocket.accept();
            // 如果有客户端连接，会打印连接提示
            System.out.println("客户端已连接，IP地址: " + socket.getInetAddress() + "，端口号: " + socket.getPort());

            new ServerThread(socket).start();
        }
    }
}

/*
 * 服务器为每一个客户端都会创建一条线程 专门为这个客户端服务
 * 读取这个客户端任意时刻发送过来的数据 并针对性的处理 比如给客户端响应数据
 */
class ServerThread extends Thread
{
    private PrintStream printStream;
    private Socket socket;

    public ServerThread(Socket socket) throws IOException
    {
        this.socket = socket;
        printStream = new PrintStream(socket.getOutputStream());
    }

    // 解析json消息
    private static ServerMessage parseMessage(String json)
    {
        Gson gson = new Gson();
        ServerMessage serverMessage = gson.fromJson(json, ServerMessage.class);
        return serverMessage;
    }

    // 利用MySQL验证账号密码是否正确，并返回给客户端结果
    private void LoginCheck(String input) throws SQLException, IOException
    {
        String[] inputs = input.split(",");

        // 将客户端输入的账号密码信息分割
        String UserName = inputs[0];
        String PassWord = inputs[1];

        // 连接MySQL
        ConnectMySQL LoginConn = new ConnectMySQL();
        // 用MySQL查询语句进行验证
        String cmd = "select * from login where Account='" + UserName + "' and PassWord='" + PassWord + "'";

        // 执行SQL查询语句，返回结果集
        ResultSet resultSet = LoginConn.queryExecute(cmd);
        // 对接收到的账号密码信息进行验证
        if (resultSet.next()) // 账号密码正确
        {
            String cmd1 = "select * from userinfo where Account='" + UserName + "'";
            ResultSet resultSet1 = LoginConn.queryExecute(cmd1);
            if (resultSet1.next())
            {
                String AvatarPath = resultSet1.getNString("avatarPath");
                String NickName = resultSet1.getString("NickName");
                // 发送验证结果到客户端
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                String returnMessage = "{\"type\": \"login_result\",\"content\": \"true\",\"avatarPath\": \"" + AvatarPath + "\",\"NickName\": \"" + NickName + "\"}";
                printStream.println(returnMessage);
                printStream.flush();
            }
        } else // 账号或密码错误
        {
            // 发送验证结果到客户端
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            String returnMessage = "{\"type\": \"login_result\",\"content\": \"false\"}";
            printStream.println(returnMessage);
            printStream.flush();
        }
    }

    // 心跳线程
    private class HeartbeatThread extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {
                try
                {
                    // 10秒检查一次客户端的心跳状态
                    checkClientHeartbeat();
                    Thread.sleep(ChatServer.HEARTBEAT_INTERVAL * 2);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }

        private void checkClientHeartbeat() throws IOException
        {
            long currentTime = System.currentTimeMillis();
            Iterator<Map.Entry<String, Socket>> iterator = ChatServer.clientNameAndSockets.entrySet().iterator();
            while (iterator.hasNext())
            {
                Map.Entry<String, Socket> entry = iterator.next();
                String clientName = entry.getKey();
                Socket socket = entry.getValue();
                // 如果超过心跳间隔时间仍未收到客户端的心跳包，则判定为连接断开
                if (currentTime - getLastHeartbeatTime(socket) > ChatServer.HEARTBEAT_INTERVAL * 2)
                {
                    System.out.println("客户端 " + clientName + " 的连接已断开");
                    try
                    {
                        socket.close();  // 关闭连接
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    iterator.remove();  // 从Map中移除当前迭代的键值对
                }
            }
        }

        private long getLastHeartbeatTime(Socket socket) throws IOException
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = reader.readLine();
            ServerMessage parse_msg = parseMessage(message);
            // 处理心跳消息
            if ("heartBeat_msg".equals(parse_msg.getType()))
            {
                String heartMsg = "{\"type\": \"heartBeat_msg\", \"content\": \"heart\"}";
                sendMessage(heartMsg);
                long lastHeartbeatTime = parse_msg.getLastHeartbeatTime();

                // 返回最后一次发送心跳包的时间戳
                return lastHeartbeatTime;
            } else
                return 0;
        }
    }

    // 线程主方法
    @Override
    public void run()
    {
        try
        {
            // 启动心跳检测线程
//            HeartbeatThread heartbeatThread = new HeartbeatThread();
//            heartbeatThread.start();
//            System.out.println("心跳线程已经启动");
            // 接收客户端信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            ServerMessage parse_msg = new ServerMessage();
            while ((message = reader.readLine()) != null)
            {
                // 解析消息
                parse_msg = parseMessage(message);
                // 根据消息类型发送回复
                if (parse_msg != null)
                {
                    // 处理注册
                    if ("register_msg".equals(parse_msg.getType()))
                    {
                        try
                        {
                            System.out.println("服务端收到客户端的注册信息" + message);
                            ConnectMySQL RegisterConn = new ConnectMySQL();
                            String cmd1 = "insert into login values('" + parse_msg.getAccount() + "','" + parse_msg.getPassword() + "')";
                            String cmd2 = "insert into userinfo values('" + parse_msg.getAccount() + "'," +
                                    "'" + parse_msg.getNickName() + "','" + parse_msg.getAvatarPath() +
                                    "','" + parse_msg.getSignature() + "')";
                            int result1 = RegisterConn.insertExecute(cmd1);
                            int result2 = RegisterConn.insertExecute(cmd2);

                            if (result1 > 0 && result2 > 0)
                            {
                                sendMessage("{\"type\" :\"register_result\",\"content\" :\"true\"}");
                            } else if (result1 <= 0)
                            {
                                sendMessage("{\"type\" :\"register_result\",\"content\" :\"haveRegistered\"}");
                            } else
                            {
                                sendMessage("{\"type\" :\"register_result\",\"content\" :\"false\"}");
                            }
                        } catch (SQLException ex)
                        {
                            throw new RuntimeException(ex);
                        }
                    }

                    // 处理登录
                    else if ("login_check_msg".equals(parse_msg.getType()))
                    {
                        System.out.println("服务端收到客户端的登录信息" + message);
                        // 将客户端输入的账号密码信息分割
                        String[] inputs = parse_msg.getContent().split(",");
                        String UserName = inputs[0];

                        // 判断当前用户是否已经登录
                        if (ChatServer.clientNameAndSockets.containsKey(UserName))
                        {
                            String result = "{\"type\": \"login_result\",\"content\": \"havaLogined\"}";
                            sendMessage(result);
                        } else
                        {
                            // 开始验证账号密码并给客户端返回结果
                            LoginCheck(parse_msg.getContent());
                            // 将用户名添加到在线人数当中
                            ChatServer.clientNameAndSockets.put(UserName, socket);
                        }

                    }

                    // 处理更新好友列表信息
                    else if ("updateFriend_msg".equals(parse_msg.getType()))
                    {
                        String Account = parse_msg.getAccount();
                        ConnectMySQL conn = new ConnectMySQL();
                        String cmd = "select * from friendsinfo where '" + Account + "'=Account";
                        // 执行数据库语句
                        ResultSet resultSet = conn.queryExecute(cmd);
                        // 将搜索结果拼接在一起
                        StringBuilder contents = new StringBuilder();
                        while (resultSet.next())
                        {
                            // 移动结果集指针到第一行
                            String friendAccount = resultSet.getString("friendAccount");
                            String friendNickName = resultSet.getString("friendNickName");
                            String friendAvatar = resultSet.getString("friendAvatar");
                            String content = friendAccount + "," + friendNickName + "," + friendAvatar + ";";
                            contents.append(content);
                        }
                        String searchResult = "{\"type\":\"updateFriend_msg\",\"content\":\"" + contents + "\"}";
                        sendMessage(searchResult);
                        System.out.println("服务端发送了更新好友信息:" + searchResult);

                    }


                    // 处理私聊消息
                    else if ("private_msg".equals(parse_msg.getType()))
                    {
                        System.out.println("服务端收到客户端的私聊信息" + message);
                        // 获取对应好友的 Socket
                        Socket friendSocket = ChatServer.clientNameAndSockets.get(parse_msg.getTo());
                        // 判断私聊好友是否在线
                        if (friendSocket != null)
                        {
                            PrintStream printStream = new PrintStream(friendSocket.getOutputStream());
                            String private_result = "{\"type\": \"private_msg\", \"from\": \"" + parse_msg.getFrom() +
                                    "\", \"to\": \"" + parse_msg.getTo() + "\", \"content\": \"" + parse_msg.getContent() + "\"}";
                            printStream.println(private_result);
                            printStream.flush();
                            System.out.println("服务端成功把" + parse_msg.getFrom() + "的私聊消息发给" + parse_msg.getTo() + ":" + private_result);
                        } else
                        {
                            System.out.println(parse_msg.getTo() + "不在线");
                        }
                    }

                    // 处理群聊消息
                    else if ("group_msg".equals(parse_msg.getType()))
                    {
                        System.out.println("服务端收到客户端的群聊信息" + message);
                        String groupMember = parse_msg.getGroupMember();
                        String[] groupMembers = groupMember.split(",");
                        for (String member : groupMembers)
                        {
                            Socket socket = ChatServer.clientNameAndSockets.get(member);
                            // 不发给不在线的群成员
                            if (socket != null)
                            {
                                System.out.println("这是" + member + "的socket");
                                sendMessage(message, socket);
                            }
                            System.out.println("服务端成功把群聊信息发给了每个群成员:" + message);
                        }
                    }

                    // 处理加入群聊消息
                    else if ("joinGroup_msg".equals(parse_msg.getType()))
                    {
                        System.out.println("服务端收到客户端的加入群聊信息" + message);
                        String groupAccount = parse_msg.getGroupAccount();
                        String memberAccount = parse_msg.getGroupMember();
                        ConnectMySQL conn = new ConnectMySQL();
                        String cmd = "insert into groupmembers values('" + groupAccount + "','" + memberAccount + "')";
                        int result = conn.insertExecute(cmd);
                        if (result > 0)
                        {
                            String addGroupResult = "{\"type\":\"joinGroup_msg\",\"content\":\"true\"}";
                            sendMessage(addGroupResult);
                        } else
                        {
                            String addGroupResult = "{\"type\":\"joinGroup_msg\",\"content\":\"haveJoined\"}";
                            sendMessage(addGroupResult);
                        }
                    }

                    // 更新群聊成员列表
                    else if ("updateGroup_msg".equals(parse_msg.getType()))
                    {
                        System.out.println("服务端收到了客户端的更新群成员信息" + message);
                        System.out.println(parse_msg.getModel());
                        if (parse_msg.getModel().equals("groupChat"))
                        {
                            String groupAccount = parse_msg.getGroupAccount();
                            System.out.println("待更新群号：" + groupAccount);
                            ConnectMySQL conn = new ConnectMySQL();
                            String cmd = "select * from userinfo u,groupmembers s where u.Account = s.memberAccount;";
                            ResultSet resultSet = conn.queryExecute(cmd);
                            StringBuilder contents = new StringBuilder();
                            // 将搜索结果拼接在一起
                            while (resultSet.next())
                            {
                                // 移动结果集指针到第一行
                                String Account = resultSet.getString("Account");
                                String NickName = resultSet.getString("NickName");
                                String avatarPath = resultSet.getString("avatarPath");
                                String content = Account + "," + NickName + "," + avatarPath + ";";
                                contents.append(content);
                            }
                            String searchResult = "{\"type\":\"updateGroup_msg\",\"model\":\"groupChat\",\"content\":\"" + contents + "\"}";
                            sendMessage(searchResult);
                            System.out.println("服务端发送了更新群成员信息" + searchResult);
                        }
                        // 更新主界面群聊列表
                        else if (parse_msg.getModel().equals("main"))
                        {
                            ConnectMySQL conn = new ConnectMySQL();
                            String cmd = "select * from groupinfo where groupAccount in(select groupAccount from groupmembers where memberAccount='" + parse_msg.getAccount() + "');";
                            System.out.println(cmd);
                            ResultSet resultSet = conn.queryExecute(cmd);
                            StringBuilder contents = new StringBuilder();
                            // 将搜索结果拼接在一起
                            while (resultSet.next())
                            {
                                // 移动结果集指针到第一行
                                String groupAccount = resultSet.getString("groupAccount");
                                String groupName = resultSet.getString("groupName");
                                String groupAvatar = resultSet.getString("groupAvatar");
                                String content = groupAccount + "," + groupName + "," + groupAvatar + ";";
                                contents.append(content);
                            }
                            String searchResult = "{\"type\":\"updateGroup_msg\",\"model\":\"main\",\"content\":\"" + contents + "\"}";
                            sendMessage(searchResult);
                            System.out.println("服务端发送了更新群聊信息" + searchResult);
                        }
                    }

                    // 处理搜索好友请求
                    else if ("searchFriend_msg".equals(parse_msg.getType()))
                    {
                        System.out.println("服务端收到客户端的搜索好友信息" + message);
                        String searchAccount = parse_msg.getContent();
                        // 执行SQL搜索语句
                        ConnectMySQL SearchFriendConn = new ConnectMySQL();
                        String cmd = "select * from userinfo where Account like '%" + searchAccount + "%' or NickName like '%" + searchAccount + "%'";
                        ResultSet resultSet = SearchFriendConn.queryExecute(cmd);

                        StringBuilder contents = new StringBuilder();
                        // 将搜索结果拼接在一起
                        while (resultSet.next())
                        {
                            String account = resultSet.getString("Account");
                            String nickName = resultSet.getString("NickName");
                            String avatarPath = resultSet.getString("avatarPath");
                            String content = account + "," + nickName + "," + avatarPath + ";";
                            contents.append(content);
                        }
                        String searchResult = "{\"type\":\"searchFriend_msg\",\"content\":\"" + contents + "\"}";

                        // 将搜索结果发送给相应客户端
                        String fromName = parse_msg.getFrom();
                        Socket socket = ChatServer.clientNameAndSockets.get(fromName);
                        sendMessage(searchResult, socket);
                        System.out.println("搜索结果为: " + contents);

                    }

                    // 处理搜索群聊请求
                    else if ("searchGroup_msg".equals(parse_msg.getType()))
                    {
                        System.out.println("服务端收到客户端的加群聊信息" + message);
                        String searchAccount = parse_msg.getContent();
                        // 执行SQL搜索语句
                        ConnectMySQL SearchFriendConn = new ConnectMySQL();
                        String cmd = "select * from groupinfo where groupAccount like '%" + searchAccount + "%' or groupName like '%" + searchAccount + "%'";
                        ResultSet resultSet = SearchFriendConn.queryExecute(cmd);

                        StringBuilder contents = new StringBuilder();
                        // 将搜索结果拼接在一起
                        while (resultSet.next())
                        {
                            String groupAccount = resultSet.getString("groupAccount");
                            String groupName = resultSet.getString("groupName");
                            String content = groupAccount + "," + groupName + ";";
                            contents.append(content);
                        }
                        String searchResult = "{\"type\":\"searchGroup_msg\",\"content\":\"" + contents + "\"}";

                        // 将搜索结果发送给相应客户端
                        String fromName = parse_msg.getFrom();
                        Socket socket = ChatServer.clientNameAndSockets.get(fromName);
                        sendMessage(searchResult, socket);
                        System.out.println("搜索结果为: " + contents);
                    }

                    // 处理添加好友请求
                    else if ("addFriend_msg".equals(parse_msg.getType()))
                    {
                        System.out.println("服务端收到了客户端的添加好友信息" + message);
                        Socket friendSocket = ChatServer.clientNameAndSockets.get(parse_msg.getTo());
                        ConnectMySQL conn = new ConnectMySQL();
                        String cmd = "select * from friendsinfo where friendAccount='" + parse_msg.getTo() + "'";
                        System.out.println(cmd);
                        ResultSet resultSet = conn.queryExecute(cmd);

                        // 判断是否加过了好友
                        // 如果已经加过好友
                        if (resultSet.next())
                        {
                            String addFriendReslt = "{\"type\":\"addFriend_msg\",\"content\":\"haveAdded\"}";
                            sendMessage(addFriendReslt);
                        }
                        // 如果没有加过好友
                        else
                        {
                            // 判断对方是否在线
                            if (friendSocket != null)
                            {
                                String addFriendReslt = "{\"type\":\"addFriend_msg\",\"from\":\"" + parse_msg.getFrom() + "\"," +
                                        "\"to\":\"" + parse_msg.getTo() + "\",\"verifyInfo\":\"" + parse_msg.getVerifyInfo() + "\",\"avatarPath\":\"" + parse_msg.getAvatarPath() + "\"" +
                                        ",\"NickName\":\"" + parse_msg.getNickName() + "\",\"remark\":\"" + parse_msg.getRemark() + "\",\"content\":\"true\"}";
                                PrintStream printStream = new PrintStream(friendSocket.getOutputStream());
                                printStream.println(addFriendReslt);
                                printStream.flush();
                                System.out.println("服务端成功把" + parse_msg.getFrom() + "的添加好友请求发给" + parse_msg.getTo() + ":" + addFriendReslt);
                            } else
                            {
                                System.out.println(parse_msg.getTo() + "不在线");
                            }
                        }
                    }
                    // 处理同意或拒绝好友申请消息
                    else if ("agreeFriend_msg".equals(parse_msg.getType()))
                    {
                        System.out.println("服务端收到了客户端的同意好友请求" + message);
                        // 如果同意申请
                        if (parse_msg.getContent().equals("agree"))
                        {
                            // 如果备注为空
                            if (parse_msg.getRemark().isEmpty())
                            {
                                // 返回给申请者结果
                                String applyResult = "{\"type\":\"agreeFriend_msg\",\"from\":\"" + parse_msg.getFrom() + "\",\"verifyInfo\":\"" + parse_msg.getVerifyInfo() + "\",\"avatarPath\":" +
                                        "\"" + parse_msg.getAppliedAvatarPath() + "\",\"NickName\": \"" + parse_msg.getAppliedNickName() + "\",\"content\":\"agree\"}";
                                Socket socket = ChatServer.clientNameAndSockets.get(parse_msg.getTo());
                                sendMessage(applyResult, socket);
                                System.out.println("返回给申请者的结果" + applyResult);

                                // 数据库中插入信息
                                ConnectMySQL conn = new ConnectMySQL();
                                String cmd = "insert into friendsinfo values('" + parse_msg.getTo() + "','" + parse_msg.getFrom() + "','" + parse_msg.getAppliedNickName() + "','" + parse_msg.getAppliedAvatarPath() + "')";
                                System.out.println(cmd);
                                conn.insertExecute(cmd);
                            }
                            // 如果备注不为空
                            else
                            {
                                // 返回给申请者结果
                                String applyResult = "{\"type\":\"agreeFriend_msg\",\"from\":\"" + parse_msg.getFrom() + "\",\"verifyInfo\":\"" + parse_msg.getVerifyInfo() + "\",\"avatarPath\":" +
                                        "\"" + parse_msg.getAppliedAvatarPath() + "\",\"NickName\": \"" + parse_msg.getRemark() + "\",\"content\":\"agree\"}";

                                Socket socket = ChatServer.clientNameAndSockets.get(parse_msg.getTo());
                                sendMessage(applyResult, socket);
                                System.out.println("返回给申请者的结果" + applyResult);

                                // 数据库中插入信息
                                ConnectMySQL conn = new ConnectMySQL();
                                String cmd = "insert into friendsinfo values('" + parse_msg.getTo() + "','" + parse_msg.getFrom() + "','" + parse_msg.getRemark() + "','" + parse_msg.getAppliedAvatarPath() + "')";
                                System.out.println(cmd);
                                conn.insertExecute(cmd);
                            }

                            // 返回给被申请者结果
                            Socket socket1 = ChatServer.clientNameAndSockets.get(parse_msg.getFrom());
                            String appliedResult = "{\"type\":\"agreeFriend_msg\",\"from\":\"" + parse_msg.getTo() + "\",\"to\":\"" + parse_msg.getTo() + "\",\"verifyInfo\":\"" + parse_msg.getVerifyInfo() + "\",\"avatarPath\":" +
                                    "\"" + parse_msg.getApplyAvatarPath() + "\",\"NickName\": \"" + parse_msg.getApplyNickName() + "\",\"content\":\"agree\"}";
                            System.out.println("返回给被申请者的结果" + appliedResult);
                            sendMessage(appliedResult, socket1);

                            // 数据库中插入被申请者信息
                            ConnectMySQL conn = new ConnectMySQL();
                            String cmd = "insert into friendsinfo values('" + parse_msg.getFrom() + "','" + parse_msg.getTo() + "','" + parse_msg.getApplyNickName() + "','" + parse_msg.getApplyAvatarPath() + "')";
                            System.out.println(cmd);
                            conn.insertExecute(cmd);

                        }
                        // 如果拒绝申请
                        else
                        {
                            String result = "{\"type\":\"agreeFriend_msg\",\"from\":\"" + parse_msg.getFrom() + "\",\"to\":\"" + parse_msg.getTo() + "\",\"verifyInfo\":\"" + parse_msg.getVerifyInfo() + "\",\"avatarPath\":" +
                                    "\"" + parse_msg.getAvatarPath() + "\",\"NickName\": \"" + parse_msg.getNickName() + "\",\"content\":\"refuse\",\"remark\":\"}";
                            Socket socket1 = ChatServer.clientNameAndSockets.get(parse_msg.getFrom());
                            sendMessage(result, socket1);
                            System.out.println("服务端发送了拒绝好友信息" + result);
                        }
                    }
                    // 处理创建群聊消息
                    else if (parse_msg.getType().equals("createGroup_msg"))
                    {
                        System.out.println("服务端收到了客户端的创建群聊请求" + message);
                        String[] inputs = parse_msg.getContent().split(",");
                        String groupAccount = inputs[0];
                        String groupName = inputs[1];
                        String groupAvatar = inputs[2];
                        ConnectMySQL conn = new ConnectMySQL();
                        String cmd1 = "insert into  groupinfo values('" + groupAccount + "','" + groupName + "','" + groupAvatar + "')";
                        String cmd2 = "insert into  groupmembers values('" + groupAccount + "','" + parse_msg.getGroupMember() + "')";
                        int result1 = conn.insertExecute(cmd1);
                        int result2 = conn.insertExecute(cmd2);
                        if (result1 > 0 && result1 > 0)
                        {
                            String createResult = "{\"type\":\"createGroup_msg\",\"content\":\"true\",\"groupAccount\":\"" + groupAccount + "\"" +
                                    ",\"groupName\":\"" + groupName + "\",\"groupAvatar\":\"" + groupAvatar + "\"}";
                            System.out.println("服务端发送了创建群聊信息" + createResult);
                            sendMessage(createResult);
                        } else if (result1 <= 0)
                        {
                            String createResult = "{\"type\":\"createGroup_msg\",\"content\":\"haveCreated\",\"groupAccount\":\"" + groupAccount + "\"" +
                                    ",\"groupName\":\"" + groupName + "\",\"groupAvatar\":\"" + groupAvatar + "\"}";
                            System.out.println("服务端发送了创建群聊信息" + createResult);
                            sendMessage(createResult);
                        } else if (result2 <= 0)
                        {
                            String createResult = "{\"type\":\"createGroup_msg\",\"content\":\"false\",\"groupAccount\":\"" + groupAccount + "\"" +
                                    ",\"groupName\":\"" + groupName + "\",\"groupAvatar\":\"" + groupAvatar + "\"}";
                            System.out.println("服务端发送了创建群聊信息" + createResult);
                            sendMessage(createResult);
                        }
                    }
                }
            }
        } catch (IOException e)
        {
            // 如果是Socket关闭引起的异常则不用处理
            if (!"Socket closed".equals(e.getMessage()))
            {
                e.printStackTrace();
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    // 发送消息
    private void sendMessage(String message) throws IOException
    {
        printStream.println(message);
        printStream.flush();
        System.out.println("成功把消息发送到客户端" + message);
    }

    private void sendMessage(String message, Socket socket) throws IOException
    {
        PrintStream printStream1 = new PrintStream(socket.getOutputStream());
        printStream1.println(message);
        printStream1.flush();
    }
}

package test;
import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        while (true)
        {
            try (ServerSocket serverSocket = new ServerSocket(12345))
            {
                System.out.println("服务器启动，等待客户端连接...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("客户端连接成功！");

                // 接收消息
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String message = in.readLine();
                System.out.println("成功接收到客户端信息");
                System.out.println(message);
                // 解析消息
                Message msg = parseMessage(message);

                // 根据消息类型发送回复
                if (msg != null)
                {
                    if ("private_msg".equals(msg.getType()))
                    {
                        sendMessage(clientSocket, "Hello!");
                    } else if ("group_msg".equals(msg.getType()))
                    {
                        sendMessage(clientSocket, "Hello everyone!");
                    }
                }

                clientSocket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }

    // 解析消息
    private static Message parseMessage(String json) {
        Gson gson = new Gson();
        Message message = gson.fromJson(json, Message.class);
        return message;
    }

    // 发送消息
    private static void sendMessage(Socket socket, String message) throws IOException
    {
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outputStream, true);
        out.println(message);
        out.flush();
        System.out.println("成功把数据发送到客户端");
    }
}

// 消息类
class Message
{
    private String type;
    // 其他字段...

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    // 其他 getter 和 setter 方法...
}

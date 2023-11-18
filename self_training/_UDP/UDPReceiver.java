package _UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver
{
    private DatagramSocket socket;
    private byte[] receiveData;

    public UDPReceiver(int port)
    {
        try
        {
            socket = new DatagramSocket(port);
            receiveData = new byte[1024];
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void startReceiving()
    {
        try
        {
            while (true)
            {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message: " + receivedMessage); // 输出接收的数据
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (socket != null)
            {
                socket.close();
            }
        }
    }
}
package _UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender
{
    public void send(String message, InetAddress receiverAddress, int port)
    {
        try
        {
            DatagramSocket socket = new DatagramSocket();
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receiverAddress, port);
            socket.send(sendPacket);
            socket.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

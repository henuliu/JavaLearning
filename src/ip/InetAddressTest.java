package ip;

import java.net.InetAddress;

public class InetAddressTest
{
    public static void main(String[] args) throws Exception
    {
        // 1、获取本机IP地址对象的主机名和IP地址
        InetAddress ip1 = InetAddress.getLocalHost();
        System.out.println(ip1.getHostName());
        System.out.println(ip1.getHostAddress());

        // 2、获取指定IP或者域名的IP地址对象。
        InetAddress ip2 = InetAddress.getByName("www.baidu.com");
        System.out.println(ip2.getHostName());
        System.out.println(ip2.getHostAddress());

        // ping www.baidu.com
        // 它会发送一个ping请求到指定主机，并等待一段时间来检测是否收到了相应。
        // 如果在指定的时间内（这里是6000毫秒，即6秒）收到了响应，则isReachable方法会返回true，表示指定主机是可达的；
        // 如果超时未收到响应，则会返回false
        System.out.println(ip2.isReachable(6000));
    }
}

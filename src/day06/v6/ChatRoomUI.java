package day06.v6;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * JFrame 界面类
 * extends JFrame 自己就是一个界面类
 */
public class ChatRoomUI extends JFrame
{

    // Jpanel 面板 什么作用？ 可以把一个界面划分成多个区块
    private JPanel jp1, jp2, jp3;

    private JTextField jTextField;

    private JButton jButton;

    public static void main(String[] args)
    {
        ChatRoomUI chatRoomUI = new ChatRoomUI();
//        chatRoomUI.initUI("zs");


    }

    public void initUI(ArrayList<String> selfAndOtherOnlineNames)
    {

        this.setTitle(selfAndOtherOnlineNames.get(0) + "的聊天室");
        this.setSize(600, 420);
        this.setLocation(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp1 = new JPanel();
        jp1.setSize(400, 280);
        jp1.setBorder(BorderFactory.createLineBorder(Color.RED)); // 面板设置边框 红色实线
        jp1.setLocation(10, 10);

        jp2 = new JPanel();
        jp2.setSize(400, 60);
        jp2.setBorder(BorderFactory.createLineBorder(Color.RED)); // 面板设置边框 红色实线
        jp2.setLocation(10, 300);


        jTextField = new JTextField(20);
        jTextField.setText("请输入聊天信息");
        jTextField.setSize(280, 40);
        jTextField.setLocation(10, 10);

        jButton = new JButton("发送");
        jButton.setSize(80, 40);
        jButton.setLocation(300, 10);

        // Jpanel默认是FlowLayout 流式布局 从中间往两边放组件 放不下换行 设置null 意思就是不要相对布局 绝对布局
        jp2.setLayout(null);
        jp2.add(jTextField);
        jp2.add(jButton);


        jp3 = new JPanel();
        jp3.setSize(160, 350);
        jp3.setBorder(BorderFactory.createLineBorder(Color.RED)); // 面板设置边框 红色实线
        jp3.setLocation(415, 10);

        // 把在线的用户 创建对应的按钮 加入到聊天室右侧的在线列表
        for (int i = 0; i < selfAndOtherOnlineNames.size(); i++)
        {
            JButton jButton1 = new JButton(selfAndOtherOnlineNames.get(i));
            jp3.add(jButton1);
        }


        // this是JFrame的实例 ， 而JFrame默认布局管理器是BorderLayout 就是把界面划分成5个区域 上下左右中
        // 现在实际是根据坐标定位，这个是绝对布局 ， 其它的BorderLayout GridLayout Jpanel默认的是FlowLayout 都是相对布局
        this.setLayout(null);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setVisible(true);

    }


}

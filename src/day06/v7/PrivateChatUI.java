package day06.v7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

/**
 *  私聊界面
 */
public class PrivateChatUI extends JFrame {

    // Jpanel 面板 什么作用？ 可以把一个界面划分成多个区块
    private JPanel jp1 , jp2 , jp3;

    private JTextField jTextField;

    private JButton jButton;

    public void initUI(String self , String friend){

        this.setTitle(self+ "和"+friend+"的聊天室");
        this.setSize(600 , 420);
        this.setLocation(600 , 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp1 = new JPanel();
        jp1.setSize(400 , 280);
        jp1.setBorder(BorderFactory.createLineBorder(Color.RED)); // 面板设置边框 红色实线
        jp1.setLocation(10 , 10);

        jp1.setLayout(new GridLayout(10,1)); // 聊天内容区 10行1列

        jp2 = new JPanel();
        jp2.setSize(400 , 60);
        jp2.setBorder(BorderFactory.createLineBorder(Color.RED)); // 面板设置边框 红色实线
        jp2.setLocation(10 , 300);



        jTextField = new JTextField(20);
        jTextField.setText("请输入聊天信息");
        jTextField.setSize(280 , 40);
        jTextField.setLocation(10 ,10);

        jButton = new JButton("发送");
        jButton.setSize(80 , 40);
        jButton.setLocation(300 ,10);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    // 拿到聊天框的消息
                    String chatContent = jTextField.getText().toString();

                    // 根据通信协议 拼接 客户端发送给服务器: 3,senderName,receiverName,message
                    String returnMessage = "3," + self + ","  + friend + "," + chatContent;

                    // 给服务器发送群聊消息
                    PrintStream printStream = new PrintStream(Client.socket.getOutputStream());
                    printStream.println(returnMessage);
                    printStream.flush();

                    // 更新自己的聊天界面
                    JLabel jLabel = new JLabel(new Date().toLocaleString()   + "我说: " + chatContent);
                    jp1.add(jLabel);
                    jp1.updateUI();

                }catch (Exception exception)
                {
                    exception.printStackTrace();
                }


            }
        });

        // Jpanel默认是FlowLayout 流式布局 从中间网俩边放组件 放不下换行 设置null 意思就是不要相对布局 绝对布局
        jp2.setLayout(null);
        jp2.add(jTextField);
        jp2.add(jButton);



        jp3 = new JPanel();
        jp3.setSize(160 , 350);
        jp3.setBorder(BorderFactory.createLineBorder(Color.RED)); // 面板设置边框 红色实线
        jp3.setLocation(415 , 10);

        // this是JFrame的实例 ， 而JFrame默认布局管理器是BorderLayout 就是把界面划分成5个区域 上下左右中
        // 现在实际是根据坐标定位，这个是绝对布局 ， 其它的BorderLayout GridLayout Jpanel默认的是FlowLayout 都是相对布局
        this.setLayout(null);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setVisible(true);

    }

    public JPanel getJp1() {
        return jp1;
    }

    public void setJp1(JPanel jp1) {
        this.jp1 = jp1;
    }

    public JPanel getJp2() {
        return jp2;
    }

    public void setJp2(JPanel jp2) {
        this.jp2 = jp2;
    }

    public JPanel getJp3() {
        return jp3;
    }

    public void setJp3(JPanel jp3) {
        this.jp3 = jp3;
    }

    public JTextField getjTextField() {
        return jTextField;
    }

    public void setjTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    public JButton getjButton() {
        return jButton;
    }

    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }

//    public static void main(String[] args) {
//        PrivateChatUI chatRoomUI = new PrivateChatUI();
//        chatRoomUI.initUI("zs" , "ls");
//
//
//    }


}

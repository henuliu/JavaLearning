package day06.v6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 注册登录 2合1
 */
public class RegisterAndLoginUI extends JFrame{

    private JTextField jTextField;

    private  JButton jButton;

    public void initUI() {

        this.setTitle("登录聊天室");
        this.setLayout(new GridLayout(1,2)); // 1个输入框 1个登录按钮

        jTextField = new JTextField(20);
        jTextField.setText("请输入名字");
        this.add(jTextField);


        jButton = new JButton("登录");

        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String name = jTextField.getText();

                try {
                    PrintStream printStream = new PrintStream(Client.socket.getOutputStream());
                    printStream.println("1,"+name);
                    printStream.flush();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        this.add(jButton);

        this.pack(); // pack是打包 就是可以不指定JFrame的大小，根据里边组件的大小确定JFrame界面的总体大小
        this.setLocation(600 , 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 点击界面的右上角的叉 关闭程序 进程结束 红颜色方块 变灰

        this.setVisible(true);

    }
}

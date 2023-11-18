package swing;

import javax.swing.*;

public class MainWindow extends JFrame
{
    public MainWindow()
    {
        super("主界面");
        //关闭窗口时退出整个进程
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置窗口初始位置和大小
        this.setBounds(550, 100, 350, 600);

        //创建一个面板作为容器
        JPanel panel = new JPanel();
        this.setContentPane(panel);

        //显示窗口
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        MainWindow mainWindow=new MainWindow();
    }
}

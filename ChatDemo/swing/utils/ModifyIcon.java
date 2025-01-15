package swing.utils;

import javax.swing.*;
import java.awt.*;

public class ModifyIcon
{
    public ImageIcon modify(ImageIcon icon, int width, int height)
    {
        Image img = icon.getImage();
        // 对Image对象进行平滑缩放,调整宽和高
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // 将缩放后的Image对象转换为ImageIcon对象
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        return scaledIcon;
    }
}

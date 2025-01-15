package serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SuperMain
{
    private static final String FILE_PATH = "./super.bin";

    public static void main(String[] args) throws Exception
    {
        serializeAnimal();
        deserializeAnimal();
    }

    private static void serializeAnimal() throws Exception
    {
        BlackCat black = new BlackCat("black", "我是黑猫");
        System.out.println("序列化前：" + black.toString());
        System.out.println("=================开始序列化================");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
        oos.writeObject(black);
        oos.flush();
        oos.close();
    }

    private static void deserializeAnimal() throws Exception
    {
        System.out.println("=================开始反序列化================");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
        BlackCat black = (BlackCat) ois.readObject();
        ois.close();
        System.out.println(black);
    }
}
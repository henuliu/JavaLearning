package day05.Abstract.PetHospital;

public class Client {

    public static void main(String[] args)
    {
        // Animal animal = new Animal(); 错  抽象类 有得有失 失去的是new的能力

        Animal animal1 = new Cat();
        animal1.cry();

        Animal animal2 = new Dog();
        animal2.cry();
    }
}

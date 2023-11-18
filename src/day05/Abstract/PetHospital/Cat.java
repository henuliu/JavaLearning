package day05.Abstract.PetHospital;

// Class 'Cat' must either be declared abstract or implement abstract method 'cry()' in 'Animal'
public class Cat extends Animal
{
    @Override
    public void cry()
    {
        System.out.println("我是猫 ， 喵喵叫！！！");
    }
}

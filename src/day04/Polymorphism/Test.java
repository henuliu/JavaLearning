package day04.Polymorphism;

public class Test
{

    public static void main(String[] args)
    {
        People p1 = new People();
        p1.run();
        go(p1);


        People p2 = new Teacher(); // 父类变量 指向 子类实例
        p2.run();
//        p2.teach(); // p2在编译的时候只知道是 自己是People类型 而People是没有teach方法的 结果就是编译出错了
        go(p2);

        Student student = (Student) p2; // 这一行代码很有意思，是骗子  p2本来是Teacher 但是伪装成了People ， 然后People强制类型转换成Student
        // java 编译时候只知道 p2是People类型 因为People类型和Student是父子关系，编译是可以通过的 因为p2有可能是Student

        Teacher teacher = new Teacher();
//        Student student1 = teacher;           // Teacher和Student没父子关系 编译就不通过
//        Student student1 = (Student)teacher;  // Teacher和Student没父子关系 根本就不能强转 报错

        People p3 = new Student();
        p3.run();
//      p3.study();  // p3在编译的时候只知道是 自己是People类型 而People是没有study方法的 结果就是编译出错了
        go(p3);


    }


    // 多态往往用在方法的参数上 : 定义抽象不定义具体 定义接口不定义实现
    // 定义抽象不定义具体 意思就是尽量声明父类型 越是父类越抽象 越是子类越具体
    // 定义的时候声明要People 比要Teacher或Student程序扩展性好 因为定义成People 给people对象 或 teacher对象 或 student对象都可以
    static void go(People people)
    {
        people.run();

        if (people instanceof Teacher)
        {
//            Teacher teacher = people; 语法错误  teacher是一个people 但是people不一定是teacher

            Teacher teacher = (Teacher) people;
            teacher.teach();
        }


        if (people instanceof Student)
        {
            Student student = (Student) people;
            student.study();
        }
    }
}

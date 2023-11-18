package day05.InnerClass;

public class OuterClass
{
    public static String a = "IT教育";
    private int age = 99;

    // 成员内部类
    public class Inner
    {
        private String name;
        private int age = 88;

        //在内部类中既可以访问自己类的成员，也可以访问外部类的成员
        public void test()
        {
            System.out.println(age); //88
            System.out.println(a);   //IT教育

            int age = 77;
            System.out.println(age); //77
            System.out.println(this.age); //88
            System.out.println(OuterClass.this.age); //99
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getAge()
        {
            return age;
        }

        public void setAge(int age)
        {
            this.age = age;
        }
    }
}

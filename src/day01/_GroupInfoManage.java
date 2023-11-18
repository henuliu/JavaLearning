package day01;

import java.util.Scanner;

public class _GroupInfoManage
{

    public static void main(String[] args)
    {

        Student[] students = new Student[8];

        Student student = new Student();
        student.name = "组长";
        student.age = 20;

        students[0] = student;
        System.out.println("欢迎登录小组成员管理系统");


        do
        {
            System.out.println("请选择要进行的操作: 1 查所有 2添加 3 删除 4 修改 5 查单个 6 退出");
            Scanner scanner = new Scanner(System.in);
            int operation = scanner.nextInt();

            switch (operation)
            {
                case 1:
                    System.out.println("您选择的操作是1 查所有 ");
                    for (int i = 0; i < students.length; i++)
                    {
                        if (students[i] != null)
                        {
                            System.out.println("名字: " + students[i].name + "\t" + "年龄: " + students[i].age);
                        }

                    }

                    break;
                case 2:
                    System.out.println("您选择的操作是2 添加 ");

                    Scanner scannerForAdd = new Scanner(System.in);

                    System.out.println("请输入名字");
                    String name = scannerForAdd.next();

                    System.out.println("请输入年龄");
                    int age = scannerForAdd.nextInt();

                    Student stu = new Student();
                    stu.name = name;
                    stu.age = age;

                    for (int i = 0; i < students.length; i++)
                    {
                        if (students[i] == null)
                        {
                            students[i] = stu;

                            System.out.println("添加成功!");

                            break;
                        }

                    }

                    break;
                case 3:

                    System.out.println("您选择的操作是3 删除 ");

                    Scanner scannerForDelete = new Scanner(System.in);

                    System.out.println("请输入待删除的组员名字");
                    String nameForDelete = scannerForDelete.next();


                    for (int i = 0; i < students.length; i++)
                    {
                        // 判断当前的Student的name和用户输入删除的name一致
                        if (nameForDelete.equals(students[i].name))
                        {
                            students[i] = null;

                            System.out.println("删除成功!");

                            break;
                        }

                    }


                    break;
                case 4:
                    System.out.println("您选择的操作是4 修改");
                    Scanner scannerForChange = new Scanner(System.in);
                    System.out.println("请输入待修改的组员名字");
                    String nameForChange = scannerForChange.next();


                    for (int i = 0; i < students.length; i++)
                    {
                        if (students[i] != null)
                        {
                            if (nameForChange.equals(students[i].name))
                            {
                                System.out.println("请输入修改后的组员名字");
                                Scanner scannerForChanged = new Scanner(System.in);
                                String nameForChanged = scannerForChanged.next();
                                students[i].name = String.valueOf(nameForChanged);
                            }
                        } else
                        {
                            System.out.println("Student at index " + i + " is null");
                        }
                    }
                    break;
                case 5:
                    System.out.println("您选择的操作是5 查单个");
                    Scanner scannerForLook = new Scanner(System.in);
                    System.out.println("请输入待查找的组员名字");
                    String nameForLook = scannerForLook.next();
                    for (int i = 0; i < students.length; i++)
                    {
                        if (students[i] != null)
                            if (nameForLook.equals(students[i].name))
                            {
                                System.out.println("查找成功!");
                                System.out.println("该学生的姓名为:" + students[i].name + ",年龄为:" + students[i].age);
                                break;
                            }
                    }
                    break;
                case 6:
                    System.out.println("您选择的操作是6 退出");
                    System.out.println("退出成功!");
                    System.exit(0);
                default:
                    System.out.println("你选择的操作无效，重新选择!");
                    break;
            }

        } while (true);
    }
}

class Student
{
    String name;
    int age;
}
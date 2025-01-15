package lambda;

/*
 * Lambda表达式，用于简化匿名内部类代码的书写
 * 1.在使用Lambda表达式之前，必须先有一个接口，而且接口中只能有一个抽象方法，注：不能是抽象类，只能是接口
 * 2.只有基于函数式接口的匿名内部类才能被Lambda表达式简化
 * 3.语法：(参数列表) -> { 方法体 }，可以根据情况简化。
 */
public class _lambda
{
    public static void main(String[] args)
    {
        // 使用匿名内部类
        MyFunctionalInterface anonymousClass = new MyFunctionalInterface()
        {
            @Override
            public void execute(String message)
            {
                System.out.println("Anonymous Class: " + message);
            }
        };
        anonymousClass.execute("Hello, World!");

        // 使用 Lambda 表达式
        MyFunctionalInterface lambdaExpression = (message) ->
        {
            System.out.println("Lambda Expression: " + message);
        };
        lambdaExpression.execute("Hello, World!");

        // 简化Lambda表达式（只有一条语句时可以省略大括号和分号）
        MyFunctionalInterface simplifiedLambda = message ->
                System.out.println("Simplified Lambda: " + message);
        simplifiedLambda.execute("Hello, World!");
    }
}

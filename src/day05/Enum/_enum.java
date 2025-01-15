package day05.Enum;

/*
 * 枚举一般表示一组信息，然后作为参数进行传输
 * 枚举类中可以像类一样，能在枚举类中定义构造器、成员变量、成员方法
 */
public enum _enum {
    // 定义枚举常量并传递参数给构造器
    APPLE("Red"),
    BANANA("Yellow"),
    GRAPE("Purple");

    // 成员变量
    private String color;

    // 构造器（必须是 private 或默认的包私有访问级别）
    _enum(String color) {
        this.color = color;
    }

    // 成员方法
    public String getColor() {
        return color;
    }

    public static void main(String[] args) {
        // 使用枚举常量
        System.out.println("Apple color: " + _enum.APPLE.getColor());
        System.out.println("Banana color: " + _enum.BANANA.getColor());
        System.out.println("Grape color: " + _enum.GRAPE.getColor());
    }
}

package reflection;

import lombok.Data;

@Data
class People
{
    private String name;
    private int age;

    public People()
    {

    }

    public People(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    private People(String name)
    {
        this.name = name;
    }

}
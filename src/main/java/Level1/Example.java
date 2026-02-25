package Level1;

import java.io.Serializable;

public class Example implements Serializable {
    private String name;
    private int age;

    public Example(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Example{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }
}

package streams;

import java.util.Arrays;
import java.util.List;

class Student{
    String name;
    int age;
    String type;

    public Student(){}

    public Student(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }

    public static List<Student> generateData() {

        List<Student> st = Arrays.asList(new Student("Ashish", 27, "College"),
                new Student("Aman", 24, "School"),
                new Student("Rahul", 18, "School"),
                new Student("Ajay", 29, "College"),
                new Student("Mathur", 25, "College"),
                new Student("Modi", 28, "College"),
                new Student("Prem", 15, "School"),
                new Student("Akash", 17, "School"));
        return st;
    }
}


public class TestingLambWithDa {

    public static void main(String[] args) {

        MathOperation add = (a, b) -> a+b;
        MathOperation sub = (a, b) -> a-b;
        MathOperation mult = (a, b) -> a*b;

        GreetMe gm = msg -> System.out.println("Hi "+ msg);

        System.out.println(add.operation(5,6));
        System.out.println(sub.operation(5,6));
        System.out.println(mult.operation(5,6));

        gm.sayHello("Ashish");
    }

    interface MathOperation{
        int operation(int c, int d);
    }

    interface GreetMe{
        void sayHello(String message);
    }
}
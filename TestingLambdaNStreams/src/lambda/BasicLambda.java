package lambda;

import java.util.Arrays;
import java.util.List;

public class BasicLambda {

    public static void main(String[] args) {

        BasicLambda bl = new BasicLambda();

        Operation mult = new Operation() {
            @Override
            public int operate(int a, int b) {
                return a*b;
            }
        };

        Operation add = (int a,int b) -> {return a+b;};

        Operation sub = (a,b) -> a-b;

        PrintMessage say = msg -> System.out.println("Hello "+ msg);

        System.out.println(mult.operate(4,8));
        System.out.println(add.operate(2, 3));
        System.out.println(sub.operate(7, 4));


        say.printMsg("Rahul");

        bl.invokePrintMsg(say);

        bl.invokePrintMsg(msg -> System.out.println("Hi! " + msg));

        List<Integer> a = Arrays.asList(3,5,7,9);

        a.forEach(s -> System.out.println(s.toString()));

        a.forEach(System.out::println);
    }

    public interface Operation{
        int operate(int a, int b);
    }

    public interface PrintMessage{
        void printMsg(String msg);
    }

    public void invokePrintMsg(PrintMessage pm){
        pm.printMsg("Aman");
    }
}

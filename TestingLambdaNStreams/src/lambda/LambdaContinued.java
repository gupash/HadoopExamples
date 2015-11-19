package lambda;

import java.util.concurrent.Callable;

public class LambdaContinued {

    /**
     *  Creating methods like in Basic Lambda, just the Interface passed as argument is not our own,
     *  its java provided. Also the methods are overloaded
     */

    static void invoke(Runnable r){
        r.run();
    }

    static Object invoke(Callable c) throws Exception {
        return c.call();
    }

    public static void main(String[] args) throws Exception {

        //The first call returns String("done"), as return is default in lambda, if in a single line
        String s = (String) invoke(() -> "done");
        System.out.println(s);

        //Second call returns nothing
        invoke(() -> System.out.println("Ashish"));
    }
}

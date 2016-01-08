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

        //First call is to invoke taking Runnable reference as Input, and it returns nothing
        invoke(() -> System.out.println("Ashish"));

        /*The second call returns String("done"), as return is default in lambda, if in a single line,
          hence it is identified against invoke taking callable as Input */
        String s = (String) invoke(() -> "done");
        System.out.println(s);

    }
}


public class TastingLambWithoutLambda {

    public static void main(String[] args) {

        TastingLambWithoutLambda tl = new TastingLambWithoutLambda();

        MathOperation addition = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a+b;
            }
        };

        MathOperation substraction = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a-b;
            }
        };

        class Multiplication implements MathOperation{

            @Override
            public int operation(int a, int b) {
                return a*b;
            }
        }

        System.out.println(tl.operate(1, 2, addition));
        System.out.println(tl.operate(3, 2, substraction));
        System.out.println(tl.operate(3, 6, new Multiplication()));
    }

    interface MathOperation{
        int operation(int a, int b);
    }

    private int operate(int a, int b, MathOperation mo){
        return mo.operation(a, b);
    }
}

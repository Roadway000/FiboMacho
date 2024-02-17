import java.util.Scanner;
public class Main {
    private static int num;
    private final static int MAX_NUM_WITH_COMMENTS = 49;
    public static void setNum(int num) {
        Main.num = num;
    }
    public static boolean getNumber(Scanner in) {
        System.out.print("Введіть число більше 0 або 0 для завершення: ");
        int nn = in.nextInt();
        setNum(nn);
        System.out.printf("Your number: %d \n", nn);
        System.out.println(String.format("Для чисел біль ніж %s коментарі по кожній ітерації відсутні",MAX_NUM_WITH_COMMENTS));
        return nn>0;
    }
    public static void getFibonacci() {
        Fibonacci fibo = new Fibonacci();
        String mess = "Час витрачений на виконання функції %s з аргументами від 0 до %d = %d ms";
        long t1 = 0;
        /* the next row only for init memory for funktion System.currentTimeMillis() Otherwise the time of the first method will be increased */
        System.currentTimeMillis();
        t1 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            if (num > MAX_NUM_WITH_COMMENTS) {
                fibo.fiboIter(i);
            } else {
                System.out.println(String.format("fiboIter(%d) = %d", i ,fibo.fiboIter(i)));
            }
        }
        System.out.println(String.format(mess, "fiboIter", num, System.currentTimeMillis() - t1));

        if (num+1 < MAX_NUM_WITH_COMMENTS) {
            t1 = System.currentTimeMillis();
            for (int i = 0; i < num; i++) {
                System.out.println(String.format("fiboRecurs(%d) = %d", i, fibo.fiboRecurs(i)));
            }
            System.out.println(String.format(mess, "fiboRecurs", num, System.currentTimeMillis() - t1));
        } else {
            System.out.println(String.format("Час обчислення зазначеного числа %d методом 'fiboRecurs' занадто тривалий, оберіть число менш ніж %d", num, MAX_NUM_WITH_COMMENTS-1));
        }

        t1 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            if (num > MAX_NUM_WITH_COMMENTS) {
                fibo.fibOptima(i);
            } else {
                System.out.println(String.format("fibOptima(%d) = %d", i, fibo.fibOptima(i)));
            }
        }
        System.out.println(String.format(mess, "fibOptima", num, System.currentTimeMillis() - t1));

        t1 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            if (num > MAX_NUM_WITH_COMMENTS) {
                fibo.fiboCache(i);
            } else {
                System.out.println(String.format("fiboCache(%d) = %d", i ,fibo.fiboCache(i)));
            }
        }
        System.out.println(String.format(mess, "fiboCache", num, System.currentTimeMillis() - t1));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (getNumber(in)) {
            getFibonacci();
        }
        in.close();
    }
}

public class Main {

    static Object mon = new Object();
    static volatile int currentNum = 3;
    static volatile int end = 16;

    public static void main(String[] args) {


        new Thread(() -> {
            try {
                for (int i = 0; i <= end; i++) {
                    synchronized (mon) {
                        while (currentNum % 3 != 0 ) {
                            mon.wait();
                        }
                        currentNum++;
                        printABC("A");
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                for (int i = 0; i <= end; i++) {
                    synchronized (mon) {
                        while ((currentNum - 1) % 3 != 0) {
                            mon.wait();
                        }
                        currentNum++;
                        printABC("B");
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i <= end; i++) {
                    synchronized (mon) {
                        while ((currentNum - 2) % 3 != 0) {
                            mon.wait();
                        }
                        currentNum++;
                        printABC("C");
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void printABC (String letter) {
//        String letters;
//        letters = letter;
        end--;
        if (letter.equals("A")) {
            System.out.println("A");
        }   else if (letter.equals("B")) {
            System.out.println("B");
        }   else if (letter.equals("C")) {
            System.out.println("C");
        }
    }

}

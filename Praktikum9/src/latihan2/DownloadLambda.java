package latihan2;

public class DownloadLambda {
    public static void main(String[] args) throws InterruptedException {

        Thread f1 = new Thread(() -> {
            String file = "File-1";
            for (int i = 10; i <= 100; i += 10) {
                System.out.println(file + " progress: " + i + "%");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(file + " selesai diunduh!");
        });

        Thread f2 = new Thread(() -> {
            String file = "File-2";
            for (int i = 10; i <= 100; i += 10) {
                System.out.println(file + " progress: " + i + "%");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(file + " selesai diunduh!");
        });

        Thread f3 = new Thread(() -> {
            String file = "File-3";
            for (int i = 10; i <= 100; i += 10) {
                System.out.println(file + " progress: " + i + "%");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(file + " selesai diunduh!");
        });

        f1.start();
        f2.start();
        f3.start();

        f1.join();
        f2.join();
        f3.join();

        System.out.println("\nSemua file selesai diunduh!");
    }
}

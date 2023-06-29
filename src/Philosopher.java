public class Philosopher implements Runnable {
    private final int id;
    private final int leftFork;
    private final int rightFork;

    public Philosopher(int id) {
        this.id = id;
        this.leftFork = id;
        this.rightFork = (id + 1) % App.getPhilosophersNum();
    }

    @Override
    public void run() {
        while (true) {
            try {
                think();
                takeForks();
                eat();
                release();
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + ": Thinking...");
        Thread.sleep((long) (Math.random() * 1000 + 1000));

    }

    private void takeForks() throws InterruptedException {
        System.out.println("Philosopher " + id + ": Taking left fork...");
        App.forks[leftFork].acquire();

        System.out.println("Philosopher " + id + ": Taking right fork...");
        App.forks[rightFork].acquire();

        System.out.println("Philosopher " + id + ": Ready to eat!");
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + ": Eating!");
        // Thread sleeps between 1 and 2 seconds
        Thread.sleep((long) (Math.random() * 1000 + 1000));
        System.out.println("Philosopher " + id + ": Finished eating!");
    }

    private void release() {
        System.out.println("Philosopher " + id + ": Releasing left fork...");
        App.forks[leftFork].release();

        System.out.println("Philosopher " + id + ": Releasing right fork...");
        App.forks[rightFork].release();
    }
}

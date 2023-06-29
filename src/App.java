import java.util.concurrent.Semaphore;

public class App {

  public static Semaphore[] forks;
  private static int philosophersNum = 5;
  private static Thread[] philosophers;

  public static void main(String[] args) throws Exception {
    forks = new Semaphore[philosophersNum];
    philosophers = new Thread[philosophersNum];
    
    // Instantiate forks and philosophers
    for (int i = 0; i < philosophersNum; i++) {
      forks[i] = new Semaphore(1);
      philosophers[i] = new Thread(new Philosopher(i));
    }

    // Start philosophers
    for (int i = 0; i < philosophersNum; i++) {
      philosophers[i].start();
    }

    // Join philosophers
    for (int i = 0; i < philosophersNum; i++) {
      philosophers[i].join();
    }
  }

  public static int getPhilosophersNum() {
    return philosophersNum;
  }
}

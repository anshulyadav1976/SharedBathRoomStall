import java.util.concurrent.Semaphore;

// FloorBathroom Class
public class FloorBathroom {
    private static final int BATHROOM_STALLS = 6; // Number of stalls
    private static final int NUM_EMPLOYEES = 100; // Number of employees/students

    public static void main(String[] args) {
        // Semaphore to manage bathroom stalls
        Semaphore stalls = new Semaphore(BATHROOM_STALLS);

        // Simulate 100 employees/students trying to use the bathroom
        for (int i = 1; i <= NUM_EMPLOYEES; i++) {
            int personId = i; // Capture the ID for the thread
            new Thread(() -> {
                try {
                    System.out.println("Person " + personId + " is waiting to use the bathroom.");
                    stalls.acquire(); // Acquire a stall
                    System.out.println("Person " + personId + " is using the bathroom.");
                    Thread.sleep(100); // Simulate time spent in the bathroom
                    System.out.println("Person " + personId + " has left the bathroom.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    stalls.release(); // Release the stall
                }
            }).start();
        }
    }
}

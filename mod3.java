package project;

// UNIT III: Generics and Multi-threading
class ServiceTask<T> extends Thread {
    private T taskName;

    ServiceTask(T taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Performing maintenance task: " + taskName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Task interrupted");
        }
    }
}

public class mod3 {
    public static void main(String[] args) {
        ServiceTask<String> t1 = new ServiceTask<>("Oil Change");
        ServiceTask<String> t2 = new ServiceTask<>("Tyre Check");

        t1.start();
        t2.start();
    }
}

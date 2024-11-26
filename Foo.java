import java.util.concurrent.Semaphore;

class Foo {
    private Semaphore secondSemaphore = new Semaphore(0); // Initially locked
    private Semaphore thirdSemaphore = new Semaphore(0);  // Initially locked

    public Foo() {}

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run(); // Print "first"
        secondSemaphore.release(); // Allow second() to proceed
    }

    public void second(Runnable printSecond) throws InterruptedException {
        secondSemaphore.acquire(); // Wait for first() to finish
        printSecond.run(); // Print "second"
        thirdSemaphore.release(); // Allow third() to proceed
    }

    public void third(Runnable printThird) throws InterruptedException {
        thirdSemaphore.acquire(); // Wait for second() to finish
        printThird.run(); // Print "third"
    }
}

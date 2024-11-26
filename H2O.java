import java.util.concurrent.Semaphore;
class H2O {
    private Semaphore hydrogenSemaphore = new Semaphore(2); // Allows up to 2 hydrogen threads
    private Semaphore oxygenSemaphore = new Semaphore(0);   // Allows 1 oxygen thread

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire(); // Acquire a permit for hydrogen
        releaseHydrogen.run();       // Print "H"
        if (hydrogenSemaphore.availablePermits() == 0) {
            oxygenSemaphore.release(); // Allow oxygen thread to proceed after 2 hydrogens
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire(); // Acquire a permit for oxygen
        releaseOxygen.run();       // Print "O"
        hydrogenSemaphore.release(2); // Allow 2 hydrogens for the next molecule
    }
}

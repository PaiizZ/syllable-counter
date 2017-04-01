package stopwatch;


/**
 * A StopWatch that measures elapsed time between a starting time
 * and stopping time, or until the present time.
 *
 * @author Wanchanapon Thanwaranurak
 * @version 1/27/2017 AD.
 */

public class Stopwatch {

    /**
     * constant for converting nanoseconds to seconds.
     */
    private static final double NANOSECONDS = 1.0E-9;

    /**
     * time that the stopwatch was started, in nanoseconds.
     */
    private long startTime;

    /**
     *
     */
    private long stopTime;

    /**
     *
     */
    private boolean running;

    /**
     *
     */
    public Stopwatch() {
        this.startTime = 0;
        this.stopTime = 0;
        this.running = false;
    }

    /**
     * Start the Stopwatch.
     */
    public void start() {
        if (!isRunning()) {
            this.startTime = System.nanoTime();
            this.stopTime = 0;
            this.running = true;
        }
    }

    /**
     * Stop the Stopwacth.
     */
    public void stop() {
        if (isRunning()) {
            this.stopTime = System.nanoTime();
            this.running = false;
        }
    }

    /**
     * check StopWacth running or stopped.
     *
     * @return true if the Stopwacth is running, false if Stopwacth is stopped.
     */
    public boolean isRunning() {
        if (this.running)
            return true;
        else
            return false;
    }

    public double getElapsed() {
        if (isRunning())
            return (System.nanoTime() - this.startTime) * NANOSECONDS;
        else
            return (this.stopTime - this.startTime) * NANOSECONDS;
    }
}

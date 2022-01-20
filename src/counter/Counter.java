package counter;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public class Counter {
    private int counter;

    /**
     * add number to current count.
     * @param number number to add
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     * @param number number to subtract
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * counter getter.
     * @return the counter value
     */
    public int getValue() {
        return counter;
    }
}

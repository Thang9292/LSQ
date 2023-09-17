import java.util.NoSuchElementException;

/**
 * Implementation of an ArrayStack.
 *
 * @author Thang Huynh
 * @version 1.0
 * 
 */
public class ArrayStack<T> {

    /*
     * The initial capacity of the ArrayStack.
     *
     */
    public static final int INITIAL_CAPACITY = 9;
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Adds the data to the top of the stack.
     *
     * Must be O(1).
     *
     * @param data the data to add to the top of the stack
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void push(T data) {
        //The Exception
        if (data == null) {
            throw new IllegalArgumentException("The data entered was null");
        }

        //Expanding the ArrayStack
        if (size + 1 > backingArray.length) {
            T[] newArray = (T[]) new Object[2 * backingArray.length];
            for (int i = 0; i < size; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }

        // Pushing the data
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the data from the top of the stack.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T pop() {
        //The Exception
        if (size == 0) {
            throw new NoSuchElementException("The ArrayStack is already empty");
        }

        //Popping
        final T removedData = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return removedData;
    }

    /**
     * Returns the data from the top of the stack without removing it.
     *
     * Must be O(1).
     *
     * @return the data from the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T peek() {
        //The Exception
        if (size == 0) {
            throw new NoSuchElementException("The ArrayStack is empty, no data can be retrieved");
        }

        // Peeking
        return backingArray[size - 1];
    }

}
import java.util.NoSuchElementException;

/**
 * Implementation of an ArrayList.
 *
 * @author Thang Huynh
 * @version 1.0
 *
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     */
    public static final int INITIAL_CAPACITY = 9;

    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so we will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Adds the element to the specified index.
     *
     * May require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        // The exceptions
        if (data == null) {
            throw new IllegalArgumentException("The data is null, use a T type data");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("The index is a negative number, use an "
                    + "index inside the range of 0 and " + size + ".");
        } else if (index > size) {
            throw new IndexOutOfBoundsException("The index is greater than " + size + ", use an index"
                    + " inside the range of 0 and " + size + ".");
        }

        // Expanding the ArrayList and Adds at index
        if (size + 1 > backingArray.length) {
            T[] anArray = (T[]) new Object[2 * backingArray.length];
            for (int i = 0; i < size + 1; i++) {
                if (i == index) {
                    anArray[i] = null; // Leaves a spot open for adding and "shifts"
                    // all the other elements to the right this way
                    // This for efficiency of O(n) instead of having two loops
                } else {
                    anArray[i] = backingArray[i];
                }
            }
            anArray[index] = data;
            backingArray = anArray;
            size++;
        }

    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // The exception
        if (data == null) {
            throw new IllegalArgumentException("The data is null, use a T type data");
        }

        // Makes a new Array of double capacity and copies over data
        if (size + 1 > backingArray.length) {
            T[] anArray = (T[]) new Object[2 * backingArray.length];
            for (int i = 0; i < size; i++) {
                anArray[i] = backingArray[i];
            }
            backingArray = anArray;
        }

        // Adding the data at index
        int num = size;
        while (num > 0) { //looping to "shift" elements
            backingArray[num] = backingArray[num - 1];
            num--;
        }
        backingArray[0] = data;
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // The exception
        if (data == null) {
            throw new IllegalArgumentException("The data is null, use a T type data");
        }

        // Makes a new Array of double capacity and copies over data
        if (size + 1 > backingArray.length) {
            T[] anArray = (T[]) new Object[2 * backingArray.length];
            for (int i = 0; i < size; i++) {
                anArray[i] = backingArray[i];
            }
            backingArray = anArray;
        }

        // Adding to the back
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * May require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        // The exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index should be > 0 and <= " + size + ".");
        }

        // ERROR - Needs to handle resize exception
        // So if you removed from a filled array, we would try to access index + 1
        // which is out of bounds and an exception
        final T dataRemoved = backingArray[index];
        if (index == size - 1) {
            backingArray[size - 1] = null;
        } else {
            int num = size;
            while (index < num) {
                backingArray[index] = backingArray[index + 1];
                index++;
            }
        }
        size--;
        return dataRemoved;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("The list is already empty");
        }

        //Removing from front
        final T dataRemoved = backingArray[0];
        int num = 0;
        while (num < size) {
            backingArray[num] = backingArray[num + 1];
            num++;
        }
        size--;
        return dataRemoved;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // The Exception
        if (size == 0) {
            throw new NoSuchElementException("The list is already empty");
        }

        // Removing from back
        final T dataRemoved = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return dataRemoved;
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        // The exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index should be > 0 and <= " + size + ".");
        }

        return backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return backingArray[0] == null;
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }
}

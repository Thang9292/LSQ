import java.util.NoSuchElementException;

/**
 * Implementation of an LinkedQueue.
 *
 * @author Thang Huynh
 * @version 1.0
 */
public class LinkedQueue<T> {


    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /**
     * Adds the data to the back of the queue.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        //The Exception
        if (data == null) {
            throw new IllegalArgumentException("The data entered was null");
        }

        //Enqueue
        LinkedNode<T> newNode = new LinkedNode<>(data);
        if (size == 0) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;

    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        //The Exception
        if (size == 0) {
            throw new NoSuchElementException("The queue is already empty");
        }

        //Dequeue
        final T removedData = head.getData();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return removedData;
    }

    /**
     * Returns the data from the front of the queue without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T peek() {
        //The Exception
        if (size == 0) {
            throw new NoSuchElementException("The queue is already empty");
        }

        //Peeking
        return head.getData();
    }

}
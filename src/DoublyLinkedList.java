import java.util.NoSuchElementException;

/**
 * Implementation of a non-circular DoublyLinkedList with a tail pointer.
 *
 * @author Thang Huynh
 * @version 1.0
 *
 */
public class DoublyLinkedList<T> {


    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    /**
     * Adds the element to the specified index. Considers whether
     * traversing the list from the head or tail is more efficient
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        // The Exceptions
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index is outside the range of the double linked list"
                + "It must be between 0 and " + size + ".");
        } else if (data == null) {
            throw new IllegalArgumentException("The data entered was null");
        }

        //Creates the new node
        DoublyLinkedListNode<T> aNode = new DoublyLinkedListNode<>(data);
        DoublyLinkedListNode<T> current;
        if (size == 0 && index == 0) { // Creates the first node in a doubly linked list
            head = aNode;
            tail = aNode;
            size++;
        } else if (index == 0) { //Adds to the front
            aNode.setNext(head); // THIS HAS TO BE FIRST
            aNode.setPrevious(null);
            if (head != null) { //You have to make sure that head isn't null
                head.setPrevious(aNode);
            }
            head = aNode;
            size++;
        } else if (index == size) { //Adds to the back
            aNode.setPrevious(tail); // THIS HAS TO BE FIRST
            aNode.setNext(null);
            if (tail != null) { //You have to make sure tail isn't null else null point except
                tail.setNext(aNode);
            }
            tail = aNode;
            size++;
        } else if (size - index >= index) { //Closer to the front
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            aNode.setNext(current);
            aNode.setPrevious(current.getPrevious());
            current.getPrevious().setNext(aNode);
            current.setPrevious(aNode);
            size++;
        } else { //Closer to the back
            current = tail;
            for (int i = size; i > index + 1; i--) {
                current = current.getPrevious();
            }
            aNode.setNext(current);
            aNode.setPrevious(current.getPrevious());
            current.getPrevious().setNext(aNode);
            current.setPrevious(aNode);
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        //The Exception
        if (data == null) {
            throw new IllegalArgumentException("The data entered was null");
        }

        //Adding to front
        DoublyLinkedListNode<T> aNode = new DoublyLinkedListNode<>(data);
        if (size == 0) { // Creates the first node in a doubly linked list
            tail = aNode;
        } else {
            aNode.setNext(head); // THIS HAS TO BE FIRST
            aNode.setPrevious(null);
            if (head != null) { //You have to make sure that head isn't null
                head.setPrevious(aNode);
            }
        }
        head = aNode;
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        //The Exception
        if (data == null) {
            throw new IllegalArgumentException("The data entered was null");
        }

        //Adding to back
        DoublyLinkedListNode<T> aNode = new DoublyLinkedListNode<>(data);
        if (size == 0) { // Creates the first node in a doubly linked list
            head = aNode;
        } else {
            aNode.setPrevious(tail); // THIS HAS TO BE FIRST
            aNode.setNext(null);
            if (tail != null) { //You have to make sure tail isn't null else null point except
                tail.setNext(aNode);
            }
        }
        tail = aNode;
        size++;
    }

    /**
     * Removes and returns the element at the specified index.
     * Considers whether traversing the list from the head or tail is more
     * efficient
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        //The Exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index is outside the range of the double linked list"
                + "It must be between 0 and " + size + ".");
        }

        //Creating a new node
        DoublyLinkedListNode<T> current;
        T removedData;
        if (index == 0) { //Removed from front
            removedData = head.getData();
            DoublyLinkedListNode<T> newHead = head.getNext();
            newHead.setPrevious(null);
            head = newHead;
            size--;
        } else if (index == size - 1) { //Removed from back
            removedData = tail.getData();
            DoublyLinkedListNode<T> newTail = tail.getPrevious();
            newTail.setNext(null);
            tail = newTail;
            size--;
        } else if (size - index >= index) { //Closer to the front
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            removedData = current.getData();
            current.getNext().setPrevious(current.getPrevious());
            current.getPrevious().setNext(current.getNext());
            size--;
        } else { //Closer to the back
            current = tail;
            for (int i = size; i > index + 1; i--) {
                current = current.getPrevious();
            }
            removedData = current.getData();
            current.getNext().setPrevious(current.getPrevious());
            current.getPrevious().setNext(current.getNext());
            size--;
        }
        return removedData;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        //The Exception
        if (size == 0) {
            throw new NoSuchElementException("The list is already empty");
        }

        // Removing from front
        final T removedData = head.getData();
        DoublyLinkedListNode<T> newHead = head.getNext();
        newHead.setPrevious(null);
        head = newHead;
        size--;
        return removedData;
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
        //The Exception
        if (size == 0) {
            throw new NoSuchElementException("The list is already empty");
        }

        //Removing from back
        final T removedData = tail.getData();
        DoublyLinkedListNode<T> newTail = tail.getPrevious();
        newTail.setNext(null);
        tail = newTail;
        size--;
        return removedData;
    }

    /**
     * Returns the element at the specified index. Considers
     * whether traversing the list from the head or tail is more efficient
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {

        DoublyLinkedListNode<T> current;
        if (index == 0) { //If you want the 1st element in the list
            return head.getData();
        } else if (index == size - 1) { //If you want the last element in the list
            return tail.getData();
        } else if (size - index >= index) { //Closer to front
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        } else { //Closer to back
            current = tail;
            for (int i = size; i > index + 1; i--) {
                current = current.getPrevious();
            }
            return current.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * It does not return the same data that was passed in. It returns the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        DoublyLinkedListNode<T> current = tail;
        T removedData;
        if (data == tail.getData()) {
            removedData = tail.getData();
            DoublyLinkedListNode<T> newTail = tail.getPrevious();
            newTail.setNext(null);
            tail = newTail;
        } else {
            while (current.getData() != data) {
                current = current.getPrevious();
            }
            removedData = current.getData();
            current.getNext().setPrevious(current.getPrevious());
            current.getPrevious().setNext(current.getNext());
        }
        size--;
        return removedData;
    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, returns an empty array.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        if (size == 0) {
            return new Object[0];
        } else {
            DoublyLinkedListNode<T> current = head;
            Object[] theArray = new Object[size];
            int i = 0;
            while (current != null) {
                theArray[i] = current.getData();
                current = current.getNext();
                i++;
            }
            return theArray;
        }
    }
}

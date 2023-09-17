import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a basic set of unit tests for ArrayStack.
 */
public class ArrayStackStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayStack<String> array;

    @Before
    public void setup() {
        array = new ArrayStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayStack.INITIAL_CAPACITY],
            array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPush() {
        array.push("0a");  // 0a
        array.push("1a");  // 0a, 1a
        array.push("2a");  // 0a, 1a, 2a
        array.push("3a");  // 0a, 1a, 2a, 3a
        array.push("4a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPop() {
        String temp = "5a";

        array.push("0a");  // 0a
        array.push("1a");  // 0a, 1a
        array.push("2a");  // 0a, 1a, 2a
        array.push("3a");  // 0a, 1a, 2a, 3a
        array.push("4a");  // 0a, 1a, 2a, 3a, 4a
        array.push(temp);  // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());

        assertSame(temp, array.pop()); // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackResize() {
        Object[] expected = {"0a", "1a", "2a", "3a", "4a", "5a", "6a", "7a", "8a", "9a", "10a", null, null, null, null, null, null, null};
        array.push("0a");
        array.push("1a");
        array.push("2a");
        array.push("3a");
        array.push("4a");
        array.push("5a");
        array.push("6a");
        array.push("7a");
        array.push("8a");
        array.push("9a");
        array.push("10a");
        assertArrayEquals(expected, array.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testArrayPeek() {
        String temp = "4a";

        array.push("0a");  // 0a
        array.push("1a");  // 0a, 1a
        array.push("2a");  // 0a, 1a, 2a
        array.push("3a");  // 0a, 1a, 2a, 3a
        array.push(temp);  // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, array.size());

        assertSame(temp, array.peek());
    }

}
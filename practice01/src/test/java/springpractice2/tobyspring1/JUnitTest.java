package springpractice2.tobyspring1;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {
    static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();

    @Test
    public void test1() {
        assertNotSame(testObjects, hasItem(this));
        testObjects.add(this);
    }

    @Test
    public void test2() {
        assertNotSame(testObjects, hasItem(this));
        testObjects.add(this);
    }

    @Test
    public void test3() {
        assertNotSame(testObjects, hasItem(this));
        testObjects.add(this);
    }
}

package xyz.lifecycle1;

import org.junit.Test;

import java.util.concurrent.BlockingDeque;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void isCorrect() {
        int a=1;
        int b=2;
        boolean c=b==a;
        assertEquals(c, false);
    }
}
package post.com.exam.lab3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainScreenMethodsTest {
    private static double e = 1.0e-3;

    @Test
    public void getDistance_method_test() {
        float[] a = {0, 1, 0}, b = {1, 0, 1};
        double template = Math.sqrt(3), res = MainScreen.getDistance(a, b);
        assertTrue(Math.abs(res - template) < e);
    }
}
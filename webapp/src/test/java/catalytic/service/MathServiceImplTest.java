package catalytic.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


/**
 * Created by gardncl on 2/16/17.
 */
public class MathServiceImplTest {

    @Spy
    MathServiceImpl mathService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fibonacci() throws Exception {
        Double result = mathService.fibonacci(10L);
        assertEquals(result,55D);
    }

    @Test(expected = RuntimeException.class)
    public void fibonacci_exception() throws Exception {
        mathService.fibonacci(-5L);
    }

    @Test
    public void factor() throws Exception {
        List<Long> results = mathService.factor(35L);
        assertTrue(results.contains(5L));
        assertTrue(results.contains(7L));
    }

    @Test
    public void factor_prime() throws Exception {
        List<Long> results = mathService.factor(17L);
        assertTrue(results.contains(1L));
        assertTrue(results.contains(17L));
    }

    @Test(expected = RuntimeException.class)
    public void factor_exception() throws Exception {
        List<Long> results = mathService.factor(-35L);
    }


    @Test
    public void palindrome_true() throws Exception {
        boolean result = mathService.palindrome("aba");
        assertEquals(result,true);
    }

    @Test
    public void palindrome_false() throws Exception {
        boolean result = mathService.palindrome("abab");
        assertEquals(result,false);
    }

    @Test(expected = RuntimeException.class)
    public void palindrome_exception() throws Exception {
        boolean result = mathService.palindrome("");
    }

}
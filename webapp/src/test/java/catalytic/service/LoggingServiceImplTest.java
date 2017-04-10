package catalytic.service;

import catalytic.model.Log;
import catalytic.model.LogDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by gardncl on 2/16/17.
 */
public class LoggingServiceImplTest {

    private String error = "error";

    @Mock
    private MathService mathService;

    @Mock
    private LogDao logDao;

    @Spy
    @InjectMocks
    private LoggingServiceImpl loggingService;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void fibonacci() throws Exception {
        doReturn(0D).when(mathService).fibonacci(anyLong());
        doReturn(new Log()).when(logDao).save(any(Log.class));
        loggingService.fibonacci(anyLong());
        verify(mathService,times(1)).fibonacci(anyLong());
        verify(logDao,times(1)).save(any(Log.class));
    }

    @Test
    public void fibonacci_exception() throws Exception {
        doThrow(new RuntimeException(error)).when(mathService).fibonacci(anyLong());
        doReturn(new Log()).when(logDao).save(any(Log.class));
        String returnValue = loggingService.fibonacci(-1L);
        assertEquals(returnValue, error);
    }

    @Test
    public void factor() throws Exception {
        List<Long> list = new LinkedList<>();
        list.add(0L);
        doReturn(list).when(mathService).factor(anyLong());
        doReturn(new Log()).when(logDao).save(any(Log.class));
        loggingService.factor(anyLong());
        verify(mathService,times(1)).factor(anyLong());
        verify(logDao,times(1)).save(any(Log.class));
    }

    @Test
    public void factor_exception() throws Exception {
        doThrow(new RuntimeException(error)).when(mathService).factor(anyLong());
        doReturn(new Log()).when(logDao).save(any(Log.class));
        String returnValue = loggingService.factor(-1L);
        assertEquals(returnValue, error);
    }

    @Test
    public void palindrome() throws Exception {
        doReturn(true).when(mathService).palindrome(anyString());
        doReturn(new Log()).when(logDao).save(any(Log.class));
        loggingService.palindrome(anyString());
        verify(mathService,times(1)).palindrome(anyString());
        verify(logDao,times(1)).save(any(Log.class));
    }

    @Test
    public void palindrome_exception() throws Exception {
        doThrow(new RuntimeException(error)).when(mathService).palindrome(anyString());
        doReturn(new Log()).when(logDao).save(any(Log.class));
        String returnValue = loggingService.palindrome("");
        assertEquals(returnValue, error);
    }

    @Test
    public void retrieveLogs() throws Exception {
        doReturn(new LinkedList<>()).when(logDao).findAll();
        loggingService.retrieveLogs();
        verify(loggingService,times(1)).retrieveLogs();
    }

}
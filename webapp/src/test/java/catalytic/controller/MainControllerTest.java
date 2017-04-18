package catalytic.controller;

import catalytic.model.Log;
import catalytic.model.LogDao;
import catalytic.service.LoggingService;
import catalytic.service.MathService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    private LogDao logDao;

    @Spy
    private MathService mathService;

    @Spy
    @InjectMocks
    private LoggingService loggingService;

    @Spy
    @InjectMocks
    private MainController mainController;

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void retrieveLogs() throws Exception {

    }

    @Test
    public void factorSuccess() throws Exception {
        final Long input = 7L;
        final MvcResult result =  mockMvc.perform(get("/factor")
                .param("input",input.toString()))
                .andExpect(status().isOk())
                .andReturn();

        List<Long> list = mapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertTrue(list.contains(1) && list.contains(7));
        assertEquals(2, list.size());
        verify(loggingService, times(1)).factor(input);
        verify(mathService, times(1)).factor(input);
        verify(logDao, times(1)).save(any(Log.class));
    }

    @Test
    public void factorRuntimeException() throws Exception {
        final Long input = -1L;
        doThrow(new RuntimeException()).when(mathService).factor(input);
        final MvcResult result = mockMvc.perform(get("/factor")
                .param("input",input.toString()))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("", result.getResponse().getContentAsString());
        verify(logDao, times(1)).save(any(Log.class));
    }

    @Test
    public void palindromeSuccess() throws Exception {
        final String input = "aba";
        final MvcResult result =  mockMvc.perform(get("/palindrome")
                .param("sentence",input))
                .andExpect(status().isOk())
                .andReturn();

        boolean returnValue = mapper.readValue(result.getResponse().getContentAsString(), Boolean.class);
        assertTrue(returnValue);
        verify(loggingService, times(1)).palindrome(input);
        verify(mathService, times(1)).palindrome(input);
        verify(logDao, times(1)).save(any(Log.class));
    }

    @Test
    public void fibonacciSuccess() throws Exception {
        final Long input = 3L;
        final MvcResult result =  mockMvc.perform(get("/fibonacci")
                .param("input",input.toString()))
                .andExpect(status().isOk())
                .andReturn();

        double returnValue = mapper.readValue(result.getResponse().getContentAsString(), Double.class);
        assertEquals(2, returnValue, 0);
        verify(loggingService, times(1)).fibonacci(input);
        verify(mathService, times(1)).fibonacci(input);
        verify(logDao, times(1)).save(any(Log.class));
    }

}
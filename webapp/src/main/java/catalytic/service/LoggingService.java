package catalytic.service;

import catalytic.model.Log;

import java.util.List;

/**
 * Created by gardncl on 2/16/17.
 */


public interface LoggingService {

    public String fibonacci(Long input) throws Exception;

    public String factor(Long input) throws Exception;

    public String palindrome(String input) throws Exception;

    public List<Log> retrieveLogs();
}

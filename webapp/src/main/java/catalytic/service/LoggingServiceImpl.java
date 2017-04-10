package catalytic.service;

import catalytic.model.Log;
import catalytic.model.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gardncl on 2/16/17.
 */

@Component
@Transactional
public class LoggingServiceImpl implements LoggingService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private MathService mathService;

    @Override
    public String fibonacci(Long input) throws RuntimeException  {
        try {
            Double returnValue = mathService.fibonacci(input);
            logDao.save(new Log("fibonacci",input.toString(),returnValue.toString(),null));
            return returnValue.toString();
        } catch (RuntimeException e){
            logDao.save(new Log("fibonacci",input.toString(),null,e.toString()));
            return e.getMessage();
        }
    }

    @Override
    public String factor(Long input) throws RuntimeException {
        try {
            List<Long> returnValue = mathService.factor(input);
            logDao.save(new Log("factor",input.toString(),returnValue.toString(),null));
            return returnValue.toString();
        } catch (RuntimeException e){
            logDao.save(new Log("factor",input.toString(),null,e.toString()));
            return e.getMessage();
        }
    }

    @Override
    public String palindrome(String input) throws RuntimeException  {
        try {
            Boolean returnValue = mathService.palindrome(input);
            logDao.save(new Log("palindrome",input.toString(),returnValue.toString(),null));
            return returnValue.toString();
        } catch (RuntimeException e){
            logDao.save(new Log("palindrome",input.toString(),null,e.toString()));
            return e.getMessage();
        }
    }

    public List<Log> retrieveLogs() {
        return logDao.findAll();
    }

}

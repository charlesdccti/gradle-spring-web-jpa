package catalytic.service;

import catalytic.model.Log;
import catalytic.model.LogDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LoggingService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private MathService mathService;

    private ObjectMapper mapper = new ObjectMapper();

    public String fibonacci(Long input) throws RuntimeException  {
        try {
            Double returnValue = mathService.fibonacci(input);
            logDao.save(new Log("fibonacci",input.toString(),returnValue.toString(),null));
            return mapper.writeValueAsString(returnValue);
        } catch (RuntimeException e){
            logDao.save(new Log("fibonacci",input.toString(),null,e.toString()));
            return e.getMessage();
        } catch (JsonProcessingException e) {
            logDao.save(new Log("fibonacci",input.toString(),null,e.toString()));
            return e.getMessage();
        }
    }

    public String factor(Long input) throws RuntimeException {
        try {
            List<Long> returnValue = mathService.factor(input);
            logDao.save(new Log("factor",input.toString(),returnValue.toString(),null));
            return mapper.writeValueAsString(returnValue);
        } catch (RuntimeException e){
            logDao.save(new Log("factor",input.toString(),null,e.toString()));
            return e.getMessage();
        } catch (JsonProcessingException e) {
            logDao.save(new Log("factor",input.toString(),null,e.toString()));
            return e.getMessage();
        }
    }

    public String palindrome(String input) throws RuntimeException  {
        try {
            Boolean returnValue = mathService.palindrome(input);
            logDao.save(new Log("palindrome",input,returnValue.toString(),null));
            return mapper.writeValueAsString(returnValue);
        } catch (RuntimeException e){
            logDao.save(new Log("palindrome",input,null,e.toString()));
            return e.getMessage();
        } catch (JsonProcessingException e) {
            logDao.save(new Log("palindrome",input,null,e.toString()));
            return e.getMessage();
        }
    }

    public List<Log> retrieveLogs() {
        return logDao.findAll();
    }

}

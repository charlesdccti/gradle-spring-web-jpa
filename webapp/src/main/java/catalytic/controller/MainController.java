package catalytic.controller;

import catalytic.model.Log;
import catalytic.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private LoggingService loggingService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Log> retrieveLogs() {
        return loggingService.retrieveLogs();
    }

    @RequestMapping(value = "/factor", method= RequestMethod.GET)
    public String factor(@RequestParam("input") Long input) throws Exception {
        return loggingService.factor(input);
    }

    @RequestMapping(value = "/palindrome", method= RequestMethod.GET)
    public String palindrome(@RequestParam("sentence") String string) throws Exception {
        return loggingService.palindrome(string);
    }

    @RequestMapping(value = "/fibonacci", method= RequestMethod.GET)
    public String fibonacci(@RequestParam("input") Long input) throws Exception {
        return loggingService.fibonacci(input);
    }
}

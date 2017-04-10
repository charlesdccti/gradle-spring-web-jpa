package catalytic.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gardncl on 2/16/17.
 */


public interface MathService {

    public Double fibonacci(Long input) throws RuntimeException;

    public List<Long> factor(Long input) throws RuntimeException;

    public boolean palindrome(String input) throws RuntimeException;
}

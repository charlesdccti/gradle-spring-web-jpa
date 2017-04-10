package catalytic.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by gardncl on 2/16/17.
 */

@Service
public class MathServiceImpl implements MathService {

    @Override
    public Double fibonacci(Long input) throws RuntimeException {
        if (input < 0) {
            throw new RuntimeException("ERROR: Input must be positive.");
        }
        Double n0 = 0D;
        Double n1 = 1D;
        Double tmp;
        for (long i = 0; i < input; i++) {
            tmp = n1;
            n1 = n0 + n1;
            n0 = tmp;
        }
        return n0;
    }

    @Override
    public List<Long> factor(Long input) throws RuntimeException {
        List<Long> factors = new LinkedList<>();
        if (input <= 0) {
            throw new RuntimeException("ERROR: Input must be positive.");
        }
        Long factor = 2L;
        Long half = input/2L;
        while(input > 1 && factor < half) {
            if(input%factor==0) {
                factors.add(factor);
                input = input/factor;
            } else {
                factor++;
            }
        }
        if (factors.size()==0 && factor > 1L) {
            factors.add(1L);
            factors.add(input);
        } else {
            factors.add(1L);
        }
        return factors;
    }

    @Override
    public boolean palindrome(String input) throws RuntimeException {
        if (input == "") {
            throw new RuntimeException("ERROR: Input is empty.");
        }
        int n = input.length() - 1;
        char[] array = input.toCharArray();
        for (int i = 0; i < n && i <= n/2; i++) {
            if (array[i]!=array[n-i]) {
                return false;
            }
        }
        return true;
    }
}

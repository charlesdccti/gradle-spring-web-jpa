package catalytic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by gardncl on 2/16/17.
 */

@Entity
public class Log {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String endpointName;

    @NotNull
    private String inputValue;

    private String returnValue;

    private String error;

    public Log() {
    }

    public Log(String endpointName, String inputValue, String returnValue, String error) {
        this.endpointName = endpointName;
        this.inputValue = inputValue;
        this.returnValue = returnValue;
        this.error = error;
    }

    public long getId() {
        return id;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

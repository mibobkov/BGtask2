package userdetails;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeInformationNotValid extends RuntimeException{
    public EmployeeInformationNotValid(String message) {
        super(message);
    }
}


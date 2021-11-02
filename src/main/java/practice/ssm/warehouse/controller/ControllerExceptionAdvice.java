package practice.ssm.warehouse.controller;

import practice.ssm.warehouse.Error;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Error> handleNestedRuntimeException(DataAccessException exception){
        Error error = new Error(exception.getClass().getSimpleName() , exception.getCause().getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

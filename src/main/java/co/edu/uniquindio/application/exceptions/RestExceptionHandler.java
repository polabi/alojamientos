package co.edu.uniquindio.application.exceptions;

import co.edu.uniquindio.application.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<List<ValidationDTO>>> validationExceptionHandler(
            MethodArgumentNotValidException ex
    ) {
        List<ValidationDTO> errors = new ArrayList<>();
        BindingResult results = ex.getBindingResult();

        for (FieldError e : results.getFieldErrors()) {
            errors.add(new ValidationDTO(e.getField(), e.getDefaultMessage()));
        }

        return ResponseEntity.badRequest()
                .body(new ResponseDTO<>(true, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> generalExceptionHandler(Exception e) {
        return ResponseEntity.internalServerError()
                .body(new ResponseDTO<>(true, e.getMessage()));
    }
}

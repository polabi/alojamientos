package co.edu.uniquindio.application.exceptions;

import co.edu.uniquindio.application.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO<String>> handleNotFound(NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO<>(true, ex.getMessage()));
    }

    @ExceptionHandler(ValueConflictException.class)
    public ResponseEntity<ResponseDTO<String>> handleConflict(ValueConflictException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseDTO<>(true, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO<>(true, "Error interno del servidor"));
    }
}

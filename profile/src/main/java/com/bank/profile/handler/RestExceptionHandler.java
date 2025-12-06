package com.bank.profile.handler;

import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.exception.ValidationException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Глобальный обработчик исключений REST.
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Обработка ошибок валидации входящих параметров.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        log.info("Ошибка валидации запроса: {}", exception.getMessage());
        Map<String, Object> body = baseBody(HttpStatus.BAD_REQUEST, "Некорректные данные");
        body.put("details", exception.getBindingResult().getFieldErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Обработка бизнес-валидации.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessValidation(ValidationException exception) {
        log.info("Ошибка бизнес-валидации: {}", exception.getMessage());
        Map<String, Object> body = baseBody(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /**
     * Обработка отсутствующих сущностей.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(EntityNotFoundException exception) {
        log.error("Сущность не найдена: {}", exception.getMessage());
        Map<String, Object> body = baseBody(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    private Map<String, Object> baseBody(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", OffsetDateTime.now());
        body.put("status", status.value());
        body.put("message", message);
        return body;
    }
}
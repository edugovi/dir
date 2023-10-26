package dev.edugovi.dir.controller;

import java.util.Optional;


import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.edugovi.dir.controller.exception.IdNotFoundException;
import dev.edugovi.dir.controller.exception.IdNumberFormatException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@RequestMapping(produces =  "application/vnd.error+json" )
public class UserControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(UserControllerAdvice.class);

    @ExceptionHandler(value = ConstraintViolationException.class)
    public HttpEntity<Problem> errorConstraintViolation(HttpServletRequest req, final ConstraintViolationException cve) {
        final String message =
                Optional.of(cve.getMessage()).orElse(cve.getClass().getSimpleName());

        log.error("Request: " + req.getRequestURL() + " raised " + cve);
        //return new ResponseEntity<>(Problem("Constraint violation exception", message), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(new Problem(null, "Constraint violation exception", HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IdNumberFormatException.class)
    public HttpEntity<Problem> handleIdNumberFormatException(HttpServletRequest req, final IdNumberFormatException infe) {
        log.error("Request: " + req.getRequestURL() + " raised " + infe.getMessage());
        return new ResponseEntity<>(new Problem(null, "Number format exception", HttpStatus.INTERNAL_SERVER_ERROR.value(), infe.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IdNotFoundException.class)
    public HttpEntity<Problem> handleIdNotFoundException(HttpServletRequest req, final IdNotFoundException inf) {
        log.error("Request: " + req.getRequestURL() + " raised " + inf.getMessage());
        return new ResponseEntity<>(new Problem(null, "Not found exception", HttpStatus.INTERNAL_SERVER_ERROR.value(), inf.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public HttpEntity<Problem> error(HttpServletRequest req, final Exception e) {
        final String message =
                Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());

        log.error("Request: " + req.getRequestURL() + " raised " + e);
        return new ResponseEntity<>(new Problem(null, "Exception", HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

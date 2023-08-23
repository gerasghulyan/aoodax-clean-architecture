package com.aoodax.platform.entrypoint.rest.exception.handler;

import com.aoodax.platform.contract.input.exception.EntityAlreadyExistsException;
import com.aoodax.platform.contract.input.exception.EntityNotFoundException;
import com.aoodax.platform.contract.input.exception.EntityValidationException;
import com.aoodax.platform.entrypoint.rest.common.response.error.ErrorReasonResponseModel;
import com.aoodax.platform.entrypoint.rest.common.response.error.ErrorResponseModel;
import com.aoodax.platform.entrypoint.rest.common.response.error.ValidationErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationRestGlobalExceptionHandler {

    private static final String INVALID_JSON_FORMAT_MESSAGE = "Invalid Json format";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseBody
    public ErrorResponseModel handleException(final WebExchangeBindException e) {
        final String defaultMessage = e.getAllErrors()
                .stream()
                .map(objectError -> Objects.requireNonNull(objectError.getDefaultMessage()))
                .findAny()
                .orElseThrow();

        return ErrorResponseModel
                .builder()
                .error(ErrorReasonResponseModel.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(defaultMessage)
                        .build()
                )
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServerWebInputException.class)
    @ResponseBody
    public ErrorResponseModel handleServerWebInputException(final ServerWebInputException e) {
        return getErrorResponseModel(HttpStatus.BAD_REQUEST, INVALID_JSON_FORMAT_MESSAGE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityValidationException.class)
    @ResponseBody
    public ErrorResponseModel handleValidationException(final EntityValidationException e) {
        return getErrorResponseModel(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponseModel handleResourceAlreadyExistException(final EntityAlreadyExistsException e) {
        return getErrorResponseModel(HttpStatus.CONFLICT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ErrorResponseModel handleNotFoundException(final EntityNotFoundException e) {
        return getErrorResponseModel(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ErrorResponseModel handleIllegalArgumentException(final IllegalArgumentException e) {
        return getErrorResponseModel(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ValidationErrorResponseModel handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final Map<String, String> errorMap = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, fieldError -> Optional.ofNullable(fieldError.getDefaultMessage()).orElse("")));
        return ValidationErrorResponseModel.builder().errors(errorMap).build();
    }

    private ErrorResponseModel getErrorResponseModel(final HttpStatus code,
                                                     final String errorMessage) {
        return ErrorResponseModel
                .builder()
                .error(ErrorReasonResponseModel.builder()
                        .code(code.value())
                        .message(errorMessage)
                        .build()
                )
                .build();
    }
}

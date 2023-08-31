package com.aoodax.platform.entrypoint.rest.api.handler;

import com.aoodax.jvm.common.rest.dto.response.error.ErrorReasonResponseModel;
import com.aoodax.jvm.common.rest.dto.response.error.ErrorResponseModel;
import com.aoodax.jvm.common.rest.dto.response.error.ValidationErrorResponseModel;
import com.aoodax.platform.contract.input.exception.AlreadyExistsException;
import com.aoodax.platform.contract.input.exception.NotFoundException;
import com.aoodax.platform.contract.input.exception.ValidationException;
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
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ErrorResponseModel handleValidationException(final ValidationException e) {
        return getErrorResponseModel(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseBody
    public ErrorResponseModel handleResourceAlreadyExistException(final AlreadyExistsException e) {
        return getErrorResponseModel(HttpStatus.CONFLICT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorResponseModel handleNotFoundException(final NotFoundException e) {
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

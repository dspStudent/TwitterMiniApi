package com.twitter.mini.globalExceptions;
import com.twitter.mini.globalExceptions.exceptionImp.*;
import com.twitter.mini.util.ErrorHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmptyContentGetException.class)
    public ResponseEntity<ErrorHandler> emptyContentGetException(EmptyContentGetException exception, WebRequest webRequest){
        ErrorHandler error=new ErrorHandler(HttpStatus.NO_CONTENT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(error);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorHandler> userNotFoundException(UserNotFoundException exception, WebRequest webRequest){
        ErrorHandler error=new ErrorHandler(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(UserIsAlredyExistedException.class)
    public ResponseEntity<ErrorHandler> userIsAlredyExistedException(UserIsAlredyExistedException exception, WebRequest webRequest){
        ErrorHandler error=new ErrorHandler(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
    @ExceptionHandler(UserNotVerfiedException.class)
    public ResponseEntity<ErrorHandler> userNotVerfiedException(UserNotVerfiedException exception, WebRequest webRequest){
        ErrorHandler error=new ErrorHandler(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(UserVerificationPinIsWrongException.class)
    public ResponseEntity<ErrorHandler> userVerficationIsWrongException(UserVerificationPinIsWrongException exception, WebRequest webRequest){
        ErrorHandler errorHandler=new ErrorHandler(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.badRequest().body(errorHandler);
    }

    @ExceptionHandler(UserVerficationPinExpiredException.class)
    public ResponseEntity<ErrorHandler> userVerficationPinExpiredException(UserVerficationPinExpiredException exception, WebRequest webRequest){
        ErrorHandler errorHandler=new ErrorHandler(HttpStatus.REQUEST_TIMEOUT, exception.getMessage());
        return ResponseEntity.badRequest().body(errorHandler);
    }

    @ExceptionHandler(UserTokenIsInvalidException.class)
    public ResponseEntity<ErrorHandler> userTokenIsInvalidException(UserTokenIsInvalidException exception, WebRequest webRequest){
        ErrorHandler errorHandler=new ErrorHandler(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.badRequest().body(errorHandler);
    }

    @ExceptionHandler(UserTokenExpiredException.class)
    public ResponseEntity<ErrorHandler> userTokenExpiredException(UserTokenExpiredException exception, WebRequest webRequest){
        ErrorHandler errorHandler=new ErrorHandler(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.badRequest().body(errorHandler);
    }

    @ExceptionHandler(PasswordAndConfrimPasswordsAreWrongException.class)
    public ResponseEntity<ErrorHandler> passwordAndConfrimPasswordsAreWrongException(PasswordAndConfrimPasswordsAreWrongException exception, WebRequest webRequest){
        ErrorHandler errorHandler=new ErrorHandler(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.badRequest().body(errorHandler);
    }
    @ExceptionHandler(UserCredentailsAreWrong.class)
    public ResponseEntity<ErrorHandler> userCredentailsAreWrong(UserCredentailsAreWrong exception, WebRequest webRequest){
        ErrorHandler errorHandler=new ErrorHandler(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return new ResponseEntity<>(errorHandler, HttpStatus.UNAUTHORIZED);
    }

}

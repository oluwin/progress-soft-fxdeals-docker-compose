package com.bloomberg.fx.deals.exception;

import com.bloomberg.fx.deals.message.FeedbackMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadException extends ResponseEntityExceptionHandler {
    public ResponseEntity<FeedbackMessage> handleUploadMaxSizeException(MaxUploadSizeExceededException mse){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new FeedbackMessage("Uploaded File too large!"));
    }
}

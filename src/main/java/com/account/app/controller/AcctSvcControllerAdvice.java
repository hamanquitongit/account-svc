package com.account.app.controller;


import com.account.app.domain.constants.APIStatusCodes;
import com.account.app.domain.responses.ExceptionResponse;
import com.account.app.service.exceptions.CharacterLengthException;
import com.account.app.service.exceptions.CustomerNotFoundException;
import com.account.app.service.exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AcctSvcControllerAdvice {

    @ExceptionHandler(CharacterLengthException.class)
    public ResponseEntity<ExceptionResponse> handleCharLengthException(CharacterLengthException e) {
        ExceptionResponse exResponse = new ExceptionResponse();
        exResponse.setTransactionStatusCode(APIStatusCodes.CHAR_LENGTH_ERROR.getStatusCode());
        exResponse.setTransactionStatusDescription(APIStatusCodes.CHAR_LENGTH_ERROR.getDesc());
        return ResponseEntity.status(APIStatusCodes.CHAR_LENGTH_ERROR.getStatusCode())
                .body(exResponse);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestException(InvalidRequestException e) {
        ExceptionResponse exResponse = new ExceptionResponse();
        exResponse.setTransactionStatusCode(APIStatusCodes.BAD_REQUEST.getStatusCode());
        exResponse.setTransactionStatusDescription(APIStatusCodes.BAD_REQUEST.getDesc());
        return ResponseEntity.status(APIStatusCodes.BAD_REQUEST.getStatusCode()).body(exResponse);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public  ResponseEntity<ExceptionResponse> handleInvalidFormatException(InvalidFormatException e) {
        ExceptionResponse exResponse = new ExceptionResponse();
        exResponse.setTransactionStatusCode(APIStatusCodes.INVALID_ACCT_TYPE.getStatusCode());
        exResponse.setTransactionStatusDescription(APIStatusCodes.INVALID_ACCT_TYPE.getDesc());
        return ResponseEntity.status(APIStatusCodes.INVALID_ACCT_TYPE.getStatusCode()).body(exResponse);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public  ResponseEntity<ExceptionResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
        ExceptionResponse exResponse = new ExceptionResponse();
        exResponse.setTransactionStatusCode(APIStatusCodes.CUST_NOT_FOUND.getStatusCode());
        exResponse.setTransactionStatusDescription(APIStatusCodes.CUST_NOT_FOUND.getDesc());
        return ResponseEntity.status(APIStatusCodes.CUST_NOT_FOUND.getStatusCode()).body(exResponse);
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ExceptionResponse> handleGenericException(Exception e) {
        log.error("Exception encountered: {}", e.getMessage());
        ExceptionResponse exResponse = new ExceptionResponse();
        if(e.getClass().equals(HttpMessageNotReadableException.class)) {
            exResponse.setTransactionStatusCode(APIStatusCodes.INVALID_ACCT_TYPE.getStatusCode());
            exResponse.setTransactionStatusDescription(APIStatusCodes.INVALID_ACCT_TYPE.getDesc());
            return ResponseEntity.status(APIStatusCodes.INVALID_ACCT_TYPE.getStatusCode()).body(exResponse);
        } else {
            exResponse.setTransactionStatusCode(APIStatusCodes.INTERNAL_ERROR.getStatusCode());
            exResponse.setTransactionStatusDescription(APIStatusCodes.INTERNAL_ERROR.getDesc());
            return ResponseEntity.status(APIStatusCodes.INTERNAL_ERROR.getStatusCode()).body(exResponse);
        }
    }
}

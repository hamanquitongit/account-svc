package com.account.app.domain.responses;

import lombok.Data;

@Data
public class ExceptionResponse {
    private int transactionStatusCode;
    private String transactionStatusDescription;
}

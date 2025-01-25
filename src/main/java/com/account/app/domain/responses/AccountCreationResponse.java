package com.account.app.domain.responses;

import lombok.Data;

@Data
public class AccountCreationResponse {
    private String customerNumber;
    private int transactionStatusCode;
    private String transactionStatusDescription;
}

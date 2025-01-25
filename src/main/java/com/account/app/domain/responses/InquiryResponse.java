package com.account.app.domain.responses;


import com.account.app.domain.requests.Account;
import lombok.Data;

@Data
public class InquiryResponse {
    private String customerNumber;
    private String customerName;
    private String customerMobile;
    private String customerEmail;
    private String address1;
    private String address2;
    private Account[] savings;
    private int transactionStatusCode;
    private String transactionStatusDescription;
}

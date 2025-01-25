package com.account.app.domain.requests;


import com.account.app.domain.constants.AccountType;
import lombok.Data;

@Data
public class AccountCreationRequest {
    private String customerName;
    private String customerMobile;
    private String customerEmail;
    private String address1;
    private String address2;
    private AccountType accountType;


}

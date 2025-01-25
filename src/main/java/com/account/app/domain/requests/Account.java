package com.account.app.domain.requests;

import lombok.Data;

@Data
public class Account {
    private long accountNumber;
    private String accountType;
    private double availableBalance;
}

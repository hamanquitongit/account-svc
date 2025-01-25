package com.account.app.domain.constants;

import lombok.Getter;

@Getter
public enum AccountType {
    S('S', "Savings"),
    C('C',"Checking");

    private final String desc;
    private final char code;
    AccountType(char code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

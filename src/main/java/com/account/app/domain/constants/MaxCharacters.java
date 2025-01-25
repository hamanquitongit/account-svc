package com.account.app.domain.constants;

public enum MaxCharacters {
    CUSTOMER_NAME(50),
    CUSTOMER_MOBILE(20),
    CUSTOMER_EMAIL(50),
    CUSTOMER_ADDRESS(100);

    public final int value;

    MaxCharacters(int value) {
        this.value = value;
    }
}

package com.account.app.domain.constants;

import lombok.Getter;

@Getter
public enum APIStatusCodes {

    CREATED(201, "Customer account created"),
    CUST_ACCT_FOUND(302, "Customer Account found"),
    BAD_REQUEST(400, "Email is required field"),
    CHAR_LENGTH_ERROR(400, "Character Length Error"),
    INVALID_ACCT_TYPE(400, "Invalid Account Type"),
    CUST_NOT_FOUND(401, "Customer not found"),
    INTERNAL_ERROR(500, "Internal Error Encountered");



    private final int statusCode;
    private final String desc;

    APIStatusCodes(int statusCode, String desc) {
        this.statusCode = statusCode;
        this.desc = desc;
    }
}

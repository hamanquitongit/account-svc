package com.account.app.service;

import com.account.app.domain.constants.APIStatusCodes;
import com.account.app.domain.constants.MaxCharacters;
import com.account.app.domain.requests.AccountCreationRequest;
import com.account.app.service.exceptions.CharacterLengthException;
import com.account.app.service.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class ValidationService {

    public void validateAccountCreationRequest(AccountCreationRequest request) throws InvalidRequestException, CharacterLengthException {
        log.info("Validating Request: {}", request);
        if(!StringUtils.hasText(request.getCustomerEmail())) {
            log.error("Error encountered: {}: {}", APIStatusCodes.BAD_REQUEST.getStatusCode(), APIStatusCodes.BAD_REQUEST.getDesc());
            throw new InvalidRequestException(APIStatusCodes.BAD_REQUEST.getDesc());
        }
        if(request.getCustomerName().length() > MaxCharacters.CUSTOMER_NAME.value) {
            log.error("Error encountered: Characters for CustomerName exceeded max value:{}", MaxCharacters.CUSTOMER_NAME.value);
            throw new CharacterLengthException("CustomerName exceeded max value");
        }
        if(request.getCustomerMobile().length() > MaxCharacters.CUSTOMER_MOBILE.value) {
            log.error("Error encountered: Characters for CustomerMobile exceeded max value:{}", MaxCharacters.CUSTOMER_MOBILE.value);
            throw new CharacterLengthException("CustomerMobile exceeded max value");
        }
        if(request.getCustomerEmail().length() > MaxCharacters.CUSTOMER_EMAIL.value) {
            log.error("Error encountered: Characters for CustomerEmail exceeded max value:{}", MaxCharacters.CUSTOMER_EMAIL.value);
            throw new CharacterLengthException("CustomerEmail exceeded max value");
        }
        if(request.getAddress1().length() > MaxCharacters.CUSTOMER_ADDRESS.value) {
            log.error("Error encountered: Characters for CustomerEmail exceeded max value:{}", MaxCharacters.CUSTOMER_ADDRESS.value);
            throw new CharacterLengthException("CustomerAddress1 exceeded max value");
        }
        if(request.getAddress2().length() > MaxCharacters.CUSTOMER_ADDRESS.value) {
            log.error("Error encountered: Characters for CustomerEmail exceeded max value:{}", MaxCharacters.CUSTOMER_ADDRESS.value);
            throw new CharacterLengthException("CustomerAddress2 exceeded max value");
        }
    }
}

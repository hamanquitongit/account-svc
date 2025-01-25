package com.account.app.controller;


import com.account.app.domain.requests.AccountCreationRequest;
import com.account.app.domain.responses.AccountCreationResponse;
import com.account.app.domain.responses.InquiryResponse;
import com.account.app.service.AccountService;
import com.account.app.service.CustomerInquiryService;
import com.account.app.service.ValidationService;
import com.account.app.service.exceptions.CharacterLengthException;
import com.account.app.service.exceptions.CustomerNotFoundException;
import com.account.app.service.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AcctSvcController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerInquiryService inquiryService;
    @Autowired
    private ValidationService validationService;


    @PostMapping()
    public AccountCreationResponse createAccount(@RequestBody AccountCreationRequest request) throws InvalidRequestException, CharacterLengthException {
        validationService.validateAccountCreationRequest(request);
        return accountService.createAccount(request);
    }

    @GetMapping("/{customerNumber}")
    public InquiryResponse getCustomerData(@PathVariable long customerNumber) throws CustomerNotFoundException {
        return inquiryService.getCustomerData(customerNumber);
    }

}

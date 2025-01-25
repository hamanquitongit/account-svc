package com.account.app.service;

import com.account.app.db.entities.AccountEntity;
import com.account.app.db.entities.CustomerEntity;
import com.account.app.db.repositories.AccountRepository;
import com.account.app.db.repositories.CustomerRepository;
import com.account.app.domain.constants.APIStatusCodes;
import com.account.app.domain.requests.AccountCreationRequest;
import com.account.app.domain.responses.AccountCreationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    public AccountCreationResponse createAccount(AccountCreationRequest request) {
        AccountCreationResponse response = new AccountCreationResponse();
        log.info("Request Received: {}", request);
        CustomerEntity customer = buildCustomerEntity(request);
        log.info("Saving to H2 DB");
        customerRepository.save(customer);
        response.setCustomerNumber(String.valueOf(customer.getCustomerNumber()));
        response.setTransactionStatusCode(APIStatusCodes.CREATED.getStatusCode());
        response.setTransactionStatusDescription(APIStatusCodes.CREATED.getDesc());

        return response;
    }


    private CustomerEntity buildCustomerEntity(AccountCreationRequest request) {
        CustomerEntity customer = new CustomerEntity();
        AccountEntity acct = new AccountEntity();
        List<AccountEntity> savings = new ArrayList<AccountEntity>();

        customer.setCustomerName(request.getCustomerName());
        customer.setCustomerMobile(request.getCustomerMobile());
        customer.setCustomerEmail(request.getCustomerEmail());
        customer.setAddress1(request.getAddress1());
        customer.setAddress2(request.getAddress2());
        customer.setCustomerNumber(generateCustomerId());
        acct.setAccountType(String.valueOf(request.getAccountType().getDesc()));
        acct.setAccountNumber(generateAcctId(request));
        savings.add(acct);
        customer.setSavings(savings);

        return customer;
    }

    private String generateCustomerId() {
        String id = String.valueOf(customerRepository.count() + 1);
        log.info("Generating Customer Number: {}", id);
        return id;
    }

    private long generateAcctId(AccountCreationRequest request) {
        long id =  accountRepository.countByAccountType(String.valueOf(request.getAccountType().getCode()))+1;
        log.info("Generating Account Number: {}", id);
        return id;
    }
}

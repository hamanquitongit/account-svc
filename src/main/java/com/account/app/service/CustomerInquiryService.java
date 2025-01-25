package com.account.app.service;

import com.account.app.db.entities.AccountEntity;
import com.account.app.db.entities.CustomerEntity;
import com.account.app.db.repositories.AccountRepository;
import com.account.app.db.repositories.CustomerRepository;
import com.account.app.domain.constants.APIStatusCodes;
import com.account.app.domain.requests.Account;
import com.account.app.domain.responses.InquiryResponse;
import com.account.app.service.exceptions.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Slf4j
@Service
public class CustomerInquiryService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    public InquiryResponse getCustomerData(long param) throws CustomerNotFoundException {
        String customerNumber = String.valueOf(param);
        log.info("FindbyId: {}", customerRepository.findByCustomerNumber(customerNumber));

        if(customerRepository.findByCustomerNumber(customerNumber).isPresent()) {
            CustomerEntity entity = customerRepository.findByCustomerNumber(customerNumber).get();
            return buildResponse(entity);
        } else {
            throw new CustomerNotFoundException(APIStatusCodes.CUST_NOT_FOUND.getDesc());
        }
    }

    private InquiryResponse buildResponse(CustomerEntity entity) {
        InquiryResponse response = new InquiryResponse();

        response.setCustomerNumber(entity.getCustomerNumber());
        response.setCustomerName(entity.getCustomerName());
        response.setCustomerMobile(entity.getCustomerMobile());
        response.setCustomerEmail(entity.getCustomerEmail());
        response.setAddress1(entity.getAddress1());
        response.setAddress2(entity.getAddress2());
        if(!entity.getSavings().isEmpty()) {
            ArrayList<Account> savingsList = new ArrayList<Account>();
                for(AccountEntity entityAcct : entity.getSavings()) {
                    Account account = new Account();
                    account.setAccountNumber(entityAcct.getAccountNumber());
                    account.setAccountType(entityAcct.getAccountType());
                    account.setAvailableBalance(entityAcct.getAvailableBalance());
                    savingsList.add(account);
                }
                response.setSavings(savingsList.toArray(new Account[0]));
                log.info("new Array Generated: {}", savingsList.toArray(new Account[0]));
        }
        response.setTransactionStatusCode(APIStatusCodes.CUST_ACCT_FOUND.getStatusCode());
        response.setTransactionStatusDescription(APIStatusCodes.CUST_ACCT_FOUND.getDesc());
        return response;
    }
}

package com.account.app.config;

import com.account.app.db.entities.AccountEntity;
import com.account.app.db.entities.CustomerEntity;
import com.account.app.db.repositories.AccountRepository;
import com.account.app.db.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Initializer {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void initializeTestData() {

        log.info("Initializing H2 Test Data...");
        AccountEntity acctEntity = new AccountEntity();
        CustomerEntity custEntity = new CustomerEntity();
        List<AccountEntity> savings = new ArrayList<AccountEntity>();

        custEntity.setCustomerNumber("12345678");
        custEntity.setCustomerName("Test");
        custEntity.setCustomerEmail("test12345@gmail.com");
        custEntity.setCustomerMobile("09081234567");
        custEntity.setAddress1("test");
        custEntity.setAddress2("test");
        acctEntity.setAccountType("Savings");
        acctEntity.setAccountNumber(10001);
        acctEntity.setAvailableBalance(500);
        savings.add(acctEntity);
        custEntity.setSavings(savings);

        customerRepository.save(custEntity);
    }
}

package com.account.app.db.repositories;


import com.account.app.db.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByCustomerNumber(String customerNumber);
}

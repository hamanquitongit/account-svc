package com.account.app.db.repositories;


import com.account.app.db.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    @Query("SELECT COUNT(a) FROM AccountEntity a WHERE a.accountType = :acctType")
    long countByAccountType(String acctType);
}

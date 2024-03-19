package com.example.accounta.repository;
import com.example.accounta.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountUserRepository extends
        JpaRepository<AccountUser,Long> { }
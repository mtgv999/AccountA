package com.example.accounta.repository;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
    Optional<Transaction> findByTransactionId(String transactionId);}
package ir.payeshgaran.atmversion1.service;

import ir.payeshgaran.atmversion1.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction  save(Transaction transaction);
    List<Transaction> getUserTransactions(Long userId);
    List<Transaction> findAll();


}

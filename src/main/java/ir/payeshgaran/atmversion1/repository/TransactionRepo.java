package ir.payeshgaran.atmversion1.repository;

import ir.payeshgaran.atmversion1.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Override
    Transaction save(Transaction transaction);

    @Override
    List<Transaction> findAll();

    List<Transaction> getUserTransactions(@Param("accountId") Long accountId);


}

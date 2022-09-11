package ir.payeshgaran.atmversion1.service.impl;

import ir.payeshgaran.atmversion1.exeption.AccountNotFound;
import ir.payeshgaran.atmversion1.model.Account;
import ir.payeshgaran.atmversion1.model.Transaction;
import ir.payeshgaran.atmversion1.repository.TransactionRepo;
import ir.payeshgaran.atmversion1.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final AccountServiceImpl accountService;


    @Override
    public Transaction save(Transaction transaction) throws AccountNotFound {
        Optional<Account> depositor = accountService.findById(transaction.getDepositorID());
        Optional<Account> receiver = accountService.findById(transaction.getReceiverID());
        if (depositor != null && receiver != null) {
            depositor.get().getTransactions().add(transaction);
            depositor.get().setRemain(depositor.get().getRemain() - transaction.getBalance());
            receiver.get().setRemain(receiver.get().getRemain() + transaction.getBalance());

        } else
            throw new AccountNotFound("Account Number Not Found");
        return transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> getUserTransactions(Long accountId) {
        return transactionRepo.getUserTransactions(accountId);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }
}

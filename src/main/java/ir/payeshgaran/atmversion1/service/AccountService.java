package ir.payeshgaran.atmversion1.service;

import ir.payeshgaran.atmversion1.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account save(Account account);
    List<Account> findAll();
    Account findAccountByAccountNumber(String accountNumber);
    Optional<Account> findById(Long aLong);


}

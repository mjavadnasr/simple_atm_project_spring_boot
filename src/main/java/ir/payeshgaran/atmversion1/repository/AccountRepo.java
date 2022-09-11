package ir.payeshgaran.atmversion1.repository;

import ir.payeshgaran.atmversion1.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account , Long> {
    Account save(Account account);

    @Override
    List<Account> findAll();
    Account findAccountByAccountNumber(String accountNumber);

    @Override
    Optional<Account> findById(Long aLong);



}

package ir.payeshgaran.atmversion1.service.impl;

import ir.payeshgaran.atmversion1.exeption.AccountNotFound;
import ir.payeshgaran.atmversion1.model.Account;
import ir.payeshgaran.atmversion1.repository.AccountRepo;
import ir.payeshgaran.atmversion1.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service  @Slf4j
public class AccountServiceImpl implements AccountService , UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public Account save(Account account) {
        log.info("save method of account service called");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepo.save(account);
    }


    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }



    @Override
    public Account findAccountByAccountNumber(String accountNumber) throws AccountNotFound {
        return accountRepo.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public Optional<Account> findById(Long aLong) throws AccountNotFound {
        return accountRepo.findById(aLong);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepo.findAccountByAccountNumber(username);
        if (account==null)
        {
            log.info("ACCOUNT NOT FOUND");
            throw new UsernameNotFoundException("ACCOUNT NOT FOUND");
        }
        else {
            log.info("ACCOUNT {} FOUND",username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(account.getAccountNumber(),account.getPassword(),authorities);
    }
}

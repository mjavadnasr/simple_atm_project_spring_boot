package ir.payeshgaran.atmversion1.controller;


import ir.payeshgaran.atmversion1.DTO.AccountDTO;
import ir.payeshgaran.atmversion1.DTO.TransactionDTO;
import ir.payeshgaran.atmversion1.mapper.AccountMapper;
import ir.payeshgaran.atmversion1.mapper.TransactionMapper;
import ir.payeshgaran.atmversion1.model.Account;
import ir.payeshgaran.atmversion1.model.Transaction;
import ir.payeshgaran.atmversion1.service.impl.AccountServiceImpl;
import ir.payeshgaran.atmversion1.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private TransactionServiceImpl transactionService;


    @GetMapping("/remain")
    public String getRemain(Model model) {
        AccountMapper accountMapper = new AccountMapper(new Account(), new AccountDTO());
        Account account = accountService.findAccountByAccountNumber(loggedInAccountDetails());
        double remain = accountMapper.toDTO(account).getRemain();
        model.addAttribute(
                "remain",
                remain
        );
        return "remain";
    }


    @GetMapping("/transaction")
    public String myTransactions(Model model) {
        Long accountId = accountService.findAccountByAccountNumber(loggedInAccountDetails()).getId();

        TransactionMapper transactionMapper = new TransactionMapper(new Transaction(), new TransactionDTO(), accountService);

        List<TransactionDTO> transactionDTOS = transactionMapper.toDTOS(transactionService.getUserTransactions(accountId));

        model.addAttribute("transactions", transactionDTOS);
        model.addAttribute("remain", accountService.findAccountByAccountNumber(loggedInAccountDetails()).getRemain());

        return "transactions";
    }


    //getting logged in user info

    public String loggedInAccountDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }


}

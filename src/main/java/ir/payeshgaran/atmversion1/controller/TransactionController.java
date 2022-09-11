package ir.payeshgaran.atmversion1.controller;

import ir.payeshgaran.atmversion1.DTO.TransactionDTO;
import ir.payeshgaran.atmversion1.mapper.TransactionMapper;
import ir.payeshgaran.atmversion1.model.Account;
import ir.payeshgaran.atmversion1.model.Transaction;
import ir.payeshgaran.atmversion1.service.impl.AccountServiceImpl;
import ir.payeshgaran.atmversion1.service.impl.TransactionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@Slf4j
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;
    @Autowired
    private AccountServiceImpl accountService;



    @GetMapping("/")
    public String showForm(Model model) {
        TransactionDTO transaction = new TransactionDTO();
        model.addAttribute("transaction", transaction);
        return "form";
    }


    //saving transaction to DB and check LoginSuccess and Receiver Filed is entered and can account do transaction with enterd money

    @PostMapping("/")
    public String transaction(
            @ModelAttribute("transaction") @Valid TransactionDTO transactionDTO,
            BindingResult result, RedirectAttributes redirAttrs) {

        if (result.hasErrors()) {
            return "form";
        }

        Account depositor = accountService.findAccountByAccountNumber(loggedInAccountDetails());
        Account receiver = accountService.findAccountByAccountNumber(transactionDTO.getReceiver());
        TransactionMapper transactionMapper = new TransactionMapper(new Transaction(), new TransactionDTO(), accountService);


        if (validation(depositor, receiver, redirAttrs, transactionDTO).equals("validated")) {
            transactionDTO.setDepositor(depositor.getAccountNumber());
            transactionService.save(transactionMapper.toEntity(transactionDTO));

            redirAttrs.addFlashAttribute("success", "Done!!");
            return "redirect:/dashboard/";
        }
        redirAttrs.addFlashAttribute("myerror", "Something wrong try again later.");
        return "redirect:/transaction/";
    }



    private String validation(Account depositor, Account receiver, RedirectAttributes redirAttrs, TransactionDTO transactionDTO) {
        if (depositor == null) {
            redirAttrs.addFlashAttribute("myerror", "Login Again.");
            return "redirect:/login";
        }
        if (receiver == null) {
            redirAttrs.addFlashAttribute("myerror", "Account Number Cannot be null OR ACCOUNT NOT FOUND.");
            return "redirect:/transaction/";
        }
        if (receiver.getAccountNumber() == loggedInAccountDetails()) {
            redirAttrs.addFlashAttribute("myerror", "YOU CANNOT TRANSFER MONEY TO YOURSELF.");
            return "redirect:/transaction/";
        }
        if (depositor.getRemain() < transactionDTO.getBalance()) {

            redirAttrs.addFlashAttribute("myerror", "You Dont Have Enough Money.");
            return "redirect:/transaction/";
        }

        return "validated";

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


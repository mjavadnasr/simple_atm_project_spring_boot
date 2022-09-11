package ir.payeshgaran.atmversion1.controller;

import ir.payeshgaran.atmversion1.DTO.AccountDTO;
import ir.payeshgaran.atmversion1.mapper.AccountMapper;
import ir.payeshgaran.atmversion1.model.Account;
import ir.payeshgaran.atmversion1.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;


@Controller
public class LoginController {

    @Autowired
    private AccountServiceImpl accountService;


    @GetMapping("/")
    public String getAccounts(Model model) {
        AccountMapper accountMapper = new AccountMapper(new Account(), new AccountDTO());
        List<AccountDTO> account = accountMapper.toDTOS(accountService.findAll());

        model.addAttribute("accounts", account);

        return "home";
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        String username = loggedInAccountDetails();

        model.addAttribute("username", username);

        return "dashboard";
    }

    //getting logged in account info

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

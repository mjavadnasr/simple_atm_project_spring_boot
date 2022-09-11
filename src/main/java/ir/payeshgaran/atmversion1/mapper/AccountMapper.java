package ir.payeshgaran.atmversion1.mapper;


import ir.payeshgaran.atmversion1.DTO.AccountDTO;
import ir.payeshgaran.atmversion1.model.Account;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//mapping Account DTO to Entity and reverse

@AllArgsConstructor
public class AccountMapper {

    private Account account;
    private AccountDTO accountDTO;


    public Account toEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setRemain(accountDTO.getRemain());
        account.setPassword(accountDTO.getPassword());
        return account;
    }

    public AccountDTO toDTO(Account Account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(Account.getAccountNumber());
        accountDTO.setRemain(Account.getRemain());
        accountDTO.setPassword(Account.getPassword());
        return accountDTO;
    }

    public List<Account> toEntities(List<AccountDTO> accountDTOS)
    {
        List<Account> accounts = new ArrayList<>();
        for (AccountDTO dto : accountDTOS) {
            Account account = new Account();
            account.setAccountNumber(dto.getAccountNumber());
            account.setPassword(dto.getPassword());
            account.setRemain(dto.getRemain());
            accounts.add(account);

        }
        return accounts;
    }
    public List<AccountDTO> toDTOS(List<Account> accounts){
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account account : accounts) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setPassword(account.getPassword());
            accountDTO.setRemain(account.getRemain());
            accountDTOList.add(accountDTO);

        }
        return accountDTOList;
    }

}

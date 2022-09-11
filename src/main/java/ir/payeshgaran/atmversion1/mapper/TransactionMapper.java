package ir.payeshgaran.atmversion1.mapper;


import ir.payeshgaran.atmversion1.DTO.TransactionDTO;
import ir.payeshgaran.atmversion1.model.Transaction;
import ir.payeshgaran.atmversion1.service.impl.AccountServiceImpl;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//mapping Transaction DTO to Entity and reverse


@AllArgsConstructor
public class TransactionMapper {


    private Transaction transaction;
    private TransactionDTO transactionDTO;
    private AccountServiceImpl accountService;



    public Transaction toEntity(TransactionDTO transactionDTO) {
        Long depositorId = accountService.findAccountByAccountNumber(transactionDTO.getDepositor()).getId();
        Long receiverId = accountService.findAccountByAccountNumber(transactionDTO.getReceiver()).getId();

        transaction.setBalance(transactionDTO.getBalance());
        transaction.setDepositorID(depositorId);
        transaction.setReceiverID(receiverId);

        return transaction;
    }

    public TransactionDTO toDTO(Transaction transaction) {
        String depositor = accountService.findById(transaction.getDepositorID()).get().getAccountNumber();
        String receiver = accountService.findById(transaction.getReceiverID()).get().getAccountNumber();
        transactionDTO.setBalance(transaction.getBalance());
        transactionDTO.setDepositor(depositor);
        transactionDTO.setReceiver(receiver);
        return transactionDTO;
    }

    public List<Transaction> toEntities(List<TransactionDTO> transactionDTOS) {
        List<Transaction> transactions = new ArrayList<>();
        for (TransactionDTO dto : transactionDTOS) {

            Transaction transaction = new Transaction();
            Long depositorId = accountService.findAccountByAccountNumber(dto.getDepositor()).getId();
            Long receiverId = accountService.findAccountByAccountNumber(dto.getReceiver()).getId();

            transaction.setBalance(dto.getBalance());
            transaction.setDepositorID(depositorId);
            ;
            transaction.setReceiverID(receiverId);
            transactions.add(transaction);
        }
        return transactions;
    }

    public List<TransactionDTO> toDTOS(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDTO transactionDTO = new TransactionDTO();

            String depositor = accountService.findById(transaction.getDepositorID()).get().getAccountNumber();
            String receiver = accountService.findById(transaction.getReceiverID()).get().getAccountNumber();

            transactionDTO.setBalance(transaction.getBalance());
            transactionDTO.setDepositor(depositor);
            transactionDTO.setReceiver(receiver);
            transactionDTOS.add(transactionDTO);

        }
        return transactionDTOS;
    }


}

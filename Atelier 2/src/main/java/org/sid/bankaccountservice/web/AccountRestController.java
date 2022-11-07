package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.DTO.BankAccountRequestDTO;
import org.sid.bankaccountservice.DTO.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccount/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("Account %s not found", id)));
    }

    @DeleteMapping("/bankAccount/{id}")
    public void delete(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);

    }

    @PutMapping("/bankAccount/{id}")
    public BankAccount save(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount bankAccount1 = bankAccountRepository.findById(id).orElseThrow();

        if (bankAccount.getBalance() != null) bankAccount1.setBalance(bankAccount.getBalance());
        if (bankAccount.getCurrency() != null) bankAccount1.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getCreatedAt() != null) bankAccount1.setCreatedAt(new Date());
        if (bankAccount.getType() != null) bankAccount1.setType(bankAccount.getType());

        return bankAccountRepository.save(bankAccount1);
    }
}

package org.sid.bankaccountservice.bankaccountservice.web;

import org.sid.bankaccountservice.DTO.BankAccountRequestDTO;
import org.sid.bankaccountservice.DTO.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.repositories.CustomerRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountService accountService;

    @QueryMapping
    public List<BankAccount> accountsList() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account not found ")));
    }

    @QueryMapping
    public List<Customer> customers() {
        return customerRepository.findAll();
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount) {
        System.out.println(bankAccount.getCurrency());
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount) {

        return accountService.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id) {

        bankAccountRepository.deleteById(id);
        return true;
    }

}


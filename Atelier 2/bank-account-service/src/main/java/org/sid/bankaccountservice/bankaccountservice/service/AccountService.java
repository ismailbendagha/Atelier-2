package org.sid.bankaccountservice.bankaccountservice.service;

import org.sid.bankaccountservice.DTO.BankAccountRequestDTO;
import org.sid.bankaccountservice.DTO.BankAccountResponseDTO;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}

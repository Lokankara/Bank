package com.wallet.bank.service;

import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import com.wallet.bank.exceptions.ClientExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

@Service
@AllArgsConstructor
public class BankDataLoaderService {

    public void readClients(Bank bank, String fileName) {
        if (fileName == null) {
            return;
        }
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new BufferedReader(new FileReader(fileName)));
            String line;
            while ((line = lineNumberReader.readLine()) != null) {
                addClient(bank, Client.parseClient(line));
            }
            lineNumberReader.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void addClient(Bank bank, Client client) {
        try {
            ClientBankService.addClient(bank, client);
        } catch (ClientExistsException e) {
            System.err.println(e.getMessage());
        }
    }
}

package com.luxoft.bank.bank.service;

import com.luxoft.bank.bank.Bank;
import com.luxoft.bank.domain.Client;
import com.luxoft.bank.exceptions.ClientExistsException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class BankDataLoaderService {
    private final Bank bank;

    public BankDataLoaderService(Bank bank) {
        this.bank = bank;
    }

    public void readClients(String fileName) {
        if (fileName == null) {
            return;
        }

        try {
            LineNumberReader lineNumberReader =
                    new LineNumberReader(
                            new BufferedReader(
                                    new FileReader(fileName)));
            String line;
            while ((line = lineNumberReader.readLine()) != null) {
                addClient(Client.parseClient(line));
            }

            lineNumberReader.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void addClient(Client client) {
        try {
            BankService.addClient(bank, client);
        } catch (ClientExistsException e) {
            System.err.println(e.getMessage());
        }
    }

}

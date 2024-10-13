package com.wallet.bank.service;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import com.wallet.bank.exceptions.ClientExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Slf4j
@Service
public class ClientBankService {
    private String serializationFileName;

    public void addClient(Bank bank, Client client) throws ClientExistsException {
        bank.addClient(client);
        saveBank(bank);
    }

    public Client getClient(Bank bank, String name) {
        return bank.getClient(name);
    }

    public void printMaximumAmountToWithdraw(Bank bank) {
        System.out.format("%nPrint maximum amount to withdraw for all clients%n");

        StringBuilder result = new StringBuilder();
        for (Client client : bank.getClients()) {
            result.append("Client: ")
                    .append(client)
                    .append("\n");
            int i = 1;
            for (IAccount IAccount : client.getIAccounts()) {
                result.append("Account nr. ")
                        .append(i++)
                        .append(", maximum amount to withdraw: ")
                        .append(Math.round(IAccount.maximumAmountToWithdraw() * 100) / 100d)
                        .append("\n");
            }
        }
        log.info(result.toString());
    }

    public void saveBank(Bank bank) {
        if (serializationFileName == null) {
            return;
        }

        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    serializationFileName));
            oos.writeObject(bank);
            oos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Bank readBank() {
        if (serializationFileName == null || !new File(serializationFileName).exists()) {
            return null;
        }

        Bank bank = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializationFileName));
            bank = (Bank) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException e) {
            log.error(e.getMessage());
        }
        return bank;
    }
}

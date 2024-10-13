package com.wallet.bank.service;

import com.wallet.bank.account.IAccount;
import com.wallet.bank.domain.Bank;
import com.wallet.bank.domain.Client;
import com.wallet.bank.exceptions.ClientExistsException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@Slf4j
public class BankService {
    private static String serialization;

    public BankService(String serialization) {
        BankService.serialization = serialization;
    }

    public static void addBank(Bank bank) {

        if (serialization != null) {
            try (FileOutputStream file =
                         new FileOutputStream(serialization);

                 ObjectOutputStream output =
                         new ObjectOutputStream(file)) {

                output.writeObject(bank);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Bank readBank() {
        //TODO Read Bank
        if (serialization != null) {

            try (FileInputStream file = new FileInputStream(serialization);

                 ObjectInputStream input = new ObjectInputStream(file)) {

                return (Bank) input.readObject();

            } catch (Exception e) {

                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static void addClient(Bank bank, Client client) throws ClientExistsException {
        bank.addClient(client);
        addBank(bank);
    }

    public static Client readClient(String username) {
        //TODO Read Client
        Bank bank = readBank();

        List<Client> clients = bank.getClients();

        return clients.stream()
                .filter(client -> client.getName().equals(username))
                .findFirst()
                .orElse(null);
    }

    public static void printMaximumAmountToWithdraw(Bank bank) {
        System.out.printf("%nPrint maximum amount to withdraw for all clients%n");

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
}

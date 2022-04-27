package com.luxoft.bank.bank.service;

import com.luxoft.bank.account.Account;
import com.luxoft.bank.bank.Bank;
import com.luxoft.bank.domain.Client;
import com.luxoft.bank.exceptions.ClientExistsException;

import java.io.*;

public class BankService {

    private static String serializationFileName;

    public static void setSerializationFileName(String serializationFileName) {
        BankService.serializationFileName = serializationFileName;
    }

    public static void addClient(Bank bank, Client client) throws ClientExistsException {
        bank.addClient(client);
        saveBank(bank);
    }

    public static Client getClient(Bank bank, String name) {
        return bank.getClient(name);
    }

    public static void printMaximumAmountToWithdraw(Bank bank) {
        System.out.format("%nPrint maximum amount to withdraw for all clients%n");

        StringBuilder result = new StringBuilder();
        for (Client client : bank.getClients()) {
            result.append("Client: ")
                    .append(client)
                    .append("\n");
            int i = 1;
            for (Account account : client.getAccounts()) {
                result.append("Account nr. ")
                        .append(i++)
                        .append(", maximum amount to withdraw: ")
                        .append(Math.round(account.maximumAmountToWithdraw() * 100) / 100d)
                        .append("\n");
            }
        }
        System.out.println(result);
    }

    public static void saveBank(Bank bank) {
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

    public static Bank readBank() {
        if (serializationFileName == null) {
            return null;
        }
        // check if file does exist and exit if not
        if (!new File(serializationFileName).exists()) {
            return null;
        }

        Bank bank = null;

        try {
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(
                                    serializationFileName));
            bank = (Bank) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println(e.getMessage());
        }

        return bank;
    }

}

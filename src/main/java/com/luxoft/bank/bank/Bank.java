package com.luxoft.bank.bank;

import com.luxoft.bank.domain.Client;
import com.luxoft.bank.domain.Gender;
import com.luxoft.bank.email.Email;
import com.luxoft.bank.email.EmailException;
import com.luxoft.bank.email.EmailService;
import com.luxoft.bank.exceptions.ClientExistsException;
import com.luxoft.bank.utils.ClientRegistrationListener;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.*;

@Getter
public class Bank implements Serializable {

    @Serial
    private static final long serialVersionUID = -4157871135257285214L;
    private final Set<Client> clients = new HashSet<>();
    private final ArrayList<ClientRegistrationListener> listeners = new ArrayList<>();
    private EmailService emailService;

    private final Client admin = new Client("Admin", Gender.MALE);
    private final Client system = new Client("System", Gender.MALE);

    private final int printedClients = 0;
    private final int emailedClients = 0;
    private final int debuggedClients = 0;

    public Bank() {
        listeners.add(new PrintClientListener());
        listeners.add(new EmailNotificationListener());
        listeners.add(new DebugListener());
        admin.setCity("New York");
        admin.setPhoneAreaCode("0123");
        admin.setPhoneNumber("9876543");

        system.setCity("Boston");
        system.setPhoneAreaCode("0121");
        system.setPhoneNumber("9875043");


    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void addClient(final Client client) throws ClientExistsException {
        if (clients.contains(client)) {
            throw new ClientExistsException(
                    "Client already exists into the bank");
        }

        clients.add(client);
        notify(client);
    }

    private void notify(Client client) {
        for (ClientRegistrationListener listener : listeners) {
            listener.onClientAdded(client);
        }
    }

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(clients);
    }

    public Client getClient(String name) {
        for (Client client : clients)
            if (client.getName().equals(name))
                return client;
        return null;
    }

    static class PrintClientListener implements ClientRegistrationListener, Serializable {
        @Serial
        private static final long serialVersionUID = 2777987742204604236L;

        @Override
        public void onClientAdded(Client client) {
            System.out.println("Client added: " + client.getName());
        }

    }

    class EmailNotificationListener implements ClientRegistrationListener, Serializable {
        @Serial
        private static final long serialVersionUID = -2360873324733537279L;

        @Override
        public void onClientAdded(Client client) {
            System.out.println(
                    "Notification email for client " + client.getName() + " to be sent");

            if (emailService != null) {
                try {
                    emailService.sendNotificationEmail(
                            new Email()
                                    .setFrom(system)
                                    .setTo(admin)
                                    .setCopy(client)
                                    .setTitle("Client Added Notification")
                                    .setBody("Client added: " + client)
                    );
                } catch (EmailException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    static class DebugListener implements ClientRegistrationListener, Serializable {
        @Serial
        private static final long serialVersionUID = -7600469994081192859L;

        @Override
        public void onClientAdded(Client client) {
            System.out.println("Client " + client.getName() + " added on: " + DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
        }
    }
}





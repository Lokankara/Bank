package com.wallet.bank.domain;

import com.wallet.bank.exceptions.ClientExistsException;
import com.wallet.bank.service.EmailService;
import com.wallet.bank.utils.ClientRegistrationListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Getter
public class Bank implements Serializable {
    private final List<Client> clients = new ArrayList<Client>();
    private final List<ClientRegistrationListener> listeners = new ArrayList<>();
    private final EmailService emailService = new EmailService();

    private int printedClients = 0;
    private int emailedClients = 0;
    private int debuggedClients = 0;

    public Bank() {
        listeners.add(new PrintClientListener());
        listeners.add(new EmailNotificationListener());
        listeners.add(new DebugListener());
    }

    public void addClient(final Client client) throws ClientExistsException {
        if (!clients.contains(client)) {
            clients.add(client);
            notify(client);
        } else {
            throw new ClientExistsException("Client already exists into the bank");
        }
    }

    private void notify(Client client) {
        for (ClientRegistrationListener listener : listeners) {
            listener.onClientAdded(client);
        }
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public void close() {
        emailService.close();
    }

    class PrintClientListener implements ClientRegistrationListener {
        @Override
        public void onClientAdded(Client client) {
            printedClients++;
            log.info(String.format("Client added: %s%n", client.getName()));
        }

    }

    class EmailNotificationListener implements ClientRegistrationListener {
        @Override
        public void onClientAdded(Client client) {
            emailedClients++;
            log.info(String.format("Notification email for client %s to be sent", client.getName()));
        }
    }

    class DebugListener implements ClientRegistrationListener {
        @Override
        public void onClientAdded(Client client) {
            debuggedClients++;
            log.info(String.format("Client %s added on: %s", client.getName(), DateFormat.getDateInstance(DateFormat.FULL).format(new Date())));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank bank)) return false;
        return printedClients == bank.printedClients && emailedClients == bank.emailedClients
                && debuggedClients == bank.debuggedClients && Objects.equals(clients, bank.clients)
                && Objects.equals(listeners, bank.listeners) && Objects.equals(emailService, bank.emailService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clients, listeners, emailService, printedClients, emailedClients, debuggedClients);
    }
}

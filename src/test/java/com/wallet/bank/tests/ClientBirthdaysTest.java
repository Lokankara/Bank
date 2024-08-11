package com.wallet.bank.tests;

import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ClientBirthdaysTest {
    Set<Client> clients = new LinkedHashSet<>();

    @BeforeEach
    public void setUp() {
        clients.add(new Client("Client1", Gender.MALE, LocalDate.of(1998, 10, 5)));
        clients.add(new Client("Client2", Gender.MALE, LocalDate.of(1965, 11, 2)));
        clients.add(new Client("Client3", Gender.MALE, LocalDate.of(2000, 12, 13)));
        clients.add(new Client("Client4", Gender.MALE, LocalDate.of(1992, 1, 14)));
        clients.add(new Client("Client5", Gender.MALE, LocalDate.of(1960, 2, 2)));
        clients.add(new Client("Client6", Gender.MALE, LocalDate.of(1985, 9, 20)));
        clients.add(new Client("Client7", Gender.MALE, LocalDate.of(1990, 10, 3)));
        clients.add(new Client("Client8", Gender.MALE, LocalDate.of(1984, 11, 1)));
        clients.add(new Client("Client9", Gender.MALE, LocalDate.of(1987, 12, 7)));
        clients.add(new Client("Client10", Gender.MALE, LocalDate.of(1991, 12, 8)));
    }

    @Test
    public void clientsBirthdaysByMonth() {
        for (Client client : clients) {
            log.info(client.getName() + " " + client.getBirthday() + ": " + client.daysUntilBirthday());
        }

        Map<Integer, List<Client>> clientsBirthdaysByMonth = clients
                .stream()
                .collect(Collectors.groupingBy(Client::getBirthdayMonth));

        log.info(clientsBirthdaysByMonth.toString());

        Map<Integer, List<Client>> expectedClients = new HashMap<>();
        expectedClients.put(1, List.of(new Client("Client4", Gender.MALE, LocalDate.of(1992, 1, 14))));
        expectedClients.put(2, List.of(new Client("Client5", Gender.MALE, LocalDate.of(1960, 2, 2))));
        expectedClients.put(9, List.of(new Client("Client6", Gender.MALE, LocalDate.of(1985, 9, 20))));
        expectedClients.put(10, Arrays.asList(new Client("Client1", Gender.MALE, LocalDate.of(1998, 10, 5)),
                new Client("Client7", Gender.MALE, LocalDate.of(1990, 10, 3))));
        expectedClients.put(11, Arrays.asList(new Client("Client2", Gender.MALE, LocalDate.of(1965, 11, 2)),
                new Client("Client8", Gender.MALE, LocalDate.of(1984, 11, 1))));
        expectedClients.put(12, Arrays.asList(new Client("Client3", Gender.MALE, LocalDate.of(2000, 12, 13)),
                new Client("Client9", Gender.MALE, LocalDate.of(1987, 12, 7)),
                new Client("Client10", Gender.MALE, LocalDate.of(1991, 12, 8))));

        assertEquals(expectedClients, clientsBirthdaysByMonth);
    }
}

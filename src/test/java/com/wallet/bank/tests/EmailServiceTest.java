package com.wallet.bank.tests;

import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Email;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.exceptions.BankException;
import com.wallet.bank.service.EmailService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EmailServiceTest {
    private static final int EMAILS = 20;
    private Client client;
    private Client to;

    @BeforeEach
    public void setup() {
        client = new Client("Andrew", Gender.MALE, "Kiev");
        to = new Client("Brian", Gender.MALE, "Lvov");
    }

    @Test
    public void testSend() throws InterruptedException {

        EmailService emailService = new EmailService();
        for (int i = 0; i < EMAILS; i++) {
            try {
                emailService.sendNotificationEmail(
                        new Email(client, List.of(to), "Email Service")
                );
            } catch (BankException e) {
                System.err.println(e.getMessage());
            }
            Thread.sleep(100);
        }
        assertEquals(EMAILS, emailService.getEmailCounter());
    }
}

package com.wallet.bank.tests;

import com.wallet.bank.domain.Client;
import com.wallet.bank.domain.Gender;
import com.wallet.bank.email.Email;
import com.wallet.bank.email.EmailException;
import com.wallet.bank.email.EmailService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class ClientSendMailTest {

    @Test
    public void testSendMail() throws InterruptedException {
        Client client = new Client("John Smith", Gender.MALE);
        client.setCity("New York");
        client.setPhoneAreaCode("0123");
        client.setPhoneNumber("9876543");
        Client clientTo = new Client("Michael Johnson", Gender.MALE);
        clientTo.setCity("Boston");
        clientTo.setPhoneAreaCode("0121");
        clientTo.setPhoneNumber("9866543");
        Client clientCopy = new Client("Michelle Williams", Gender.FEMALE);
        clientCopy.setCity("Boston");
        clientCopy.setPhoneAreaCode("0121");
        clientCopy.setPhoneNumber("9276543");

        EmailService emailService = new EmailService();
        for (int i = 0; i < 10; i++) {
            try {
                emailService.sendNotificationEmail(
                        new Email()
                                .setFrom(client)
                                .setTo(clientTo)
                                .setCopy(clientCopy)
                                .setTitle("Client Added Notification")
                                .setBody("Your e-mail has been sent!")
                );
            } catch (EmailException e) {
                System.err.println(e.getMessage());
            }
            Thread.sleep(1000);
        }

        assertEquals(10, emailService.getSentEmails());
    }
}

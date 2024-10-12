package com.wallet.bank;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = BankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = "spring.ai.openai.api-key=test-api-key")
class BankApplicationTests {

    @Test
    void contextLoads() {
    }
}

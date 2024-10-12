package com.wallet.bank;

import com.wallet.bank.config.OpenAIRestTemplateConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootTest(classes = BankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = OpenAIRestTemplateConfig.class))
class BankApplicationTests {

    @Test
    void contextLoads() {
    }
}

package com.wallet.bank;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaRepositories("com.wallet.bank.dao")
@EntityScan("com.wallet.bank.domain")
public class TestConfig {
}
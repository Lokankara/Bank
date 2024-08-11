package com.wallet.bank.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public record Email(Client from, List<Client> to, String message) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email)) return false;
        return Objects.equals(from, email.from) && Objects.equals(to, email.to) && Objects.equals(message, email.message);
    }
}

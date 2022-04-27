package com.luxoft.bank.domain;

import com.luxoft.bank.account.AbstractAccount;
import com.luxoft.bank.account.Account;
import com.luxoft.bank.utils.Params;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = -6343841891631428291L;
    private final String name;
    private final Gender gender;
    private String phoneAreaCode;
    private String phoneNumber;
    private Set<Account> accounts = new HashSet<>();
    private String city;

    public Client(String name, Gender gender) {
        this(name, gender, new ArrayList<>());
    }

    public Client(String name, Gender gender, Collection<Account> accounts) {
        this.name = name;
        this.gender = gender;
        this.accounts.addAll(accounts);
    }

    public Client(String name, Gender gender, Account[] accounts) {
        this(name, gender, Arrays.asList(accounts));
    }

    public Client(final String name, final Gender gender, Account account) {
        this(name, gender, new Account[]{account});
    }

    public String getClientGreeting() {
        if (gender != null) {
            return gender.getGreeting() + " " + name;
        } else {
            return name;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(getClientGreeting())
                .append("\n").append("City: ").append(getCity())
                .append("\n").append("phoneAreaCode: ").append(getPhoneAreaCode())
                .append("\n").append("phoneNumber: ").append(getPhoneNumber())
                .append("\n");

        for (Account account : getAccounts()) {
            builder.append(account).append("\n");
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (gender != other.gender)
            return false;
        if (name == null) {
            return other.name == null;
        } else return name.equals(other.name);
    }

    public static Client parseClient(String str) {
        Params params = new Params(str.split(";"));

        Client client = new Client(
                params.get("name"),
                Gender.parse(params.get("gender")),
                AbstractAccount.parse(params));

        client.setCity(params.get("city"));

        return client;
    }
}

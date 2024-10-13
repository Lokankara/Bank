package com.wallet.bank.domain;

import com.wallet.bank.utils.Params;
import com.wallet.bank.account.AbstractIAccount;
import com.wallet.bank.account.IAccount;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = -6343841891631428291L;
    private final String name;
    private final Gender gender;
    private String phoneAreaCode;
    private String phoneNumber;
    @Setter
    private Set<IAccount> IAccounts = new HashSet<>();
    private String city;
    private final LocalDate birthday;

    public Client(String name, Gender gender) {
        this(name, gender, new ArrayList<>());
    }

    public Client(String name, Gender gender, Collection<IAccount> IAccounts) {
        this.name = name;
        this.gender = gender;
        this.IAccounts.addAll(IAccounts);
        this.birthday = LocalDate.now();
    }

    public Client(String name, Gender gender, String city) {
        this.name = name;
        this.gender = gender;
        this.city = city;
        this.birthday = LocalDate.now();
    }

    public Client(String name, Gender gender, IAccount[] IAccounts) {
        this(name, gender, Arrays.asList(IAccounts));
    }

    public Client(final String name, final Gender gender, IAccount IAccount) {
        this(name, gender, new IAccount[]{IAccount});
    }

    public Client(String client, Gender gender, LocalDate date) {
        this.name = client;
        this.gender = gender;
        this.birthday = date;
    }

    public String getClientGreeting() {
        if (gender != null) {
            return gender.getGreeting() + " " + name;
        } else {
            return name;
        }
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
                AbstractIAccount.parse(params));

        client.setCity(params.get("city"));

        return client;
    }

    public Set<IAccount> getIAccounts() {
        return Collections.unmodifiableSet(IAccounts);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(getClientGreeting())
                .append("\n").append("City: ").append(getCity())
                .append("\n").append("phoneAreaCode: ").append(getPhoneAreaCode())
                .append("\n").append("phoneNumber: ").append(getPhoneNumber())
                .append("\n");

        for (IAccount IAccount : getIAccounts()) {
            builder.append(IAccount).append("\n");
        }
        return builder.toString();
    }

    public void addAccount(final IAccount IAccount) {
        IAccounts.add(IAccount);
    }

    public long daysUntilBirthday() {
        LocalDate today = LocalDate.now();
        Temporal temporal = LocalDate.of(today.getYear(), birthday.getMonth(), birthday.getDayOfMonth());

        long daysInBetween = ChronoUnit.DAYS.between(today, temporal);
        if (daysInBetween < 0) {
            daysInBetween += 365;
        }

        return daysInBetween;
    }

    public int getBirthdayMonth() {
        return birthday.getMonth().getValue();
    }

}

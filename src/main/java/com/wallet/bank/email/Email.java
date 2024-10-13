package com.wallet.bank.email;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wallet.bank.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable {

	@Serial
    private static final long serialVersionUID = -3686472195559526951L;
	private Client from;
    private List<Client> to, copy;
    private String title, body;

    public Email setFrom(Client from) {
        this.from = from;
        return this;
    }

    public Email setTo(List<Client> to) {
        this.to = to;
        return this;
    }

    public Email setTo(Client to) {
    	ArrayList<Client> toList = new ArrayList<>();
    	toList.add(to);
        setTo(toList);
        return this;
    }

    public Email setCopy(ArrayList<Client> copy) {
        this.copy = copy;
        return this;
    }

    public Email setCopy(Client copy) {
    	ArrayList<Client> copyList = new ArrayList<>();
    	copyList.add(copy);
        setCopy(copyList);
        return this;
    }

    public Email setTitle(String title) {
        this.title = title;
        return this;
    }

    public Email setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
    	List<Client> clients = getTo();
    	StringBuilder clientsTo = new StringBuilder();
    	for (Client c: clients) {
    		clientsTo.append(c);
    	}

    	clients = getCopy();
    	StringBuilder clientsCopy = new StringBuilder();
    	for (Client c: clients) {
    		clientsCopy.append(c);
    	}

    	return "SEND EMAIL:" + "\n" +
    			"From: " + getFrom() +
    			"To: " + clientsTo +
    			"Copy: " + clientsCopy +
    			"Title: " + getTitle() + "\n" +
    			"Body: " + getBody() + "\n";
    }
}

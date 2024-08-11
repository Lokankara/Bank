package com.wallet.bank.utils;

import com.wallet.bank.domain.Client;

public interface ClientRegistrationListener {

    void onClientAdded(Client client);
}

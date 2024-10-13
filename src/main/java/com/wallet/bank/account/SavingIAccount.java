package com.wallet.bank.account;

import java.io.Serial;
import java.util.Formatter;
import java.util.Locale;

import com.wallet.bank.domain.Currency;
import com.wallet.bank.utils.Params;

public class SavingIAccount extends AbstractIAccount {

	@Serial
	private static final long serialVersionUID = 9200460687227050240L;
	private Currency currency;

	public SavingIAccount(int id, double amount) {
		super(id, amount);
		this.setType(AbstractIAccount.SAVING_ACCOUNT_TYPE);
	}
	
	public SavingIAccount(int id, double amount, Currency currency) {
		super(id, amount);
		this.currency = currency;
		this.setType(AbstractIAccount.SAVING_ACCOUNT_TYPE);
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	@Override
	public String toString() {
		Formatter fmt = new Formatter(Locale.US);
		String stringAccount = fmt.format(
				"Saving account %d, balance: %.2f",
				getId(), getBalance()).toString();
		fmt.close();
		return stringAccount;
	}
	
	public static IAccount parse(Params params) {
		String id = params.get("id");
		String balance = params.get("balance");
		String currency = params.get("currency");
		
        return new SavingIAccount(
        		Integer.parseInt(id), 
        		Double.parseDouble(balance),
        		new Currency(currency));
    }
	
}

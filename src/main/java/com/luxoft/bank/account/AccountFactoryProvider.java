package com.luxoft.bank.account;

public class AccountFactoryProvider implements AccountFactory {
    @Override
    public Account createAccount(AccountDetails details){
        int id = details.getId();
        double amount = details.getAmount();
        String type = details.getType();

        if (type.equals("s")){
            return new SavingAccount(id, amount);
        }else if (type.equals("c")){
            return new CheckingAccount(id, amount,0.1);
        } else {
            System.out.println("Invalid type");
        }
        return null;
    }
}

package com.wallet.bank.employees;


public class FinancialSpecialist extends AbstractSpecialist {

    @Override
    public boolean makeDecision(
            double amount,
            double creditRate,
            double clientSalary,
            boolean propertyGuarantee) {

        boolean decision = creditRate <= clientSalary / 3;

        if (null != this.nextSpecialist && decision) {
            System.out.format(
                    "Approved at the %s level, passing to next specialist%n",
                    this.getClass().getSimpleName());

            return this.nextSpecialist.makeDecision(
                    amount, creditRate, clientSalary, propertyGuarantee);
        }

        printFinalDecision(decision);

        return decision;
    }
}

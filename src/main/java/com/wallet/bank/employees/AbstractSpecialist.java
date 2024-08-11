package com.wallet.bank.employees;


public abstract class AbstractSpecialist extends BankEmployee {

    private static final double OTHER_COMMISSION = 0.005;
    private static final double PROPERTY_GUARANTEE_COMMISSION = 0.002;
    //next element in chain or responsibility
    protected AbstractSpecialist nextSpecialist;

    public void setNextSpecialist(AbstractSpecialist nextSpecialist) {
        this.nextSpecialist = nextSpecialist;
    }

    public void printFinalDecision(boolean decision) {
        if (decision) {
            System.out.format("Final approval at the %s level%n", this.getClass().getSimpleName());
        } else {
            System.out.format("Rejected at the %s level%n", this.getClass().getSimpleName());
        }
    }

    public static double calculateAnalysisCommission(
            double amount, boolean propertyGuarantee) {

        double commission;
        if (propertyGuarantee) {
            commission = amount * PROPERTY_GUARANTEE_COMMISSION;
        } else {
            commission = amount * OTHER_COMMISSION;
        }
        return commission;
    }

    abstract public boolean makeDecision(
            double amount,
            double creditRate,
            double clientSalary,
            boolean propertyGuarantee);
}

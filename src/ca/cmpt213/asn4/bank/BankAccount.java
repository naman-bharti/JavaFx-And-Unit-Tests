package ca.cmpt213.asn4.bank;

/**
 * Bank Account class creates a bank account with several variables and
 * methods such as balance, annualRate, calcInterest and so on.
 */

public abstract class BankAccount {
    private double balance;
    private int nDeposits;
    private int nWithdrawals;
    private double annualRate;
    private double serviceCharges;

    public double getBalance() {
        return this.balance;
    }

    public int getNWithdrawals() {
        return this.nWithdrawals;
    }

    public void setServiceCharges(double serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    BankAccount (double balance, double annualRate) throws IllegalArgumentException {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot negative");
        }
        if (annualRate < 0) {
            throw new IllegalArgumentException("Annual interest rate cannot be negative");
        }

        this.balance = balance;
        this.annualRate = annualRate;
    }

    public void deposit (double deposit) throws IllegalArgumentException {
        if (deposit <= 0) {
            throw new IllegalArgumentException("Deposit amount cannot be zero or negative");
        }

        this.balance = this.balance + deposit;
        nDeposits++;
    }

    public void withdraw (double withdraw) throws IllegalArgumentException {
        if (withdraw <= 0) {
            throw new IllegalArgumentException("Withdraw amount cannot be zero or negative");
        }
        if (withdraw > this.balance) {
            throw new IllegalArgumentException("Withdraw amount cannot be more than the account balance");
        }

        this.balance = this.balance - withdraw;
        nWithdrawals++;
    }

    public void calcInterest () {
        double monthlyRate = this.annualRate / 12;
        double monthlyInterestAmount = this.balance * (monthlyRate/100);
        this.balance = this.balance + monthlyInterestAmount;
    }

    public void monthlyProcess () {
        this.balance = this.balance - serviceCharges;
        calcInterest();
        nWithdrawals = 0;
        nDeposits = 0;
        serviceCharges = 0;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNDeposits() {
        return nDeposits;
    }

    public void setNDeposits(int nDeposits) {
        this.nDeposits = nDeposits;
    }

    public void setNWithdrawals(int nWithdrawals) {
        this.nWithdrawals = nWithdrawals;
    }

    public double getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(double annualRate) {
        this.annualRate = annualRate;
    }

    public double getServiceCharges() {
        return serviceCharges;
    }
}
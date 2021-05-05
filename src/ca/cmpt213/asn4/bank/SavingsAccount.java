package ca.cmpt213.asn4.bank;

/**
 * Savings account class creates a savings account by extending the bank account class
 * and its own variable, status.
 */

public class SavingsAccount extends BankAccount {
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    SavingsAccount(double balance, double annualRate) throws IllegalArgumentException {
        super(balance, annualRate);
        checkStatus();
    }

    private boolean checkStatus() {
        if (getBalance() > 25) {
            status = true;
            return true;
        }
        else {
            status = false;
            return false;
        }
    }

    @Override
    public void withdraw(double withdraw) throws IllegalArgumentException {
        if (checkStatus()) {
            super.withdraw(withdraw);
            checkStatus();
        }
        else {
            throw new IllegalArgumentException("Withdrawal not allowed. Account is inactive.");
        }
    }

    @Override
    public void deposit(double deposit) throws IllegalArgumentException {
        if (getBalance() + deposit > 25) {
            super.deposit(deposit);
            checkStatus();
        }
        else {
            throw new IllegalArgumentException("Deposit not allowed. Account is inactive." +
                                                            " Please make a sufficient deposit.");
        }
    }

    @Override
    public void monthlyProcess() {
        if (getNWithdrawals() > 4) {
            setServiceCharges(getNWithdrawals() - 4);
        }
        super.monthlyProcess();
        checkStatus();
    }
}
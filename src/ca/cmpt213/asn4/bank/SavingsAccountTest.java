package ca.cmpt213.asn4.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Savings account test class creates several tests for testing the savings account class
 *  which also ensures the testing of bank account class.
 */

class SavingsAccountTest {

    @Test
    void constructor() {
        // Normal case
        SavingsAccount account1 = new SavingsAccount(20, 0.2);
        assertEquals(account1.getBalance(), 20.0);
        assertEquals(account1.getAnnualRate(), 0.2);

        // Error case for annual rate
        assertThrows(IllegalArgumentException.class, () ->{
            SavingsAccount account2 = new SavingsAccount(20, -1);
        });

        // Error case for balance
        assertThrows(IllegalArgumentException.class, () ->{
            SavingsAccount account3 = new SavingsAccount(-1, 0.2);
        });
    }

    @Test
    void calcInterest() {
        SavingsAccount account = new SavingsAccount(10, 12);
        account.calcInterest();
        assertEquals(account.getBalance(), 10.1, 0.1);
    }

    @Test
    void withdraw() {
        SavingsAccount account = new SavingsAccount(30,2);

        // Normal case
        account.withdraw(3);
        assertEquals(account.getBalance(), 27);
        assertEquals(account.getNWithdrawals(), 1);
        assertTrue(account.getStatus());

        // Error case for withdrawing less than or equal to 0
        assertThrows(IllegalArgumentException.class, () ->{
            account.withdraw(-1);
        });

        // Error case for withdrawing more than the balance in account
        assertThrows(IllegalArgumentException.class, () ->{
            account.withdraw(30);
        });

        // Normal case
        account.withdraw(7);
        assertEquals(account.getBalance(), 20);
        assertEquals(account.getNWithdrawals(), 2);

        // Error case for withdrawing when account is inactive
        assertFalse(account.getStatus());
        assertThrows(IllegalArgumentException.class, () ->{
            account.withdraw(10);
        });
    }

    @Test
    void deposit() {
        SavingsAccount account = new SavingsAccount(30, 2);

        // Normal case
        account.deposit(10);
        assertEquals(account.getBalance(), 40);
        assertEquals(account.getNDeposits(), 1);
        assertTrue(account.getStatus());

        // Error case for depositing less than or equal to 0
        assertThrows(IllegalArgumentException.class, () ->{
            account.deposit(-1);
        });

        // Error case for not depositing sufficient amount
        account.withdraw(20);
        assertFalse(account.getStatus());
        assertThrows(IllegalArgumentException.class, () ->{
            account.deposit(3);
        });

        // Normal case for depositing sufficient amount
        account.deposit(20);
        assertEquals(account.getBalance(), 40);
        assertEquals(account.getNDeposits(), 2);
        assertTrue(account.getStatus());

    }

    @Test
    void monthlyProcess() {
        SavingsAccount account = new SavingsAccount(50, 2);

        // Normal case for number of withdrawals less than or equal to 4
        account.withdraw(2);
        account.withdraw(2);
        assertEquals(account.getNWithdrawals(), 2);
        account.monthlyProcess();
        assertEquals(account.getBalance(), 46.1, 0.1);
        assertEquals(account.getNWithdrawals(), 0);
        assertEquals(account.getServiceCharges(), 0);

        // Normal case for number of withdrawals more than 4
        account.withdraw(3);
        account.withdraw(3);
        account.withdraw(3);
        account.withdraw(3);
        account.withdraw(3);
        assertEquals(account.getNWithdrawals(), 5);
        account.monthlyProcess();
        assertEquals(account.getBalance(), 30.1, 0.1);
        assertEquals(account.getNWithdrawals(), 0);
        assertEquals(account.getServiceCharges(), 0);
    }
}
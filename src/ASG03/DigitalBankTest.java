package ASG03;

import ASG02.Account;
import ASG02.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitalBankTest {

    @Test
    void testAddCustomer() {
        DigitalBank bank = new DigitalBank();
        String customerId = "012345678911";
        String customerName = "FUNIX";
        bank.addCustomer(customerId, customerName);
        Customer customer = bank.getCustomerById(customerId);
        assertNotNull(customer);
        assertEquals(customerName, customer.getName());
    }

    @Test
    void testWithdrawMoney() {
        DigitalBank bank = new DigitalBank();
        String customerId = "012345678911";
        String accountNumber = "123456";
        double balance = 100000;
        bank.addCustomer(customerId, "FUNIX");
        bank.addAccount(customerId, new SavingsAccount(accountNumber, balance));
        bank.withdraw(customerId, accountNumber, 50000);

        assertEquals(balance - 50000, 50000);
    }
}

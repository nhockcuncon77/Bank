package ASG04;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Account implements Serializable  {
    private String accountNumber;
    public double balance;
    public String customerId;
    private final List<Transaction> transactions = new ArrayList<>();
    public Account() {
        super();
    }
    public Account(String accountNumber, double balance) {
        super();
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        for(Customer customer : CustomerDao.list()) {
            if(customer.getCustomerId().equals(this.customerId)) {
                return customer;
            }
        }
        return null;
    }

    public String getCustomerId() {
        return String.valueOf(customerId);
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public boolean isPremium() {
        return this.balance >= 10000000;
    }

    public List<Transaction> getTransactions() {
        List<Transaction> accountTransactions = TransactionDao.list();
        for (Transaction transaction : TransactionDao.list()) {
            if (transaction.getAccountNumber() == accountNumber) {
                accountTransactions.add(transaction);
            }
        }
        return accountTransactions;
    }

    @Override
    public String toString() {
//        DecimalFormat formatter = new DecimalFormat("#,###đ");
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return accountNumber + " |             " + formatter.format(balance);
    }

}

package ASG04.model;

import ASG04.common.ReportService;
import ASG04.dao.CustomerDao;
import ASG04.dao.TransactionDao;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Account implements Serializable, ReportService  {
    private String accountNumber;
    private static final long serialVersionUID = -2642485200149884560L;
    Locale locale = new Locale("vi", "VN");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

    private List<Transaction> listTransactions = new ArrayList<Transaction>();
    public double balance;
    public String customerId;

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

    public void transfer(Account toAccount, double amount) throws IOException {
        if (this.balance < amount) {
            System.out.println("Insufficient funds!");
            return;
        }
        this.balance -= amount;
        toAccount.balance += amount;
        log1(toAccount, amount);
    }

    public void log1(Account toAccount, double amount) throws IOException {
        System.out.println("+--------------+-----------------------------+");
        System.out.println("BIEN LAI GIAO DICH SAVINGS");
        String date = getDate();
        System.out.println("NGAY G/D: " + date);
        System.out.println("ATM ID: DIGITAL-BANK-ATM-2023");
        System.out.println("SO TK:       " + getAccountNumber());
        System.out.println("SO TK NHAN:       " + toAccount.getAccountNumber());
        System.out.println("So TIEN:     " + amount);
        System.out.println("SO DU:       " + getBalance());
        System.out.println("PHI + VAT:   " + 0 );
        System.out.println("+--------------+-----------------------------+");
        Transaction transaction = new Transaction(getAccountNumber(), "TRANSFER", amount, date, true);
        if(listTransactions == null)listTransactions = new ArrayList<Transaction>();
        listTransactions.add(transaction);
        TransactionDao.save(listTransactions);
    }
    public void log(double amount) throws IOException {
        System.out.println("+--------------+-----------------------------+");
        System.out.println("BIEN LAI GIAO DICH SAVINGS");
        String date = getDate();
        System.out.println("NGAY G/D: " + date);
        System.out.println("ATM ID: DIGITAL-BANK-ATM-2023");
        System.out.println("SO TK:       " + getAccountNumber());
        System.out.println("So TIEN:     " + (amount));
        System.out.println("SO DU:       " + getBalance());
        System.out.println("PHI + VAT:   " + 0 );
        System.out.println("+--------------+-----------------------------+");
        Transaction transaction = new Transaction(getAccountNumber(), "WITHDRAW", amount, date, true);
        if(listTransactions == null)listTransactions = new ArrayList<Transaction>();
        listTransactions.add(transaction);
        TransactionDao.save(listTransactions);
    }


    public String getDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String formattedDate = df.format(today);
        return formattedDate;
    }




    @Override
    public String toString() {
//        DecimalFormat formatter = new DecimalFormat("#,###đ");
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return accountNumber + " |             " + formatter.format(balance);
    }

}

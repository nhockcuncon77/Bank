package ASG04;


import java.sql.Array;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SavingsAccount extends Account implements ReportService, WithDraw {
    final static double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    Locale locale = new Locale("vi", "VN");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
    List<Transaction> listTransactions = new ArrayList<Transaction>();
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }
    @Override
    public boolean withdraw(double amount){
        if(isAccepted(amount)){
            super.setBalance(super.getBalance() - amount);
            System.out.println("Success");
            log(amount);

            return true;
        }
        return false;
    }
    @Override
    public boolean isAccepted(double amount){
        if(amount > SAVINGS_ACCOUNT_MAX_WITHDRAW && super.isPremium() == false){
            System.out.println("You cannot withdraw more than" + SAVINGS_ACCOUNT_MAX_WITHDRAW);
            return false;
        } else if(super.getBalance() - amount < 0){
            System.out.println("Ban khong du tien de rut!!!");
            return false;
        } else if(amount < 50000){
            System.out.println("So tien toi thieu phai rut la 50000!!!");
            return false;
        } else if(amount % 10000 != 0){
            System.out.println("So tien phai rut phai la boi so cua 10000!!!");
            return false;
        }
        return true;
    }
    @Override
    public void log(double amount){
        System.out.println("+--------------+-----------------------------+");
        System.out.println("BIEN LAI GIAO DICH SAVINGS");
        String date = getDate();
        System.out.println("NGAY G/D: " + date);
        System.out.println("ATM ID: DIGITAL-BANK-ATM-2023");
        System.out.println("SO TK:       " + super.getAccountNumber());
        System.out.println("So TIEN:     " + formatter.format(amount));
        System.out.println("SO DU:       " + formatter.format(super.getBalance()));
        System.out.println("PHI + VAT:   " + 0 );
        System.out.println("+--------------+-----------------------------+");
        Transaction transaction = new Transaction(super.getAccountNumber(), amount, date, true);
        listTransactions.add(transaction);
    }

    public String getDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String formattedDate = df.format(today);
        return formattedDate;
    }

    public void showTransaction(){
        for(Transaction transaction : listTransactions){
            System.out.println(transaction);
        }
    }

    @Override
    public String toString() {

        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return getAccountNumber() + " | SAVING           " + formatter.format(getBalance());
    }
}

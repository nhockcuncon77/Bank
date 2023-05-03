package ASG02;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private String accountNumber ;
    private double balance ;
    public Account(){

    }
    public Account(String accountNumber, double balance){
        this.accountNumber = accountNumber ;
        this.balance = balance ;
    }
    public String getAccountNumber(){
        return accountNumber ;
    }
    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber ;
    }
    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance ;
    }
    public boolean isPremium(){
        return balance >= 10000000 ? true : false ;
    }
    @Override
    public String toString() {
//        DecimalFormat formatter = new DecimalFormat("#,###đ");
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return accountNumber + " |             " + formatter.format(balance);
    }

}

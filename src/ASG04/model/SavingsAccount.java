package ASG04.model;


import java.io.IOException;
import java.sql.Array;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SavingsAccount extends Account implements WithDraw {
    final static double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    private static final long serialVersionUID = -4847390367260363608L;
    Locale locale = new Locale("vi", "VN");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }
    @Override
    public boolean withdraw(double amount) throws IOException {
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


    public String getDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String formattedDate = df.format(today);
        return formattedDate;
    }

    public void showTransaction(){
        for(Transaction transaction : super.getTransactions()){
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

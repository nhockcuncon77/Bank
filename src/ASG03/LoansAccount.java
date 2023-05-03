package ASG03;

import ASG02.Account;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LoansAccount extends Account implements ReportService, WithDraw{
    List<Transaction> listTransactions = new ArrayList<Transaction>();
    Locale locale = new Locale("vi", "VN");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

    public LoansAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }
    @Override
    public boolean withdraw(double amount){
        if(isAccepted(amount)){
            log(amount);

            return true;
        }
        return false;
    }
    @Override
    public boolean isAccepted(double amount){
        if(amount > 100000000 ){
            System.out.println("Hạn mức không được quá giới hạn 100.000.000đ" );
            return false;
        } else if(super.getBalance() - amount - amount* (super.isPremium() == true ? 0.01 : 0.05) < 50000){
            System.out.println("Hạn mức còn lại sau khi rút tiền không được nhỏ hơn 50.000đ");
            return false;
        }
        return true;
    }

    public void log(double amount){
        boolean check = super.isPremium();
        super.setBalance(super.getBalance() - amount - amount* (super.isPremium() == true ? 0.01 : 0.05));
        System.out.println("+--------------+-----------------------------+");
        System.out.println("BIEN LAI GIAO DICH LOANS");
        String date = getDate();
        System.out.println("NGAY G/D: " + date);
        System.out.println("ATM ID: DIGITAL-BANK-ATM-2023");
        System.out.println("SO TK:       " + super.getAccountNumber());
        System.out.println("So TIEN:     " + formatter.format(amount));
        System.out.println("SO DU:       " + formatter.format(super.getBalance()));
        System.out.println("PHI + VAT:   " + formatter.format(amount* (check ? 0.01 : 0.05) ));
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
        return getAccountNumber() + " | LOAN           " + formatter.format(getBalance());
    }
}

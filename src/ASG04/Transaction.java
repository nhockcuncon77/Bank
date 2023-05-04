package ASG04;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Transaction {
    Locale locale = new Locale("vi", "VN");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
    private String id;
    private String accountNumber;
    private double amount;

    private String time;

    private boolean status;

    public Transaction(String accountNumber, double amount, String time, boolean status) {
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = getDate();
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }



    public String getDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String formattedDate = df.format(today);
        return formattedDate;
    }

    public String toString(){
        return id + "   " + accountNumber + "   " + formatter.format(amount) +"   "+ time+ "   " + status;
    }

}



package ASG04.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Transaction implements Serializable {

    private static final long serialVersionUID = -2524520318243850811L;

    Locale locale = new Locale("vi", "VN");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
    private String id;
    private String accountNumber;
    private double amount;

    private String time;

    private String type;

    private boolean status;

    public Transaction(String accountNumber, String type, double amount, String time, boolean status) {
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.time = getDate();
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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
        return id + "   " + type + "    " + accountNumber + "   " + formatter.format(amount) +"   "+ time;
    }

}



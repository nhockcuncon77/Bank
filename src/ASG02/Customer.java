package ASG02;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class Customer extends User{
    private List<Account> accounts = new ArrayList<Account>();;
    public Customer() {
    }
    public Customer(String customerId, String name){
        super(customerId, name);
    }
    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    public boolean isPremium() {
        for(Account acc : accounts){
            if(acc.isPremium()) {
                return true;
            }
        }
        return false;
    }
    public void addAccount(Account account){
        accounts.add(account);
    }
    public double getBalance(){
        double balance = 0;
        for(Account acc : accounts){
            balance += acc.getBalance();
        }
        return balance;
    }
    public void displayInformation(){
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        System.out.print(super.getCustomerId() + "  |");
        System.out.print(getName() + "  |");
        System.out.print((isPremium() == true) ? "Premium " : "Normal " + "  | ");
        System.out.println(formatter.format(getBalance()));

        for(int i= 0; i<accounts.size(); i++){
            System.out.println(i+1 + "   " + accounts.get(i));
        }
    }


}
package ASG04;



import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable{
    private List<Account> accounts = new ArrayList<Account>();;
    public Customer() {
    }
    public Customer(List<String> values){
        this(values.get(0),values.get(1));
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
//        List<Account> AccountList = AccountDao.list();
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        System.out.println(getCustomerId());
        System.out.println(getName());
//        System.out.println(AccountList.size());
//        for(int i= 0; i<AccountList.size(); i++){
//            System.out.println(i+1 + "   " + AccountList.get(i));
//        }
    }



    public boolean isValidId(String cccd){
        if (cccd.length() != 12) {
            return false;
        }
        // kiểm tra chuỗi có chỉ chứa số hay không
        return cccd.matches("[0-9]+");
    }

    public String toString(){
        return getCustomerId() + " " + getName();
    }

}
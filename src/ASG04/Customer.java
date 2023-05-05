package ASG04;



import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable{
    private static final long serialVersionUID = 1L;
    static Scanner sc = new Scanner(System.in);


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
    public String isPremium() {
        final String[] premium = {"Normal"};
        AccountDao.list().stream().filter(new Predicate<Account>() {
            @Override
            public boolean test(Account account) {
                return account.customerId.equals(getCustomerId());
            }
        }).forEach(new Consumer<Account>() {
            @Override
            public void accept(Account account) {
                if (account.isPremium()){
                    premium[0] = "Premium";
                }
            }
        });
        return premium[0];
    }
    public void addAccount(Account account){
        accounts.add(account);
    }
    public String getBalance(){
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        final double[] sum = {0};
        List<Account> accountList = AccountDao.list();
        accountList.stream().filter(new Predicate<Account>() {
            @Override
            public boolean test(Account account) {
                return account.customerId.equals(getCustomerId());
            }
        }).forEach(new Consumer<Account>() {
            @Override
            public void accept(Account account) {
                sum[0] += account.getBalance();
            }
        });
        return formatter.format(sum[0]);

    }
    public void displayInformation(){
        List<Account> AccountList = AccountDao.list();
        final int[] index = {1};
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        System.out.print(getCustomerId() + " |  ");

        System.out.println(getName() + " |  "  + isPremium()  + " |  "+ getBalance());
//        for(Account acc : AccountList){
//            System.out.print(acc.getAccountNumber() + " |  " );
//            System.out.println(formatter.format(acc.getBalance()));
//        }


        AccountList.stream().filter(new Predicate<Account>() {
            @Override
            public boolean test(Account account) {
                return account.customerId.equals(getCustomerId());
            }
        }).forEach(new Consumer<Account>() {
            @Override
            public void accept(Account account) {
                System.out.println((account.getAccountNumber()
                        +"  |   \t\t"
                        + (account instanceof LoansAccount ? "LOAN" : "SAVING") + "| "
                        + "\t\t\t"
                        + formatter.format(account.getBalance())));
                index[0]++;
            }
        });
    }

    public void withdraw() {
        List<Account> accountList = AccountDao.list();
        String accountNumber;
        if(!accountList.isEmpty()){
            Account account;
            double amount;

            do {
                System.out.println("nhap so tai khoan : ");
                accountNumber = sc.nextLine();
                account =getAccountByAccountNumber(accountList,accountNumber);
            }while (account ==null);
            do {
                System.out.println("nhap so tien rut : ");
                amount = sc.nextDouble();
                sc.nextLine();
            }while (amount<=50000);
            if(account instanceof SavingsAccount){
                ((SavingsAccount)account).withdraw(amount);
            }
            else {
                System.out.println("Khach hang khong co tai khoan nao, thao tac khong thanh cong ");
            }
        }
    }

    public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }



    public boolean isValidId(String cccd){
        if (cccd.length() != 12) {
            return false;
        }
        // kiểm tra chuỗi có chỉ chứa số hay không
        return cccd.matches("[0-9]+");
    }

    public void displayAccounts() {
        final int[] index = {1};
//        Locale locale = new Locale("vi", "VN");
//        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        System.out.println(getCustomerId() + "  |           " + getName() + " | " + isPremium() + " |   "
                + getBalance());
        List<Account> accountList = AccountDao.list();
        accountList.stream().filter(new Predicate<Account>() {
            @Override
            public boolean test(Account account) {
                return account.customerId.equals(getCustomerId());
            }
        }).forEach(new Consumer<Account>() {
            @Override
            public void accept(Account account) {
                System.out.println((index[0]) + "     " + account.getAccountNumber()
                        +"  |   \t\t"
                        + (account instanceof LoansAccount ? "LOAN" : "SAVING") + "| "
                        + "\t\t\t"
                        + account.getBalance());
                index[0]++;
            }
        });
    }

    public String toString(){
        return getCustomerId() + " " + getName();
    }

}
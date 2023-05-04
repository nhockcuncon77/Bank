package ASG04;


public class DigitalCustomer extends Customer {

    public DigitalCustomer(String customerId, String name) {
        super(customerId, name);
    }
    public boolean withdraw(String accountNumber, double amount){
        for(Account acc : getAccounts()){
            if(acc.getAccountNumber().equals(accountNumber)){
                if (acc instanceof WithDraw){
                    return ((WithDraw) acc).withdraw(amount);
                }
            }
        }
        return false;
    }
}

package ASG04;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private final String id;
    private List<Customer> customers;



    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public Bank(){
        customers = new ArrayList<Customer>();
        id = String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }
    public void addCustomer(Customer customer){
        if(!isCustomerExisted(customer.getCustomerId())){
            customers.add(customer);
        }
        else{
            System.out.println("Customer already existed");
        }
    }
    public void addAccount(String customerId, Account account){
        List<Customer> customers = CustomerDao.list();
        for(Customer customer: customers){
            for(Account acc: customer.getAccounts()){
                if(acc.getAccountNumber().equals(account.getAccountNumber())) {
                    System.out.println("Account already existed");
                    return;
                }
            }
            if(customer.getCustomerId().equals(customerId) && !isAccountExisted(account.getAccountNumber())){
                customer.addAccount(account);
                return;
            }
        }
        System.out.println("Customer not found");

    }
    public boolean isCustomerExisted(String CustomerId){
        for(Customer customer: customers){
            if(customer.getCustomerId().equals(CustomerId)){
                return true;
            }
        }
        return false;
    }

    public boolean isAccountExisted(String AccountNumber){
        for(Customer customer: customers){
            for(Account acc: customer.getAccounts()){
                if(acc.getAccountNumber().equals(AccountNumber)) {
                    return true;
                }
            }
        }
        return false;
    }
    public List<Customer> getCustomers() {
        return customers;
    }


}
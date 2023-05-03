package ASG03;

import ASG02.Bank;
import ASG02.Customer;

public class DigitalBank extends Bank {
    public boolean withdraw(String customerId, String accountNumber, double amount){
        for(Customer cus : getCustomers()){
            if(cus.getCustomerId().equals(customerId)){
                if(cus instanceof DigitalCustomer){
                    return ((DigitalCustomer)cus).withdraw(accountNumber, amount);
                }
            }
        }
        return false;
    }

    public void addCustomer(String customerId, String name){
        addCustomer(new DigitalCustomer(customerId, name));

    }

    public Customer getCustomerById(String customerId){
        for(Customer cus : getCustomers()){
            if(cus.getCustomerId().equals(customerId)){
                return cus;
            }
        }
        return null;
    }
}

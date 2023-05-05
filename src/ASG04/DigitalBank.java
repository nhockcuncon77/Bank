package ASG04;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DigitalBank extends Bank {

    static Scanner sc = new Scanner(System.in);

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

    public void addCustomer(String fileName) {
        List<Customer> newCustomers = readCustomersFromFile(fileName);
        List<Customer> customerNew = CustomerDao.list();
        boolean validData = false;
        int count=0;
        for (Customer customer : newCustomers) {
            if (!customer.isValidId(customer.getCustomerId())) {
                System.out.println("Không thể thêm khách hàng: " + customer.getCustomerId()
                        + ", số ID không hợp lệ.");
            } else if (findCustomerById(customer.getCustomerId()) != null) {
                System.out.println("Không thể thêm khách hàng: " + customer.getCustomerId()
                        + ", đã tồn tại trong danh sách.");
            } else {
                validData =true;
                customerNew.add(customer);
                count++;
            }
        }
        if (validData) {
            try {
                CustomerDao.save(customerNew);
                System.out.println("Đã thêm " +count + " khách hàng vào danh sách.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public Customer getCustomerById(String customerId){
        for(Customer cus : getCustomers()){
            if(cus.getCustomerId().equals(customerId)){
                return cus;
            }
        }
        return null;
    }

    public void showCustomer(){
        List<Customer> customerList = CustomerDao.list();
        if(customerList.isEmpty()){
            System.out.println("Chưa có khách hàng nào trong danh sách!");
        }
        else{
            for(Customer cus : customerList){
//                System.out.println(cus);
                cus.displayInformation();
            }
        }
    }

    private List<Customer> readCustomersFromFile(String fileName) {
        List<Customer> customers = new ArrayList<>();
        // dung TextFileService
        ArrayList<String[]> customerValues = TextFileService.readFile(fileName);
        for (String[] customerValue : customerValues) {
            customers.add(new Customer(customerValue[0],customerValue[1]));
        }
        return customers;
    }
    private Customer findCustomerById(String customerId) {

        for (Customer customer : CustomerDao.list()) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private void displayAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            System.out.println("- Account number: " + account.getAccountNumber() + ", balance: " + account.getBalance());
        }
    }
    public static boolean checkAccountID(String customerID) {
        if (customerID == null || customerID.length() != 6) {
            return false;
        }
        for (char c : customerID.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public void addSavingAccount(String customerId) throws IOException {
        Customer customer = findCustomerById(customerId);
        List<Account> AccountNews = AccountDao.list();
        double balance;
        String accountNumber;
        Account Accountnew;
        if (customer != null) {
            System.out.println("Tạo tài khoản mới cho khách hàng " + customer.getName() + ".");
            do {
                System.out.print("Nhập ma so tai khoan khach hang : ");
                accountNumber = sc.nextLine();
                Accountnew = new Account();
                Accountnew.setAccountNumber(accountNumber);
            }while (!checkAccountID(accountNumber) || isAccountExisted(AccountNews, Accountnew ));
            do {
                System.out.print("Nhập số dư tai khoan >= 50000đ: ");
                balance = sc.nextDouble();
            } while (balance <= 50000);
            SavingsAccount account = new SavingsAccount(accountNumber, balance);
            account.setCustomerId(customerId);
            AccountDao.update(account);
            System.out.println("Đã thêm tài khoản mới cho khách hàng " + customer.getName() + ".");
        } else {
            System.out.println("Không tìm thấy khách hàng với số ID là " + customerId + ".");
        }
        sc.nextLine();
    }

    public boolean isAccountExisted(List<Account> accountsList, Account newAccount) {
        for (Account account : accountsList) {
            if (account.getAccountNumber().equals(newAccount.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }

    public void withdraw(String customerId) {
        List <Transaction> T1 = TransactionDao.list();
        Customer customer = findCustomerById(customerId);
        customer.displayAccounts();
        System.out.println("ok ?");
        List<Account> accountList = AccountDao.list();
        Account acc1 = new Account();
        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng với số ID: " + customerId);
            return;
        }
        for (Account account : accountList) {
            if (((customer.getCustomerId()).equals(account.customerId))) {
                customer.withdraw();
                try {
                    AccountDao.update(account);
                    TransactionDao.save(account.getTransactions());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
package ASG04.model;


import ASG04.dao.AccountDao;
import ASG04.dao.CustomerDao;
import ASG04.service.TextFileService;
import ASG04.dao.TransactionDao;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DigitalBank extends Bank {

    static Scanner sc = new Scanner(System.in);
    private List<Transaction> listTransactions = new ArrayList<Transaction>();
    public boolean withdraw(String customerId, String accountNumber, double amount) throws IOException {
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

        int count = (int) newCustomers.stream()
                .filter(customer -> customer.isValidId(customer.getCustomerId()))
                .filter(customer -> findCustomerById(customer.getCustomerId()) == null)
                .peek(customer -> customerNew.add(customer))
                .count();

        if (count > 0) {
            try {
                CustomerDao.save(customerNew);
                System.out.println("Đã thêm " + count + " khách hàng vào danh sách.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có khách hàng nào được thêm vào danh sách.");
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

    public void showCustomer() {
        List<Customer> customerList = CustomerDao.list();
        if (customerList.isEmpty()) {
            System.out.println("Chưa có khách hàng nào trong danh sách!");
        } else {
            customerList.stream()
                    .forEach(Customer::displayInformation);
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
        String date = getDate();
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
            Transaction transaction = new Transaction(accountNumber, "DEPOSIT", balance, date, true);
            if(listTransactions == null)listTransactions = new ArrayList<Transaction>();
            listTransactions.add(transaction);
            TransactionDao.save(listTransactions);
            System.out.println("Đã thêm tài khoản mới cho khách hàng " + customer.getName() + ".");
        } else {
            System.out.println("Không tìm thấy khách hàng với số ID là " + customerId + ".");
        }
        sc.nextLine();
    }

    public String getDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String formattedDate = df.format(today);
        return formattedDate;
    }

    public static boolean  isAccountExisted(List<Account> accountsList, Account newAccount) {
        for (Account account : accountsList) {
            if (account.getAccountNumber().equals(newAccount.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }

    public void withdraw(String customerId) throws IOException {
        List <Transaction> T1 = TransactionDao.list();
        Customer customer = findCustomerById(customerId);
        customer.displayAccounts();
        List<Account> accountList = AccountDao.list();
        Account acc1 = new Account();
        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng với số ID: " + customerId);
            return;
        }
        Account account;
        String accountNumber;
        do {
            System.out.println("nhap so tai khoan : ");
            accountNumber = sc.nextLine();
            account = customer.getAccountByAccountNumber(accountList,accountNumber);
        }while (account ==null);

        double amount;

        do {
            System.out.println("nhap so tien rut : ");
            amount = sc.nextDouble();
            sc.nextLine();
        }while (amount<=50000);


        if (((customer.getCustomerId()).equals(account.customerId))) {

            customer.withdraw(account, amount);
            try {
                AccountDao.update(account);
                TransactionDao.save(account.getTransactions());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void transfers(String customerId) throws IOException {
        Customer customer = findCustomerById(customerId);
        customer.displayAccounts();
        customer.transfers();

    }

    public void showHistory(){
        List<Transaction> transactionList = TransactionDao.list();
        if(transactionList.isEmpty()){
            System.out.println("Chưa có danh sách nào trong danh sách!");
        }
        else{
            for(Transaction transaction : transactionList){
                System.out.println(transaction);
            }
        }
    }

}
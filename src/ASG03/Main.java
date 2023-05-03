package ASG03;

import ASG02.Account;
import ASG02.Customer;

import java.util.Date;
import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final DigitalBank activeBank = new DigitalBank();
    static final String CUSTOMER_ID = "012345678911";
    static final String CUSTOMER_NAME = "FUNIX";

    public static void main(String[] args) {
        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
        String chucNang;
        do{
            System.out.println("+----------------+------------------------------+");
            System.out.println("| NGAN HANG SO | FX123456@v2.0.0                |");
            System.out.println("+----------------+------------------------------+");
            System.out.println(" 1. Thong tin khach hang");
            System.out.println(" 2. Them tai khoan ATM");
            System.out.println(" 3. Them tai khoan tin dung");
            System.out.println(" 4. Rut tien");
            System.out.println(" 5. Lich su giao dich");
            System.out.println(" 0. Thoat");
            System.out.println("+----------------+------------------------------+");
            System.out.println("Chuc nang: ");


            chucNang = sc.nextLine();
            switch (chucNang) {
                case "1":
                    showCustomer();
                    break;
                case "2":
                    addSavingAccount();
                    break;
                case "3":
                    addLoansAccount();
                    break;
                case "4":
                    withdrawMoney();
                    break;
                case "5":
                    History();
                    break;
                case "0":
                    break;
            }
        }while(!chucNang.equals("0"));
    }

    private static void showCustomer() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if(customer != null) {
            customer.displayInformation();
        }
    }
    private static void addSavingAccount(){
        System.out.println("Nhap ma STK gom 6 chu so: ");
        String STK = sc.nextLine();
        System.out.println("Nhap so du: ");
        double soDu = sc.nextDouble();
        if(checkso(STK, 6) && soDu > 0) {
            Account account = new SavingsAccount(STK, soDu);
            activeBank.addAccount(CUSTOMER_ID, account);
        } else{
            System.out.println("Tai khoan khong hop le");
        }
    }

    private static void addLoansAccount(){
        System.out.println("Nhap ma STK gom 6 chu so: ");
        String STK = sc.nextLine();
        double soDu = 100000000;
        if(checkso(STK, 6)) {
            Account account = new LoansAccount(STK, soDu);
            activeBank.addAccount(CUSTOMER_ID, account);
        } else{
            System.out.println("Tai khoan khong hop le");
        }
    }

    public static boolean checkso(String cccd, int length){
        // kiểm tra độ dài của chuỗi có đúng 12 kí tự không
        if (cccd.length() != length) {
            return false;
        }
        // kiểm tra chuỗi có chỉ chứa số hay không
        return cccd.matches("[0-9]+");
    }

    public static void withdrawMoney() {
        System.out.println("Nhap STK: ");
        String accountNumber = sc.nextLine();
        System.out.println("Nhap so tien can rut: ");
        double amount = sc.nextDouble();

        activeBank.withdraw(CUSTOMER_ID, accountNumber, amount);
    }

    public static void History() {
        for(Customer customer : activeBank.getCustomers()) {
            for(Account account : customer.getAccounts()) {
                if( account instanceof SavingsAccount) {
                    SavingsAccount savingsAccount = (SavingsAccount) account;
                    customer.displayInformation();
                    savingsAccount.showTransaction();
                } else if( account instanceof LoansAccount) {
                    customer.displayInformation();
                    LoansAccount loansAccount = (LoansAccount) account;
                    loansAccount.showTransaction();
                }
            }
        }
    }

}

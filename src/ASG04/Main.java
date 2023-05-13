package ASG04;
import ASG04.model.DigitalBank;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static final DigitalBank activeBank = new DigitalBank();

    static String fileName = "store\\customers.txt";


    public static void main(String[] args) throws IOException {

        String chucNang;
        do{
            System.out.println("+----------------+------------------------------+");
            System.out.println("| NGAN HANG SO | FX123456@v2.0.0                |");
            System.out.println("+----------------+------------------------------+");
            System.out.println(" 1. Xem danh sách khách hàng");
            System.out.println(" 2. Nhập danh sách khách hàng");
            System.out.println(" 3. Thêm tài khoản ATM");
            System.out.println(" 4. Chuyển tiền");
            System.out.println(" 5. Rút tiền");
            System.out.println(" 6. Tra cứu lịch sử giao dịch");
            System.out.println(" 0. Thoát");
            System.out.println("+----------------+------------------------------+");
            System.out.println("Chuc nang: ");


            chucNang = sc.nextLine();
            switch (chucNang) {
                case "1":
                    showCustomerList();
                    break;
                case "2":
                    addCustomerList();
                    break;
                case "3":
                    addAccount();
                    break;
                case "4":
                    transfer();
                    break;
                case "5":
                    withdrawMoney();
                    break;
                case "6":
                    History();
                    break;
                case "0":
                    break;
            }
        }while(!chucNang.equals("0"));
    }

    public static void showCustomerList() {
        activeBank.showCustomer();
    }
    public static void addCustomerList(){
        activeBank.addCustomer(fileName);
    }

    public static void addAccount() throws IOException {
        String cusid;
        do {
            System.out.println("nhap ma so khach hang:  ");
            cusid =sc.nextLine();
        }while (!checkSo(cusid, 12)&&!activeBank.isAccountExisted(cusid));
        activeBank.addSavingAccount(cusid);
    }


    public static boolean checkSo(String cccd, int length){
        // kiểm tra độ dài của chuỗi có đúng 12 kí tự không
        if (cccd.length() != length) {
            return false;
        }
        // kiểm tra chuỗi có chỉ chứa số hay không
        return cccd.matches("[0-9]+");
    }

    public static void withdrawMoney() throws IOException {
        String cusid;
        do {
            System.out.println("nhap ma so khach hang : ");
            cusid =sc.nextLine();
        }while (!checkSo(cusid, 12)&&!activeBank.isAccountExisted(cusid));
        activeBank.withdraw(cusid);
    }

    public static void transfer() throws IOException {
        String cusid;
        do {
            System.out.println("nhap ma so khach hang : ");
            cusid =sc.nextLine();
        }while (!checkSo(cusid, 12)&&!activeBank.isAccountExisted(cusid));
        activeBank.transfers(cusid);
    }

    public static void History() {
        activeBank.showHistory();
    }

}

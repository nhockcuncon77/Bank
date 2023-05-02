import java.util.Scanner;


public class Main {
    static Bank bank = new Bank();
    public static void main(String[] args) {
        String chucNang;
        do{
            System.out.println("+----------------+------------------------------+");
            System.out.println("| NGAN HANG SO | FX123456@v2.0.0                |");
            System.out.println("+----------------+------------------------------+");
            System.out.println(" 1. Them khach hang");
            System.out.println(" 2. Them tai khoan cho khach hang");
            System.out.println(" 3. Hien thi danh sach khach hang");
            System.out.println(" 4. Tim theo CCCD");
            System.out.println(" 5. Tim theo ten khach hang");
            System.out.println(" 0. Thoat");
            System.out.println("+----------------+------------------------------+");
            System.out.println("Chuc nang: ");
            Scanner sc = new Scanner(System.in);
            chucNang = sc.next();
            switch (chucNang) {
                case "1":
                    sc.nextLine();
                    System.out.print("Nhap ten khach hang:");
                    String cccd;
                    String name = sc.nextLine();
                    do{
                        System.out.print("Nhap so CCCD: ");
                        cccd = sc.nextLine();
                        checkso(cccd,12);
                        if(!checkso(cccd,12)){
                            System.out.println("Nhap lai can cuoc cong dan");
                        }

                    }while(!checkso(cccd,12));
                    Customer customer = new Customer(name,cccd);
                    bank.addCustomer(customer);
                    break;
                case "2":
                    sc.nextLine();
                    System.out.print("NHap CCCD khach hang: ");
                    String cccd1 = sc.nextLine();
                    String stk;
                    do{
                        System.out.print("Nhap ma STK gom 6 so: ");
                        stk = sc.nextLine();
                        checkso(stk,6);
                        if(!checkso(stk,6)){
                            System.out.println("Nhap lai STK");
                        }

                    }while(!checkso(stk,6));


                    System.out.print("Nhap so du: ");
                    double sodu = sc.nextDouble();
                    Account account = new Account(stk, sodu);
                    bank.addAccount(cccd1, account);
                    break;
                case "3":
                    for(Customer cus : bank.getCustomers()){
                        cus.displayInformation();
                    }
                    break;
                case "4":
                    sc.nextLine();
                    System.out.println("Nhap can cuoc cong dan can tim: ");
                    String cccd2 = sc.nextLine();
                    boolean check = false;
                    for(Customer custom : bank.getCustomers()){
                        if(custom.getCustomerId().equals(cccd2)){
                            custom.displayInformation();
                            check = true;
                        }
                    }
                    if(check == false){
                        System.out.println("Khong tim thay so can cuoc cong dan");
                    }
                    break;
                case "5":
                    sc.nextLine();
                    System.out.println("Nhap ten khach hang: ");
                    String ten = sc.nextLine();
                    boolean check_ten = false;
                    for(Customer custom : bank.getCustomers()){
                        if(custom.getName().contains(ten)){
                            custom.displayInformation();
                            check_ten = true;
                        }
                    }
                    if(check_ten == false){
                        System.out.println("Khong tim ten khach hang!");
                    }
                    break;
                case "0":
                    break;
            }
        }while(!chucNang.equals("0"));
    }

    public static boolean checkso(String cccd, int length){
        // kiểm tra độ dài của chuỗi có đúng 12 kí tự không
        if (cccd.length() != length) {
            return false;
        }
        // kiểm tra chuỗi có chỉ chứa số hay không
        return cccd.matches("[0-9]+");
    }

}
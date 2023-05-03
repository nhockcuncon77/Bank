package ASG02;

import java.util.Scanner;

/**
 * Hiển thị menu chức năng và lựa chọn thực hiện chức năng tương ứng
 */
public class Main {
    static Bank bank = new Bank();
    static Scanner sc = new Scanner(System.in);
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

            chucNang = sc.next();
            switch (chucNang) {
                case "1":
                    themKhachHang();
                    break;
                case "2":
                    themTaiKhoan();
                    break;
                case "3":
                    hienThiDanhSach();
                    break;
                case "4":
                    timTheoCCCD();
                    break;
                case "5":
                    timTheoTen();
                    break;
                case "0":
                    break;
            }
        }while(!chucNang.equals("0"));
    }
    /**
     * Kiểm tra số CCCD có đúng định dạng và độ dài hay không
     * @param cccd chuỗi số CCCD cần kiểm tra
     * @param length độ dài chuỗi số CCCD
     * @return true nếu chuỗi CCCD hợp lệ, ngược lại trả về false
     */
    public static boolean checkSo(String cccd, int length){
        // kiểm tra độ dài của chuỗi có đúng 12 kí tự không
        if (cccd.length() != length) {
            return false;
        }
        // kiểm tra chuỗi có chỉ chứa số hay không
        return cccd.matches("[0-9]+");
    }
    /**
     * Thêm khách hàng mới vào danh sách khách hàng của ngân hàng
     */
    public static void themKhachHang(){
        sc.nextLine();
        System.out.print("Nhap ten khach hang:");
        String cccd;
        String name = sc.nextLine();
        do{
            System.out.print("Nhap so CCCD: ");
            cccd = sc.nextLine();
            checkSo(cccd,12);
            if(!checkSo(cccd,12)){
                System.out.println("Nhap lai can cuoc cong dan");
            }

        }while(!checkSo(cccd,12));
        Customer customer = new Customer(cccd,name);
        bank.addCustomer(customer);
    }
    /**
     * Thêm tài khoản cho một khách hàng đã tồn tại trong danh sách khách hàng của ngân hàng
     */
    public static void themTaiKhoan(){
        sc.nextLine();
        System.out.print("NHap CCCD khach hang: ");
        String cccd1 = sc.nextLine();
        String stk;
        do{
            System.out.print("Nhap ma STK gom 6 so: ");
            stk = sc.nextLine();
            checkSo(stk,6);
            if(!checkSo(stk,6)){
                System.out.println("Nhap lai STK");
            }

        }while(!checkSo(stk,6));

        double sodu;
        do{
            try{
                System.out.print("Nhap so du: ");
                sodu = sc.nextDouble();
                if(sodu >0){
                    break;
                }
                System.out.println("Nhap so du duong: ");
            }catch(Exception e){
                sc.nextLine();
                System.out.println("Nhap lai so du:");
            }
        }while(true);
        Account account = new Account(stk, sodu);
        bank.addAccount(cccd1, account);
    }
    /**
     * Hiển thị thông tin tất cả các khách hàng trong danh sách của ngân hàng
     */
    public static void hienThiDanhSach(){
        for(Customer cus : bank.getCustomers()){
            cus.displayInformation();
        }
    }
    /**
     * Tìm kiếm khách hàng theo số CCCD
     */
    public static void timTheoCCCD(){
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
    }
    /**
     * Tìm kiếm khách hàng theo tên
     */
    public static void timTheoTen(){
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
    }

}
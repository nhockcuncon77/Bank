package ASG03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String chucNang;
        do{
            System.out.println("+----------------+------------------------------+");
            System.out.println("| NGAN HANG SO | FX123456@v2.0.0                |");
            System.out.println("+----------------+------------------------------+");
            System.out.println(" 1. Thong tin khach hang");
            System.out.println(" 2. Them tai khoan ATM");
            System.out.println(" 3. Them tai khoang tin dung");
            System.out.println(" 4. Rut tien");
            System.out.println(" 5. Lich su giao dich");
            System.out.println(" 0. Thoat");
            System.out.println("+----------------+------------------------------+");
            System.out.println("Chuc nang: ");
            Scanner sc = new Scanner(System.in);
            chucNang = sc.next();
            switch (chucNang) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":
                    break;
                case "4":

                    break;
                case "5":

                    break;
                case "0":
                    break;
            }
        }while(!chucNang.equals("0"));
    }
}
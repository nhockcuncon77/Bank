package ASG04.dao;

import ASG04.model.Account;
import ASG04.service.BinaryFileService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class AccountDao {
    private final static String FILE_PATH = "store\\accounts.dat";

    public static void save(List<Account> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }

    public static List<Account> list(){
        return BinaryFileService.readFile(FILE_PATH);
    }

//    public static void update(Account editAccount) throws IOException {
//
//        List<Account> accounts = list();
//        boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));
//
//
//        List<Account> updatedAccounts;
//        if(!hasExist){
//
//            updatedAccounts = new ArrayList<>(accounts);
//            updatedAccounts.add(editAccount);
//        }
//        else{
//            updatedAccounts = new ArrayList<>();
//            for(Account account : accounts){
//                if(!account.getAccountNumber().equalsIgnoreCase( editAccount.getAccountNumber())){
//
//                    updatedAccounts.add(account);
//                } else{
//
//                    updatedAccounts.add(editAccount);
//                }
//            }
//        }
//        save(updatedAccounts);
//    }
public static void update(Account editAccount) throws IOException {
    List<Account> accounts = list();
    boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));

    List<Account> updatedAccounts;
    if (!hasExist) {
        updatedAccounts = new ArrayList<>(accounts);
        updatedAccounts.add(editAccount);
    } else {
        updatedAccounts = new ArrayList<>(accounts.size());
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (Account account : accounts) {
            executor.execute(() -> {
                if (!account.getAccountNumber().equalsIgnoreCase(editAccount.getAccountNumber())) {
                    updatedAccounts.add(account);
                } else {
                    updatedAccounts.add(editAccount);
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Chờ tất cả các tác vụ thực thi hoàn thành
        }
    }

    save(updatedAccounts);
}
}

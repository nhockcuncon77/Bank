package ASG04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final static String FILE_PATH = "C:\\Users\\tlbbs\\IdeaProjects\\ASG-02\\store\\accounts.dat";

    public static void save(List<Account> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }

    public static List<Account> list(){
        return BinaryFileService.readFile(FILE_PATH);
    }

    public static void update(Account editAccount) throws IOException {
        List<Account> accounts = list();
        boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNumber() == editAccount.getAccountNumber());


        List<Account> updatedAccounts;
        if(!hasExist){
            updatedAccounts = new ArrayList<>(accounts);
            updatedAccounts.add(editAccount);
        }
        else{
            updatedAccounts = new ArrayList<>();
            for(Account account : accounts){
                if(account.getAccountNumber()!= editAccount.getAccountNumber()){
                    updatedAccounts.add(editAccount);
                } else{
                    updatedAccounts.add(account);
                }
            }
        }
        save(updatedAccounts);
    }
}

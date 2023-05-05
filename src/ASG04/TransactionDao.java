package ASG04;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class TransactionDao implements Serializable {
    private final static String FILE_PATH = "C:\\Users\\tlbbs\\IdeaProjects\\ASG-02\\store\\transactions.dat";

    public static void save(List<Transaction> transactions) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }

    public static List<Transaction> list(){
        return BinaryFileService.readFile(FILE_PATH);
    }
}



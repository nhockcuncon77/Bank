package ASG04.dao;

import ASG04.model.Transaction;
import ASG04.service.BinaryFileService;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class TransactionDao implements Serializable {
    private final static String FILE_PATH = "store\\transactions.dat";

    public static void save(List<Transaction> transactions) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }

    public static List<Transaction> list(){
        return BinaryFileService.readFile(FILE_PATH);
    }
}



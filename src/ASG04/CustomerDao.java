package ASG04;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CustomerDao implements Serializable {
    private final static String FILE_PATH = "C:\\Users\\tlbbs\\IdeaProjects\\ASG-02\\store\\customers.dat";
    static List<Customer> test = new ArrayList<>();
    public static void save(List<Customer> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, customers);
        test = customers;
    }

    public static List<Customer> list() {
//        BinaryFileService.readFile(FILE_PATH);
        return test;
    }
}

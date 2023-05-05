package ASG04;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CustomerDao implements Serializable {
    private final static String FILE_PATH = "C:\\Users\\tlbbs\\IdeaProjects\\ASG-02\\store\\customers.dat";
    public static void save(List<Customer> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, customers);

    }

    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);

    }
}

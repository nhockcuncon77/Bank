package ASG04.dao;

import ASG04.model.Customer;
import ASG04.service.BinaryFileService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CustomerDao implements Serializable {
    private final static String FILE_PATH = "store\\customers.dat";
    public static void save(List<Customer> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH, customers);

    }

    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);

    }
}

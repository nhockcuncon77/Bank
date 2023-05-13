package ASG04.Test;

import ASG04.model.Customer;
import ASG04.model.DigitalBank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ASG04.Main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class MainTest {
    private DigitalBank activeBank;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        activeBank = new DigitalBank();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testShowCustomerList() {
        activeBank.addCustomer("John Doe");
        activeBank.addCustomer("Jane Smith");

        Main.showCustomerList();

        String expectedOutput = "Customer List:\n1. John Doe\n2. Jane Smith\n";
        Assertions.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void testAddCustomerList() {
        String input = "John Doe\nJane Smith\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Main.addCustomerList();

        Assertions.assertEquals(2, activeBank.getCustomers().size());
        Assertions.assertEquals("John Doe", activeBank.getCustomers().get(0).getName());
        Assertions.assertEquals("Jane Smith", activeBank.getCustomers().get(1).getName());
    }

    // Write similar tests for other methods

    private String getOutput() {
        return outputStream.toString(StandardCharsets.UTF_8);
    }
}

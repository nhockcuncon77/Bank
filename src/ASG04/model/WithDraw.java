package ASG04.model;

import java.io.IOException;

public interface WithDraw {
    boolean withdraw(double amount) throws IOException;

    boolean isAccepted(double amount);
}

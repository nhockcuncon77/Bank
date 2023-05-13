package ASG04.common;
import ASG04.model.Account;
public interface ITransfer {
    public void transfer(Account receiveAccount, double amount);
}

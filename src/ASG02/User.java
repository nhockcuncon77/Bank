package ASG02;

public class User {
    private String name;
    private String customerId;

    public User(){

    }

    public User(String customerId, String name){
        this.customerId = customerId;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCustomerId(){
        return customerId;
    }

    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }
}

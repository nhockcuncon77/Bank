package ASG04;

import java.io.Serializable;

public class User implements Serializable {
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

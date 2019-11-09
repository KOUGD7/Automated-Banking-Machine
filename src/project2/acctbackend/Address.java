package src.project2.acctbackend;

import java.io.Serializable;

public class Address implements Serializable{
	static final long serialVersionUID=42L;
    private String address;
    private Parish parish;

    public Address(String street, Parish p){
        this.address = street;
        this.parish = p;
    }

    public Address(String street, String p){
        this.address = street;
        this.parish = Parish.findByName(p);
    }

    public String getAddress(){
        return String.format("%s, %s", this.address, this.parish.fullName());
    }
    
    public String getAddressOnly(){
        return this.address;
    }

    public Parish getParish(){
        return this.parish;
    }

    public String getParishString(){
    	return this.parish.fullName();
    }
    
    
    public String toString(){
        return getAddress();
    }
    
    
}
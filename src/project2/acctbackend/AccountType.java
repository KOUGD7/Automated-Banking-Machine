package  src.project2.acctbackend;;

public enum AccountType {
    savings ("Savings", 0.05),
    directbanking ("Direct Banking", 0.0), chequing ("Chequing", 0.0),
    investment ("Investment", 0.15);
	
	
     
     
    private final String longname;
    private final double irate;

    AccountType(String name, double rate){
        this.longname = name;
        this.irate = rate;
    }
    
    public String longName(){
        return this.longname;
    }
    public double interestRate(){
        return this.irate;
    }
}
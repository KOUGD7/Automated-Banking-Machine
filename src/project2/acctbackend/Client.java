package src.project2.acctbackend;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * ABM Project 2
 * 
 * @author (MICHAEl GOLDSON) 
 * @version (21042018)
 */


public abstract class Client implements Serializable {
    
    protected static final long serialVersionUID=42L;
    protected String clientid, telephone, name;
    
    //protected String clientid;
    protected String trn;
    protected ArrayList<Address> addresses;
    protected ArrayList<Account> accounts;
    
    
    
    public Client() {
        
    }
    

    public Client(String clid, String name_, String address_, String parish_,
                  String tel_){
        accounts = new ArrayList<Account>();
        addresses = new ArrayList<Address>();
        // Parish pa = Parish.KingstonStAndrew;  // default parish
        Parish pa = Parish.findByName(parish_);     
            addresses.add(new Address(address_, pa));      
        //telephone = tel_;
        //name = name_;
        clientid = clid;
    }
 
    public abstract String[] getData();
    //public abstract void uploadData();
    //public abstract int getCardNum();
    //public abstract int getPin();
    
  
    
    public String getTRN(){
        return trn;
    }

    
    public Client(String trn){
        this.trn=trn;       
    }
       
    public Address getPrimaryAddress(){
        return this.addresses.get(0);
        
    }
    
    public String getClientID() {
     return clientid;
    }
    
    public String getClientId() {
        return clientid;
    }
 
    
    public Parish getPrimaryParish(){
        Address a;
        try{
            a = getPrimaryAddress();
        }
        catch(Exception e){
            a = null;
        }
        return a.getParish();
    }
        
    public void addAccount(AccountType act, double balance){
        addAccount(new Account(act, balance));
    }
      
    public abstract String getName();
    
    public abstract String getFullName();
    
    
    public void addAccount(Account account){
        accounts.add(account);
    }
    
    public void addAccount(String type, double balance){
        type = String.join("", type.split(" "));
        AccountType act = AccountType.valueOf(type);
        addAccount(new Account(act, balance));
    }
    
    public void addAccount(int accNum, String type, double balance){
        type = String.join("", type.split(" "));
        AccountType act = AccountType.valueOf(type);
        addAccount(new Account(accNum, act, balance));
    }
    
    
    public void addAddress(Address addr_){
        addresses.add(addr_);
    }

    public Account getAccount(int seq){
        if (seq < accounts.size())
            return accounts.get(seq);
        else
            return null;
    }
    
    
    public ArrayList<Account> getAccounts(){
        return this.accounts;
    }
    
    public Account getAllAccounts(){
        for(Account a: accounts){
            return a;
        } 
        return null ;
    }
    
    public int getAllAccountNo(){
        for(Account a: accounts){
            return a.getAccNum();
        } 
        return 0 ;
    }
    
    public void updateClient(String type, double balance, String street, String parish) {
         if(checkAddress(parish, street)) {
              addAddress(new Address(street, parish));
         }
        addAccount(type, balance);              
    }
    
    public boolean checkAddress( String pa, String address) {
        for (Address a: this.addresses) {
            if (a.getParishString().equals(pa) && a.getAddressOnly().equals(address)) {
                return false;
            }
        }                                  
        return true;
    }
         
    public Account accountSelect(int accountNum) {
        for (Account a: accounts) {
            if (accountNum == a.getAccNum()) {
                return a;
            }
        }
        return null;
    }
     
    
    public void ABMDeposit(int account, double depos) {         
        accountSelect(account).deposit(depos);
                }
    
    
    public void ABMWithdrawal(int account, double wit) { 
        accountSelect(account).withdraw(wit);
    } 
   
    
    public String toString(){
        String f = "Client: %s (%s)\n  CTel: %s%s%s";
        String aa, ac;
        ac = "\n  Accounts:";
        for (Account a : accounts){
            ac += String.format("\n    %-20s:    $%,25.2f", a.getType(), a.currentBalance());
        }
        aa = "\n  Address:";
        for (Address b: addresses){
            aa += String.format("\n    %s", b.toString());
        }
       return String.format(f, name, clientid, telephone, ac, aa);
       // return String.format(f,  clientid,  ac, aa);
    }
    public boolean authcard(String acnum, int pin) {
        // TODO Auto-generated method stub
        return false;
    }          
}

/*    
public void ABMDeposit(int account, double depos) {          
     for (Account a : accounts) {     
         if (account==a.getAccNum()){
             a.deposit(depos);
             }
         }                   
 }
   
 public void ABMWithdrawal(int account, double wit) {        
     for (Account a : accounts) {     
         if (account==a.getAccNum()){
             a.withdraw(wit);
             }
         }                   
 }   
    
    
*/

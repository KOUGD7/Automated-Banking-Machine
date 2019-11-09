package src.project2.acctbackend;

import src.project2.abmui.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * ABM Project 2
 * 
 * @author (MICHAEl GOLDSON) 
 * @version (21042018)
 */

public class BusinessClient extends Client{
    
    private String tradingName, officialName, telephone, telephone2;
    private ArrayList<PersonalClient> clients;
    private String Data []= new String [34];
    
    public BusinessClient () {
        }
    
    public BusinessClient (String clid, String name_, String surname_ , String address_, String parish_,
            String tel_, String tel2_, String trn){
        super (trn);
        //Data = new String [34];
        accounts = new ArrayList<Account>();
        addresses = new ArrayList<Address>();
        clients= new  ArrayList<PersonalClient>();
        // Parish pa = Parish.KingstonStAndrew;  // default parish
        Parish pa = Parish.findByName(parish_);

        addresses.add(new Address(address_, pa));
        telephone = tel_;
        telephone2 = tel2_;
        tradingName = name_;
        officialName = surname_ ;
        clientid = clid;
    }
    
    public void addClient(PersonalClient c){    
        clients.add(c); 
    }
          
    public void addClient(String clid, String name_, String surname_ , String address_, String parish_,
            String tel_, String trn){                    
       
                clients.add(new PersonalClient(clid, name_, surname_ , address_, parish_, tel_, trn));      
    }
    
    public String getName() {
        return tradingName;
    }

    public String getFullName() {
        return officialName+" t/a "+tradingName;
    }
    
    public int getPin(int pin) {
        for (PersonalClient pc: clients) {
            if (pin==pc.getPin()) {
                return pc.getPin();
            }
        }
        return 0;
    }
    
    public String getCardNum(String cardnum) {
        
        for (PersonalClient pc: clients) {
            if (cardnum.equals(pc.getCardNum())) {
                return pc.getCardNum();
            }
        }
        return "";
    }
    
    public String getClientID() {
        for (PersonalClient pc: clients) {      
                return pc.getClientId();
        }
        return "";
    }   
    
    public String getClientID(String clientID) {
        for (PersonalClient pc: clients) {
            if (clientID.equals(pc.getClientId())) {
                return pc.getClientId();
            }
        }
        return "";
    }        
    
    public boolean authcard(String cnum, int cpin)
    {
        if (cnum.equals(getCardNum(cnum)) && cpin == getPin(cpin))
            return true;
        else
            return false;
    }
      
    public String toString(){
        String f = "\nClient: %s (%s)\n  Tel: %s%s%s%s%s ";
        String aa, ac, pc;
        ac = "\n  Accounts:";
        for (Account a : accounts){
            ac += String.format("\n  %-20s No. %-15s $%,18.2f", a.getType(),a.getAccNum()+":" , a.currentBalance());
        }
        aa = "\n  Address:";
        for (Address b: addresses){
            aa += String.format("\n    %s", b.toString());
        }
        pc = "\n  Signatories:";
        for (PersonalClient c: clients){
            pc += String.format("\n  %s", c.signatoryToString());
        }
        
       return String.format(f, getFullName(), clientid, telephone+"    TRN: "+trn, "\n       "+telephone2, ac, aa, pc);
       // return String.format(f,  clientid,  ac, aa);
    }
    
    public String[] getData() {
        return Data;
    }  

    
/**Method and Constructor for CSV read and write code
 * 
 * 
 * 
public void uploadData() {
    Data[0]= clientid;
    Data[1]= trn ;
    Data[2]= officialName;
    Data[3]= tradingName;
    Data[4]= telephone;
    Data[5]= "1-876-613-1889";

    Data[6]= ""+accounts.get(0).getAccNum();
    Data[7]= accounts.get(0).getType();
    Data[8]= ""+accounts.get(0).currentBalance();
    
    Data[9]= addresses.get(0).getParishString();
    Data[10]= addresses.get(0).getAddressOnly();
    
    Data[11]= clients.get(0).getClientId();
    Data[12]= clients.get(0).getFirstname();
    Data[13]= clients.get(0).getSurname();
    Data[14]= clients.get(0).getTelephone();
    Data[15]= clients.get(0).getTrn();
    Data[16]= ""+clients.get(0).getPin(); 
    Data[17]= ""+clients.get(0).getCardNum();
    Data[18]= clients.get(0).addresses.get(0).getParishString();
    Data[19]= clients.get(0).addresses.get(0).getAddressOnly();
    
    if (accounts.size()>=2) {
        Data[20]= ""+accounts.get(1).getAccNum() ;
        Data[21]= accounts.get(1).getType();
        Data[22]= ""+accounts.get(1).currentBalance(); }
    
    if (addresses.size()>=2) {
        Data[23]= addresses.get(1).getParishString();
        Data[24]= addresses.get(1).getAddressOnly(); } 
    
    if (clients.size()>=2) {
        Data[25]= clients.get(1).getClientId();
        Data[26]= clients.get(1).getFirstname();
        Data[27]= clients.get(1).getSurname();
        Data[28]= clients.get(1).getTelephone();
        Data[29]= clients.get(1).getTrn();
        Data[30]= ""+clients.get(1).getPin();
        Data[31]= ""+clients.get(1).getCardNum();
        //if (clients.get(1).addresses.size()>=2) {
        Data[32]= clients.get(1).addresses.get(0).getParishString();
        Data[33]= clients.get(1).addresses.get(0).getAddressOnly();
        //}
    }               
}

public BusinessClient (String clid, String trn, String official, String trade, String tel_, String tel2_, String accountNum, String accountType,
         String balance, String parish_, String address_, String pclid, String pname_, String psurname_ , String ptel_, String ptrn, 
         String pin, String card, String pparish_, String paddress_ ){
    
    super (trn);
    //Data = new String [40];
    accounts = new ArrayList<Account>();
    addresses = new ArrayList<Address>();
    clients= new  ArrayList<PersonalClient>();
    clientid = clid; 
    officialName = official ; 
    tradingName = trade;
    telephone = tel_;  
    telephone2 = tel2_;
    this.trn=trn;
    Parish pa = Parish.findByName(parish_);
    addresses.add(new Address(address_, pa));
    
    clients.add(new PersonalClient (pclid, pname_,  psurname_ ,  ptel_, ptrn, 
        pin,  card,  pparish_,  paddress_));    
}  

public void addClient(String clid, String name_, String surname_ , String tel_, String trn, String pin, String card, String parish_, String address_
        ){
        
    clients.add(new PersonalClient (clid, name_,  surname_ ,  tel_, trn, 
            pin,  card,  parish_,  address_));
}
*/
}
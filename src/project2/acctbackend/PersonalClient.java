package src.project2.acctbackend;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import src.project2.utils.DateUtil;
/**
 * ABM Project 2
 * 
 * @author (MICHAEl GOLDSON) 
 * @version (21042018)
 */
public class PersonalClient extends Client {
    
    private String surname, givenName, telephone, cardNum;
    private Date dob;
    private int pin ;         
    private int  nextPin, nextCard, nextYear, nextMM, nextDD;
    
    private String Data [] = new String [17];
     
    public PersonalClient (){
        super ();
    }
    
    public PersonalClient (String clid, String name_, String surname_ , String address_, String parish_,
            String tel_, String trn){
   
        super (trn);
        accounts = new ArrayList<Account>();
        addresses = new ArrayList<Address>();
        // Parish pa = Parish.KingstonStAndrew;  // default parish
        Parish pa = Parish.findByName(parish_);
        addresses.add(new Address(address_, pa));
        telephone = tel_;
        givenName = name_;
        surname=surname_ ;
        clientid = clid;
        this.cardNum = "" + genCardNum()+""+genCardNum2();
        this.pin = genPin();       
        this.dob = DateUtil.stringToDate(""+genYYYY()+"-"+genMM()+"-"+genDD());
    }
    
    
    private int genYYYY() {
    	Random rand = new Random();
    	do {
         nextYear =rand.nextInt(2002-1940) + 1940;
    	}while (nextYear < 1940 || nextYear > 2002);
    	return nextYear; 
        }
    
    private int genMM() {
        Random rand = new Random();
        do {
         nextMM =rand.nextInt(12-1) + 1;
        }while (nextMM > 12);
        return nextMM;
        }
    
    private int genDD() {
        Random rand = new Random();
        do {
        	nextDD =rand.nextInt(31-1) + 1;
        }while(nextDD > 31);
        return nextDD; 
        }
    
    public String getTrn() {
        return trn;
    }
      
    public String getTelephone() {
        return telephone;
    }

    private int genCardNum() {
        Random rand = new Random();
        do {
        	nextCard =rand.nextInt(99999999-10000000) + 10000000;
        }while (nextCard>99999999);
        return nextCard;
        }
    
    private int genCardNum2() {
        Random rand = new Random();
        do {
        	nextCard =rand.nextInt(99999999-10000000) + 10000000;
        }while (nextCard>99999999);
        return nextCard;
        }
                
    private int genPin() {
    	Random rand = new Random();
            do { 
            	nextPin=rand.nextInt(9999-1000) + 1000;
            }while (nextPin>9999 || nextPin>9999);
            return nextPin;
            }
    
    public String getCardNum() {
        return cardNum;
    }
    
    
    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
    
    public String getSurname() {
        return surname;
    }

    public String getFirstname() {
        return givenName;
    }

    
    public String getFullName() {
        return givenName+" "+surname;
    }
    
    public boolean authcard(String cnum, int cpin)
    {
        if (cnum.equals(this.cardNum) && cpin == this.pin)
            return true;
        else
            return false;
    }
    
    public String[] getData() {
        return Data;
    }
 

    public String getName() {        
       return givenName+" "+surname;
    }       
    
    public String toString(){
        String f = "Client: %s (%s)\n\n  Tel: %s%s%s";
        String aa, ac;
        ac = "\n  Accounts:";
        for (Account a : accounts){
            ac += String.format("\n  %-20s No. %-15s $%,18.2f", a.getType(),a.getAccNum()+":" , a.currentBalance());
                    
        }
        aa = "\n  Address:";
        for (Address b: addresses){
            aa += String.format("\n    %s", b.toString());
        }
       return String.format(f, getFullName()+" (CardNo.: "+ cardNum +", Pin:"+ pin +")", clientid, telephone +
    		   "    DOB: "+ DateUtil.dateToString(dob, "MMM dd, yyyy") + "    TRN: "+trn, 
    		   				ac, aa );
      
    }
    
    
    public String signatoryToString() {
        return String.format("  Signer: %-18s", getFullName()) + " (CardNo.: "+ cardNum +", Pin:"+ pin +")\n"+              
                            "       Tel: "+telephone+"     Address: " +addresses.get(0);
    } 

 
    
/** Method and Constructor for CSV code read and write code
 * 
 * 
 * 
public void uploadData() {
    
    Data[0]= clientid;
    Data[1]= givenName;
    Data[2]= surname;   
    Data[3]= telephone;
    Data[4]= trn;
    Data[5]= ""+pin;
    Data[6]= ""+ cardNum;
    
            
    Data[7]= addresses.get(0).getParishString();
    Data[8]= addresses.get(0).getAddressOnly();
            
    Data[9]= ""+accounts.get(0).getAccNum();
    Data[10]= accounts.get(0).getType();
    Data[11]= ""+accounts.get(0).currentBalance();
               
    if (accounts.size()>=2) {
        Data[12]= ""+accounts.get(1).getAccNum() ;
        Data[13]= accounts.get(1).getType();
        Data[14]= ""+accounts.get(1).currentBalance();}
    
    if (addresses.size()>=2) {
        Data[15]= addresses.get(1).getParishString();
        Data[16]= addresses.get(1).getAddressOnly();}   
    }

PersonalClient(String pclid, String pname_, String psurname_ , String ptel_, String ptrn, String pin_, String card, 
        String pparish_, String paddress_ ){
    
    accounts = new ArrayList<Account>();
    addresses = new ArrayList<Address>();
    clientid = pclid;
    givenName = pname_;
    surname = psurname_  ;
    telephone = ptel_;
    trn = ptrn;
    pin = Integer.parseInt(pin_);
    cardNum = card;
    addresses.add (new Address (paddress_ , pparish_));
    
}
 */

}
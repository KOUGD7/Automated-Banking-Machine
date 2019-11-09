package src.project2.abmui;
import src.project2.acctbackend.*;
import src.project2.utils.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.JFrame;

/**
 * ABM Project 2
 * 
 * @author (MICHAEL GOLDSON) 
 * @version (27042018)
 * 
 */

public class ABM{

    private Client client;
    private Scanner scan;

    private ArrayList<String[]> accountData;
    private static ArrayList<String[]> clientData2;
    private static ArrayList<Client> clientData;
    private static ArrayList<Client> BusinessClientData;
    private static ArrayList<Account> BusinessAccountData;
     
    private static int next;
   

    public static void main(String[] args){
        ABM m = new ABM();
                           
        /**Initialize file writer, Print information to File (open with Notepad++)*/       
        PrintWriter out = null;                   
        try {
            out = new PrintWriter(new FileWriter("data/output.txt"));            
        }catch (IOException e) {
            e.printStackTrace();
        } 
        
        out.println("\n\nClients\\n*******************************************************************"); 
        for (Client q: m.clientData){
            out.print(q);
            out.println("\n--------------------------------------------------------------------");   
        }         
        double fundsUnderManagement = m.totalFunds();
        out.printf("The total sum under management is %,.2f", fundsUnderManagement);
        out.close();          
        
       /**THIS BELOW IS TO TEST CODE WITHOUT GUI*/
       // m.test();
        
       /**THIS BELOW IS TO RUN CODE WITH GUI*/ 
        JFrame frame = new JFrame ("JLCB Automated Banking Machine Platform v1.0");
        frame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize( 960,480 );
        frame.setResizable( false );
        ABMUi panel = new ABMUi();     
        frame.addWindowListener(new java.awt.event.WindowAdapter() { 
            
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) { 
        frame.getContentPane().removeAll();
        frame.getContentPane().invalidate();
        frame.getContentPane().validate();           
        frame.getContentPane().add(new ABMUi());                     
        frame.pack();
        frame.setVisible(true);
        }
    });        
        frame.getContentPane().add(new ABMUi());        
        frame.pack();
        frame.setVisible(true);
    }
    
    public ABM(){
    /** instantiate accountData, the arraylist with string arrays*/
      accountData = new ArrayList<String[]>();
      clientData = new ArrayList<Client>();
      BankData bd= new BankData (); 
      
      /**THESE METHOD [initialize() & createObjects()] BELOW IS USE TO INITIALIZE DATA USING TEST CODE AND WRITE NEW DATA TO FILE  */                    
     //initialize();
     //createObjects();
      
      readData(); 
    }
             
    
/*********************** ABM FUNCTIONS AND TEST CODE *********************************/ 
                  
                            
    private void readData(){
        /**
         * This Should Include the Code to Read the Data From the File Using the functions in util.SimpleData  For Now I Will Simply Add Data Here!
        */
        try {
           FileInputStream fis = new FileInputStream("data/clientdata.dat");
           ObjectInputStream ois = new ObjectInputStream(fis);
           clientData = (ArrayList<Client>)ois.readObject();
           fis.close();
        } 
        catch (IOException e)       {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)         {
           // e.printStackTrace();
            System.out.print(e);
        }   
    }   
    
    public static void transactionLog(int Account, double Amount, String TransactionType) {
        
        PrintWriter log = null;         
        try {
            log = new PrintWriter(new FileWriter("data/ABMTransactionLog.txt",true));           
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        String trans = String.format("%-22s  %-21s %-26s %s \n", "Date/Time" , "Account" , "Amount","Transaction_Type");
        log.append(trans);
        log.append(String.format("%-23s %-21s $%,-25.2f %s \n\n", DateUtil.getCurrentTime(), Account, Amount, TransactionType));   
        log.close(); 
    }
    
    
    public ArrayList<Account> getBusinessAccounts (String clientId) {
        BusinessAccountData = new ArrayList<Account>();
        BusinessClient b = new BusinessClient();         
        for (Client q: clientData){
            if (b.getClass()==q.getClass()) { 
                BusinessClient bc= (BusinessClient)q;
                if (clientId.equals(bc.getClientID(clientId))) {
                    for (Account a: q.getAccounts()){
                    BusinessAccountData.add(a);
                }           
                }
            }                                
     }
        return BusinessAccountData;
   }
   
   public ArrayList<Client> getBusinessClients (String clientId) {
       BusinessClientData = new ArrayList<Client>();
       BusinessClient b = new BusinessClient();         
       for (Client q: clientData){
            if (b.getClass()==q.getClass()) { 
                BusinessClient bc= (BusinessClient)q;
                if (clientId.equals(bc.getClientID(clientId))) {
                    BusinessClientData.add(q);
                }
            }                                
        }
        return BusinessClientData;
   }
   
   
   public PersonalClient addPC(String clid){
       PersonalClient a1 = new PersonalClient();
       for (Client c: clientData){
           if (a1.getClass()==c.getClass() && clid.equals(c.getClientID())){
               return (PersonalClient) c;
            }
        }
       return null;
    }
   
   public void depositOth(int account, double amount) {    
       for (Client c: clientData) {
           if(c.accountSelect(account) == c.getAllAccounts()) {  //condition to avoid deposit to a null account
               c.ABMDeposit(account, amount);
           }
       }       
   }
   
   public Account getAcNum(int account) {      
       for (Client c: clientData) {
           if(c.accountSelect(account) == c.getAllAccounts()) {  //condition to avoid deposit to a null account
               return c.accountSelect(account);
           }
        }
        return null;
   }
   
   
   public static String getClientIDs(){
       for (Client c: clientData){
           return c.getClientID();  
        }
       return ""; 
    }
   
   
  
   public double totalFunds() {
       double mgmtTotal = 0;
       for (Client k: clientData){
           ArrayList<Account> accs = k.getAccounts();
           for (int i = 0; i < accs.size(); i++){
               mgmtTotal += accs.get(i).currentBalance();
           }
       }
       // loop through clientData to extract the balance for accounts contained within to 
       // general a total that is then returned.
       return mgmtTotal;
   }   
   
    protected Client uiAuth(String acnum, int pin){
        for (int i = 0; i < clientData.size(); i++){
            Client c = clientData.get(i);
            if (c.authcard(acnum,pin) == true){
                return c;
            }
        }
        return null;
    }
    
    
    public static ArrayList<Client> getClientData() {
        return clientData;
    }
    
    public static void setClient(Client c) {
        clientData.add(c);
    }
    
    public static ArrayList<String[]> getClientData2() {
        return clientData2;
    }
         
    
/**------------------------------------------ TEST CODE ---------------------------------------------------*/  
          
 void test () {  
        //Test code without GUI
        
        String card, code, request, status = null;
        int  pin ;
        PersonalClient a1 = new PersonalClient();      
        BusinessClient b2 = new BusinessClient() ;                         
        scan = new Scanner(System.in);
        
        do {            
            System.out.print("\nPlease enter Card Num");
            card=scan.nextLine();
            System.out.print("Please enter Pin");
            pin=scan.nextInt();
         
            //Cycle through clients and compare card and pin number
            for (Client c: clientData) {
                //PERSONAL CLIENTS
                if (a1.getClass()==c.getClass()) {
                    PersonalClient pc= (PersonalClient)c;
                    if (pin == pc.getPin() && card.equals(pc.getCardNum())) {
                        for (Account a : c.getAccounts()) {
                            System.out.print(a);
                        }
                    System.out.print("\n\nYOUR BUSINESS ACCOUNTS\n");               
                    for (Account business : getBusinessAccounts(pc.getClientId())) {
                        System.out.print(business);
                    }
                    //Prompt user for account number and process transactions
                    clientTransac(c);
                    break;                
                    }              
                }      
                //BUSINESS CLIENTS
                else if (b2.getClass()==c.getClass()) {
                    BusinessClient bc= (BusinessClient)c;
                    if (pin == bc.getPin(pin) && card.equals(bc.getCardNum(card))) {
                        for (Account a : c.getAccounts()) {
                            System.out.print(a);
                        }                    
                        //Prompt user for account number and process transactions
                    clientTransac(c);
                    break;
                    }
                }
            }         
            System.out.println("\nClients\n****************************************************************");
            for (Client q: clientData){
                System.out.print(q);
                System.out.println("\n--------------------------------------------------------------------");
                /**dataUpload();*/         
            }
            System.out.printf("The total sum under management is %,.2f", totalFunds());                 
            System.out.print("\nEnter Q to stop");
            status=scan.nextLine();
            status=scan.nextLine();           
        }while (!status.equalsIgnoreCase("Q"));
        
      //write to file after user Quit
        BankData.writeData(); 
        
        
        //Add data to array for upload with CSV method
        /**clientData2Add();*/                
        /**BankData.writeDataString()*/
        
    }
    
    public void clientTransac(Client c) {
        //Process transaction for a specific account. Prompt user for account number and process transactions 
            String request;
            int account;            
            
            System.out.print("\nSelect account");
            account = scan.nextInt();
            
            for (Account a : c.getAccounts()) {     
                if (account==a.getAccNum()) {
            
                    System.out.print("\n\nW for Withdrawal or D for Deposit using a/c:"+ a.getAccNum());                                                
                    request = scan.nextLine();
                    request = scan.nextLine();
                    
                    System.out.print("\nPlease enter amount");
                    double amount = scan.nextDouble();
            
                    if (request.equalsIgnoreCase("w")) {
                        c.ABMWithdrawal(account, amount); 
                        transactionLog(a.getAccNum(), amount, "Witdrawal");                 
                    }
                    else if (request.equalsIgnoreCase("d")) {
                        c.ABMDeposit(account, amount);
                        transactionLog(a.getAccNum(), amount, "Deposit");                       
                    }
                }
                else {
                    //System.out.print("\nInvalid account Selected");
                }
           }  
            
            System.out.print("Enter account if you want to deposit to other account"); 
            account = scan.nextInt();
            System.out.print("\nPlease enter amount");
            double amount = scan.nextDouble();
            
            depositOth(account, amount);
            transactionLog(account, amount, "Deposit_Other");
        }
    
    private void initialize(){
        // This method just adds some test data to the accountData arraylist
        // You will write code in the method: createObjects to loop through
        // the array initialized here to create instances of your classes
        // You probably shouldn't modify this method - unless:
        // you're adding some new sample data
        // elements in the array  :Client ID, surname, firstname, Acc type, balance, address, Parish, phone num
        accountData.add(new String[]{"JBX09", "Bond", "James", "savings", "15984789.74", "Main St., Oracabessa", "St. Mary", "1-876-987-5583"});
        accountData.add(new String[]{"JBX09", "Bond", "James", "chequing", "7568445.74", "Main St., Oracabessa", "St. Mary", "1-876-987-5583"});
        accountData.add(new String[]{"JBX09", "Bond", "James", "chequing", "465466.74", "Main St., Oracabessa", "St. Mary", "1-876-987-5583"});
        accountData.add(new String[]{"JBX09", "Bond", "James", "investment", "7897896.74", "Shop 9 96 Waltham Pk Rd, Kingston 11", "Kingston    ", "1-876-987-5583"});
        accountData.add(new String[]{"JBX09", "Bond", "James", "direct banking", "6456446.74", "Main St., Oracabessa", "St. Mary", "1-876-987-5583"});
        accountData.add(new String[]{"SHW34", "Holmes", "Sherlock", "investment", "6489546.0", "Brigade St. Santa Cruz", "St. Elizabeth", "1-876-759-4441"});
        accountData.add(new String[]{"THW03", "Hardy", "Thomas", "investment", "6548.40", "Brigade St. Santa Cruz", "St. Elizabeth", "1-876-345-9703"});
        accountData.add(new String[]{"NDW58", "Drew", "Nancy", "investment", "48827.50", "12 High St., Black River", "St. Elizabeth", "1-876-859-8089"});
        accountData.add(new String[]{"JJX12", "Jones", "Jessica", "direct banking", "85169.08", "15 Old Hope Road, Kgn 5", "KSA", "1-876-893-5197"});
        accountData.add(new String[]{"JBC85", "Bourne", "Jason", "direct banking", "416.0", "9 Fearon Ave, May Pen", "Clarendon", "1-876-424-4743"});
        accountData.add(new String[]{"HDW05", "Dent", "Harvey", "savings", "4658416.0", "144 Barnett St., Montego Bay", "St. James", "1-876-484-6108"});
        accountData.add(new String[]{"PCW44", "Coulson", "Phil", "savings", "7436546.0", "Propsper Rd., Lucea", "Hanover", "1-876-598-2378"});
        accountData.add(new String[]{"PCW44", "Coulson", "Phil", "direct banking", "4536546.0", "93 East St Kingston", "Kingston", "1-876-598-2378"});
        // Array Items listed below are business clients with chequing and investment accounts
        // elements in the array  :ClientID, Common Name, Full Name, account type, balance, address, parish, phone numbers
        accountData.add(new String[]{"HWA12", "Huwawei Jamaica", "Shenzen Telecoms Caribbean Ltd.", "chequing", "145689214.35" ,"14 Constant Sprint Road, Kgn 10", "Kingston and St. Andrew", "1-876-633-1859", "1-876-631-5587"});
        accountData.add(new String[]{"DIG01", "Digicel", "Mossel Jamaica Ltd.", "investment", "3245455249.75", "15 Ocean Boulevard, Kingston", "Kingston and St. Andrew", "1-876-633-1000", "1-876-631-5000"});
        accountData.add(new String[]{"DIG01", "Digicel", "Mossel Jamaica Ltd.", "chequing", "958655579.75", "15 Ocean Boulevard, Kingston", "Kingston and St. Andrew", "1-876-633-1000", "1-876-631-5000"});
        accountData.add(new String[]{"DIG01", "Digicel", "Mossel Jamaica Ltd.", "chequing", "5464639.75", "15 Ocean Boulevard, Kingston", "Kingston and St. Andrew", "1-876-633-1000", "1-876-631-5000"});
        accountData.add(new String[]{"WUU88", "Western Photo Ltd.", "Western Photo Ltd.", "chequing", "2586492.09", "8 Main Street, Savannah-la-mar", "Westmoreland", "1-876-945-6688", "1-876-945-6689"});
        accountData.add(new String[]{"SHA47", "Sharkie\'s Seafood Restaurant", "Sharkie\'s Seafood Restaurant", "chequing", "14340048.93", "Howard Cook Boulevard, Montego Bay", "St. James", "1-876-954-8469",""});
        accountData.add(new String[]{"KPK24", "Sassy Super Salon", "Emily Ramson", "chequing", "486975.00", "Chudleigh District, Christiana", "Manchester", "1-876-355-3534",""});
        accountData.add(new String[]{"KPK24", "Sassy Super Salon", "Emily Ramson", "chequing", "64566545.00", "Bromley Ave, Kingston 20", "Kingston", "1-876-355-3534",""});
    }

   
    private void createObjects(){
        
        String[] accountInfo;
        
        for (int i=0; i<accountData.size(); i++ ) {
            accountInfo = accountData.get(i);
           
            for (int s=0; s<clientData.size(); s++) {
                
                //check if client already exist and upadate information
                if (clientData.get(s).getClientId() == accountInfo[0]) {
                    clientData.get(s).updateClient(accountInfo[3], Double.parseDouble(accountInfo[4]), accountInfo[5], accountInfo[6]);
                    }           
                }           
            // PersonalClient (String clid, String name_, String surname_ , String address_, String parish_,
            //         String tel_, String trn)
            if (accountInfo.length==8) {
                client= new PersonalClient(accountInfo[0], accountInfo[2],accountInfo[1], accountInfo[5],accountInfo[6], accountInfo[7], "1"+genTRN());
                client.addAccount(accountInfo[3],Double.parseDouble(accountInfo[4]));
                if (check (accountInfo)) {
                    clientData.add(client);
                }
            }
            //BusinessClient (String clid, String name_, String surname_ , String address_, String parish_,
            //        String tel_, String tel2_, String trn)          
            else if (accountInfo.length>8) {
                client= new BusinessClient(accountInfo[0], accountInfo[1],accountInfo[2], accountInfo[5],accountInfo[6], accountInfo[7], accountInfo[8], "0"+ genTRN());
                client.addAccount(accountInfo[3],Double.parseDouble(accountInfo[4]));
                
                Random rand = new Random();               
                int d = rand.nextInt(4)+2;
               
                
                if (check (accountInfo)) {
                    clientData.add(client);
                    
                    if (i%d == 0) {
                        ((BusinessClient)client).addClient(addPC("HDW05"));                         
                        }
                    
                    if (i%d == 1) {
                    ((BusinessClient)client).addClient(addPC("JBC85")); 
                    ((BusinessClient)client).addClient(addPC("NDW58"));                     
                    //((BusinessClient)client).addClient("MGM93","Michael","Goldson","UWI MONA","Kingston","1-876-876-5687", "125478564");
                    //((BusinessClient)client).addClient("RBR12","Rhon","Bramwell","UWI MONA","Kingston","1-876-812-7312", "175474764");
                    }
                   if (i%d == 2) {                                          
                        ((BusinessClient)client).addClient(addPC("THW03")); 
                        ((BusinessClient)client).addClient(addPC("PCW44"));                         
                    }
                   if (i%2==0) {
                      ((BusinessClient)client).addClient(addPC("JBX09"));
                      ((BusinessClient)client).addClient(addPC("JJX12"));
                     //((BusinessClient)client).addClient("JBX09","Bond", "James","Main St., Oracabessa", "St. Mary","1-876-987-5583", "175474764");
                     //((BusinessClient)client).addClient("SHW34", "Holmes", "Sherlock","Brigade St. Santa Cruz", "St. Elizabeth", "1-876-759-4441","1456475464");
                    }
                   if (i%2==1) {
                         ((BusinessClient)client).addClient(addPC("SHW34"));
                    //((BusinessClient)client).addClient("PCW44", "Coulson", "Phil","Propsper Rd., Lucea", "Hanover", "1-876-598-2378", "1452786254");
                    }
                }
            }        
        }
    }
    
    public boolean check (String[] a) {
        for (int s=0; s<clientData.size(); s++) {
           if (clientData.get(s).getClientId() == a[0]) {
              return false;
           }    
        }
            return true;
    }
    
    
    private int genTRN() {
        Random rand = new Random();
        do {
            next =rand.nextInt(99999999-10000000) +10000000;
        }while (next>99999999 || next<10000000);
        return  next;
        }
    
    
    
/** 
 * Method for CSV write
 *   
    public void clientData2Add() {
        
        //Add data to ArrayList<String[]> to be save to file
        clientData2 = new ArrayList<String[]>();
        PersonalClient a1 = new PersonalClient(); 
        BusinessClient b2 = new BusinessClient();
        
        for (Client q: clientData){
            if (a1.getClass()==q.getClass()) {
                PersonalClient pc= (PersonalClient)q;
                clientData2.add(pc.getData());
            }   
            else if (b2.getClass()==q.getClass()) {
                BusinessClient bc= (BusinessClient)q;
                clientData2.add(bc.getData());              
                System.out.println("\nBC ADDED TO Client");
            }   
        }
    }
   


 * 
 * 
 * public void dataUpload(){
        if (a1.getClass()==q.getClass()) {
            PersonalClient pc= (PersonalClient)q;
            pc.uploadData();                
            System.out.println("PC ADDED");
        }
        else if (b2.getClass()==q.getClass()) {
            BusinessClient bc= (BusinessClient)q;
            bc.uploadData();                
            System.out.println("\nBC ADDED");
        }     
   */
    
    
}   







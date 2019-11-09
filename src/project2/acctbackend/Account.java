package src.project2.acctbackend;
import src.project2.utils.*;

import java.util.*;
import java.io.Serializable;
import java.util.Random;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ABM Project 2
 * 
 * @author (MICHAEL GOLDSON) 
 * @version (27042018)
 * 
 */

public class Account implements Serializable{
    static final long serialVersionUID=42L;
    private final AccountType type;
    private double balance;
    private int accNum;
    private int nextId ;
    

    public Account(AccountType accType, double accBalance){
        type = accType;
        balance = accBalance;
        this.accNum = genAccNum();        
    }
    
    public Account(int accountNum, AccountType accType, double accBalance){
        type = accType;
        balance = accBalance;
        this.accNum = accountNum;
        
    }
      
    
    private int genAccNum() {
        Random rand = new Random();
        do {
         nextId=rand.nextInt(9999999-1000000) + 1000000; 
        }while (nextId > 9999999 || nextId < 1000000);
         return nextId;
    }
    
    public int getAccNum() {
        return accNum;
    }
    
    public String getType(){
        return type.longName();
    }

    public AccountType getAccountType(){
        return type;
    }

    public double currentBalance(){
        return balance;
    }

    public double withdraw (double wit) {    	
        transactionLog(accNum, wit, "Withdraw");
        return balance=balance-wit; 
        
    }

    public double deposit (double des) {
        transactionLog(accNum, des, "Deposit");
        return balance=balance+des;
        
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
    
    public String checkAccount (double request) {
    	
    	double min = 100;   
    	
    	if ((balance-request)<= min){
    		return "<html><b>ERROR</b><br><br>Not Enough Funds</html>";    		
    	}
    	else if (getType().equals("Investment")){
    		return "<html><b>ERROR</b><br><br>Withdrawals from Investment accounts is not allowed!</html>";    		
    	}    	
    	else if (request % 100 != 0 ) {
    		return "<html><b>ERROR</b><br><br>Not a multiple of 100!</html>";    		
    	}    	
    	else {
    		return "Ok";
    	}   	
    }
    
    
    public double calcInterest(int days){
        double totalInterest, daysInterest;
        totalInterest = balance * type.interestRate();
        daysInterest = totalInterest * ((double)days/365.25);
        return daysInterest;
    }
    
    public String toStringBrief(){
        return getType() + " - Account Number: " + getAccNum();
    }
    
    public String toString(){
    return String.format("\n   %-20s No."+getAccNum()+":    $%,12.2f", getType(), currentBalance());
    }
    
    
    
}
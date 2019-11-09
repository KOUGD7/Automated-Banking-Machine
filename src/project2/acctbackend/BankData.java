package src.project2.acctbackend;

import src.project2.abmui.*;
import src.project2.utils.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * ABM Project 2
 * 
 * @author (MICHAEl GOLDSON) 
 * @version (21042018)
 */


public class BankData {
	
	private ArrayList<String[]> clientData;
	private static Client client;
	
	public BankData () {
	}
	
	
	public static void writeData(){
        /*
         * This should include the functions to write data back to the file and should be run when the program is being closed.
         */       
        try {
            FileOutputStream fos = new FileOutputStream("data/clientdata.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ABM.getClientData());
            fos.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }                   
    } 
	
	
/*****************************************	
 * 
 * Write and read using CSV code
 * 
 * 
 * 
	
	public static void writeDataString(){		
		try {
			SimpleData.writeCSV("data/clients.csv", ABM.getClientData2());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public void readDataString() {
		addData();
		//for (String[] e: eventData) {
		//	for (String a: e) {
		//		System.out.println(a);
		//	}
		//}
		//for (String[] c: clientData.subList(0,clientData.size())) {
			//for (String a: c) {
			//System.out.println(a);	
			//break;
			//}
		//}
		
		//Reading data from data files
		try {
		for (String[] c: clientData.subList(0,clientData.size())) {
			//Client(String name, String address, String email, String email2, String telephoneNumber, String telephone2, String code)		
			//(String clid, String trn, String official, String trade, String tel_, String tel2_, String accountNum, String accountType,
   		 	//String balance, String parish_, String address_, String pclid, String pname_, String psurname_ , String ptel_, String ptrn, 
   		 	//String pin, String card, String pparish_, String paddress_ )
			if (c.length>=33) {
					client = new BusinessClient(c[0],c[1],c[2],c[3],c[4],c[5],c[6],c[7],c[8],c[9],c[10],
											c[11],c[12],c[13],c[14],c[15],c[16],c[17],c[18],c[19]);
					client.addAccount(Integer.parseInt(c[6]), c[7],Double.parseDouble(c[8]));
					
					if (!c[20].equals("null")) {
					client.addAccount(Integer.parseInt(c[20]), c[21] ,Double.parseDouble(c[22]));
					}
					
					if (!c[23].equals("null")) {						
					client.addAddress(new Address(c[23],c[24]));
					}
					
					if (!c[25].equals("null") || !c[33].equals("null") ) {
						System.out.print("pass\n ");
					((BusinessClient) client).addClient(c[25],c[26],c[27],c[28],c[29],c[30],c[31],c[32], c[33]);
					}
					
					ABM.setClient(client);
					System.out.print(c[2]+c[3]+" added");
			}
			
				else if(c.length>=16){
				
					client = new PersonalClient(c[0],c[1],c[2],c[3],c[4],c[5],c[6],c[7],c[8]);
					client.addAccount(Integer.parseInt(c[9]), c[10],Double.parseDouble(c[11]));
					
					if (!c[12].equals("null")) {						
						client.addAccount(Integer.parseInt(c[12]), c[13],Double.parseDouble(c[14]));
					}
					if (!c[15].equals("null")) {
						
						client.addAddress(new Address(c[15],c[16]));
					}
					
					ABM.setClient(client);
					System.out.print(c[1]+c[2]+" added\n");
				}
			}
		
		}
		catch(Exception e) {
				System.out.print(e);
			//e.printStackTrace();
			}
			
			
			//for (String a: e) {
			//	System.out.println(a);			//}		
		}
	
	public void addData(){
		try {
		clientData = SimpleData.ReadCSV("data/clients.csv");
		} 
		catch (IOException e) {
		
			System.out.print(e);
		}
		
		catch (Exception e) {
			
				System.out.print(e);
			}
	}
	
	public ArrayList<String[]> getClientData() {
		return clientData;
	}
	**/
}
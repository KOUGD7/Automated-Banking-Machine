package src.project2.abmui;
import src.project2.*;
import src.project2.acctbackend.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class ABMUi here.
 *
 * @author (Rhon-Kaniel Bramwell)
 * @version (17042018)
 */
public class ABMUi extends JPanel
{
    // instance variables - replace the example below with your own
    private JPanel main, center, right, left;
    private JButton but_l1, but_l2, but_l3, but_l4, but_r1, but_r2, but_r3, but_r4;
    private JTextField acnum, amount;
    private JTextField bills5000, bills1000, bills500, bills100;
    private JPasswordField pin;
    private JLabel toplabel, errlabel, loginlabel, mainlabel, infolabel;
    private String cardnumber;
    //Font Setup
    private Font topfont = new Font("Arial", Font.PLAIN, 24);
    private Font mainfont = new Font("Arial", Font.PLAIN, 16);
    private ABM m = new ABM(); 
    private GridLayout mylayout = new GridLayout(4,0,70,40);
    private Dimension butdimension = new Dimension(120,60);
    private boolean erronscreen = false;
    private Client c;
    private ArrayList<Account> acc;
    
    
    

    /**
     * Constructor for objects of class ABMUi
     */
    public ABMUi()
    {       
        
        but_l1 = new JButton("");
        but_l2 = new JButton("");
        but_l3 = new JButton("");
        but_l4 = new JButton("");
        but_r1 = new JButton("");
        but_r2 = new JButton("");
        but_r3 = new JButton("");
        but_r4 = new JButton("");
                      
        //Main Panel Setup
        main = new JPanel();
        main.setLayout(new BorderLayout(120,10));
 
        //Top Panel Setup
        JPanel top = new JPanel();
        toplabel = new JLabel("JLCB Automated Banking Machine (ABM)", SwingConstants.CENTER);
        toplabel.setPreferredSize(new Dimension(500,50));
        toplabel.setFont(topfont);
        top.add(toplabel);

        //Center Panel Setup
        center = new JPanel();
        center.setLayout(new GridLayout(8,1,0,5));
        center.setPreferredSize(new Dimension(450,300));       
        loginlabel = new JLabel("Please Enter Your Card Number:", SwingConstants.CENTER);
        loginlabel.setFont(mainfont);
        acnum = new JTextField();
        acnum.setHorizontalAlignment(JTextField.CENTER);
        acnum.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))
                        && (caracter != '\b')) {
                    e.consume();
                }
                if (acnum.getText().length() >= 16 ) // limit textfield to 6 characters
                            e.consume(); 
            }
        });
        acnum.setFont(mainfont);
        center.add(loginlabel);
        center.add(acnum);
        but_r4.setText("Continue");
        //Set Action Command for the Login Button so its easy for the Action Listener
        but_r4.setActionCommand("Login1");
        
        ButtonListener listen = new ButtonListener();
        but_r1.addActionListener(listen);
        but_r2.addActionListener(listen);
        but_r3.addActionListener(listen);
        but_r4.addActionListener(listen);
        but_l1.addActionListener(listen);
        but_l2.addActionListener(listen);
        but_l3.addActionListener(listen);
        but_l4.addActionListener(listen);
        
        InputMap im = main.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = main.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "l1");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "l2");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "l3");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "l4");
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "r1");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0), "r2");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0), "r3");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "r4");
        
        am.put("l1", new KeyAction("l1"));
        am.put("l2", new KeyAction("l2"));
        am.put("l3", new KeyAction("l3"));
        am.put("l4", new KeyAction("l4"));
        am.put("r1", new KeyAction("r1"));
        am.put("r2", new KeyAction("r2"));
        am.put("r3", new KeyAction("r3"));
        am.put("r4", new KeyAction("r4"));
        
      
        left = new JPanel();
        
        left.setLayout(mylayout);
        left.add(but_l1);
        but_l1.setPreferredSize(butdimension);
        left.add(but_l2);
        left.add(but_l3);
        left.add(but_l4);
        
        
        
        right = new JPanel();
              
        right.setLayout(mylayout);
        right.add(but_r1);
        but_r1.setPreferredSize(butdimension);
        right.add(but_r2);
        right.add(but_r3);
        right.add(but_r4);
        
        main.add(top, BorderLayout.NORTH);
        main.add(center, BorderLayout.CENTER);
        main.add(left, BorderLayout.WEST);
        main.add(right, BorderLayout.EAST);
        
        
        add(main);
  
    }
    
   private class KeyAction extends AbstractAction
    {
        private String cmd;

        public KeyAction(String cmd) {
            this.cmd = cmd;
        }
        
        public void actionPerformed( ActionEvent tf )
        {
            
            
            if (cmd.equalsIgnoreCase("l1"))
            {
                but_l1.doClick();
            }
            
            if (cmd.equalsIgnoreCase("l2"))
            {
                but_l2.doClick();
            }
            
            if (cmd.equalsIgnoreCase("l3"))
            {
                but_l3.doClick();
            }
            
            if (cmd.equalsIgnoreCase("l4"))
            {
                but_l4.doClick();
            }
            
            if (cmd.equalsIgnoreCase("r1"))
            {
                but_r1.doClick();
            }
            
            if (cmd.equalsIgnoreCase("r2"))
            {
                but_r2.doClick();
            }
            
            if (cmd.equalsIgnoreCase("r3"))
            {
                but_r3.doClick();
            }
            
            if (cmd.equalsIgnoreCase("r4"))
            {
                but_r4.doClick();
            }
            
        }
    }

        
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //JOptionPane.showMessageDialog(null, "An Event Is Happening! " + event.getActionCommand());
            if (event.getActionCommand() == "Login1")
            {
               if (acnum.getText().equals(""))
               {
                   JOptionPane.showMessageDialog(null, "No Card Number Entered!");
                   return;
               }
               else
               {
                   if (erronscreen == true)
                   {
                       center.remove(errlabel);
                       center.revalidate();
                       center.repaint();
                    }
                   cardnumber = acnum.getText();
                   but_r3.setText("Cancel");
                   but_r3.setActionCommand("ULogout");
                   but_r4.setText("Login");
                   but_r4.setActionCommand("Login2");
                   loginlabel.setText("Please Enter Your Pin to Continue");
                   center.remove(acnum);
                   pin = new JPasswordField();
                   pin.setHorizontalAlignment(JTextField.CENTER);
                   pin.addKeyListener(new KeyAdapter() 
                   {
                       public void keyTyped(KeyEvent e) {
                           char caracter = e.getKeyChar();
                           if (((caracter < '0') || (caracter > '9'))
                                    && (caracter != '\b')) {
                                e.consume();
                           }
                           if (pin.getText().length() >= 6 ) // limit textfield to 6 characters
                                    e.consume(); 
                        }
                   });
                   center.add(pin);
                }
               
            }
            
            if (event.getActionCommand() == "Login2")
            {
                if (String.valueOf(pin.getPassword()).equals(""))
               {
                   JOptionPane.showMessageDialog(null, "No Pin Entered!");
                   return;
               }
                int accountpin = Integer.parseInt(String.valueOf(pin.getPassword()));
                c = m.uiAuth(cardnumber, accountpin);
                if (accountpin == 4321 && cardnumber.equals("444555"))
                {
                    adminquit();
                }
                else if (c == null)
                {
                    center.remove(pin);
                    returntologin();
                    errlabel = new JLabel();
                    errlabel.setFont(mainfont);
                    errlabel.setText("<html><font color='red'><b><center>Unknown Card or Invalid PIN</center></b></font></html>");
                    center.add(errlabel);
                    erronscreen = true;
                }
                else
                {
                    returntomain();
                }                
            }
            
            if (event.getActionCommand() == "BQuery")
            {
                addacctbuttons("PersACBal", "Balance Inquiry", "Personal");
            }
            
            if (event.getActionCommand() == "BQueryB")
            {
                addacctbuttons("PersACBal", "Balance Inquiry", "Business");
            }
            
            if (event.getActionCommand().equals("PersACBal0"))
            {
                Account x = acc.get(0);//c.getAccount(0);
                mainlabel.setText(x.toString());                
            }
            
            if (event.getActionCommand().equals("PersACBal1"))
            {
                Account x = acc.get(1);
                mainlabel.setText(x.toString());
            }
            if (event.getActionCommand().equals("PersACBal2"))
            {
                Account x = acc.get(2);
                mainlabel.setText(x.toString());               
            }
            
            if (event.getActionCommand().equals("PersACBal3"))
            {
                Account x = acc.get(3);
                mainlabel.setText(x.toString());
            }
            if (event.getActionCommand().equals("PersACBal4"))
            {
                Account x = acc.get(4);
                mainlabel.setText(x.toString());                
            }
            
                        
            if (event.getActionCommand() == "BacktoMain")
            {
                returntomain();
            }
            
            if (event.getActionCommand() == "ULogout")
            {
                returntologin();
            }
            
            if (event.getActionCommand() == "UIQuit")
            {
                //write to file after user Quit
                BankData.writeData();
                
                //code to end software
                System.exit(0);
            }
                                   
            if (event.getActionCommand() == "Wdrwl")
            {
                addacctbuttons("PersACWdrwl", "Withdrawal", "Personal");//Action Command for Deposit Options Should Initialize but_l1 and but_l2 for action DpstOther and DpstMine
            }
            
            if (event.getActionCommand() == "WdrwlB")
            {
                addacctbuttons("PersACWdrwl", "Withdrawal", "Business");//Action Command for Deposit Options Should Initialize but_l1 and but_l2 for action DpstOther and DpstMine
            }
            
            if (event.getActionCommand().equals("PersACWdrwl0"))
            {
                setupWithdrawal(0);
                but_r3.setActionCommand("PerfWdrwl0");
            }
            
            if (event.getActionCommand().equals("PersACWdrwl1"))
            {
                setupWithdrawal(1);
                but_r3.setActionCommand("PerfWdrwl1");
            }
            
            if (event.getActionCommand().equals("PersACWdrwl2"))
            {
                setupWithdrawal(2);
                but_r3.setActionCommand("PerfWdrwl2");
            }
            
            if (event.getActionCommand().equals("PersACWdrwl3"))
            {
                setupWithdrawal(3);
                but_r3.setActionCommand("PerfWdrwl3");
            }
            
            if (event.getActionCommand().equals("PersACWdrwl4"))
            {
                setupWithdrawal(4);
                but_r3.setActionCommand("PerfWdrwl4");
            }
            
            if (event.getActionCommand().equals("PerfWdrwl0"))
            {
                if (amount.getText().equals(""))
                {
                   JOptionPane.showMessageDialog(null, "No Amount Entered!");
                   return;
                }
                double amountforw = Double.parseDouble(amount.getText());
                Account x = acc.get(0);
                if (x.checkAccount(amountforw).equals("Ok"))
                {
                    x.withdraw(amountforw);
                    returntomain("<b><u>Withdrawal of " + amountforw + " from Account " + x.getAccNum() + " Successful</b></u><br><br>" + wdrwlbrkdown(amountforw));
                }
                else
                {
                   JOptionPane.showMessageDialog(null, x.checkAccount(amountforw));
                   return;
                }
            }
            
            if (event.getActionCommand().equals("PerfWdrwl1"))
            {
                if (amount.getText().equals(""))
                {
                   JOptionPane.showMessageDialog(null, "No Amount Entered!");
                   return;
                }
                double amountforw = Double.parseDouble(amount.getText());
                Account x = acc.get(1);
                if (x.checkAccount(amountforw).equals("Ok"))
                {
                    x.withdraw(amountforw);
                    returntomain("<b><u>Withdrawal of " + amountforw + " from Account " + x.getAccNum() + " Successful</b></u><br><br>" + wdrwlbrkdown(amountforw));
                }
                else
                {
                   JOptionPane.showMessageDialog(null, x.checkAccount(amountforw));
                   return;
                }
            }
            
            if (event.getActionCommand().equals("PerfWdrwl2"))
            {
                if (amount.getText().equals(""))
                {
                   JOptionPane.showMessageDialog(null, "No Amount Entered!");
                   return;
                }
                double amountforw = Double.parseDouble(amount.getText());
                Account x = acc.get(2);
                if (x.checkAccount(amountforw).equals("Ok"))
                {
                    x.withdraw(amountforw);
                    returntomain("<b><u>Withdrawal of " + amountforw + " from Account " + x.getAccNum() + " Successful</b></u><br><br>" + wdrwlbrkdown(amountforw));
                }
                else
                {
                   JOptionPane.showMessageDialog(null, x.checkAccount(amountforw));
                   return;
                }
            }
            
            if (event.getActionCommand().equals("PerfWdrwl3"))
            {
                if (amount.getText().equals(""))
                {
                   JOptionPane.showMessageDialog(null, "No Amount Entered!");
                   return;
                }
                double amountforw = Double.parseDouble(amount.getText());
                Account x = acc.get(3);
                if (x.checkAccount(amountforw).equals("Ok"))
                {
                    x.withdraw(amountforw);
                    returntomain("<b><u>Withdrawal of " + amountforw + " from Account " + x.getAccNum() + " Successful</b></u><br><br>" + wdrwlbrkdown(amountforw));
                }
                else
                {
                   JOptionPane.showMessageDialog(null, x.checkAccount(amountforw));
                   return;
                }
            }
            
            if (event.getActionCommand().equals("PerfWdrwl4"))
            {
                if (amount.getText().equals(""))
                {
                   JOptionPane.showMessageDialog(null, "No Amount Entered!");
                   return;
                }
                double amountforw = Double.parseDouble(amount.getText());
                Account x = acc.get(4);
                if (x.checkAccount(amountforw).equals("Ok"))
                {
                    x.withdraw(amountforw);
                    returntomain("<b><u>Withdrawal of " + amountforw + " from Account " + x.getAccNum() + " Successful</b></u><br><br>" + wdrwlbrkdown(amountforw));
                }
                else
                {
                   JOptionPane.showMessageDialog(null, x.checkAccount(amountforw));
                   return;
                }
            }
            
            if (event.getActionCommand() == "Dpst")
            {
                center.removeAll();
                center.revalidate();
                center.repaint();
                center.setLayout(new GridLayout(8,1,0,5));
                loginlabel.setFont(mainfont);
                loginlabel.setText("<html><center><b>Please Press the Button Corresponding to the Desired Function</b></center></html>");
                JLabel infolabel1 = new JLabel(" A. Deposit to My Accounts");
                JLabel infolabel2 = new JLabel(" B. Deposit to Other Account");
                infolabel1.setFont(mainfont);
                infolabel2.setFont(mainfont);
                center.add(loginlabel);
                center.add(infolabel1);
                center.add(infolabel2);
                
                but_l1.setText("A");
                but_l1.setActionCommand("DpstMine");
                but_l2.setText("B");
                but_l2.setActionCommand("DpstOther");
                but_r1.setText("");
                but_r1.setActionCommand("");
                but_r2.setText("");
                but_r2.setActionCommand("");
                but_r3.setText("");
                but_r3.setActionCommand("");

                but_r4.setText("Return");
                but_r4.setActionCommand("BacktoMain");
                
            }
            
            if (event.getActionCommand() == "DpstOther")
            {
                center.removeAll();
                center.revalidate();
                center.repaint();
                center.setLayout(new GridLayout(8,1,0,5));
                loginlabel.setText("Please Enter Account Number For Deposit:");
                loginlabel.setFont(mainfont);
                acnum = new JTextField();
                acnum.setHorizontalAlignment(JTextField.CENTER);
                acnum.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) { 
                        char caracter = e.getKeyChar();
                        if (((caracter < '0') || (caracter > '9'))
                                && (caracter != '\b')) {
                            e.consume();
                        }
                        if (acnum.getText().length() >= 7 ) // limit textfield to 6 characters
                                    e.consume(); 
                    }
                });
                acnum.setFont(mainfont);
                center.add(loginlabel);
                center.add(acnum);
                but_l1.setText("");
                but_l2.setText("");
                but_l3.setText("");
                but_l4.setText("");
                but_r1.setText("");
                but_r2.setText("");
                but_r3.setText("Continue");
                but_r3.setActionCommand("DpstOther2");
                but_r4.setText("Return");
                but_r4.setActionCommand("BacktoMain");
                //Action Command for Deposit to Other Accounts Should bring up a TextField with Option to Enter Account Number
            }
            
            if (event.getActionCommand() == "DpstOther2")
            {
                setupDeposit();
                but_r3.setActionCommand("PerfDpstOther");
            }
            
            if (event.getActionCommand() == "DPSTComplete")
            {
                setupDeposit();
            }
            
            if (event.getActionCommand() == "DpstMine")
            {
                addacctbuttons("PersACDpst", "Deposit", "Personal");//Action Command for Deposit to Own Accounts
            }
            
            if (event.getActionCommand() == "DpstMineB")
            {
                addacctbuttons("PersACDpst", "Deposit", "Business");//Action Command for Deposit to Own Accounts
            }
            
            if (event.getActionCommand().equals("PersACDpst0"))
            {
                setupDeposit();
                but_r3.setActionCommand("PerfDpst0");
            }
            
            if (event.getActionCommand().equals("PersACDpst1"))
            {
                setupDeposit();
                but_r3.setActionCommand("PerfDpst1");
            }
            
            if (event.getActionCommand().equals("PersACDpst2"))
            {
                setupDeposit();
                but_r3.setActionCommand("PerfDpst2");
            }
            
            if (event.getActionCommand().equals("PersACDpst3"))
            {
                setupDeposit();
                but_r3.setActionCommand("PerfDpst3");
            }
            
            if (event.getActionCommand().equals("PersACDpst4"))
            {
                setupDeposit();
                but_r3.setActionCommand("PerfDpst4");
            }
            
            if (event.getActionCommand().equals("PerfDpstOther"))
            {
                double amounttodeposit = calcDeposit();
                Account x = m.getAcNum(Integer.parseInt(acnum.getText()));
                if (x == null)
                {
                    returntomain("Error - Account Not Found!");
                }
                else
                {
                    x.deposit(amounttodeposit);
                    returntomain("Deposit Successful!");
                }
            }
            
            if (event.getActionCommand().equals("PerfDpst0"))
            {
                double amounttodeposit = calcDeposit();
                Account x = acc.get(0);
                x.deposit(amounttodeposit);
                returntomain("Deposit Successful!");
            }
            
            if (event.getActionCommand().equals("PerfDpst1"))
            {
                double amounttodeposit = calcDeposit();
                Account x = acc.get(1);
                x.deposit(amounttodeposit);
                returntomain("Deposit Successful!");
            }
            
            if (event.getActionCommand().equals("PerfDpst2"))
            {
                double amounttodeposit = calcDeposit();
                Account x = acc.get(2);
                x.deposit(amounttodeposit);
                returntomain("Deposit Successful!");
            }
            
            if (event.getActionCommand().equals("PerfDpst3"))
            {
                double amounttodeposit = calcDeposit();
                Account x = acc.get(3);
                x.deposit(amounttodeposit);
                returntomain("Deposit Successful!");
            }
            
            if (event.getActionCommand().equals("PerfDpst4"))
            {
                double amounttodeposit = calcDeposit();
                Account x = acc.get(4);
                x.deposit(amounttodeposit);
                returntomain("Deposit Successful!");
            }
                
        }
    }
    
    private double calcDeposit()
    {
        int num5000 = 0;
        int num1000 = 0;
        int num500 = 0;
        int num100 = 0;
        double depositamount;
        
        if ("" != bills5000.getText().intern())
        {
           num5000 = Integer.parseInt(bills5000.getText());
        }
        if ("" != bills1000.getText().intern())
        {
           num1000 = Integer.parseInt(bills1000.getText());
        }
        if ("" != bills500.getText().intern())
        {
           num500 = Integer.parseInt(bills500.getText());
        }
        if ("" != bills100.getText().intern())
        {
           num100 = Integer.parseInt(bills100.getText());
        }
        
        if (num5000 == 0 && num1000 == 0 && num500 == 0 && num100 == 0)
        {
            JOptionPane.showMessageDialog(null, "No Amount Entered!");
            return 0;
        }
        depositamount = (num5000*5000) + (num1000*1000) + (num500*500) + (num100*100);
        return depositamount;
    }
    
    private void setupDeposit()
    {
        center.removeAll();
        center.revalidate();
        center.repaint();
        center.setLayout(new GridLayout(9,1,0,5));
        loginlabel.setText("Please Enter The Amount You Would Like to Deposit:");
        loginlabel.setFont(mainfont);
        JLabel l5000 = new JLabel("Please Enter Amount of J$5000 Bills");
        JLabel l1000 = new JLabel("Please Enter Amount of J$1000 Bills");
        JLabel l500 = new JLabel("Please Enter Amount of J$500 Bills");
        JLabel l100 = new JLabel("Please Enter Amount of J$100 Bills");
        l5000.setFont(mainfont);
        l1000.setFont(mainfont);
        l500.setFont(mainfont);
        l100.setFont(mainfont);
        bills5000 = new JTextField();
        bills1000 = new JTextField();
        bills500 = new JTextField();
        bills100 = new JTextField();
        bills5000.setHorizontalAlignment(JTextField.CENTER);
        bills1000.setHorizontalAlignment(JTextField.CENTER);
        bills500.setHorizontalAlignment(JTextField.CENTER);
        bills100.setHorizontalAlignment(JTextField.CENTER);
        
        KeyAdapter k = new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))
                        && (caracter != '\b')) {
                    e.consume();
                }
            }
        };
        
        bills5000.addKeyListener(k);
        bills1000.addKeyListener(k);
        bills500.addKeyListener(k);
        bills100.addKeyListener(k);
        
        bills5000.setFont(mainfont);
        bills1000.setFont(mainfont);
        bills500.setFont(mainfont);
        bills100.setFont(mainfont);
        
        center.add(loginlabel);
        center.add(l5000);
        center.add(bills5000);
        center.add(l1000);
        center.add(bills1000);
        center.add(l500);
        center.add(bills500);
        center.add(l100);
        center.add(bills100);
        
        but_l1.setText("");
        but_l2.setText("");
        but_l3.setText("");
        but_l4.setText("");
        but_r1.setText("");
        but_r2.setText("");
        but_r3.setText("Continue");
        but_r3.setActionCommand("DPSTComplete");
        but_r4.setText("Return");
        but_r4.setActionCommand("BacktoMain");
    }
    
    private void setupWithdrawal(int account)
    {
        Account x = acc.get(account);
        center.removeAll();
        center.revalidate();
        center.repaint();
        center.setLayout(new GridLayout(8,1,0,5));
        loginlabel.setText("Please Enter The Amount You Would Like to Withdraw:");
        loginlabel.setFont(mainfont);
        amount = new JTextField();
        amount.setHorizontalAlignment(JTextField.CENTER);
        amount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))
                        && (caracter != '\b')) {
                    e.consume();
                }
                if (amount.getText().length() >= 16 ) // limit textfield to 6 characters
                            e.consume(); 
            }
        });
        amount.setFont(mainfont);
        center.add(loginlabel);
        center.add(amount);
        but_l1.setText("");
        but_l2.setText("");
        but_l3.setText("");
        but_l4.setText("");
        but_r1.setText("");
        but_r2.setText("");
        but_r3.setText("Continue");
        but_r4.setText("Return");
        but_r4.setActionCommand("BacktoMain");
        mainlabel.setText(x.toString());
        center.add(mainlabel);
    }
    
    protected void returntologin()
    {
        but_l1.setText("");
        but_l1.setActionCommand("");
        but_l2.setText("");
        but_l2.setActionCommand("");
        but_l3.setText("");
        but_l3.setActionCommand("");
        but_l4.setText("");
        but_l4.setActionCommand("");
        but_r1.setText("");
        but_r1.setActionCommand("");
        but_r2.setText("");
        but_r2.setActionCommand("");
        but_r3.setText("");
        but_r3.setActionCommand("");
        but_r4.setText("Continue");
        but_r4.setActionCommand("Login1");
        center.removeAll();
        center.revalidate();
        center.repaint();
        center.setLayout(new GridLayout(8,1,0,5));
        loginlabel.setText("Please Enter Your Card Number:");
        loginlabel.setFont(mainfont);
        acnum = new JTextField();
        acnum.setHorizontalAlignment(JTextField.CENTER);
        acnum.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))
                        && (caracter != '\b')) {
                    e.consume();
                }
                if (acnum.getText().length() >= 16 ) // limit textfield to 6 characters
                            e.consume(); 
            }
        });
        acnum.setFont(mainfont);
        center.add(loginlabel);
        center.add(acnum);
        c = null;
        acc = null;
    }
    
    private void returntomain()
    {
        center.removeAll();
        center.revalidate();
        center.repaint();
        center.setLayout(new GridLayout(2,1,0,5));
        but_l1.setText("");
        but_l1.setActionCommand("");
        but_l2.setText("");
        but_l2.setActionCommand("");
        but_l3.setText("");
        but_l3.setActionCommand("");
        but_l4.setText("");
        but_l3.setActionCommand("");
        but_r1.setText("Balance Query");
        but_r1.setActionCommand("BQuery");
        but_r2.setText("Withdrawal");
        but_r2.setActionCommand("Wdrwl");
        but_r3.setText("Deposit");
        but_r3.setActionCommand("Dpst");
        but_r4.setText("Logout");
        but_r4.setActionCommand("ULogout");
        loginlabel.setText("<html><b><center>Welcome " + c.getFullName() + "</center></b><br><br><br>Please Select From the buttons on the right!</html>");
        center.add(loginlabel);
    }
    
     private void adminquit()
    {
        center.removeAll();
        center.revalidate();
        center.repaint();
        center.setLayout(new GridLayout(2,1,0,5));
        but_l1.setText("");
        but_l1.setActionCommand("");
        but_l2.setText("");
        but_l2.setActionCommand("");
        but_l3.setText("");
        but_l3.setActionCommand("");
        but_l4.setText("");
        but_l3.setActionCommand("");
        but_r1.setText("");
        but_r1.setActionCommand("");
        but_r2.setText("");
        but_r2.setActionCommand("");
        but_r3.setText("");
        but_r3.setActionCommand("");
        but_r4.setText("Exit");
        but_r4.setActionCommand("UIQuit");
        loginlabel.setText("<html><b><center>Welcome Administrator</center></b><br><br><br>Please Exit the Software Using the Button to the Right!</html>");
        center.add(loginlabel);
    }
    
    private void returntomain(String messagetoshow)
    {
        center.removeAll();
        center.revalidate();
        center.repaint();
        center.setLayout(new GridLayout(1,1,0,5));
        but_l1.setText("");
        but_l1.setActionCommand("");
        but_l2.setText("");
        but_l2.setActionCommand("");
        but_l3.setText("");
        but_l3.setActionCommand("");
        but_l4.setText("");
        but_l3.setActionCommand("");
        but_r1.setText("Balance Query");
        but_r1.setActionCommand("BQuery");
        but_r2.setText("Withdrawal");
        but_r2.setActionCommand("Wdrwl");
        but_r3.setText("Deposit");
        but_r3.setActionCommand("Dpst");
        but_r4.setText("Logout");
        but_r4.setActionCommand("ULogout");
        loginlabel.setText("<html><b><center>Welcome " + c.getFullName() + "</center></b><br><br><br>" + messagetoshow + "</html>");
        center.add(loginlabel);
    }
    
    private static String wdrwlbrkdown(double numtobreak)
    {
        double orignumber = numtobreak;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (i1 = 0; numtobreak > 0 ; i1++)
        {
            if (numtobreak < 5000)
            {
                i1--;
                for (i2 = 0; numtobreak > 0 ; i2++)
                {
                    if (numtobreak < 1000)
                    {
                        i2--;
                        for (i3 = 0; numtobreak > 0 ; i3++)
                        {
                            if (numtobreak < 500)
                            {
                                i3--;
                                for (i4 = 0; numtobreak > 0 ; i4++)
                                {
                                    numtobreak = numtobreak - 100;
                                }
                            }
                            numtobreak = numtobreak - 500;
                        }
                    }
                    numtobreak = numtobreak - 1000;
                }
            }
            numtobreak = numtobreak - 5000;
        }
        return "<center>Number of J$5000 - " + i1 + "<br>Number of J$1000 - " + i2 + "<br>Number of J$ 500 - " + i3 + "<br>Number of J$ 100 - " + i4 + "</center>";
    }
    
    private void addacctbuttons(String action, String displayedaction, String actype)
    {
        String getaccounts = "<html>";
        but_r1.setText("");
        but_r1.setActionCommand("");
        but_r2.setText("");
        but_r2.setActionCommand("");
        but_l1.setText("");
        but_l1.setActionCommand("");
        but_l2.setText("");
        but_l2.setActionCommand("");
        but_l3.setText("");
        but_l3.setActionCommand("");
        but_l4.setText("");
        but_l4.setActionCommand("");
        if (actype.equals("Business"))
        {
            but_r3.setText("Pers. Accounts");
            if (displayedaction.equals("Balance Inquiry"))
            {
                but_r3.setActionCommand("BQuery");
            }
            if (displayedaction.equals("Withdrawal"))
            {
                but_r3.setActionCommand("Wdrwl");
            }
            if (displayedaction.equals("Deposit"))
            {
                but_r3.setActionCommand("DpstMine");
            }
            acc = m.getBusinessAccounts(c.getClientId());
        }
        else
        {
            but_r3.setText("Bus. Accounts");
            if (displayedaction.equals("Balance Inquiry"))
            {
                but_r3.setActionCommand("BQueryB");
            }
            if (displayedaction.equals("Withdrawal"))
            {
                but_r3.setActionCommand("WdrwlB");
            }
            if (displayedaction.equals("Deposit"))
            {
                but_r3.setActionCommand("DpstMineB");
            }
            acc = c.getAccounts();
        }
        Account a;
        int j = acc.size() + 65;
        int numaccts = acc.size();
        for (int i = 65;i < j;i++)
        {
            a = acc.get(i-65);
            getaccounts = getaccounts + (char)i + ". " + a.toStringBrief() + "<br>";
        }
        getaccounts = getaccounts + "<html>";
        but_r4.setText("Return");
        but_r4.setActionCommand("BacktoMain");
        center.removeAll();
        center.revalidate();
        center.repaint();
        center.setLayout(new GridLayout(2,1,0,3));
        mainlabel = new JLabel();
        mainlabel.setText(getaccounts);
        mainlabel.setFont(mainfont);
        infolabel = new JLabel();
        infolabel.setFont(mainfont);
        infolabel.setText("<html><center><b><u>" + displayedaction + "</u><br><br>Please Select the Letter Corresponding to your Account</b></center></html>");
        center.add(infolabel);
        center.add(mainlabel);
        
        for (int i = 0; i < numaccts; i++)
        {
           if (i == 0)
           {
               but_l1.setText("A");
               but_l1.setActionCommand(action + i);
           }
           if (i == 1)
           {
               but_l2.setText("B");
               but_l2.setActionCommand(action + i);
           }
           if (i == 2)
           {
               but_l3.setText("C");
               but_l3.setActionCommand(action + i);
           }
           if (i == 3)
           {
               but_l4.setText("D");
               but_l4.setActionCommand(action + i);
           }
           if (i == 4)
           {
               but_r1.setText("E");
               but_r1.setActionCommand(action + i);
           }
           
       }
    }
}
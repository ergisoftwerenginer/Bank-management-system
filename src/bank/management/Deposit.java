package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.text.*;


public class Deposit extends JFrame implements ActionListener{
    JTextField amount;
    JButton deposit, back;
    String pinnumber;
    

    Deposit(String pinnumber){
        this.pinnumber= pinnumber;
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 700,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel (i3);
        image.setBounds(0, 0, 700, 700);
        add(image);
        
        JLabel text = new JLabel("Enter The Amount You Want To Deposit");
        text.setBounds(150,230,700,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD,12));
        image.add(text);
       
        amount = new JTextField();
        amount.setFont(new Font("System", Font.BOLD,12));
        amount.setBounds(185,280,150,20);

          // Lejo vetëm numra
           ((PlainDocument) amount.getDocument()).setDocumentFilter(new DocumentFilter() {
          @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                 if (string.matches("\\d+")) {
            super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
         if (text.matches("\\d+")) {
             super.replace(fb, offset, length, text, attrs);
        }
        }
      });
     image.add(amount);

        
        deposit = new JButton ("Deposit");
        deposit.setBounds(264,380,135,20);
        deposit.addActionListener(this);
        image.add(deposit);
            
        back = new JButton("Back");
        back.setBounds(264,405,135,20);
        back.addActionListener(this);
        image.add(back);
        
        setSize(700,700);
        setLocation(300,0);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == deposit){
            String number = amount.getText();
Date date = new Date();
if (number.equals("")) {
    JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
} else {
    try {
        int amountInt = Integer.parseInt(number);
        if (amountInt <= 0) {
            JOptionPane.showMessageDialog(null, "Amount must be greater than 0");
            return;
        }
        Conn conn = new Conn();
        String query = "insert into bank values ('" + pinnumber + "','" + date + "','Deposit','" + amountInt + "')";
        conn.s.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "ALL " + amountInt + " Deposited Successfully");
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
    } catch (Exception e) {
        System.out.println(e);
    }


            
            }
        }else if (ae.getSource()== back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
   
    
    
    public static void main(String args[]) {
        new Deposit("");
        
    }
}

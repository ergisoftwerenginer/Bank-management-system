package bank.management;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author ergik
 */
public class BalanceEnquiry extends JFrame implements ActionListener {
    JButton back;
    String pinnumber; 
    JLabel text;
    
    BalanceEnquiry (String pinnumber){
        this.pinnumber = pinnumber;
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 700, 750);
        add(image);
        
        back = new JButton("Back");
        back.setBounds(264, 430, 135, 20);
        back.addActionListener(this);
        image.add(back);
        
         Conn c = new Conn();
         double balance = 0;
        try{
            ResultSet all = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");

            while (all.next()) {
                String type = all.getString("type");
                double amt = Double.parseDouble(all.getString("amount"));
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }
        }catch (Exception e){
            System.out.println(e);
           
        }
        
        text = new JLabel("Your current account balance is ALL:"+ balance);
        text.setBounds(165,250,700,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD,14));
        image.add(text);
        


        setSize(700, 750);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }
 
    
    @Override
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }
    
    
    public static void main(String args[]) {
        new BalanceEnquiry(" ");

    }
}

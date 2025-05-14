package bank.management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;




public class SignupThree extends JFrame implements ActionListener {
    
    JButton cancel, submit;
    JRadioButton r1,r2,r3,r4;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    String formno;
   
    
    SignupThree(String formno){
        this.formno = formno;
        setLayout(null);
        JLabel l1 = new JLabel("Page 3: Account Details");
        l1.setFont( new Font("Releway", Font.BOLD,22 ));
        l1.setBounds(280, 40, 300, 40);
        add(l1);
    
        JLabel type = new JLabel("Account Type");
        type.setBounds(110,70,150,60);
        type.setFont(new Font ("Releway",Font.BOLD,22));
        add(type);
        
        r1= new JRadioButton("Saving Account");
        r1.setBounds(110, 120, 150, 50);
        add(r1);
       r2 = new JRadioButton("Fixed Deposit Account");
        r2.setBounds(110, 170, 150, 40);
        add(r2);
        r3 = new JRadioButton("Current  Account");
        r3.setBounds(360, 120, 190, 50);
        add(r3);
        
        r4 = new JRadioButton("Reccuring Deposit Account ");
        r4.setBounds(360, 170, 190, 50);
        add(r4);
        
        ButtonGroup groupacc = new ButtonGroup();
        groupacc.add(r1);
        groupacc.add(r2);
        groupacc.add(r3);
        groupacc.add(r4);
        
        
        JLabel card = new JLabel("Card Number");
        card.setBounds(110,220,150,60);
        card.setFont(new Font ("Releway",Font.BOLD,22));
        add(card);
        
        JLabel number = new JLabel ("XXXX-XXXX-XXXX-2005");
        number.setBounds(360,220,300,60);
        number.setFont(new Font ("Releway",Font.BOLD,22));
        add(number);
        
        JLabel carddetail = new JLabel("(Numri 16-shifror i kartes suaj)");
        carddetail.setBounds(110, 265, 300, 40);
        add(carddetail);
        
        JLabel appear = new JLabel ("it would appear on ATM Card/Cheque Book and Statements");
        appear.setBounds(360, 265, 360, 40);
        add(appear);
        
        JLabel pin = new JLabel ("Pin :");
        pin.setBounds(110, 300, 60, 40);
        pin.setFont(new Font ("Releway",Font.BOLD,22));
        add(pin);

        JLabel pnumber = new JLabel("XXXX");
        pnumber.setBounds(360, 300, 60, 40);
        pnumber.setFont(new Font ("Releway",Font.BOLD,22));
        add(pnumber);
        
        JLabel services = new JLabel("Services Required");
        services.setBounds(110,350,360,40);
        services.setFont(new Font ("Releway",Font.BOLD,22));
        add(services);
        
        
        c1 = new JCheckBox(" ATM CARD ");
        c1.setBounds(110,380,120,40);
        add(c1);
        c2 = new JCheckBox(" Internet Banking");
        c2.setBounds(360,380,150,40);
        add(c2);
        c3 = new JCheckBox("Mobile Banking");
        c3.setBounds(110,410,150,40);
        add(c3);
        c4 = new JCheckBox("Email Alerts");
        c4.setBounds(360,410,150,40);
        add(c4);
        c5 = new JCheckBox("Cheque Book");
        c5.setBounds(110,440,150,40);
        add(c5);
        c6 = new JCheckBox("E-Statement");
        c6.setBounds(360,440,120,40);
        add(c6);
        c7 = new JCheckBox("I confirm that the above details are correct to the best of my knowledge.");
        c7.setBounds(110,483,460,50);
        add(c7);
        
        
        
       submit = new JButton ("Submi");
       submit.setBounds(290, 580, 150, 30);
       submit.setBackground(Color.black);
       submit.setForeground(Color.white);
       submit.addActionListener(this) ;
      add(submit);
    
       
        
        cancel = new JButton("Cancle");
        cancel.setBounds(480, 580, 150, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);

       
        setSize(850,800);
        setLocation(350,10);
        setLocationRelativeTo(null);   
        setVisible(true); 

    }

    SignupThree() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == submit){
            String accountType = null;
            if (r1.isSelected()){
                accountType = "Saving Account";
            } else if (r2.isSelected()){
                accountType = "Fixed Deposit Account";
            }else if (r3.isSelected()){
                accountType = "Current Account";
            }else if (r4.isSelected()){
                accountType = "Reccuring Account";
            }
            
            Random random = new Random();
            String cardnumber = "" + Math.abs((random.nextLong() & 90000000L) + 5040936000000000L);
            
            
            String pinnumber = "" + Math.abs((random.nextLong()&9000L)+ 1000L);
            
            String facility = " ";
            if (c1.isSelected()) {
                facility = facility + "ATM Card";
            }else if (c2.isSelected()){
                facility = facility + "Internet Banking";
            }else if (c3.isSelected()){
                facility = facility + "Mobile Banking";
            }else if (c4.isSelected()){
                facility = facility + "Emails Alerts";
            }else if (c5.isSelected()){
                facility = facility + "Cheque Book";
            }else if (c6.isSelected()){
                facility = facility + "E-Statement";
            }

            try{
                if (accountType.equals(" ")){
                    JOptionPane.showMessageDialog(null , "Account type is Requaired");
                }else {
                    Conn conn = new Conn();
                    String query1 = "insert into Signupthree values('"+formno +"','"+accountType +"','"+cardnumber +"','"+pinnumber +"','"+facility +"')";
                    String query2 = "insert into login values('"+formno +"','"+cardnumber +"','"+pinnumber +"')";
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);

                    
                    
                    JOptionPane.showMessageDialog(null,"Card NO:" + cardnumber + "\n Pin:" + pinnumber);
                    
                    
                    setVisible(false);
                    new Deposit(pinnumber).setVisible(false);
                }
             
                    
            }catch(Exception e) {
                System.out.println(e);
            }
            
            
        }else if (ae.getSource() == cancel){
            setVisible(false);
            new Login().setVisible(true);
            
        }

    }
    public static void main(String args[]) {
        new SignupThree(" ");
    }

    

   

}
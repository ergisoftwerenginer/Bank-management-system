package bank.management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




public class Transactions extends JFrame implements ActionListener{
    
    JButton deposit, withdrow,fastcash, ministatement, pinchange, balanceenquiry,exit;
    String pinnumber;
    
    Transactions(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon (ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,700,750);
        add(image);
        
        JLabel text = new JLabel ("Please Select your Transaction");
        text.setBounds(165,250,700,35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD,14));
        image.add(text);
       
        deposit = new JButton("Deposit");
        deposit.setBounds(130,352,115,20);
        deposit.addActionListener(this);
        image.add(deposit);
        
        withdrow = new JButton("Cash Withdrawl");
        withdrow.setBounds(264,352,135,20);
        withdrow.addActionListener(this);
        image.add(withdrow);
        
        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(130,378,115,20);
        fastcash.addActionListener(this);
        image.add(fastcash);
        
        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(264,378,135,20);
        ministatement.addActionListener(this);
        image.add(ministatement);
        
        pinchange = new JButton("Pin Change ");
        pinchange.setBounds(130,404,115,20);
        pinchange.addActionListener(this);
        image.add(pinchange);
        
        balanceenquiry = new JButton("Balance Enquiry");
        balanceenquiry.setBounds(264,404,135,20);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);
        
        exit = new JButton("Exit ");
        exit.setBounds(264,430,135,20);
        exit.addActionListener(this);
        image.add(exit);
        
        
        setSize(700,750);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
       
    }

    /**
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()== exit){
            System.exit(0);
        }else if(ae.getSource()== deposit){
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);                    
        }else if (ae.getSource()== withdrow){
            setVisible(false);
            new Withdrawl(pinnumber).setVisible(true);
        }else if(ae.getSource()== fastcash){
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);                    
        }else if(ae.getSource()== pinchange){
            setVisible(false);
            new PinChange(pinnumber).setVisible(true);
        }
      
    }
   
    public static void main(String args[]) {
        new Transactions("");      
    }
}

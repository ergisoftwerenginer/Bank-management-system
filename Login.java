package bank.management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JButton login, signup, clear;
    
    JTextField cardTextField;
    JPasswordField pinTextField;        
            
             
    Login(){
        setTitle("Automated Teller Machine by:Ergi Kasa");
        
        setLayout(null);
        
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        ImageIcon i3 = new ImageIcon(i2); 
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);
        
        JLabel text = new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Osward" , Font.BOLD,28));
        text.setBounds(200,40,400,40);
        add (text);
        
        JLabel email = new JLabel("   Email/");
        email.setFont(new Font("Relaway" , Font.BOLD,28));
        email.setBounds(130,140,150,40);
        add (email);
        JLabel username = new JLabel("username");
        username.setFont(new Font("Relaway" , Font.BOLD,28));
        username.setBounds(125, 160, 150, 40);
        add(username);
        
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 155, 250,30);
        cardTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(cardTextField);
        
         JLabel pin = new JLabel(" password:");
        pin.setFont(new Font("Relaway" , Font.BOLD,28));
        pin.setBounds(110,230,400,40);
        add (pin);
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 235, 250,30);
        pinTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextField);
        
        login = new JButton ("Log in");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);
        
        clear = new JButton ("Clear");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);
        
        
        signup= new JButton ("Sign up");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,480);
        setVisible(true);
        setLocationRelativeTo(null); // Vendos dritaren në qendër
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Përfundon programin kur mbyllet dritarja
         
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear){
        cardTextField.setText("");
        pinTextField.setText("");
        }else if(ae.getSource() == login){
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText().trim();
            String pinnumber = pinTextField.getText().trim();
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";
            try{
               ResultSet rs = conn.s.executeQuery(query);
               if (rs.next()){
                   setVisible(false);
                   new Transactions(pinnumber).setVisible(true);
               } else {
                   JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
               }
            }catch (Exception e){
                System.out.println(e);
            }
        }else if(ae.getSource()== signup){
            setVisible(false);
            new SignupOne().setVisible(true);
        }}
    
    
    
        
    public static void main(String Args[]){
        new Login();
    
    }

   
        
    }


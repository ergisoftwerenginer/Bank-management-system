package bank.management;

/**
 *
 * @author ergik
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;





public class SignupOne extends JFrame implements ActionListener {
    
    long random;
    JTextField idnumTextField,emriTextField,mbiemriTextField,emailTextField,adresaTextField,qytetiTextField,kodipostarTextField ;
    JButton next, backButton;
    //JRadioButton male,female,unmarried , married, other ;
    JDateChooser dateChooser;
    

    SignupOne(){
        
        setLayout(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      

       
        Random ran = new Random();
        random = (Math.abs(ran.nextLong()%9000L ) + 1000L);
        
        
        JLabel formno = new JLabel("APPLICATION FORM NO." + random  );
        formno.setFont(new Font("Raleway",Font.BOLD,30));
        formno.setBounds(140,20,600,40);
        add(formno);
        getContentPane().setBackground(Color.WHITE);
 
        
        JLabel personDetails = new JLabel("Page 1: Person Details");
        personDetails.setFont(new Font("Raleway",Font.BOLD,22));
        personDetails.setBounds(290,70,400,30);
        add(personDetails);
        
        JLabel idnum = new JLabel("Numri Personal:");
        idnum.setFont(new Font("Raleway",Font.BOLD,20));
        idnum.setBounds(100,140,160,30);
        add(idnum);
        idnumTextField = new JTextField();
        idnumTextField.setBounds(300, 140, 250,30);
        idnumTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(idnumTextField);
        
     
        JLabel emri = new JLabel("Emri:");
        emri.setFont(new Font("Raleway",Font.BOLD,20));
        emri.setBounds(100,200,200,30);
        add(emri);
        emriTextField = new JTextField();
        emriTextField.setBounds(300, 200, 250,30);
        emriTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(emriTextField);
        
        
        JLabel mbiemri = new JLabel("Mbiemri:");
        mbiemri.setFont(new Font("Raleway",Font.BOLD,20));
        mbiemri.setBounds(100,250,200,30);
        add(mbiemri);
        mbiemriTextField = new JTextField();
        mbiemriTextField.setBounds(300, 250, 250,30);
        mbiemriTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(mbiemriTextField);
        
        
        
        JLabel dob = new JLabel("Dite Lindja:");
        dob.setFont(new Font("Raleway",Font.BOLD,20));
        dob.setBounds(100,300,200,30);
        add(dob);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300,300,250,30);
        add(dateChooser);
       
        
       
        //JLabel gender = new JLabel("Gender:");
        //gender.setFont(new Font("Raleway",Font.BOLD,20));
        //gender.setBounds(100,260,200,30);
        //add(gender);
        //male = new JRadioButton("Male");
        //male.setBounds(300,260,60,30);
        //male.setBackground(Color.DARK_GRAY);
        //male.setForeground(Color.WHITE);
        //add(male);
        
        //female = new JRadioButton("Female");
        //female.setBackground(Color.DARK_GRAY);
        //female.setForeground(Color.white);
        //female.setBounds(450,260,70,30);
       // add(female);
       //ButtonGroup gendergroup = new ButtonGroup();
        //gendergroup.add(male);
        //gendergroup.add(female);
        
        
     
        
        JLabel email = new JLabel("Email Address:");
        email.setFont(new Font("Raleway",Font.BOLD,20));
        email.setBounds(100,350,200,30);
        add(email);
        emailTextField = new JTextField();
        emailTextField.setBounds(300, 350, 250,30);
        emailTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(emailTextField);
        
        /*JLabel marital = new JLabel("Marital Status:");
        marital.setFont(new Font("Raleway",Font.BOLD,20));
        marital.setBounds(100,340,200,30);
        add(marital);
        
        married = new JRadioButton("Married");
        married.setBounds(270,340,80,30);
        add(married);
   
        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(370,340,90,30);
        add(unmarried);
        other = new JRadioButton("Other");
        other.setBounds(500,340,70,30);
        add(other);
        
        ButtonGroup marritalgroup = new ButtonGroup();
        marritalgroup.add(married);
        marritalgroup.add(unmarried);
        marritalgroup.add(other);
        */
        
        JLabel addresa = new JLabel("Addresa:");
        addresa.setFont(new Font("Raleway",Font.BOLD,20));
        addresa.setBounds(100,400,200,30);
        add(addresa);
        adresaTextField = new JTextField();
        adresaTextField.setBounds(300, 400, 250,30);
        adresaTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(adresaTextField);
        
        
        JLabel qyteti = new JLabel("Qyteti:");
        qyteti.setFont(new Font("Raleway",Font.BOLD,20));
        qyteti.setBounds(100,450,200,30);
        add(qyteti);
        qytetiTextField = new JTextField();
        qytetiTextField.setBounds(300, 450, 250,30);
        qytetiTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(qytetiTextField);
        
     
        JLabel kodipostar = new JLabel("Kodi Postar:");
        kodipostar.setFont(new Font("Raleway",Font.BOLD,20));
        kodipostar.setBounds(100,500,200,30);
        add(kodipostar);
        kodipostarTextField = new JTextField();
        kodipostarTextField.setBounds(300, 500, 250,30);
        kodipostarTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(kodipostarTextField);
        
        
        /*JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway",Font.BOLD,20));
        state.setBounds(100,500,200,30);
        add(state);
        stateTextField = new JTextField();
        stateTextField.setBounds(300, 500, 250,30);
        stateTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(stateTextField);
         */
        next = new JButton ("next");
        next.setBounds(650, 580, 100, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);
        
        
        backButton = new JButton("Back to Log in");
        backButton.setBounds(480, 580, 150, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.white);
        backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         setVisible(false);
         new Login().setVisible(true);
         }
         });
        add(backButton);

        getContentPane().setBackground(Color.WHITE);
        
        
        setSize(850,800);
        setLocation(350,10);
        setVisible(true); 
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae){
        String formno = " " + random;
        String idnum = idnumTextField.getText();
        String emri = emriTextField.getText();
        String mbiemri = mbiemriTextField.getText();
        String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
        /* String gender = null;
        if (male.isSelected()){
            gender = "Male";
        }else if (female.isSelected()){
            gender = "Female";
        */
        String email = emailTextField.getText();
       /* String marital = null;
        if (married.isSelected()){
            marital = "married";        
        }else if (unmarried.isSelected()){
        marital = "unmarried";       
        } else if (other.isSelected()){
        marital = "other";
        }*/
        
        String adresa = adresaTextField.getText();
        String qyteti = qytetiTextField.getText();
       
        String kodipostar = kodipostarTextField.getText();
        
        try{
            if (idnum.equals("")){
                JOptionPane.showMessageDialog(null,"ID is Requared");
           
            
            }else{
                Conn c = new Conn();
                String query = "INSERT INTO signup VALUES('" + formno + "', '" + idnum + "','" + emri + "', '" + mbiemri + "', '" + dob + "',  '" + email + "',  '" + adresa + "', '" + qyteti + "', '" + kodipostar + "')";
                c.s.executeUpdate(query);
                
                setVisible(false);
                new SignupTwo(formno).setVisible(true);
            
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
  
    public static void main(String[] args) {
        new SignupOne();
    }
    
    
    
    

}
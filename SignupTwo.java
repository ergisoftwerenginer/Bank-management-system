package bank.management;


/**
 *
 * @author ergik
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SignupTwo extends JFrame implements ActionListener {
    
    
    JTextField uname ;
    JButton next,backButton;
    JRadioButton eyes,eno,sno , syes;
    JComboBox occupation, income,education;
    String formno;
    
    
    SignupTwo(String formno){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.formno = formno;
        setLayout(null);
        setTitle("New Account Application Form - Page 2");
        
        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway",Font.BOLD,22));
        additionalDetails.setBounds(290,70,400,30);
        add(additionalDetails);
        
        JLabel username = new JLabel("Username ");
        username.setFont(new Font("Raleway",Font.BOLD,20));
        username.setBounds(100,140,130,30);
        add(username);
        
        uname = new JTextField();
        uname.setBounds(370, 140, 250,30);
        uname.setFont(new Font("Arial",Font.BOLD,14));
        add(uname);
        
        JLabel dob = new JLabel("Te Ardhurat:");
        dob.setFont(new Font("Raleway",Font.BOLD,20));
        dob.setBounds(100,220,250,30);
        add(dob);
        String valincome [] = {"0 to 12000ALL","12001ALL to 70000ALL ","70001ALL to 200000ALL ","20K+"};
        income = new JComboBox(valincome);
        income.setBounds(370, 220, 250, 30);      
        add(income);
      
       
        JLabel gender = new JLabel("Edukimi:");
        gender.setFont(new Font("Raleway",Font.BOLD,20));
        gender.setBounds(100,260,200,30);
        add(gender);
        
        String valqualification [] = {"Gjimnaz"," Bachelor", "Master ", "Doktorrature"};
        education = new JComboBox (valqualification);
        education.setBounds(370, 260, 250, 30);
        add(education);
        
        JLabel marital = new JLabel("Punesimi:");
        marital.setFont(new Font("Raleway",Font.BOLD,20));
        marital.setBounds(100,340,200,30);
        add(marital);
        
        String valoccupation [] = {"Vete-Punesuar", "I punesuar ", "Biznesmen", "i papun", "Pensionisr "};
        occupation = new JComboBox (valoccupation);
        occupation.setBounds(370,340, 250, 30);
        add(occupation);
        
      /*  JLabel address = new JLabel("");
        address.setFont(new Font("Raleway",Font.BOLD,20));
        address.setBounds(100,380,270,30);
        add(address);
        
     
        //TIN Input Field (vetëm numra)
        txtTIN = new JTextField();
        txtTIN.setBounds(370, 380, 250, 30);
        add(txtTIN);
       */
        
        setVisible(true);
    
       
      /*  JLabel city = new JLabel("Personal Identification NO:");
        city.setFont(new Font("Raleway",Font.BOLD,20));
        city.setBounds(100,420,270,30);
        add(city);
        txtPIN = new JTextField();
        txtPIN.setBounds(370, 420, 250, 30);
        add(txtPIN);
        */
     
        JLabel state = new JLabel("i/e moshuar");
        state.setFont(new Font("Raleway",Font.BOLD,20));
        state.setBounds(100,500,200,30);
        add(state);
      
        syes = new JRadioButton("Yes");
        syes.setBounds(370,500,80,30);
        add(syes);
        sno = new JRadioButton("No");
        sno.setBounds(520,500,90,30);
        add(sno);
        
         
        ButtonGroup smarritalgroup = new ButtonGroup();
        smarritalgroup.add(syes);
        smarritalgroup.add(sno);
       
        JLabel pincode = new JLabel("Existing Account");
        pincode.setFont(new Font("Raleway",Font.BOLD,20));
        pincode.setBounds(100,460,200,30);
        add(pincode);
        
        eyes = new JRadioButton("Yes");
        eyes.setBounds(370,460,80,30);
        add(eyes);
        eno = new JRadioButton("No");
        eno.setBounds(520,460,90,30);
        add(eno);
        
         
        ButtonGroup emarritalgroup = new ButtonGroup();
        emarritalgroup.add(eyes);
        emarritalgroup.add(eno);
      
        

        
        next = new JButton ("next");
        next.setBounds(650, 580, 100, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String suname = uname.getText();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String socupation = (String) occupation.getSelectedItem();
        String seniourcitizen = syes.isSelected() ? "Yes" : sno.isSelected() ? "No" : null;
        String existingaccount = eyes.isSelected() ? "Yes" : eno.isSelected() ? "No" : null;
        
        if (suname.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username is required!");
            return;
        }

        try {
            Conn c = new Conn();
            String query = "INSERT INTO signuptwo (username, income, education, occupation, seniorcitizen, existingaccount) " +
                    "VALUES('" + suname + "', '" + sincome + "', '" + seducation + "', '" + socupation + "', '" + seniourcitizen + "', '" + existingaccount + "')";
            c.s.executeUpdate(query);

            // Proceed to next page
            setVisible(false);
            new SignupThree(formno).setVisible(true);  // Ensure formno is passed correctly
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
});

        add(next);

        
        
        
        backButton = new JButton("Back to SignupOne");
        backButton.setBounds(480, 580, 150, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.white);
        backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         setVisible(false);
         new SignupOne().setVisible(true);
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
       
      
        String suname = (String) uname.getText(); 
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String socupation = (String) occupation.getSelectedItem();
        String seniourcitizen = null;
        if (syes.isSelected()){
            seniourcitizen = "Yes";
        }else if (sno.isSelected()){
            seniourcitizen = "NO";
        }
        
        String existingaccount = null;
        if (eyes.isSelected()){
            existingaccount = "Yes";        
        }else if (eno.isSelected()){
        existingaccount = "No";       
        }
        
      
        
        try{
            Conn c = new Conn();
            String query = "INSERT INTO signuptwo VALUES( '" + suname + "',  '" + sincome + "', '" + seducation + "', '" + socupation + "', '" + seniourcitizen + "', '" + existingaccount + "' )";
            c.s.executeUpdate(query);
            
            //Signup3 Obkect
            setVisible(false);
            new SignupThree(formno).setVisible(true);
            
        }catch (Exception e){
            System.out.println(e);
        }
    }
  

    
    public static void main(String[] args) {
        new SignupTwo("");
    }
    
    
    
    

}
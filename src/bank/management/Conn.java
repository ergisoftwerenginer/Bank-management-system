 package bank.management;
import java.sql.*;
/**
 *
 * @author ergik
 */
public class Conn {
    Connection c;
    Statement s;
    
    public Conn(){
        try{
            c  = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "maloku.A1");
            s = c.createStatement();
        }catch(SQLException e){
            System.out.println(e);
        }
        }

    }
    
    


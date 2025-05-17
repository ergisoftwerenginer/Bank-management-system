package bank.management;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {

        setTitle("Mini Statement");
        setLayout(null);

        JLabel mini = new JLabel();
        mini.setBounds(20, 140, 350, 300);  // për listën e transaksioneve
        add(mini);

        JLabel bank = new JLabel("Albanian Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);

        JLabel card = new JLabel("");
        card.setBounds(20, 80, 300, 20);
        add(card);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(20, 460, 300, 20);  // mbi butonin back
        add(balanceLabel);

        JButton back = new JButton("Back");
        back.setBounds(280, 520, 80, 30);
        add(back);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // mbyll këtë dritare
            }
        });

        setSize(400, 600);
        setLocation(400, 50);
        setVisible(true);

        // Merr numrin e kartës
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM login WHERE pin = '" + pinnumber + "'");
            while (rs.next()) {
                card.setText("Card Number : " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }

        } catch (Exception e) {
            System.out.println("Error in login query: " + e);
        }

        // Merr transaksionet dhe llogarit balancën
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");

            StringBuilder statementText = new StringBuilder();
            statementText.append("<html>");

            double balance = 0;

            while (rs.next()) {
                String type = rs.getString("type");
                double amount = Double.parseDouble(rs.getString("amount"));

                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amount;
                } else if (type.equalsIgnoreCase("Withdrawal")) {
                    balance -= amount;
                }

                statementText.append(rs.getString("date"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(type)
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(amount)
                        .append("<br><br>");
            }

            statementText.append("</html>");
            mini.setText(statementText.toString());

            balanceLabel.setText(String.format("Current Balance: %.2f Lekë", balance));

        } catch (Exception e) {
            System.out.println("Error in bank query: " + e);
        }
    }

    public static void main(String args[]) {
        new MiniStatement("1234"); // ose vendos pin-in real që ekziston në databazën tënde
    }
}

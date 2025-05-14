 package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton deposit, withdraw, fastcash, ministatement, pinchange, balanceenquiry, exit;
    String pinnumber;
    JLabel balanceLabel;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 700, 750);
        add(image);

        JLabel text = new JLabel("Select Withdrawal Amount");
        text.setBounds(165, 235, 700, 35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 14));
        image.add(text);

        balanceLabel = new JLabel("Balance: Loading...");
        balanceLabel.setBounds(165, 300, 400, 25);
        balanceLabel.setForeground(Color.YELLOW);
        balanceLabel.setFont(new Font("System", Font.BOLD, 14));
        image.add(balanceLabel);

        deposit = new JButton("ALL 2000");
        deposit.setBounds(130, 352, 115, 20);
        deposit.addActionListener(this);
        image.add(deposit);

        withdraw = new JButton("ALL 5000");
        withdraw.setBounds(264, 352, 135, 20);
        withdraw.addActionListener(this);
        image.add(withdraw);

        fastcash = new JButton("ALL 20000");
        fastcash.setBounds(130, 378, 115, 20);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("ALL 50000");
        ministatement.setBounds(264, 378, 135, 20);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("ALL 60000");
        pinchange.setBounds(130, 404, 115, 20);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("ALL 75000");
        balanceenquiry.setBounds(264, 404, 135, 20);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("Back");
        exit.setBounds(264, 430, 135, 20);
        exit.addActionListener(this);
        image.add(exit);

        fetchAndDisplayBalance();

        setSize(700, 750);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    private void fetchAndDisplayBalance() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            double balance = 0;
            while (rs.next()) {
                String type = rs.getString("type");
                double amt = Double.parseDouble(rs.getString("amount"));
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }
            balanceLabel.setText("Balance: ALL " + balance);
        } catch (Exception e) {
            balanceLabel.setText("Error loading balance");
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            String amountStr = ((JButton) ae.getSource()).getText().substring(4);
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Amount must be greater than 0");
                    return;
                }

                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
                double balance = 0;
                while (rs.next()) {
                    String type = rs.getString("type");
                    double amt = Double.parseDouble(rs.getString("amount"));
                    if (type.equalsIgnoreCase("Deposit")) {
                        balance += amt;
                    } else {
                        balance -= amt;
                    }
                }

                if (balance < amount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance! Your current balance is: ALL " + balance);
                    return;
                }

                Date date = new Date();
                String query = "INSERT INTO bank (pin, date, type, amount) VALUES('" + pinnumber + "','" + date + "', 'Withdrawal','" + amount + "')";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "ALL " + amount + " withdrawn successfully");
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid amount format.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new FastCash("");
    }
}

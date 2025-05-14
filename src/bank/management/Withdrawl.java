package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Withdrawl extends JFrame implements ActionListener {
    JTextField amount;
    JButton withdraw, back;
    String pinnumber;

    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 700, 700);
        add(image);

        JLabel text = new JLabel("Enter The Amount You Want To Withdraw");
        text.setBounds(150, 230, 700, 35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 12));
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("System", Font.BOLD, 12));
        amount.setBounds(185, 280, 150, 20);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(264, 380, 135, 20);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(264, 405, 135, 20);
        back.addActionListener(this);
        image.add(back);

        setSize(700, 700);
        setLocation(300, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw) {
            String number = amount.getText();
            Date date = new Date();

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
            } else {
                try {
                    double amountValue = Double.parseDouble(number);

                    if (amountValue <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid amount greater than 0");
                        return;
                    }

                    // Kontrollo balancën aktuale
                    Conn conn = new Conn();
                    ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
                    double balance = 0;
                    while (rs.next()) {
                        String type = rs.getString("type");
                        double amt = Double.parseDouble(rs.getString("amount"));
                        if (type.equalsIgnoreCase("Deposit")) {
                            balance += amt;
                        } else if (type.equalsIgnoreCase("Withdrawl")) {
                            balance -= amt;
                        }
                    }

                    if (amountValue > balance) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance! Your current balance is: " + balance);
                        return;
                    }

                    String query = "INSERT INTO bank VALUES ('" + pinnumber + "','" + date + "','Withdrawl','" + amountValue + "')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "ALL " + amountValue + " Withdrawn Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount");
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Withdrawl("");
    }
}

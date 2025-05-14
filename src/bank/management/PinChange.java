package bank.management;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin, repin;
    JButton exit, change;
    JLabel repintext, pintext;
    String pinnumber;

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 700, 750);
        add(image);

        JLabel text = new JLabel("Change Your Pin");
        text.setBounds(185, 235, 700, 35);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 14));
        image.add(text);

        pintext = new JLabel("New Pin:");
        pintext.setBounds(135, 270, 250, 35);
        pintext.setForeground(Color.white);
        pintext.setFont(new Font("System", Font.BOLD, 14));
        image.add(pintext);

        pin = new JPasswordField();
        pin.setBounds(260, 277, 100, 20);
        image.add(pin);

        repintext = new JLabel("Confirm New Pin:");
        repintext.setBounds(135, 310, 250, 35);
        repintext.setForeground(Color.white);
        repintext.setFont(new Font("System", Font.BOLD, 12));
        image.add(repintext);

        repin = new JPasswordField();
        repin.setBounds(260, 315, 100, 20);
        image.add(repin);

        change = new JButton("Change");
        change.setBounds(264, 380, 135, 20);
        change.addActionListener(this);
        image.add(change);

        exit = new JButton("Back");
        exit.setBounds(264, 405, 135, 20);
        exit.addActionListener(this);
        image.add(exit);

        setSize(700, 750);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }

                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }

                Conn conn = new Conn();
                String query1 = "UPDATE bank SET pin = '" + rpin + "' WHERE pin = '" + pinnumber + "'";
                String query2 = "UPDATE login SET pin = '" + rpin + "' WHERE pin = '" + pinnumber + "'";
                String query3 = "UPDATE signupthree SET pin = '" + rpin + "' WHERE pin = '" + pinnumber + "'";

                int updated1 = conn.s.executeUpdate(query1);
                int updated2 = conn.s.executeUpdate(query2);
                int updated3 = conn.s.executeUpdate(query3);

                if (updated1 > 0 || updated2 > 0 || updated3 > 0) {
                    JOptionPane.showMessageDialog(null, "PIN changed successfully");
                    setVisible(false);
                    new Transactions(rpin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No records updated. PIN might be incorrect.");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else if (ae.getSource() == exit) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("pinnumber").setVisible(true);
    }
}

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Create Bank Object
        Bank bank = new Bank();
        bank.loadFromFile();

        // ================= FRAME =================
        JFrame frame = new JFrame("Bank Management System");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // ================= TITLE =================
        JPanel titlePanel = new JPanel();

        JLabel titleLabel = new JLabel("BANK MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // ================= FORM =================
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel accountLabel = new JLabel("Account Number:");
        JTextField accountField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        formPanel.add(accountLabel);
        formPanel.add(accountField);

        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JPanel centerPanel = new JPanel();
        centerPanel.add(formPanel);

        frame.add(centerPanel, BorderLayout.CENTER);

        // ================= BUTTON PANEL =================
        JPanel buttonPanel = new JPanel();

        JButton loginButton = new JButton("Login");
        JButton createButton = new JButton("Create Account");

        buttonPanel.add(loginButton);
        buttonPanel.add(createButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // ================= LOGIN =================
        loginButton.addActionListener(e -> {

            try {

                int accNumber = Integer.parseInt(accountField.getText());

                String password =
                        new String(passwordField.getPassword());

                Account currentUser =
                        bank.login(accNumber, password);

                if (currentUser != null) {

                    frame.dispose();
                    new Dashboard(currentUser, bank);

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Invalid Account Number or Password");

                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please enter a valid Account Number.");

            }

        });

        // ================= CREATE ACCOUNT =================
        createButton.addActionListener(e -> {

            new CreateAccount(bank);

        });

        // ================= SHOW FRAME =================
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
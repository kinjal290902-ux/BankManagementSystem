import java.awt.*;
import javax.swing.*;

public class Dashboard {

    private JFrame frame;
    private Account currentUser;
    private Bank bank;

    public Dashboard(Account currentUser, Bank bank) {

        this.currentUser = currentUser;
        this.bank = bank;

        frame = new JFrame("Customer Dashboard");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // ================= TITLE =================
        JLabel title = new JLabel(
                "Welcome, " + currentUser.getAccHolderName(),
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 22));

        frame.add(title, BorderLayout.NORTH);

        // ================= BUTTON PANEL =================
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton balanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer Money");
        JButton historyButton = new JButton("Transaction History");
        JButton logoutButton = new JButton("Logout");

        panel.add(balanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(transferButton);
        panel.add(historyButton);
        panel.add(logoutButton);

        frame.add(panel, BorderLayout.CENTER);

        // ================= CHECK BALANCE =================
        balanceButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    frame,
                    "Current Balance : $" + currentUser.getBalance());

        });

        // ================= DEPOSIT =================
        depositButton.addActionListener(e -> {

            try {

                String input = JOptionPane.showInputDialog(
                        frame,
                        "Enter Deposit Amount");

                if (input == null)
                    return;

                double amount = Double.parseDouble(input);

                currentUser.deposit(amount);

                bank.saveTofile();

                JOptionPane.showMessageDialog(
                        frame,
                        "Deposit Successful");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Amount");

            }

        });

        // ================= WITHDRAW =================
        withdrawButton.addActionListener(e -> {

            try {

                String input = JOptionPane.showInputDialog(
                        frame,
                        "Enter Withdraw Amount");

                if (input == null)
                    return;

                double amount = Double.parseDouble(input);

                if (currentUser.withdraw(amount)) {

                    bank.saveTofile();

                    JOptionPane.showMessageDialog(
                            frame,
                            "Withdraw Successful");

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Insufficient Balance");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Amount");

            }

        });

        // ================= TRANSFER =================
        transferButton.addActionListener(e -> {

            try {

                String receiver =
                        JOptionPane.showInputDialog(
                                frame,
                                "Receiver Account Number:");

                if (receiver == null)
                    return;

                String amountInput =
                        JOptionPane.showInputDialog(
                                frame,
                                "Amount:");

                if (amountInput == null)
                    return;

                int receiverAcc =
                        Integer.parseInt(receiver);

                double amount =
                        Double.parseDouble(amountInput);

                boolean success =
                        bank.transfer(
                                currentUser.getAccNumber(),
                                receiverAcc,
                                amount);

                if (success) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Transfer Successful");

                } else {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Transfer Failed");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Input");

            }

        });

        // ================= TRANSACTION HISTORY =================
        historyButton.addActionListener(e -> {

            StringBuilder history = new StringBuilder();

            if (currentUser.getTransactions().isEmpty()) {

                history.append("No Transactions");

            } else {

                for (String t : currentUser.getTransactions()) {

                    history.append(t).append("\n");

                }

            }

            JTextArea area = new JTextArea(history.toString());
            area.setEditable(false);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(400, 250));

            JOptionPane.showMessageDialog(
                    frame,
                    scroll,
                    "Transaction History",
                    JOptionPane.INFORMATION_MESSAGE);

        });

        // ================= LOGOUT =================
        logoutButton.addActionListener(e -> {

            frame.dispose();

            SwingUtilities.invokeLater(() -> Main.main(new String[0]));

        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
import java.awt.*;
import javax.swing.*;

public class CreateAccount {

    public CreateAccount(Bank bank) {

        JFrame frame = new JFrame("Create Account");
        frame.setSize(450,350);
        frame.setLayout(new BorderLayout(10,10));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("CREATE NEW ACCOUNT",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,22));

        frame.add(title,BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(4,2,10,10));

        JLabel nameLabel = new JLabel("Account Holder Name:");
        JTextField nameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JLabel balanceLabel = new JLabel("Initial Balance:");
        JTextField balanceField = new JTextField();

        JLabel accountLabel = new JLabel("Account Number:");
        JTextField accountField = new JTextField();

        form.add(accountLabel);
        form.add(accountField);

        form.add(nameLabel);
        form.add(nameField);

        form.add(passwordLabel);
        form.add(passwordField);

        form.add(balanceLabel);
        form.add(balanceField);

        frame.add(form,BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton createButton = new JButton("Create Account");

        buttonPanel.add(createButton);

        frame.add(buttonPanel,BorderLayout.SOUTH);

        createButton.addActionListener(e->{

            try{

                int accountNumber =
                        Integer.parseInt(accountField.getText());

                if(bank.findAccount(accountNumber)!=null){

                    JOptionPane.showMessageDialog(
                            frame,
                            "Account Number Already Exists");

                    return;
                }

                String name = nameField.getText();

                String password =
                        new String(passwordField.getPassword());

                double balance =
                        Double.parseDouble(balanceField.getText());

                Account account =
                        new Account(
                                accountNumber,
                                name,
                                password,
                                balance);

                bank.addAccount(account);

                bank.saveTofile();

                JOptionPane.showMessageDialog(
                        frame,
                        "Account Created Successfully");

                frame.dispose();

            }
            catch(Exception ex){

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Input");

            }

        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
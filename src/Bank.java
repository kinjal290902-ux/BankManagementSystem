import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {

    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    // ================= ADD ACCOUNT =================

    public void addAccount(Account account) {
        accounts.add(account);
    }

    // ================= FIND ACCOUNT =================

    public Account findAccount(int accNumber) {

        for (Account acc : accounts) {

            if (acc.getAccNumber() == accNumber) {
                return acc;
            }

        }

        return null;
    }

    // ================= LOGIN =================

    public Account login(int accNumber, String password) {

        Account acc = findAccount(accNumber);

        if (acc != null && acc.getPassword().equals(password)) {
            return acc;
        }

        return null;
    }

    // ================= TRANSFER =================

    public boolean transfer(int senderAccNo, int receiverAccNo, double amount) {

        Account sender = findAccount(senderAccNo);
        Account receiver = findAccount(receiverAccNo);

        if (sender == null || receiver == null) {
            return false;
        }

        if (senderAccNo == receiverAccNo) {
            return false;
        }

        if (sender.transferAmount(amount)) {

            receiver.deposit(amount);

            sender.addTransaction(
                    "Transferred $" + amount +
                    " to Account " + receiverAccNo);

            receiver.addTransaction(
                    "Received $" + amount +
                    " from Account " + senderAccNo);

            saveTofile();

            return true;
        }

        return false;
    }

    // ================= SAVE =================

    public void saveTofile() {

        try {

            FileWriter writer = new FileWriter("accounts.txt");

            for (Account acc : accounts) {

                writer.write(
                        acc.getAccNumber() + "," +
                        acc.getAccHolderName() + "," +
                        acc.getPassword() + "," +
                        acc.getBalance() + "\n");

                for (String t : acc.getTransactions()) {

                    writer.write("TRANSACTION," + t + "\n");

                }

            }

            writer.close();

        }
        catch (IOException e) {

            System.out.println("Error Saving File");

        }

    }

    // ================= LOAD =================

    public void loadFromFile() {

        try {

            File file = new File("accounts.txt");

            if (!file.exists()) {
                return;
            }

            Scanner sc = new Scanner(file);

            Account current = null;

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                if (line.startsWith("TRANSACTION")) {

                    if (current != null) {

                        current.loadTransaction(line.substring(12));

                    }

                    continue;
                }

                String[] data = line.split(",");

                int number = Integer.parseInt(data[0]);
                String name = data[1];
                String password = data[2];
                double balance = Double.parseDouble(data[3]);

                Account acc =
                        new Account(number, name, password, balance);

                accounts.add(acc);

                current = acc;

            }

            sc.close();

        }
        catch (Exception e) {

            System.out.println("Error Loading File");

        }

    }

}
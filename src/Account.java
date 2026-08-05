import java.time.LocalDateTime;
import java.util.ArrayList;

public class Account
{
    private int AccNumber;
    private String AccHolderName;
    private String password;
    private double balance;
    private ArrayList<String> transactions;


    public Account(int AccNumber,String AccHolderName,String password,double balance)
    {
        this.AccNumber = AccNumber;
        this.AccHolderName = AccHolderName;
        this.balance = balance;
        this.password = password;
        transactions = new ArrayList<>();
    }

    public int getAccNumber()
    {
        return AccNumber;
    }

    public String getAccHolderName()
    {
        return AccHolderName;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double amount)
    {
        if(amount > 0)
        {
             balance = balance + amount;
             transactions.add(LocalDateTime.now() + " - Deposited: $" + amount);
        }
        else{
            System.out.println("invalid balance");
        }
       
    }

    public boolean withdraw(double amount)
    {
        if(amount <= 0)
        {
            System.out.println("Invalid amount");
            return false;
        }

        if(amount > balance)
        {
            System.out.println("Insufficient balance");
            return false;
        }

        balance = balance - amount;
        transactions.add(LocalDateTime.now() + " - Withdrawn: $" + amount);
        return true;
    }

    public String getPassword()
    {
        return password;
    }
    public ArrayList<String> getTransactions()
    {
        return transactions;
    }
    public void loadTransaction(String transaction)
    {
        transactions.add(transaction);
    }

    public void showTransactions()
    {
        if(transactions.isEmpty())
        {
            System.out.println("No transactions found.");
        }
        else
        {
            System.out.println("===== TRANSACTION HISTORY =====");

            for(String transaction : transactions)
            {
                System.out.println(transaction);
            }
        }
    }

    public void addTransaction(String message)
    {
        transactions.add(LocalDateTime.now() + " - " + message);
    }
      
    public boolean transferAmount(double amount)
    {
        if(amount <= 0)
        {
            return false;
        }

        if(amount > balance)
        {
            return false;
        }

        balance = balance - amount;
        return true;
    }
}

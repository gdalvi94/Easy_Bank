package com.example.banking.models;
import java.sql.*;
import java.time.LocalDate;
//Database driver for all database functions and table
public class DatabaseDriver {
    private Connection conn;
    public DatabaseDriver(){
        try{
            this.conn = DriverManager.getConnection("jdbc:sqlite:easy_bank.db");
            System.out.println("Connected to " + this.conn);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    // Client Login
    public ResultSet getClientDetails(String username,String pass){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM CLIENT WHERE Username ='"+username+"' and Password ='"+pass+"';");
            System.out.println(resultSet);


        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;


    }

    public ResultSet getTransactions(String username, double limit) {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            System.out.println("Transactions Username: " + username);
//            resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE Sender = '"+username+"' OR Receiver = '"+username+"' LIMIT="+limit+";");
            resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE Sender='"+username+"' OR Receiver='"+username+"' LIMIT "+limit+";");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    // Admin Login
    public ResultSet getAdminDetails(String username,String pass){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM ADMIN WHERE Username ='"+username+"' and Password ='"+pass+"';");
            System.out.println(resultSet);


        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
    // Add client details
    public void addClient(String fname, String lname, String username, String password, LocalDate date){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " + "Client (FirstName,LastName,Username,Password,Date)" +
                    "Values ('"+fname+"' ,'"+lname+"','"+username+"','"+password+"','"+date.toString()+"');");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add checking account
    public void addCheckingAcc(String username, String accountNo, double transactionLimit, double amount){
        Statement statement;
        try{
            System.out.println("Username inside function" + username);
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " + "CheckingAcc (Username,AccountNo,TransactionLimit,Amount)" +
                    "Values ('"+username+"' ,'"+accountNo+"','"+transactionLimit+"','"+amount+"');");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add saving account

    public void addSavingAcc(String username, String accountNo, double withdrawalLimit, double amount){
        Statement statement;
        try{
            statement = this.conn.createStatement();
            statement.executeUpdate("INSERT INTO " + "SavingAcc (Username,AccountNo,WithdrawalLimit,Amount)" +
                    "Values ('"+username+"' ,'"+accountNo+"','"+withdrawalLimit+"','"+amount+"');");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getLastClientsId() {
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sqlite_sequence WHERE name='Client';");
            id = resultSet.getInt("seq");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    //Method to execute sql query to display all data of clients
    public ResultSet showAllClientsData() {
        Statement statement;
        ResultSet resultSet = null;

        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Client");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Checking unique username
//    public boolean checkUsername(String username){
//        Statement statement;
//        ResultSet resultSet = null;
//        try{
//            statement = this.conn.createStatement();
//            resultSet = statement.executeQuery("select count(*) from client where username '"+username+"';");
//            System.out.println("Output" + username + resultSet);
//            if(resultSet.equals(1)){
//                return false;
//            }
//            else{
//                return true;
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }


    /*
       UTILITY METHODS for admin to view checking and savings details of clients
    * */
    public ResultSet showCheckingAccountData(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;

        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAcc WHERE Username = '"+pAddress+"'");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet showSavingsAccountData(String Username) {
        Statement statement;
        ResultSet resultSet = null;

        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingAcc WHERE Username = '"+Username+"'");
            System.out.println("StackTrace: " + resultSet);
            System.out.println("Username: " + Username);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Search Section

    public ResultSet searchUser(String username) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Client WHERE username='"+username+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    // Deposit Section
    public void depositSavingBal(String username, double amount) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate("UPDATE SavingAcc SET Amount = "+amount+" WHERE Username = '"+username+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Method returns Savings account Balance
    public double getSavingsBalance(String username) {
        Statement statement;
        ResultSet resultSet = null;
        double amount = 0;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingAcc WHERE Username = '"+username+"'");
            amount = resultSet.getDouble("Amount");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }
    public double getCheckingBalance(String username) {
        Statement statement;
        ResultSet resultSet = null;
        double amount = 0;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAcc WHERE Username = '"+username+"'");
            amount = resultSet.getDouble("Amount");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }



    // Method to Update User Account Balance Saving
    public void updateBalance(String username, double amount, String operation) {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SavingAcc WHERE Username = '"+username+"'");
            double newBalance;
            if(operation.equals("ADD")){
                newBalance = resultSet.getDouble("Amount") + amount;
                statement.executeUpdate("UPDATE SavingAcc SET Amount = '"+newBalance+"' WHERE Username = '"+username+"';");
            }else {
                if (resultSet.getDouble("Amount") >= amount) {
                    newBalance = resultSet.getDouble("Amount") - amount;
                    statement.executeUpdate("UPDATE SavingAcc SET Amount = '"+newBalance+"' WHERE Username = '"+username+"';");
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
  // Update Balance Checking
    public void updateBalanceChecking(String username, double amount, String operation) {
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CheckingAcc WHERE Username = '"+username+"'");
            double newBalance;
            if(operation.equals("ADD")){
                newBalance = resultSet.getDouble("Amount") + amount;
                statement.executeUpdate("UPDATE CheckingAcc SET Amount = '"+newBalance+"' WHERE Username = '"+username+"';");
            }else {
                if (resultSet.getDouble("Amount") >= amount) {
                    newBalance = resultSet.getDouble("Amount") - amount;
                    statement.executeUpdate("UPDATE CheckingAcc SET Amount = '"+newBalance+"' WHERE Username = '"+username+"';");
                }

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Creates and Records new Transactions
    public void newTransaction(String sender, String receiver, double amount, String message) {
        Statement statement;
//        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            LocalDate date = LocalDate.now();
            statement.executeUpdate("INSERT INTO " +
                    "Transactions(Sender, Receiver, Amount, Date, Message) " +
                    "VALUES ('"+sender+"', '"+receiver+"', "+amount+", '"+date+"', '"+message+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
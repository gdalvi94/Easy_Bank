package com.example.banking.models;

import com.example.banking.view.View;
import com.example.banking.view.AccountTypes;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.time.LocalDate;

//Model class
public class Model {
    private static Model model;
    private final View view;
    private final DatabaseDriver databaseDriver;

    private Client clientData;

    private AccountTypes loginUser = AccountTypes.CLIENT;

    private boolean adminFlagSet;

    private final ObservableList<Client> clients;
    private boolean clientFlagSet;

    //Transactions Section
    private final ObservableList<Transaction> latestTransactions;
    private final ObservableList<Transaction> allTransactions;

    private Model() {
        this.view = new View();
        this.databaseDriver = new DatabaseDriver();
        this.adminFlagSet = false;
        this.clientFlagSet = false;
        this.clientData = new Client("", "", "", null, null, null);
        this.clients = FXCollections.observableArrayList();

        //Transactions Section Initialisation in Model
        this.latestTransactions = FXCollections.observableArrayList();
        this.allTransactions = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public View getView() {
        return view;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public AccountTypes getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(AccountTypes loginUser) {
        this.loginUser = loginUser;
    }

    // Client Login
    public Client getClientData() {
        return clientData;
    }

    public void setClientData(Client clientData) {
        this.clientData = clientData;
    }

    public boolean getClientFlagSet() {
        return clientFlagSet;
    }

    public void setClientFlagSet(boolean clientFlagSet) {
        this.clientFlagSet = clientFlagSet;
    }


    //validation for all fields
    public void validation(String username, String pass) {
        CheckingAcc checkingAcc;
        SavingAcc savingAcc;
        ResultSet resultSet = databaseDriver.getClientDetails(username, pass);
        try {
            if (resultSet.isBeforeFirst()) {
                this.clientData.fnameProperty().set(resultSet.getString("FirstName"));
                this.clientData.lnameProperty().set(resultSet.getString("LastName"));
                this.clientData.usernameProperty().set(resultSet.getString("Username"));
                String[] date1 = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]));
                this.clientData.dateProperty().set(date);
                //for checking and savings account we have pAddress as username
                checkingAcc = getCheckingAccount(username);
                savingAcc = getSavingAccount(username);
                this.clientData.checkingAccProperty().set(checkingAcc);
                this.clientData.savingAccProperty().set(savingAcc);
                this.clientFlagSet = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Transactions Section for private and public Methods

    private void prepareTransactions(ObservableList<Transaction> transactions, double limit) {
        ResultSet resultSet = databaseDriver.getTransactions(this.clientData.usernameProperty().get(), limit);
        try {
            while (resultSet.next()) {
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                String[] date1 = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]));
                String message = resultSet.getString("Message");
                transactions.add(new Transaction(sender, receiver, amount, date, message));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLatestTransactions() {
        prepareTransactions(this.latestTransactions, 4);
    }

    public ObservableList<Transaction> getLatestTransactions() {
        return latestTransactions;
    }

    public void setAllTransactions() {
        prepareTransactions(this.allTransactions, -1); // -1 will return all transactions
    }

    public ObservableList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    // Admin Login

    public boolean getAdminFlagSet() {
        return this.adminFlagSet;
    }

    public void setAdminFlagSet(boolean adminFlagSet) {
        this.adminFlagSet = adminFlagSet;
    }

    public void validationAdmin(String username, String pass) {
        CheckingAcc checkingAcc;
        SavingAcc savingAcc;
        ResultSet resultSet = databaseDriver.getAdminDetails(username, pass);
        try {
            if (resultSet.isBeforeFirst()) {
                this.adminFlagSet = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void setClients() {
        CheckingAcc checkingAcc;
        SavingAcc savingAcc;
        ResultSet resultSet = databaseDriver.showAllClientsData();
        try {
            while (resultSet.next()) {
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String Username = resultSet.getString("Username"); // payee address is username
                String[] dateParts = (resultSet.getString("Date").split("-"));
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                checkingAcc = getCheckingAccount(Username);
                System.out.println("Checking" + checkingAcc);
                savingAcc = getSavingAccount(Username);
                System.out.println("Saving" + savingAcc);
                clients.add(new Client(fName, lName, Username, date, checkingAcc, savingAcc));
                System.out.println(clients);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Utility Methods Section
     *  For Admin and/or Client views
     * */

    public CheckingAcc getCheckingAccount(String Username) {
        CheckingAcc acc = null; //temporarily set to null
        ResultSet resultSet = databaseDriver.showCheckingAccountData(Username);
        try {
            String num = resultSet.getString("AccountNo");
            double transactionLimit = resultSet.getDouble("TransactionLimit");
            double balance = resultSet.getDouble("Amount");
            acc = new CheckingAcc(Username, num, transactionLimit, balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    public SavingAcc getSavingAccount(String Username) {
        SavingAcc acc = null; //temporarily set to null
        ResultSet resultSet = databaseDriver.showSavingsAccountData(Username);
        try {
            String num = resultSet.getString("AccountNo");
            double withdrawLimit = resultSet.getDouble("WithdrawalLimit");
            double balance = resultSet.getDouble("Amount");
            acc = new SavingAcc(Username, num, withdrawLimit, balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    public ObservableList<Client> searchUser(String username) {
        ObservableList<Client> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchUser(username);
        try {
            CheckingAcc checkingAccount = getCheckingAccount(username);
            SavingAcc savingsAccount = getSavingAccount(username);
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String[] dateParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
            searchResults.add(new Client(fName, lName, username, date, checkingAccount, savingsAccount));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResults;
    }
}


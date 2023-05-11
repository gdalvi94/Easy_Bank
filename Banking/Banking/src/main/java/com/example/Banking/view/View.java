package com.example.Banking.view;

import com.example.Banking.controllers.admin.AdminController;
import com.example.Banking.controllers.client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//view class to show all the anchorpane and windows of admin and client panel
public class View {
    private final ObjectProperty<ClientOptions> selectedItem;
    private final ObjectProperty<AdminOptions> selectedAdminItem;
    private AnchorPane dashboard;
    private AnchorPane transaction;
    private AnchorPane clientAdd;
    private AnchorPane accounts;
    private AnchorPane clientList;

    private AccountTypes accountTypes;
    private AnchorPane paymoney;

    private AnchorPane depositView;
    public View(){
        this.accountTypes=AccountTypes.CLIENT;
        this.selectedItem=new SimpleObjectProperty<>();
        this.selectedAdminItem=new SimpleObjectProperty<>();
    }

    public AccountTypes getAccountTypes(){
        return  accountTypes;
    }

    public ObjectProperty<ClientOptions> getSelectedItem(){

        return selectedItem;
    }

    public ObjectProperty<AdminOptions> getSelectedAdminItem(){
        return selectedAdminItem;
    }
    public void setAccountTypes(AccountTypes accountTypes){
        this.accountTypes=accountTypes;
    }
    public AnchorPane showViewDashboard(){
        if(dashboard==null){
            try{
                dashboard=new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return dashboard;
    }

    public AnchorPane showPayMoneyView(){
        if(paymoney==null){
            try{
                paymoney=new FXMLLoader(getClass().getResource("/Fxml/Client/PayMoney.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return paymoney;
    }



    public AnchorPane showClientList(){
        if(clientList==null){
            try{
                clientList=new FXMLLoader(getClass().getResource("/Fxml/Admin/ClientList.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return clientList;
    }

    public AnchorPane getDepositView(){
        if (depositView==null){
            try{
                depositView=new FXMLLoader(getClass().getResource("/Fxml/Admin/Deposit.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return depositView;
    }

    public AnchorPane showViewTransaction(){
        if(transaction==null){
            try{
                transaction=new FXMLLoader(getClass().getResource("/Fxml/Client/Transaction.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return transaction;
    }


    public AnchorPane showViewAccount(){
        if(accounts==null){
            try{
                accounts=new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return accounts;
    }



    public void showClientView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController=new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }


        public AnchorPane showClientAdd(){
            if(clientAdd==null){
                try{
                    clientAdd=new FXMLLoader(getClass().getResource("/Fxml/Admin/ClientAdd.fxml")).load();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return clientAdd;

        }
    public void showAdminView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController=new AdminController();
        loader.setController(adminController);
        createStage(loader);

    }

    public void showLoginView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);

    }

    public void createStage(FXMLLoader loader){
        Scene scene=null;
        try{
            scene=new Scene(loader.load());

        }catch(Exception e){
            e.printStackTrace();

        }
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Easy Banking");
        stage.show();
    }

    public void closeView(Stage stage){
        stage.close();
    }
}

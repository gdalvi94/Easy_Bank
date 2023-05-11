package com.example.Banking.view;

import com.example.Banking.controllers.admin.ClientCellController;
import com.example.Banking.models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
// client cell in client list in admin panel
public class ClientCellFactory extends ListCell<Client> {
    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ClientCell.fxml"));
            ClientCellController controller = new ClientCellController(client);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
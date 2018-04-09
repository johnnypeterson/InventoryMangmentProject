package view;



import inventory.Main;
import inventory.Model.Inventory;
import inventory.Model.Parts;
import inventory.Model.Product;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public class MainScreenController {



    @FXML
    private TextField searchPartTextFeild;
    @FXML
    private TextField searchProductTextFeild;

    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productIdcolumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private TableView<Parts> partsTableView;
    @FXML
    private TableColumn<Parts, Integer> partIdColum;
    @FXML
    private TableColumn<Parts, String> partNameColumn;
    @FXML
    private TableColumn<Parts, Integer> partInventoryColumn;
    @FXML
    private TableColumn<Parts, Double> partPriceColumn;
    @FXML
    Button exitButton;
    @FXML
    Button partModifyButton;
    @FXML
    Button partAddButton;
    @FXML
    Button partDeleteButton;
    @FXML
    Button searchPartButton;
    @FXML
    Button searchProductButton;
    @FXML
    Button productModifyButton;
    @FXML
    Button productAddButton;
    @FXML
    Button productDeleteButton;

    public MainScreenController() {}

    private Main app;
    private Inventory inventory;


    @FXML
    private void initialize() {

        //Part Table
        partIdColum.setCellValueFactory(cell -> cell.getValue().partIDProperty().asObject());
        partNameColumn.setCellValueFactory(cell -> cell.getValue().partNameProperty());
        partInventoryColumn.setCellValueFactory(cell -> cell.getValue().partsInStockProperty().asObject());
        partPriceColumn.setCellValueFactory(cell -> cell.getValue().partPriceProperty().asObject());

        productIdcolumn.setCellValueFactory(cell -> cell.getValue().productIDProperty().asObject());
        productNameColumn.setCellValueFactory(cell -> cell.getValue().productNameProperty());
        productInventoryColumn.setCellValueFactory(cell -> cell.getValue().productInStockProperty().asObject());
        productPriceColumn.setCellValueFactory(cell -> cell.getValue().productPriceProperty().asObject());




    }


    public void setMainScreen(Main mainApp) {
        app = mainApp;
        inventory = mainApp.getInventory();
//        partsTableView.setItems(inventory.getAllParts());
//        productTableView.setItems(inventory.getAllProducts());
    }

    @FXML
    private void addPart() {
        app.showPartScreen(null);

    }



    @FXML
    private void modifyPart() {
        Parts existingPart = partsTableView.getSelectionModel().getSelectedItem();
        app.showPartScreen(existingPart);

    }

    private void buttonState(TableView<?> table, boolean state) {
        if (table.equals(partsTableView)) {
            partModifyButton.setDisable(state);
            partDeleteButton.setDisable(state);
        } else if (table.equals(productTableView)) {
            productModifyButton.setDisable(state);
            productDeleteButton.setDisable(state);
        }
    }

    @FXML
    private void handleExit() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.setContentText("Data will not be saved.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            app.primaryStage.close();
        }

    }














}

package view;


import inventory.Main;
import inventory.Model.Inventory;
import inventory.Model.Parts;
import inventory.Model.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

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

    public MainScreenController() {
    }

    private Main app;
    private Inventory inventory;


    @FXML
    private void initialize() {

        //Part Table
        partIdColum.setCellValueFactory(cell -> cell.getValue().partIDProperty().asObject());
        partNameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        partInventoryColumn.setCellValueFactory(cell -> cell.getValue().partsInStockProperty().asObject());
        partPriceColumn.setCellValueFactory(cell -> cell.getValue().partPriceProperty().asObject());
        //Product Table
        productIdcolumn.setCellValueFactory(cell -> cell.getValue().productIDProperty().asObject());
        productNameColumn.setCellValueFactory(cell -> cell.getValue().productNameProperty());
        productInventoryColumn.setCellValueFactory(cell -> cell.getValue().productInStockProperty().asObject());
        productPriceColumn.setCellValueFactory(cell -> cell.getValue().productPriceProperty().asObject());

        buttonState(partsTableView, true);
        buttonState(productTableView, true);

        partsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> buttonState(partsTableView, newValue == null));
        productTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> buttonState(productTableView, newValue == null));

        //Listeners for search text field
        searchPartTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                partsTableView.setItems(inventory.getPartList());
            }
        });
        searchProductTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                productTableView.setItems(inventory.getProdcutList());
            }
        });


    }


    public void setMainScreen(Main mainApp) {
        app = mainApp;
        inventory = mainApp.getInventory();

        partsTableView.setItems(inventory.getAllParts());
        productTableView.setItems(inventory.getProdcutList());

    }


    @FXML
    private void addPart() {
        app.showPartScreen(null);

    }

    @FXML
    private void addProductButton() {
        app.showProductScreen(null);
    }

    @FXML
    private void modifyProductButton() {
        Product productExists = productTableView.getSelectionModel().getSelectedItem();
        app.showProductScreen(productExists);


    }

    @FXML
    private void modifyPartButton() {
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
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            app.primaryStage.close();
        }


    }


    @FXML
    private void deletePart() {
        int partIndex = partsTableView.getSelectionModel().getSelectedIndex();
        Parts selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        if (partIndex >= 0) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Part");
            alert.setHeaderText("Do you want to delete this part?");
            alert.setContentText("This Action can not be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                inventory.deletePart(selectedPart);
            }
        }
    }

    @FXML
    private void deleteProduct() {
        int productIndex = productTableView.getSelectionModel().getSelectedIndex();
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        boolean hasNoParts = selectedProduct.getParts().isEmpty();
        if (productIndex >= 0) {

            if (hasNoParts) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Delete Product");
                alert.setHeaderText("Do you want to delete this product?");
                alert.setContentText("This Action can not be undone.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    inventory.getProdcutList().remove(selectedProduct);
                }
            } else {
                Alert alert2 = new Alert(AlertType.WARNING);
                alert2.initOwner(app.getPrimaryStage());
                alert2.setTitle("Part Associated");
                alert2.setHeaderText("This product has a part associated with it");
                alert2.setContentText("delete associated parts before deleting product");

                alert2.showAndWait();

            }

        }


    }

    @FXML
    private void searchProduct() {
        String searchTerm = searchProductTextFeild.getText();
        if (searchTerm == null || searchTerm.isEmpty()) {
            productTableView.setItems(inventory.getProdcutList());
        }
        ObservableList<Product> productObservableList = inventory.searchforProduct(searchTerm);
        productTableView.setItems(productObservableList);
    }

    @FXML
    private void searchPart() {
        String searchTerm = searchPartTextFeild.getText();
        if (searchTerm == null || searchTerm.isEmpty()) {
            partsTableView.setItems((inventory.getPartList()));
        }
        ObservableList<Parts> partsFound = inventory.searchforPart(searchTerm);
        partsTableView.setItems(partsFound);

    }


}

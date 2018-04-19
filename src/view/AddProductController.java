package view;


import inventory.Model.Inventory;
import inventory.Model.Parts;
import inventory.Model.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


/**
 * Created by johnnypeterson on Mar, 2018
 */
public class AddProductController {

    @FXML
    private Label productLabel;

    @FXML
    private TextField productSearchField;

    @FXML
    private Label productId;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productInvField;

    @FXML
    private TextField productPriceField;

    @FXML
    private TextField productMinField;

    @FXML
    private TextField productMaxField;

    @FXML
    private TableView<Parts> partSearchTable;

    @FXML
    private TableView<Parts> partTable;

    @FXML
    private TableColumn<Parts, Integer> partDelete;

    @FXML
    private TableColumn<Parts, String> partNameSearch;

    @FXML
    private TableColumn<Parts, Integer> invSearch;

    @FXML
    private TableColumn<Parts, Double> priceSearch;

    @FXML
    private TableColumn<Parts, Integer> partAdd;

    @FXML
    private TableColumn<Parts, String> partNameAdd;

    @FXML
    private TableColumn<Parts, Integer> invAdd;

    @FXML
    private TableColumn<Parts, Double> priceAdd;

    @FXML
    private Button productSearchButton;

    @FXML
    private Button productDeleteButton;

    @FXML
    private Button productSaveButton;

    @FXML
    private Button productCancelButton;

    @FXML
    private Button productAddButton;

    public AddProductController() {
    }

    @FXML
    private void initialize() {
        partDelete.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partNameSearch.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        priceSearch.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        partNameAdd.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        invAdd.setCellValueFactory(cellData -> cellData.getValue().partsInStockProperty().asObject());
        invSearch.setCellValueFactory(cellData -> cellData.getValue().partsInStockProperty().asObject());
        priceAdd.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());

        partAdd.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());

        productSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == null || newValue.isEmpty()) {
                partSearchTable.setItems(inventory.getPartList());
            }
        });






    }


    private Stage productStage;
    private Inventory inventory;
    private Product product;
    private boolean newProduct;
    private ObservableList<Parts> parts;


    public void setScreenTitle(Stage stage, Inventory hasInventory, Product hasProduct) {
        productStage = stage;
        inventory = hasInventory;
        product = hasProduct;

        if (hasProduct == null) {
            newProduct = true;
            productLabel.setText("Add Product");

        } else {
            newProduct = false;
            productLabel.setText("Modify Product");
            setProduct(hasProduct);

        }
        partSearchTable.setItems(inventory.getPartList());

    }

    public void setProduct(Product product) {
        productId.setText(Integer.toString(product.getProductID()));
        productNameField.setText(product.getProductName());
        productInvField.setText(Integer.toString(product.getProductInStock()));
        productPriceField.setText(Double.toString(product.getProductPrice()));
        productMaxField.setText(Integer.toString(product.getProductMax()));
        productMinField.setText(Integer.toString(product.getProductMin()));


        partTable.setItems(product.getParts());


    }

    @FXML
    private void deletePart() {
        Parts part = partTable.getSelectionModel().getSelectedItem();

        partTable.getItems().remove(part);

    }

    @FXML
    private void addPart() {
        Parts addedPart = partSearchTable.getSelectionModel().getSelectedItem();


            String priceString = priceSearch.getText();
            double price = priceSearch.getCellData(0);
            partTable.getItems().add(addedPart);
            double partCost = addCostOfParts();
            if(partCost > price) {
                price += addedPart.getPartPrice();
                productPriceField.setText(priceString);
            }

        }



    @FXML
    public void saveProduct() {
        Product editProduct = null;

        String name = this.productNameField.getText();
        String invtory = this.productInvField.getText();
        String price = this.productPriceField.getText();
        String max = this.productMaxField.getText();
        String min = this.productMinField.getText();
        parts = partTable.getItems();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(productStage);
        alert.setTitle("Unable to Save Product");

        try {
            if (name.isEmpty() || invtory.isEmpty() ||
                    price.isEmpty() || max.isEmpty() ||
                    min.isEmpty()) {
                throw new IllegalArgumentException("Must complete all fields.");
            }
            if (parts.isEmpty()) {
                throw new IllegalArgumentException("All Products must be associated with one part.");
            }
            double productPrice = Double.parseDouble(price);
            int instock = Integer.parseInt(invtory);
            int productMax = Integer.parseInt(max);
            int productMin = Integer.parseInt(min);
            double partCost = addCostOfParts();

            if (partCost > productPrice) {
                throw new IllegalArgumentException("Price of product must be greater then total price of parts.");

            }
            editProduct = new Product(name, productPrice, instock, productMax, productMin, parts);

            if (newProduct) {
                Product searchProduct = inventory.lookupProduct(editProduct.getProductName());

                if (searchProduct == null) {
                    inventory.addProduct(editProduct);

                }
            } else {
                inventory.updateProduct(product, editProduct);
            }
        } catch (NumberFormatException e) {
            alert.setContentText("Invalid number");
            alert.showAndWait();
            return;
        } catch (IllegalArgumentException e) {
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
        productStage.close();


    }

    private double addCostOfParts() {
        double partCost = 0;

        ObservableList<Parts> parts = partTable.getItems();
        if (parts == null || parts.isEmpty()) {
            return partCost;
        }
        for (Parts parts1 : parts) {
            partCost += parts1.getPartPrice();
        }
        return partCost;
    }

    @FXML
    private void handelCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit?");
        alert.setHeaderText("Data not Saved.");
        alert.initOwner(productStage);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            productStage.close();
        }
    }

    @FXML
    private void searchParts() {

        String searchTerm = productSearchField.getText();
        if(searchTerm == null || searchTerm.isEmpty()) {
            partSearchTable.setItems((inventory.getPartList()));
        }
        ObservableList<Parts> partsFound = inventory.searchforPart(searchTerm);
        partSearchTable.setItems(partsFound);

    }




}

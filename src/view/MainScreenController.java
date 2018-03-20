package view;



import inventory.Main;
import inventory.Model.Parts;
import inventory.Model.Product;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableColumn<Product, Integer> productIntegerTableColumn;
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


    @FXML
    private void initialize() {

    }



}

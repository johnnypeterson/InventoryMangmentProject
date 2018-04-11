package view;


import inventory.Model.Inventory;
import inventory.Model.Parts;
import inventory.Model.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 * Created by johnnypeterson on Mar, 2018
 */
public class AddProductController {

    @FXML
    private Label productLabel;

    @FXML
    private TextField productSearchField;

    @FXML
    private TextField productId;

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
    private TableView<Parts>  partSearchTable;

    @FXML
    private TableView<Parts> partTable;

    @FXML
    private TableColumn<Parts, Integer> partIdSearch;

    @FXML
    private TableColumn<Parts, String> partNameSearch;

    @FXML
    private TableColumn<Parts, Integer> invSearch;

    @FXML
    private TableColumn<Parts, Integer> priceSearch;

    @FXML
    private TableColumn<Parts, Integer> partAdd;

    @FXML
    private TableColumn<Parts, String> partNameAdd;

    @FXML
    private TableColumn<Parts, Integer> invAdd;

    @FXML
    private TableColumn<Parts, Integer> priceAdd;

    @FXML
    private Button productSearchButton;

    @FXML
    private  Button productDeleteButton;

    @FXML
    private Button productSaveButton;

    @FXML
    private Button productCancelButton;

    @FXML
    private Button productAddButton;

    public AddProductController() {}

    @FXML
    private void initialize () {

    }


    private Stage productStage;
    private Inventory inventory;
    private Product product;
    private boolean newPart;
    private ObservableList<Parts> parts;

    public void setScreenTitle(Stage stage, Inventory hasInventory, Product hasProduct) {
        productStage = stage;
        inventory = hasInventory;
        product = hasProduct;

        if (hasProduct == null) {
            newPart = true;
            productLabel.setText("Add Part");

        } else {
            newPart = false;
            productLabel.setText("Modify Part");

        }

    }


}

package view;


import inventory.Model.Parts;
import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 * Created by johnnypeterson on Mar, 2018
 */
public class AddProductController {

    @FXML
    private Label productLabel;

    @FXML
    private TextField productSearchField;

    @FXML
    private Label productID;

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



}

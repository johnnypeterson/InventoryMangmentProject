package view;

import inventory.Model.Inventory;
import inventory.Model.Parts;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 * Created by johnnypeterson on Mar, 2018
 */
public class AddPartController {

    @FXML
    private Label partTitle;

    @FXML
    private RadioButton inHouseButton;

    @FXML
    private RadioButton outsourcedButton;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField invField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField companyNameField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label machineOrCompany;

    public AddPartController() {}

    @FXML
    private void initialize() {

        ToggleGroup radioButton = new ToggleGroup();
        inHouseButton.setToggleGroup(radioButton);
        outsourcedButton.setToggleGroup(radioButton);

        radioButton.selectedToggleProperty().addListener((observableValue, oldToggle, newToggle) -> changeLable(newToggle));



    }

    private void changeLable(Toggle toggle) {
        String labelText = "Machine ID";
        if(toggle.equals(inHouseButton)) {
            labelText = "Machine ID";

        } else if (toggle.equals(outsourcedButton)) {
            labelText = "Company Name";
        }
        machineOrCompany.setText(labelText);



    }


    private Stage partStage;
    private Inventory inventory;
    private Parts part;
    private boolean newPart;

    public void setScreenTitle(Stage stage, Inventory hasInventory, Parts hasPart) {
        partStage = stage;
        inventory = hasInventory;
        part = hasPart;

        if (hasPart == null) {
            newPart = true;
            partTitle.setText("Add Part");

        } else {
            newPart = false;
            partTitle.setText("Modify Part");

        }

    }






}

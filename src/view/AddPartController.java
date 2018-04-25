package view;

import inventory.Model.InHouse;
import inventory.Model.Inventory;
import inventory.Model.Outsourced;
import inventory.Model.Parts;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;


/**
 * Created by johnnypeterson on Mar, 2018
 */
public class AddPartController {

    @FXML
    private Label partTitle;

    @FXML
    private Label partIdField;

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
    private TextField priceField;

    @FXML
    private TextField companyNameField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label machineOrCompany;

    private Stage partStage;
    private Inventory inventory;
    private Parts part;
    private boolean newPart;


    public AddPartController() {
    }

    @FXML
    private void initialize() {

        ToggleGroup radioButton = new ToggleGroup();
        inHouseButton.setToggleGroup(radioButton);
        outsourcedButton.setToggleGroup(radioButton);

        radioButton.selectedToggleProperty().addListener((observableValue, oldToggle, newToggle) -> changeLable(newToggle));


    }

    private void changeLable(Toggle toggle) {
        String labelText = "Machine ID";
        if (toggle.equals(inHouseButton)) {
            labelText = "Machine ID";

        } else if (toggle.equals(outsourcedButton)) {
            labelText = "Company Name";
        }
        machineOrCompany.setText(labelText);


    }


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
            setPart(hasPart);

        }

    }

    public void setPart(Parts part) {
        if (part instanceof InHouse) {
            inHouseButton.setSelected(true);
            companyNameField.setText(Integer.toString(((InHouse) part).getMachineID()));
            machineOrCompany.setText("Machine Id");

        } else if (part instanceof Outsourced) {
            outsourcedButton.setSelected(true);
            companyNameField.setText(((Outsourced) part).getCompnayName());
            machineOrCompany.setText("Company");
        }
        partIdField.setText(Integer.toString(part.getPartID()));
        partNameField.setText(part.getName());
        invField.setText(Integer.toString(part.getPartsInStock()));
        maxField.setText(Integer.toString(part.getPartMax()));
        minField.setText(Integer.toString(part.getPartMin()));
        priceField.setText(Double.toString(part.getPartPrice()));


    }

    @FXML
    private void handelCancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit?");
        alert.setHeaderText("Data not Saved.");
        alert.initOwner(partStage);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            partStage.close();
        }
    }
    @FXML
    private void savePart() {
        Parts editPart = null;
        boolean isInhouse = inHouseButton.isSelected();

        String name = partNameField.getText();
        String inStock = invField.getText();
        String price = priceField.getText();
        String max = maxField.getText();
        String min = minField.getText();
        String companyOrMachine = companyNameField.getText();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(partStage);
        alert.setTitle("Unable to Save Part");

        try {
            if (name.isEmpty() || inStock.isEmpty() || price.isEmpty() || max.isEmpty() ||
                    min.isEmpty() || companyOrMachine.isEmpty()){
                throw new IllegalArgumentException("Must complete all fields.");
            }  if (Integer.parseInt(max) <  Integer.parseInt(min)) {
                throw new IllegalArgumentException("The Max field can't be less then your min field.");
            } else if (Integer.parseInt(inStock) > Integer.parseInt(max) || Integer.parseInt(inStock) < Integer.parseInt(min)) {
                throw new IllegalArgumentException("The Inv field can't be greater then your max field or less then your min field.");
            }
            String companyName = null;
            int machineId = -1;
            double priceDouble = Double.parseDouble(price);
            int inStockInt = Integer.parseInt(inStock);
            int maxInt = Integer.parseInt(max);
            int minInt = Integer.parseInt(min);




            if (isInhouse) {
                machineId = Integer.parseInt(companyOrMachine);
                editPart = new InHouse(name, priceDouble, inStockInt, minInt, maxInt, machineId);
            } else {
                companyName = companyOrMachine;
                editPart = new Outsourced(name, priceDouble, inStockInt, minInt, maxInt, companyName);
            }
            if(newPart) {
                Parts searchPart = inventory.lookupPart(editPart.getName());

                if (searchPart == null || partFound() == ButtonType.YES) {
                    inventory.addPart(editPart);
                }
            } else  {
                inventory.updatePart(part, editPart);
            }

        } catch (NumberFormatException e) {
            alert.setContentText("One of your numbers is invalid");
            alert.showAndWait();
            return;

        }  catch (IllegalArgumentException e) {
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
        partStage.close();

    }

    private ButtonType partFound() {
        ButtonType result = ButtonType.NO;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(partStage);
        alert.setHeaderText("Add duplicate part?");
        alert.setTitle("Found duplicate part");
        alert.setContentText("A part with that name already exists.");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> answer = alert.showAndWait();
        result = answer.get();
        return result;
    }


}

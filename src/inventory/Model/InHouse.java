package inventory.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public class InHouse extends Parts {

    private IntegerProperty machineID;

    public int getMachineID() {
        return machineID.get();
    }

    public IntegerProperty machineIDProperty() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }

    public InHouse(String name, double partPrice, int partInstock) {
        super(name, partPrice, partInstock);
        this.machineID = new SimpleIntegerProperty(1);
    }



}

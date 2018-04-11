package inventory.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public class InHouse extends Parts {

    private IntegerProperty machineID = new SimpleIntegerProperty();

    public InHouse() {
        this("New Part", 0, 0);
        setPartName(getPartName() + " " + getPartID());
    }

    public InHouse(String name, double price, int machineID) throws IllegalArgumentException{
        this(name, price, 0, 0, 0, machineID);
    }



    public InHouse(String name, double price, int instock, int min, int max, int machineID) throws IllegalArgumentException{

        setPartID();
        setPartName(name);
        setPartPrice(price);
        setPartMax(max);
        setPartMin(min);
        setPartsInStock(instock);

        setMachineID(machineID);
    }



    public int getMachineID() {
        return machineID.get();
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }

    public IntegerProperty machineIDProperty() {
        return machineID;
    }


}

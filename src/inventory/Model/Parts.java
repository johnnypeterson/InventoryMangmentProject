package inventory.Model;

import javafx.beans.property.*;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public abstract class Parts {

    private static int incrementId = 1;
    private IntegerProperty partID = new SimpleIntegerProperty();
    private StringProperty partName = new SimpleStringProperty();
    private DoubleProperty partPrice = new SimpleDoubleProperty();
    private IntegerProperty partsInStock = new SimpleIntegerProperty();
    private IntegerProperty partMin = new SimpleIntegerProperty();
    private IntegerProperty partMax = new SimpleIntegerProperty();


    protected Parts() {
    }

    public static int getIncrementId() {
        return incrementId;
    }

    public static void setIncrementId(int incrementId) {
        Parts.incrementId = incrementId;
    }

    public int getPartID() {
        return partID.get();
    }
    public IntegerProperty partIDProperty() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID.set(partID);
    }

    public String getPartName() {
        return partName.get();
    }

    public StringProperty partNameProperty() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName.set(partName);
    }

    public double getPartPrice() {
        return partPrice.get();
    }

    public DoubleProperty partPriceProperty() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice.set(partPrice);
    }

    public int getPartsInStock() {
        return partsInStock.get();
    }

    public IntegerProperty partsInStockProperty() {
        return partsInStock;
    }

    public void setPartsInStock(int partsInStock) {
        this.partsInStock.set(partsInStock);
    }

    public int getPartMin() {
        return partMin.get();
    }

    public IntegerProperty partMinProperty() {
        return partMin;
    }

    public void setPartMin(int partMin) {
        this.partMin.set(partMin);
    }

    public int getPartMax() {
        return partMax.get();
    }

    public IntegerProperty partMaxProperty() {
        return partMax;
    }

    public void setPartMax(int partMax) {
        this.partMax.set(partMax);
    }


    public void copyPartId(Parts oldPart) {
        this.partID.set(oldPart.getPartID());
    }

    public void setPartID(){
        this.partID.set(Parts.incrementId++);
    }



}



package inventory.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public abstract class Parts {

    private static AtomicInteger incrementId = new AtomicInteger(0);
    private SimpleIntegerProperty partID;
    private SimpleStringProperty partName;
    private SimpleDoubleProperty partPrice;
    private SimpleIntegerProperty partsInStock;
    private SimpleIntegerProperty partMin;
    private SimpleIntegerProperty partMax;

    public Parts(String name, double partPrice, int partInstock) {
        this.partID = new SimpleIntegerProperty(incrementId.incrementAndGet());
        this.partName = new SimpleStringProperty(name);
        this.partPrice = new SimpleDoubleProperty(partPrice);
        this.partsInStock = new SimpleIntegerProperty(partInstock);
        this.partMin = new SimpleIntegerProperty(0);
        this.partMax = new SimpleIntegerProperty(0);
    }

    public static AtomicInteger getIncrementId() {
        return incrementId;
    }

    public static void setIncrementId(AtomicInteger incrementId) {
        Parts.incrementId = incrementId;
    }

    public int getPartID() {
        return partID.get();
    }

    public SimpleIntegerProperty partIDProperty() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID.set(partID);
    }

    public String getPartName() {
        return partName.get();
    }

    public SimpleStringProperty partNameProperty() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName.set(partName);
    }

    public double getPartPrice() {
        return partPrice.get();
    }

    public SimpleDoubleProperty partPriceProperty() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice.set(partPrice);
    }

    public int getPartsInStock() {
        return partsInStock.get();
    }

    public SimpleIntegerProperty partsInStockProperty() {
        return partsInStock;
    }

    public void setPartsInStock(int partsInStock) {
        this.partsInStock.set(partsInStock);
    }

    public int getPartMin() {
        return partMin.get();
    }

    public SimpleIntegerProperty partMinProperty() {
        return partMin;
    }

    public void setPartMin(int partMin) {
        this.partMin.set(partMin);
    }

    public int getPartMax() {
        return partMax.get();
    }

    public SimpleIntegerProperty partMaxProperty() {
        return partMax;
    }

    public void setPartMax(int partMax) {
        this.partMax.set(partMax);
    }


    public void copyPartId(Parts oldPart) {
        this.partID.set(oldPart.getPartID());
    }



}



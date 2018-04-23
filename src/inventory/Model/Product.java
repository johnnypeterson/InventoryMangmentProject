package inventory.Model;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public class Product {

    private ObservableList<Parts> associatedParts = FXCollections.observableArrayList();
    private IntegerProperty productID = new SimpleIntegerProperty();
    private StringProperty productName = new SimpleStringProperty();
    private DoubleProperty productPrice = new SimpleDoubleProperty();
    private IntegerProperty productInStock = new SimpleIntegerProperty();
    private IntegerProperty productMin = new SimpleIntegerProperty();
    private IntegerProperty productMax = new SimpleIntegerProperty();

    private static int incrementId = 1;


    public ObservableList<Parts> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ObservableList<Parts> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public void addPart(Parts part) {
        this.associatedParts.add(part);
    }

    public boolean removePart(int id) {
        return associatedParts.removeIf(part -> part.getPartID() == id);
    }

    public void updatePart(int id) throws Exception {
        for (Parts part : associatedParts) {
            if (part.getPartID() == id) {
                return;
            } else {
                throw new Exception("Part not found");
            }
        }
    }

    public Parts lookupPart(int id) throws Exception {
        for (Parts part : associatedParts) {
            if (part.getPartID() == id) {
                return part;
            } else {
                throw new Exception("Part not found");
            }
        }
        return null;
    }

    public int getProductID() {
        return productID.get();
    }

    public IntegerProperty productIDProperty() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public double getProductPrice() {
        return productPrice.get();
    }

    public DoubleProperty productPriceProperty() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice.set(productPrice);
    }

    public int getProductInStock() {
        return productInStock.get();
    }

    public IntegerProperty productInStockProperty() {
        return productInStock;
    }

    public void setProductInStock(int productInStock) {
        this.productInStock.set(productInStock);
    }

    public int getProductMin() {
        return productMin.get();
    }

    public IntegerProperty productMinProperty() {
        return productMin;
    }

    public void setProductMin(int productMin) {
        this.productMin.set(productMin);
    }

    public int getProductMax() {
        return productMax.get();
    }

    public IntegerProperty productMaxProperty() {
        return productMax;
    }

    public void setProductMax(int productMax) {
        this.productMax.set(productMax);
    }

    public ObservableList<Parts> getParts() {
        return associatedParts;
    }

    public void setProductID() {
        this.productID.set(incrementId++);
    }

    public Product(String name, double price, int instock, int min, int max, Parts firstpart) throws IllegalArgumentException {
        this(name, price, instock, min, max, FXCollections.observableArrayList(firstpart));
    }


    public Product(String name, double price, int instock, int min, int max, List<Parts> parts) throws IllegalArgumentException {
        setProductID();
        this.associatedParts.setAll(parts);
        setProductName(name);
        setProductPrice(price);
        setProductInStock(instock);
        setProductMax(max);
        setProductMin(min);
    }


    public void copyId(Product oldProduct) {
        this.productID.set(oldProduct.getProductID());
    }


}

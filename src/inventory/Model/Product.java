package inventory.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Observable;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public class Product {

    private ObservableList<Parts> associatedParts = FXCollections.observableArrayList();
    private Integer productID;
    private String productName;
    private Double productPrice;
    private Integer productInStock;
    private Integer productMin;
    private Integer productMax;

    public ObservableList<Parts> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ObservableList<Parts> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductInStock() {
        return productInStock;
    }

    public void setProductInStock(Integer productInStock) {
        this.productInStock = productInStock;
    }

    public Integer getProductMin() {
        return productMin;
    }

    public void setProductMin(Integer productMin) {
        this.productMin = productMin;
    }

    public Integer getProductMax() {
        return productMax;
    }

    public void setProductMax(Integer productMax) {
        this.productMax = productMax;
    }





}

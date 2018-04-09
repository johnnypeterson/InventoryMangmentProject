package inventory.Model;


        import javafx.beans.property.DoubleProperty;
        import javafx.beans.property.IntegerProperty;
        import javafx.beans.property.StringProperty;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;

        import java.util.List;
        import java.util.Observable;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public class Product {

    private ObservableList<Parts> associatedParts = FXCollections.observableArrayList();
    private IntegerProperty productID;
    private StringProperty productName;
    private DoubleProperty productPrice;
    private IntegerProperty productInStock;
    private IntegerProperty productMin;
    private IntegerProperty productMax;



    public ObservableList<Parts> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ObservableList<Parts> associatedParts) {
        this.associatedParts = associatedParts;
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
}

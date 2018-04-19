package inventory.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public class Outsourced extends Parts{

    private StringProperty compnayName = new SimpleStringProperty();

    public String getCompnayName() {
        return compnayName.get();
    }

    public void setCompnayName(String compnayName) {
        if (compnayName == null || compnayName.isEmpty()) {
            compnayName = "No Company";
        }
        this.compnayName.set(compnayName);
    }
    public Outsourced(String name, double price, int instock, int min, int max, String companyName) throws IllegalArgumentException {
        setPartID();
        setPartName(name);
        setPartPrice(price);
        setPartMax(max);
        setPartMin(min);
        setPartsInStock(instock);
        setCompnayName(companyName);
    }


}

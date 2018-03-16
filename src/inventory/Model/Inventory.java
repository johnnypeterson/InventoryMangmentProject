package inventory.Model;

import java.util.ArrayList;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public class Inventory {

    private ArrayList<Product> allProducts;
    private ArrayList<Parts> allParts;

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(ArrayList<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public ArrayList<Parts> getAllParts() {
        return allParts;
    }

    public void setAllParts(ArrayList<Parts> allParts) {
        this.allParts = allParts;
    }
}

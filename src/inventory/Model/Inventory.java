package inventory.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by johnnypeterson on Mar, 2018
 */
public class Inventory {

    private ObservableList<Product> allProducts;
    private ObservableList<Parts> allParts;
    private static int partIdCount;
    private static int productIdCount;

    public Inventory() {
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }



    //Setters and Getters
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(ObservableList<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public ObservableList<Parts> getAllParts() {
        return allParts;
    }

    public void setAllParts(ObservableList<Parts> allParts) {
        this.allParts = allParts;
    }

    public static int getPartIdCount() {
        partIdCount++;
        return partIdCount;
    }

    public static int getProductIdCount() {
        productIdCount++;
        return productIdCount;
    }

    //Methods for Inventory

    public void addPart(Parts part) {
        if(part != null) {
            allParts.add(part);
        }
    }

    public void addProduct(Product addProduct) {
        if (addProduct != null) {
            allProducts.add(addProduct);
        }
    }

    public boolean removePart(Parts part) {
        boolean result = false;
        if(part != null){
            result = allParts.remove(part);
        }
        return result;
    }
    public boolean removeProduct(Product removeProduct) throws Exception {
        boolean result = false;
        if (removeProduct == null) return false;

        if(!removeProduct.getParts().isEmpty()) {
            throw new Exception("This Product is still associated with one or more parts.");
        }
        result = this.allProducts.remove(removeProduct);
        return result;
    }


    public Parts lookupPart(String searchTerm) {
        Parts partFound = null;
        for(Parts part : allParts) {
            if(searchTerm.toLowerCase().equals(part.getPartName().toLowerCase())) {
                partFound = part;
                break;
            }

        }
        return partFound;

    }

    public Product lookupProduct(String searchTerm) {
        Product searchProduct = null;

        for (Product product : allProducts) {
            if(searchTerm.toLowerCase().equals(product.getProductName().toLowerCase())) {
                searchProduct = product;
                break;
            }
        }
        return searchProduct;


    }

    public void updatePart(Parts oldPart, Parts newPart) {
        newPart.copyPartId(oldPart);
        removePart(oldPart);
        addPart(newPart);

    }

    public static boolean isInt(String input) {
        try{
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ObservableList<Parts> getPartList() {
        return allParts;
    }

    public ObservableList<Product> getProdcutList() {
        return allProducts;
    }








}

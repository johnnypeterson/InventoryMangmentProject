package inventory.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        if (part != null) {
            allParts.add(part);
        }
    }

    public void addProduct(Product addProduct) {
        if (addProduct != null) {
            allProducts.add(addProduct);
        }
    }

    public boolean deletePart(Parts part) {
        boolean result = false;
        if (part != null) {
            result = allParts.remove(part);
        }
        return result;
    }


    public Parts lookupPart(String searchTerm) {
        Parts partFound = null;
        for (Parts part : allParts) {
            if (searchTerm.toLowerCase().equals(part.getName().toLowerCase())) {
                partFound = part;
                break;
            }

        }
        return partFound;

    }

    public Product lookupProduct(String searchTerm) {
        Product searchProduct = null;

        for (Product product : allProducts) {
            if (searchTerm.toLowerCase().equals(product.getProductName().toLowerCase())) {
                searchProduct = product;
                break;
            }
        }
        return searchProduct;


    }

    public void updatePart(Parts oldPart, Parts newPart) {
        newPart.copyPartId(oldPart);
        deletePart(oldPart);
        addPart(newPart);

    }


    public void updateProduct(Product oldProduct, Product newProduct) {
        newProduct.copyId(oldProduct);
        deleteProduct(oldProduct);
        addProduct(newProduct);
    }

    public boolean deleteProduct(Product productToRemove) {
        boolean result = false;

        if (productToRemove == null) return result;

        result = this.allProducts.remove(productToRemove);

        return result;
    }

    public ObservableList<Parts> getPartList() {
        return allParts;
    }

    public ObservableList<Product> getProdcutList() {
        return allProducts;
    }

    public ObservableList<Product> searchforProduct(String string) {
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();

        String productName = null;

        for (Product product : allProducts) {
            productName = product.getProductName();

            for (Parts part : product.getParts()) {

                if (part.getName().toLowerCase().contains(string.toLowerCase())) {
                    productObservableList.add(product);
                }

            }
            if (productName.toLowerCase().contains(string.toLowerCase())) {
                productObservableList.add(product);
            }

        }
        return productObservableList;

    }


    public ObservableList<Parts> searchforPart(String string) {


        ObservableList<Parts> partsObservableList = FXCollections.observableArrayList();

        String machineId = null;
        String companyName = null;


        String partName = null;

        for (Parts part : allParts) {
            partName = part.getName();
            machineId = part instanceof InHouse ? String.valueOf(((InHouse) part).getMachineID()) : "";
            companyName = part instanceof Outsourced ? ((Outsourced) part).getCompnayName() : "";

            if (partName.toLowerCase().contains(string.toLowerCase()) ||
                    machineId.toLowerCase().contains(string.toLowerCase()) ||
                    companyName.toLowerCase().contains(string.toLowerCase())) {
                partsObservableList.add(part);
            }


        }
        return partsObservableList;


    }

}

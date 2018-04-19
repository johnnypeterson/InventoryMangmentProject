package inventory;

import inventory.Model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.AddPartController;
import view.AddProductController;
import view.MainScreenController;

import java.io.IOException;

public class Main extends Application {

    private Inventory inventory;
    public Stage primaryStage;
    private Main app;
    private AnchorPane rootLayout;

    private ObservableList<Parts> partsData = FXCollections.observableArrayList();
    private ObservableList<Product> productData = FXCollections.observableArrayList();


    public Main () {
        inventory = new Inventory();

        try {
            inventory.addPart(new InHouse("test", 1.34, 23,213, 241, 3));
            inventory.addPart(new Outsourced("outsourcedpart", 12,234,32,54,"test"));
            inventory.addPart(new Outsourced("outsourcedpart", 12,234,32,54,null));

            Product productTest = new Product("Test", 50, 89, 56, 98, inventory.lookupPart("outsourcedpart"));
            productTest.addPart(inventory.lookupPart("test"));
            inventory.addProduct(productTest);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Johnny Peterson C482");

        ininRootLayout();



    }
    public  void ininRootLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/MainScreen.fxml"));
            rootLayout = (AnchorPane) loader.load();


            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.show();

            MainScreenController controller = loader.getController();
            controller.setMainScreen(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  ObservableList<Parts> getPartsData() {
        return partsData;
    }
    public void addPartsData(Parts part) {partsData.add(part);}

    public ObservableList<Product> getProductData() {
        return productData;
    }
    public void addProdData(Product prod){productData.add(prod);}




    public Inventory getInventory() {
        return inventory;
    }

    public void showPartScreen(Parts part) {
        String title;
        if (part == null) {
            title = "Add a Part";

        } else {
            title = "Modify Part";
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/AddPartScreen.fxml"));
            AnchorPane partScreen = (AnchorPane) loader.load();

            Stage partScreenStage = new Stage();
            partScreenStage.setTitle(title);
            partScreenStage.initModality(Modality.WINDOW_MODAL);
            partScreenStage.initOwner(primaryStage);


            Scene scene = new Scene(partScreen);
            partScreenStage.setScene(scene);

            AddPartController controller = loader.getController();
            controller.setScreenTitle(partScreenStage, inventory, part);

            partScreenStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showProductScreen(Product product) {
        String title;
        if (product == null) {
            title = "Add Product";
        } else {
            title = "Modify Product";
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/AddProductScreen.fxml"));
            AnchorPane productScreen = (AnchorPane) loader.load();

            Stage productScreenStage = new Stage();
            productScreenStage.setTitle(title);
            productScreenStage.initModality(Modality.WINDOW_MODAL);
            productScreenStage.initOwner(primaryStage);

            Scene scene = new Scene(productScreen);
            productScreenStage.setScene(scene);

            AddProductController controller = loader.getController();
            controller.setScreenTitle(productScreenStage, inventory, product);

            productScreenStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }






    public static void main(String[] args) {
        launch(args);
    }
}

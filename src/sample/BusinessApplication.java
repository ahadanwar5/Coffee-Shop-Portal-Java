package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BusinessApplication extends Application {

    public static final String BLANK = "";

    User businessUser = null;
    Item item = null;
    List<Item> itemsList = null;

    GridPane loginGrid, resetPwdGrid, signupGrid, optionsGrid, addItemGrid, updateItemGrid, sellItemGrid, printGrid;
    Label usernameLabel, passwordLabel, emailLabel, phoneLabel, itemNameLabel, itemCodeLabel, itemPriceLabel, quantityLabel, totalSalesLabel;
    TextField usernameField, emailField, phoneField, loginUserIdField, itemNameField, itemCodeField, itemPriceField, quantityField, totalSalesField;
    PasswordField passwordField, loginPasswordField;
    Button loginButton, signUpButton, resetPasswordButton, addNewItemButton, updateInventoryButton, sellProductButton, printTotalSalesButton, sellItemButton;
    ComboBox comboBox;

    Scene loginScene, signUpScene, resetScene, optionsScene, updateItemScene, sellItemScene, totalSalesScene;
    Stage signUpStage, resetStage, optionsStage, addItemStage, updateItemStage, sellItemStage, totalSalesStage;

    Hyperlink signupLink, resetPwdLink;

    String userName, password, email, phone;

    String userDataFilePath = "C:\\Drive E\\COMSATS\\COMSATS SEM 1-3\\COMSATS, Spring 2021\\Object Oriented Programming CSC241\\PROJECT\\UserData.txt";
    String itemsInventoryFilePath = "E:\\PROJECT\\InventoryData.txt";
    String salesDataFilePath = "E:\\PROJECT\\SalesData.txt";

    BufferedReader br = null;
    FileWriter writer = null;
    String line = "";

    //Loads this Primary Stage on application start up (primaryStage is login stage)
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Business Application-JavaFX");

        loginGrid = new GridPane();
        loginGrid.setAlignment(Pos.CENTER);
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Login");
        sceneTitle.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        loginGrid.add(sceneTitle, 0, 0, 2, 1);

        usernameLabel = new Label("Username:");
        loginGrid.add(usernameLabel, 0, 1);

        loginUserIdField = new TextField();
        loginGrid.add(loginUserIdField, 1, 1);

        passwordLabel = new Label("Password:");
        loginGrid.add(passwordLabel, 0, 2);

        loginPasswordField = new PasswordField();
        loginGrid.add(loginPasswordField, 1, 2);

        loginButton = new Button("Login");

        signupLink = new Hyperlink("Signup");
        resetPwdLink = new Hyperlink("Reset Password");

        HBox hBoxDisplay = new HBox(loginButton);
        loginGrid.add(hBoxDisplay, 1, 3);

        loginGrid.add(signupLink, 0, 4);
        loginGrid.add(resetPwdLink, 1, 4);

        //Action event on click of Login Button
        loginButton.setOnAction(actionEvent -> {
            userName = loginUserIdField.getText();
            password = loginPasswordField.getText();
            if (!BLANK.equals(userName) && !BLANK.equals(password)) {
                businessUser = readUserData(userName);
                if (businessUser != null) {
                    if (userName.equals(businessUser.getUsername()) && password.equals(businessUser.getPassword())) {
                        primaryStage.close();

                        optionsStage = new Stage();
                        optionsStage.setTitle("Business");

                        optionsGrid = new GridPane();
                        optionsGrid.setAlignment(Pos.CENTER);
                        optionsGrid.setHgap(10);
                        optionsGrid.setVgap(10);
                        optionsGrid.setPadding(new Insets(25, 25, 25, 25));

                        Text addTitle = new Text("Welcome to Coffee Planet");
                        addTitle.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
                        optionsGrid.add(addTitle, 0, 0, 2, 1);

                        addNewItemButton = new Button("Add New Item");
                        optionsGrid.add(addNewItemButton, 0, 1);

                        updateInventoryButton = new Button("Update Inventory");
                        optionsGrid.add(updateInventoryButton, 0, 2);

                        sellProductButton = new Button("Sell Product");
                        optionsGrid.add(sellProductButton, 0, 3);

                        printTotalSalesButton = new Button("Print Sales");
                        optionsGrid.add(printTotalSalesButton, 0, 4);

                        optionsScene = new Scene(optionsGrid, 500, 300);
                        optionsStage.setScene(optionsScene);
                        optionsStage.show();

                        //Displays the Add Item form
                        addNewItemButton.setOnAction(ae -> {

                            addItemStage = new Stage();
                            addItemStage.setTitle("Add Item");

                            addItemGrid = new GridPane();
                            addItemGrid.setAlignment(Pos.CENTER);
                            addItemGrid.setHgap(10);
                            addItemGrid.setVgap(10);
                            addItemGrid.setPadding(new Insets(25, 25, 25, 25));

                            Text title = new Text("Add Item");
                            title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
                            addItemGrid.add(title, 0, 0, 2, 1);

                            itemNameLabel = new Label("Item Name:");
                            addItemGrid.add(itemNameLabel, 0, 1);

                            itemNameField = new TextField();
                            addItemGrid.add(itemNameField, 1, 1);

                            itemCodeLabel = new Label("Item Code");
                            addItemGrid.add(itemCodeLabel, 0, 2);

                            itemCodeField = new TextField();
                            addItemGrid.add(itemCodeField, 1, 2);

                            itemPriceLabel = new Label("Price");
                            addItemGrid.add(itemPriceLabel, 0, 3);

                            itemPriceField = new TextField();
                            addItemGrid.add(itemPriceField, 1, 3);

                            addNewItemButton = new Button("Add");
                            addItemGrid.add(addNewItemButton, 1, 4);

                            signUpScene = new Scene(addItemGrid, 500, 275);
                            addItemStage.setScene(signUpScene);
                            addItemStage.show();

                            //Action event on click of Add Item Button
                            addNewItemButton.setOnAction(acev -> {
                                String itemName = itemNameField.getText();
                                String itemCode = itemCodeField.getText();
                                String price = itemPriceField.getText();
                                item = new Item(itemName, itemCode, Double.parseDouble(price));
                                try {
                                    writer = new FileWriter(itemsInventoryFilePath, true);
                                    writer.write(itemName+","+itemCode+","+price+"\n");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        writer.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                this.alert("Success", "Item added Successfully!", AlertType.INFORMATION);
                                addItemStage.close();
                                optionsStage.show();
                            });

                        });

                        //Displays the Update Item form
                        updateInventoryButton.setOnAction(ae -> {
                            updateItemStage = new Stage();
                            updateItemStage.setTitle("Update Item");

                            updateItemGrid = new GridPane();
                            updateItemGrid.setAlignment(Pos.CENTER);
                            updateItemGrid.setHgap(10);
                            updateItemGrid.setVgap(10);
                            updateItemGrid.setPadding(new Insets(25, 25, 25, 25));

                            Text title = new Text("Update Item");
                            title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
                            updateItemGrid.add(title, 0, 0, 2, 1);

                            itemNameLabel = new Label("Item Name:");
                            updateItemGrid.add(itemNameLabel, 0, 1);

                            comboBox = new ComboBox();
                            itemsList = getItemsFromInventory();
                            for (Item itemObj: itemsList) {
                                comboBox.getItems().add(itemObj.getName());
                            }
                            comboBox.getSelectionModel();
                            updateItemGrid.add(comboBox, 1, 1);

                            itemCodeLabel = new Label("Item Code");
                            updateItemGrid.add(itemCodeLabel, 0, 2);

                            itemCodeField = new TextField();
                            updateItemGrid.add(itemCodeField, 1, 2);

                            itemPriceLabel = new Label("Price");
                            updateItemGrid.add(itemPriceLabel, 0, 3);

                            itemPriceField = new TextField();
                            updateItemGrid.add(itemPriceField, 1, 3);

                            updateInventoryButton = new Button("Update");
                            updateItemGrid.add(updateInventoryButton, 1, 4);

                            updateItemScene = new Scene(updateItemGrid, 500, 275);
                            updateItemStage.setScene(updateItemScene);
                            updateItemStage.show();

                            //Action event on click of Update Item Button
                            updateInventoryButton.setOnAction(acev -> {
                                String itemName = (String) comboBox.getSelectionModel().getSelectedItem();
                                String itemCode = itemCodeField.getText();
                                String price = itemPriceField.getText();
                                item = new Item(itemName, itemCode, Double.parseDouble(price));

                                updateInventory(item);
                                this.alert("Success", "Item updated Successfully!", AlertType.INFORMATION);
                                updateItemStage.close();
                                optionsStage.show();

                            });

                            //On changing the Item name select option
                            comboBox.setOnAction(acEv -> {
                                String selectedItem = (String) comboBox.getSelectionModel().getSelectedItem();
                                for (Item itemObj: itemsList) {
                                    if (selectedItem.equals(itemObj.getName())) {
                                        itemCodeField.setText(itemObj.getCode());
                                        itemPriceField.setText(String.valueOf(itemObj.getPrice()));
                                    }
                                }
                            });

                        });

                        //Displays the Selling Items Item Form
                        sellProductButton.setOnAction(ae -> {

                            sellItemStage = new Stage();
                            sellItemStage.setTitle("Sell Item");

                            sellItemGrid = new GridPane();
                            sellItemGrid.setAlignment(Pos.CENTER);
                            sellItemGrid.setHgap(10);
                            sellItemGrid.setVgap(10);
                            sellItemGrid.setPadding(new Insets(25, 25, 25, 25));

                            Text title = new Text("Sell Item");
                            title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
                            sellItemGrid.add(title, 0, 0, 2, 1);

                            itemNameLabel = new Label("Item Name:");
                            sellItemGrid.add(itemNameLabel, 0, 1);

                            comboBox = new ComboBox();
                            itemsList = getItemsFromInventory();
                            for (Item itemObj: itemsList) {
                                comboBox.getItems().add(itemObj.getName());
                            }
                            comboBox.getSelectionModel();
                            sellItemGrid.add(comboBox, 1, 1);

                            quantityLabel = new Label("Quantity");
                            sellItemGrid.add(quantityLabel, 0, 2);

                            quantityField = new TextField();
                            sellItemGrid.add(quantityField, 1, 2);

                            sellItemButton = new Button("Sell");
                            sellItemGrid.add(sellItemButton, 1, 3);

                            sellItemScene = new Scene(sellItemGrid, 500, 275);
                            sellItemStage.setScene(sellItemScene);
                            sellItemStage.show();

                            //Action event on click of Sell Button
                            sellItemButton.setOnAction(acev -> {
                                String itemName = (String) comboBox.getSelectionModel().getSelectedItem();
                                int quantity = Integer.parseInt(quantityField.getText());

                                addOrUpdateSalesData(itemName, quantity);
                                this.alert("Success", "Item sold Successfully!", AlertType.INFORMATION);
                                sellItemStage.close();
                                optionsStage.show();

                            });

                        });

                        //Prints Total Sales
                        printTotalSalesButton.setOnAction(ae -> {

                            totalSalesStage = new Stage();
                            totalSalesStage.setTitle("Total Sales");

                            printGrid = new GridPane();
                            printGrid.setAlignment(Pos.CENTER);
                            printGrid.setHgap(10);
                            printGrid.setVgap(10);
                            printGrid.setPadding(new Insets(25, 25, 25, 25));

                            Text title = new Text("Total Sales");
                            title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
                            printGrid.add(title, 0, 0, 2, 1);

                            totalSalesLabel = new Label("Total Sales:");
                            printGrid.add(totalSalesLabel, 0, 1);

                            double totalSalesValue = calculateTotalSales();

                            totalSalesField = new TextField(String.valueOf(totalSalesValue));
                            totalSalesField.setEditable(false);
                            printGrid.add(totalSalesField, 1, 1);

                            totalSalesScene = new Scene(printGrid, 500, 175);
                            totalSalesStage.setScene(totalSalesScene);
                            totalSalesStage.show();

                        });

                    } else {
                        this.alert("Error", "Invalid credentials!", AlertType.ERROR);
                    }
                } else {
                    this.alert("Error", "You haven't created an account. Please sign up!", AlertType.ERROR);
                }

            } else {
                this.alert("Error", "Please enter login details", AlertType.ERROR);
            }

        });

        //Displays the Sign Up Form
        signupLink.setOnAction(actionEvent -> {
            primaryStage.close();

            signUpStage = new Stage();
            signUpStage.setTitle("Sign Up");

            signupGrid = new GridPane();
            signupGrid.setAlignment(Pos.CENTER);
            signupGrid.setHgap(10);
            signupGrid.setVgap(10);
            signupGrid.setPadding(new Insets(25, 25, 25, 25));

            Text addTitle = new Text("Sign Up");
            addTitle.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
            signupGrid.add(addTitle, 0, 0, 2, 1);

            usernameLabel = new Label("User Name:");
            signupGrid.add(usernameLabel, 0, 1);

            usernameField = new TextField();
            signupGrid.add(usernameField, 1, 1);

            passwordLabel = new Label("Password");
            signupGrid.add(passwordLabel, 0, 2);

            passwordField = new PasswordField();
            signupGrid.add(passwordField, 1, 2);

            emailLabel = new Label("Email");
            signupGrid.add(emailLabel, 0, 3);

            emailField = new TextField();
            signupGrid.add(emailField, 1, 3);

            phoneLabel = new Label("Phone");
            signupGrid.add(phoneLabel, 0, 4);

            phoneField = new TextField();
            signupGrid.add(phoneField, 1, 4);

            signUpButton = new Button("Sign Up");
            signupGrid.add(signUpButton, 1, 5);

            signUpScene = new Scene(signupGrid, 500, 275);
            signUpStage.setScene(signUpScene);
            signUpStage.show();

            //Action event on click of Sign Up Button
            signUpButton.setOnAction(ae -> {
                String userName = usernameField.getText();
                String password = passwordField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                businessUser = new User(userName, password, email, phone);
                try {
                    writer = new FileWriter(userDataFilePath, true);
                    writer.write(userName+","+password+","+email+","+phone+"\n");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                this.alert("Success", "Signup Successful!", AlertType.INFORMATION);
                signUpStage.close();
                primaryStage.show();
            });

        });


        //Displays the Reset Password Form
        resetPwdLink.setOnAction(actionEvent -> {
            primaryStage.close();

            resetStage = new Stage();
            resetStage.setTitle("Reset Password");

            resetPwdGrid = new GridPane();
            resetPwdGrid.setAlignment(Pos.CENTER);
            resetPwdGrid.setHgap(10);
            resetPwdGrid.setVgap(10);
            resetPwdGrid.setPadding(new Insets(25, 25, 25, 25));

            Text addTitle = new Text("Reset Password");
            addTitle.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
            resetPwdGrid.add(addTitle, 0, 0, 2, 1);

            usernameLabel = new Label("User Name:");
            resetPwdGrid.add(usernameLabel, 0, 1);

            usernameField = new TextField();
            resetPwdGrid.add(usernameField, 1, 1);

            passwordLabel = new Label("New Password");
            resetPwdGrid.add(passwordLabel, 0, 2);

            passwordField = new PasswordField();
            resetPwdGrid.add(passwordField, 1, 2);

            resetPasswordButton = new Button("Reset Password");
            resetPwdGrid.add(resetPasswordButton, 1, 5);

            resetScene = new Scene(resetPwdGrid, 500, 275);
            resetStage.setScene(resetScene);
            resetStage.show();

            //Action event on click of Reset Password Button
            resetPasswordButton.setOnAction(ae -> {
                userName = usernameField.getText();
                password = passwordField.getText();
                if (!BLANK.equals(userName) && !BLANK.equals(password)) {
                    businessUser = readUserData(userName);
                    if (businessUser != null) {
                        updatePassword(businessUser, userName, password);
                        this.alert("Success", "Password reset Successful!", AlertType.INFORMATION);
                    } else {
                        this.alert("Error", "System doesn't have an account with username '"+userName+"' to reset password!", AlertType.ERROR);
                    }

                } else {
                    this.alert("Error", "Please provide new password!", AlertType.ERROR);
                }
                resetStage.close();
                primaryStage.show();
            });
        });

        loginScene = new Scene(loginGrid, 500, 275);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    //Calculates the total amount of Sales
    private double calculateTotalSales() {
        double result = 0;
        try {
            Map<String, Double> itemPriceMap = new HashMap<>();
            String line = "";

            br = new BufferedReader(new FileReader(itemsInventoryFilePath));
            while ((line = br.readLine()) != null) {
                String[] recordArr = line.split(",");
                itemPriceMap.put(recordArr[0], Double.parseDouble(recordArr[2]));
            }

            br = new BufferedReader(new FileReader(salesDataFilePath));
            while ((line = br.readLine()) != null) {
                String[] recordArr = line.split(",");
                result += (Integer.parseInt(recordArr[1]) * itemPriceMap.get(recordArr[0]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Adds or Updates items sold and its quantity in a SalesData file
    private void addOrUpdateSalesData(String itemName, int quantity) {
        FileWriter writer = null;
        try {
            Map<String, Integer> salesDataMap = getSalesData();
            if (salesDataMap.containsKey(itemName)) {
                salesDataMap.put(itemName, salesDataMap.get(itemName)+quantity);
            } else {
                salesDataMap.put(itemName, quantity);
            }
            writer = new FileWriter(salesDataFilePath, false);
            for (String key: salesDataMap.keySet()) {
                writer.write(key+","+ salesDataMap.get(key)+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Reads the data from Business data file and returns a map
    private Map<String, Integer> getSalesData() {
        Map<String, Integer> result = new HashMap<String, Integer>();
        try {
            br = new BufferedReader(new FileReader(salesDataFilePath));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] recordArr = line.split(",");
                result.put(recordArr[0], Integer.parseInt(recordArr[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //Updates the items in Inventory File
    private void updateInventory(Item itemToUpdate) {
        FileWriter writer = null;
        try {
            StringBuffer buffer = new StringBuffer();
            br = new BufferedReader(new FileReader(itemsInventoryFilePath));
            String line = "";
            String lineToModify = "";
            while ((line = br.readLine()) != null) {
                String[] recordArr = line.split(",");
                if (itemToUpdate.getName().equals(recordArr[0])) {
                    lineToModify = line;
                }
                buffer.append(line+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            String newLine = itemToUpdate.getName()+","+itemToUpdate.getCode()+","+itemToUpdate.getPrice();
            fileContents = fileContents.replaceAll(lineToModify, newLine);

            writer = new FileWriter(itemsInventoryFilePath, false);
            writer.write(fileContents);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Reads the data from Inventory data file and returns list of Items available
    private List<Item> getItemsFromInventory() {
        List<Item> items = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(itemsInventoryFilePath));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] itemArr = line.split(",");
                Item item = new Item(itemArr[0], itemArr[1], Double.parseDouble(itemArr[2]));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    //Updates the passwords and stores in the Customer file.
    private void updatePassword(User businessUserToUpdate, String userNameToUpdate, String passwordToUpdate) {
        FileWriter writer = null;
        try {
            StringBuffer buffer = new StringBuffer();
            br = new BufferedReader(new FileReader(userDataFilePath));
            String line = "";
            String lineToModify = "";
            while ((line = br.readLine()) != null) {
                String[] recordArr = line.split(",");
                if (userNameToUpdate.equals(recordArr[0])) {
                    lineToModify = line;
                }
                buffer.append(line+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            String newLine = userNameToUpdate+","+passwordToUpdate+","+businessUserToUpdate.getEmail()+","+businessUserToUpdate.getPhone();
            fileContents = fileContents.replaceAll(lineToModify, newLine);

            writer = new FileWriter(userDataFilePath, false);
            writer.write(fileContents);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Reads customer data from file.
    private User readUserData(String usrNm) {
        User user = null;
        try {
            br = new BufferedReader(new FileReader(userDataFilePath));
            while ((line = br.readLine()) != null) {
                String[] userDataArr = line.split(",");
                if (usrNm.equals(userDataArr[0])) {
                    user = new User(userDataArr[0], userDataArr[1], userDataArr[2], userDataArr[3]);
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void alert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //Execution starts from here
    public static void main(String[] args) {
        launch(args);
    }

}
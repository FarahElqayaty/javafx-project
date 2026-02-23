package com.example.beadsfinalproject;

import com.example.beadsproject.JewelryItem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
//s

public class HelloApplication extends Application {

    String jewelryType;
    RadioButton btnNecklace;
    RadioButton btnBracelet;
    RadioButton btnRing;
    Map<String,Map<String, List<String>>>CustomisedBeads=new HashMap<>();  // Hash map realted to Shape--> CHATGPT
    String selectedShape;
    String selectedColor;
    String shape;
    String color;
    String type;
    float  size;
    ComboBox<String> shapeBox;
    ComboBox<String> colorBox;
    ComboBox<String> typeBox;
    TextField itemSizeField;
    ToggleGroup toggleJewelryType;
    Button placeOrderButton = new Button("Place Order");
    double totalCost;
    double itemCost;

    ArrayList<JewelryItem> cart=new ArrayList<>();
    Label receiptStatusLabel = new Label();


    @Override
    public void start(Stage primaryStage) throws IOException {


//----------------- WINDOW NUMBER 1 -----------------------

        primaryStage.setX(800);
        primaryStage.setY(400);

        primaryStage.setWidth(500);
        primaryStage.setHeight(400);

        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(400);

        primaryStage.setTitle("Beads Store");



        Label labelMain = new Label("Welcome to Beads Store");
        labelMain.setStyle("-fx-font-weight: bold;-fx-font-size: 16");
        labelMain.setPadding(new Insets(10,10,10,10));


        Label label = new Label("Please enter your Information: ");
        label.setStyle("-fx-font-size: 14");
        label.setPadding(new Insets(10,10,10,10));


        Label nameLabel = new Label("      Name: ");        // Name Label
        nameLabel.setStyle("-fx-font-size: 14");
        TextField nameField = new TextField();           // name text
        Label phoneLabel = new Label("      Phone: ");      // Phone Label
        phoneLabel.setStyle("-fx-font-size: 14");
        TextField phoneField = new TextField();          //phone text

        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                phoneField.setText(oldValue);
            }
        });


        HBox nameHBox = new HBox(nameLabel, nameField);
        nameHBox.setSpacing(20);
        HBox phoneHBox = new HBox(phoneLabel, phoneField);
        phoneHBox.setSpacing(20);

        Button CntuButton1 = new Button("Continue");//---------> continue Button 1 will go to window 2
        CntuButton1.setStyle("-fx-font-size: 14");
        HBox cntuHbox1= new HBox(CntuButton1);
        cntuHbox1.setAlignment(Pos.CENTER);

//----------------- WINDOW NUMBER 2 -----------------------

        Label selectJewelrylbl=new Label("Select Jewelry Type:");
        selectJewelrylbl.setStyle("-fx-font-size: 16 ; -fx-font-weight: bold");
        selectJewelrylbl.setPadding(new Insets(10));

        btnNecklace = new RadioButton("Necklace");
        btnNecklace.setStyle("-fx-font-size: 14");
        btnNecklace.setPadding(new Insets(10));
        Image image1 = new Image("2.png");
        ImageView imageView1 = new ImageView(image1);
        HBox Hbpic1=new HBox(btnNecklace,imageView1);

        btnBracelet= new RadioButton("Bracelet");
        btnBracelet.setStyle("-fx-font-size: 14");
        btnBracelet.setPadding(new Insets(10));
        Image image2 = new Image("3.png");
        ImageView imageView2 = new ImageView(image2);
        HBox Hbpic2=new HBox(btnBracelet,imageView2);

        btnRing=new RadioButton("Ring");
        btnRing.setStyle("-fx-font-size: 14");
        btnRing.setPadding(new Insets(10));
        Image image3 = new Image("1.png");
        ImageView imageView3 = new ImageView(image3);
        HBox Hbpic3=new HBox(btnRing,imageView3);


        toggleJewelryType = new ToggleGroup();
        toggleJewelryType.getToggles().addAll(btnNecklace,btnBracelet,btnRing);


        Map<String,List<String>> heartColors=new HashMap<>();
        heartColors.put("Red", Arrays.asList("Glass", "Wood", "Plastic")); // Arrays.asList
        heartColors.put("pink", Arrays.asList("Wood", "Plastic"));

        Map<String,List<String>> circleColors=new HashMap<>();
        circleColors.put("Red", Arrays.asList("Glass", "Wood", "Plastic"));
        circleColors.put("pink", Arrays.asList("Wood", "Plastic"));
        circleColors.put("White", Arrays.asList("Glass", "Wood", "Plastic"));

        Map<String,List<String>> starColors=new HashMap<>();
        starColors.put("Yellow", Arrays.asList("Glass", "Wood", "Plastic"));
        starColors.put("Green", Arrays.asList("Wood", "Plastic"));

        CustomisedBeads.put("Heart", heartColors);
        CustomisedBeads.put("Circle", circleColors);
        CustomisedBeads.put("Star", starColors);


             //--------Customize Items---------
        Label customizeLabel= new Label("Customize Your Item ");
        customizeLabel.setStyle("-fx-font-size: 16 ; -fx-font-weight: bold");

        customizeLabel.setPadding(new Insets(10));

        Label shapeLabel= new Label(" Bead Shape: ");
        shapeLabel.setStyle("-fx-font-size: 14");
        shapeBox = new ComboBox<>();
        shapeBox.getItems().addAll("Heart", "Circle", "Star");
        HBox shapeHBox = new HBox(shapeLabel,shapeBox);
        shapeHBox.setSpacing(10);
        shapeHBox.setPadding(new Insets(10));

        Label colorLabel= new Label("Bead Color: ");
        colorLabel.setStyle("-fx-font-size: 14");
        colorBox = new ComboBox<>();
        HBox colorHBox = new HBox(colorLabel,colorBox);
        colorHBox.setSpacing(10);
        colorHBox.setPadding(new Insets(10));

        Label typeLabel= new Label("Bead Type: ");
        typeLabel.setStyle("-fx-font-size: 14");
        typeBox = new ComboBox<>();
        HBox typeHBox = new HBox(typeLabel,typeBox);
        typeHBox.setSpacing(10);
        typeHBox.setPadding(new Insets(10));


        shapeBox.setOnAction(e->

        {
            selectedShape =shapeBox.getValue();
            if(selectedShape != null)
            {
                Map<String,List<String>> beadColors=CustomisedBeads.get(selectedShape);
                colorBox.getItems().clear();
                typeBox.getItems().clear();
                colorBox.getItems().addAll(beadColors.keySet());

            }});
        colorBox.setOnAction(e->
        {
            selectedColor = colorBox.getValue();
            if(selectedShape != null && selectedColor != null )
            {
                Map<String,List<String>> beadColors=CustomisedBeads.get(selectedShape);
                List<String> types = beadColors.get(selectedColor);
                typeBox.getItems().clear();
                if (types != null) {
                    typeBox.getItems().addAll(types);
                }
            }});


             // -----size Labels----

        Label sizeLabel= new Label("Choose Item Size: ");
        sizeLabel.setStyle("-fx-font-size: 16 ; -fx-font-weight: bold");
        sizeLabel.setPadding(new Insets(10));

        Label sizeLabel1= new Label(" Size(1cm-10cm) = $ 5.00 ");
        sizeLabel1.setStyle("-fx-font-size: 14");
        sizeLabel1.setPadding(new Insets(10));

        Label sizeLabel2= new Label(" Size(11cm-30cm) = $ 25.00 ");
        sizeLabel2.setStyle("-fx-font-size: 14");
        sizeLabel2.setPadding(new Insets(10));

        Label sizeLabel3= new Label(" Size(31cm- above cm) = $ 45.00 ");
        sizeLabel3.setStyle("-fx-font-size: 14");
        sizeLabel3.setPadding(new Insets(10));
        sizeLabel3.setLineSpacing(10);

        Label itemSizeLabel= new Label("Item Size: ");
        itemSizeLabel.setStyle("-fx-font-size: 16");
        //itemSizeLabel.setPadding(new Insets(10));
        itemSizeField = new TextField();


        itemSizeField.textProperty().addListener((observable, oldValue, newValue) -> { //---Stack overflow
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                itemSizeField.setText(oldValue);
            }
        });
        HBox sizeHbox = new HBox(itemSizeLabel,itemSizeField);
        sizeHbox.setPadding(new Insets(10));


        //---- add to cart button---and---continue 2 Button------

        Button addToCartButton= new Button("Add To Cart");
        addToCartButton.setStyle("-fx-font-size: 14");
        addToCartButton.setOnAction(e -> addCart());
        Button CntuButton2= new Button("Continue");//--->continue button 2 will go to window 3
        CntuButton2.setStyle("-fx-font-size: 14");

        HBox hb2= new HBox(addToCartButton,CntuButton2);
        hb2.setAlignment(Pos.CENTER);
        hb2.setSpacing(20);


//---------------------------WINDOW 3----------------------------------------------------

        //------labels------

       Label addressLabel= new Label("Enter Address Information ");
       addressLabel.setStyle("-fx-font-size: 16");
        addressLabel.setPadding(new Insets(10));

        Label streetLabel= new Label("Street: ");
        streetLabel.setStyle("-fx-font-size: 14");

        Label cityLabel= new Label("City: ");
        cityLabel.setStyle("-fx-font-size: 14");

        Label stateLabel= new Label("State: ");
        stateLabel.setStyle("-fx-font-size: 14");

        Label zipLabel= new Label("Zip Code: ");
        zipLabel.setStyle("-fx-font-size: 14");

        //----Text Fields----

        TextField streetField= new TextField();
        HBox streetHBox= new HBox(streetLabel,streetField);
        streetHBox.setPadding(new Insets(10));
        streetHBox.setSpacing(10);


        TextField cityField= new TextField();
        HBox cityHBox = new HBox(cityLabel,cityField);
        cityHBox.setPadding(new Insets(10));
        cityHBox.setSpacing(10);

        TextField stateField= new TextField();
        HBox stateHBox= new HBox(stateLabel,stateField);
        stateHBox.setPadding(new Insets(10));
        stateHBox.setSpacing(10);

        TextField zipField= new TextField();
        HBox zipHBox= new HBox(zipLabel,zipField);
        zipHBox.setPadding(new Insets(10));
        zipHBox.setSpacing(10);


       Button viewCart= new Button("View Cart");//----> view cart button
       viewCart.setStyle("-fx-font-size: 14");
       HBox viewCartHBox= new HBox(viewCart);
       viewCartHBox.setAlignment(Pos.CENTER);


 //---------------------------VBOX 4----------------------------------------------------
        VBox vbox4 = new VBox();
        vbox4.setStyle("-fx-background-color: rgba(212,204,243,0.88);");
        vbox4.setSpacing(10);
        vbox4.setPadding(new Insets(10));
        Scene scene4 = new Scene(vbox4);
        Stage stage4 = new Stage();
        stage4.setWidth(600);
        stage4.setHeight(500);
        stage4.setTitle("Beads Store");

//-------------------------Window 4---------View Cart Button Action -----------------------------------------------
        viewCart.setOnAction(e -> {
            vbox4.getChildren().clear(); // Clear old data if opened again



            Label title = new Label("Cart Summary");
            title.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
            vbox4.getChildren().add(title);

             totalCost = 0.0;

            for (JewelryItem item : cart) {
                itemCost = item.calculatePrice(); // I got calculate pric (method) from beads class
                totalCost += itemCost;

                Label itemLabel = new Label( String.format("Item: %s | Shape: %s | Color: %s | Type: %s | Size: %.1f cm | Cost: $%.2f",      // string format way
                        jewelryType, item.getShape(), item.getColor(), item.getType(), item.getSize(), itemCost));

                itemLabel.setStyle("-fx-font-size: 14");
                vbox4.getChildren().add(itemLabel);
            }

            Label totalLabel = new Label(String.format("Total Cost: $%.2f", totalCost)); // I used string format way here too
            totalLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
            totalLabel.setPadding(new Insets(10, 0, 10, 0));
            vbox4.getChildren().add(totalLabel);


            Label addressDisplay = new Label("Shipping Information:");
            addressDisplay.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
            vbox4.getChildren().add(addressDisplay);

            Label addressDetail = new Label(
                    "Name: "+
                     nameField.getText() + "\n" +
                            "Phone Number: "+phoneField.getText() + "\n\n" +"Address Information: "+"\n" +
                             streetField.getText() + " , " +
                             cityField.getText() + " , " +
                            stateField.getText() + " , " +
                           zipField.getText()
            );
            addressDetail.setStyle("-fx-font-size: 14;");
            vbox4.getChildren().add(addressDetail);



            if(streetField.getText().isEmpty() || cityField.getText().isEmpty() || stateField.getText().isEmpty() || zipField.getText().isEmpty())
            {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields");
               return;
            }


            placeOrderButton.setStyle("-fx-font-size: 14");
            HBox placeOrderHbox= new HBox(placeOrderButton);
            placeOrderHbox.setAlignment(Pos.CENTER);
            vbox4.getChildren().add(placeOrderHbox);

            receiptStatusLabel.setStyle("-fx-font-size: 14; -fx-text-fill: green;");
            vbox4.getChildren().add(receiptStatusLabel);

           stage4.setScene(scene4);
            stage4.show();
        });
//-----------------------Window 5---------------------------------------------------

        Label placeOrderLabel = new Label("Your Order Is Placed Successfully !");
        placeOrderLabel.setPadding(new Insets(10,10,10,10));
        placeOrderLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        HBox placeOrderHBox= new HBox(placeOrderLabel);
        placeOrderHBox.setAlignment(Pos.CENTER);

        Button printReceiptButton = new Button("Print Receipt"); //---> print receipt button to a file
        printReceiptButton.setStyle("-fx-font-size: 14");
        HBox printReciptHBox= new HBox(printReceiptButton);
        printReciptHBox.setAlignment(Pos.CENTER);


//----------------------VBOX 5----------------------------------------------------

        VBox vbox5 = new VBox(placeOrderHBox, printReciptHBox);
        vbox5.setStyle("-fx-background-color: rgba(212,204,243,0.88);");
        vbox5.setSpacing(10);
        vbox5.setPadding(new Insets(10));
        Scene scene5= new Scene(vbox5);
        Stage stage5 = new Stage();
        stage5.setWidth(400);
        stage5.setHeight(200);
        stage5.setTitle("Beads Store");


//------------------Place Order Button-------------------------------

     placeOrderButton.setOnAction(e -> {
         stage5.setScene(scene5);
         stage5.show();
     }) ;

//-----------------Print Receipt to a file  set on Action---------------------

              printReceiptButton.setOnAction(e -> {

          String receiptFileName = "receipt.txt";

          try (// in used try and catch here for files

                  BufferedWriter filewriter = new BufferedWriter(new FileWriter(receiptFileName)))// created an object for bufferedwriter to put the receipt in it
          {
              filewriter.write("\n\n********** BEADS STORE RECEIPT **********\n\n");
              filewriter.write("Customer Name: " + nameField.getText() + "\n");
              filewriter.write("Phone: " + phoneField.getText() + "\n");
              filewriter.write("Address: " + streetField.getText() + ", " + cityField.getText() + ", "
                      + stateField.getText() + ", " + zipField.getText() + "\n\n");

              filewriter.write("----- Items -----\n");

              for (JewelryItem item : cart) // got the items from the array list that i already did
              {
                  double cost = item.calculatePrice();
                  filewriter.write(String.format("Item: %s | Shape: %s | Color: %s | Type: %s | Size: %.1f cm | Cost: $%.2f\n",
                          jewelryType, item.getShape(), item.getColor(), item.getType(), item.getSize(), cost));
              }

              filewriter.write(String.format("Total Cost: $%.2f", totalCost));
              filewriter.write("\nThank you for shopping with us!");

              receiptStatusLabel.setText("Receipt printed successfully to: " + receiptFileName);
              showAlert(Alert.AlertType.INFORMATION, "Success", "Receipt printed to: " + receiptFileName);

          } catch (IOException ex) {
              showAlert(Alert.AlertType.ERROR, "Error", "Failed to print receipt.");
              ex.printStackTrace();
          }
      });



//======================== I Used this method First ===========================================
//
//        printReceiptButton.setOnAction(e -> {
//            StringBuilder receiptContent = new StringBuilder();
//
//            receiptContent.append("\n\n********** BEADS STORE RECEIPT **********\n\n");
//            receiptContent.append("Customer Name: ").append(nameField.getText()).append("\n");
//            receiptContent.append("Phone: ").append(phoneField.getText()).append("\n");
//            receiptContent.append("Address: ").append(streetField.getText()).append(", ")
//                    .append(cityField.getText()).append(", ").append(stateField.getText()).append(", ")
//                    .append(zipField.getText()).append("\n\n");
//
//            receiptContent.append("----- Items -----\n");
//
//            for (JewelryItem item : cart) {
//                double cost = item.calculatePrice();
//                receiptContent.append(String.format("Item: %s | Shape: %s | Color: %s | Type: %s | Size: %.1f cm | Cost: $%.2f\n",
//                        jewelryType, item.getShape(), item.getColor(), item.getType(), item.getSize(), cost));
//            }
//
//            receiptContent.append(String.format("\nTotal Cost: $%.2f\n", totalCost));
//            receiptContent.append("\nThank you for shopping with us!");
//
//            Receiptsaver.saveReceiptToFile(receiptContent.toString());
//
//            receiptStatusLabel.setText("Receipt saved successfully.");
//            showAlert(Alert.AlertType.INFORMATION, "Success", "Receipt saved to Desktop > BeadsReceipts");
//        });


//------------------VB0x 3--------------------------------------------------------

        VBox vbox3 = new VBox(addressLabel,streetHBox,cityHBox,stateHBox,zipHBox,viewCartHBox);
        vbox3.setStyle("-fx-background-color: rgba(212,204,243,0.88);");
        vbox3.setSpacing(10);
        vbox3.setPadding(new Insets(10));
        Scene scene3 = new Scene(vbox3);
        Stage stage3 = new Stage();
        stage3.setWidth(500);
        stage3.setHeight(500);
        stage3.setTitle("Beads Store");


//----------------------------------------------------------------------
        CntuButton2.setOnAction(e->{//---> when CntuButton2 pressed go to Window 3

            if(cart.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cart is Empty");
                alert.setHeaderText(null);
                alert.setContentText("Please add at least one item to te cart before proceeding.");
                alert.showAndWait();
            } else{

            stage3.setScene(scene3);
            stage3.show();}
        });

//---------------VBox 2-----------------------------------------------------------------------
        VBox vbox2 = new VBox(selectJewelrylbl,Hbpic1,Hbpic2,Hbpic3,customizeLabel,shapeHBox,colorHBox,typeHBox,sizeLabel,sizeLabel1
                ,sizeLabel2,sizeLabel3,sizeHbox,hb2);
        vbox2.setStyle("-fx-background-color: rgba(212,204,243,0.88);");
        vbox2.setSpacing(5);
        Scene scene2 = new Scene(vbox2); // create scene
        Stage stage2 = new Stage();// create stage
        stage2.setWidth(500);
        stage2.setHeight(800);
        stage2.setTitle("Beads Store");


        //------------------continue Botton 1------------------------------------------------------

        //------I used Show Alert method-----
        CntuButton1.setOnAction(e -> {
            if (nameField.getText().isEmpty() || phoneField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Missing Information", "Please enter both Name and Phone number.");
            } else {
                stage2.setScene(scene2);
                stage2.show();
            }
        });


//----------------VBox 1---------------------------------------------------------------------
        VBox vbox1=new VBox(labelMain,label,nameHBox,phoneHBox, cntuHbox1); // create one vBox for first Window
        vbox1.setSpacing(20);
        vbox1.setStyle("-fx-background-image: url('beads.jpeg');");
        Scene scene1=new Scene(vbox1);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    //-------- end start() method-----------------------------------------------------------------------------



//------------------------------------------FUNCTIONS---------------------------------------------
    //  _________________________________________________________________________________________


//----------add Cart function()-----
    public void addCart()
    {

        if(toggleJewelryType.getSelectedToggle() == null || shapeBox.getValue()==null|| colorBox.getValue()==null|| typeBox.getValue()==null ||
        itemSizeField.getText().isEmpty())
        {
            showAlert(Alert.AlertType.WARNING,"Incomplete Selection","Please select an Shape,Color,Type,Size!");
       return;
        }

        String sizeText=itemSizeField.getText();
        if(sizeText==null ||sizeText.isEmpty() )
        {
            showAlert(Alert.AlertType.ERROR, "Invalid Size","Please enter a valid size!");
            return;
        }

        try{
            size=Float.parseFloat(itemSizeField.getText());
            if(size<=0 || size<1)
            {
                showAlert(Alert.AlertType.ERROR, "Invalid Size","Size must be greater than or equal 1 !");
                return;
            }

        }catch(NumberFormatException e){
            showAlert(Alert.AlertType.ERROR, "Invalid Size","Please enter a valid size!");
        }


        if(btnNecklace.isSelected())
        {
            jewelryType="Necklace";
        }
        else if (btnBracelet.isSelected())
        {
            jewelryType="Bracelet";
        }
        else if(btnRing.isSelected())
        {
            jewelryType="Ring";
        }

        shape=shapeBox.getValue();
        color=colorBox.getValue();
        type=typeBox.getValue();
        size=Float.parseFloat(itemSizeField.getText());


        cart.add(new JewelryItem(shape,color, type, size));// array list to carry items

        shapeBox.getSelectionModel().clearSelection();
        colorBox.getSelectionModel().clearSelection();
        typeBox.getSelectionModel().clearSelection();
        itemSizeField.clear();
        toggleJewelryType.selectToggle(null);


    }// end of cart function


//----------show Alert method----

    public  void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }// end of Alet fuction





    public static void main(String[] args) {
        launch();
    }
}//---------- end Class




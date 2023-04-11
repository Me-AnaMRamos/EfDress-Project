package com.example.efdressfx;

import javafx.application.Application;
// import javafx.fxml.FXMLLoader;

import javafx.geometry.HPos;
import javafx.stage.Stage;

//me
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.Group;

import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

//import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
public class InterfaceApplication extends Application {

    public static String[][] itemCat= {{"Coat", "Jacket","Vest","Shirt","T-shirt","Top"},{"Trouser",
            "Skirt",
            "Short",
            "Jeans",
            "Pants"},{"Dress",
            "Overall",
            "Swimsuit",
            "Jumpsuit"},{"Boots",
            "Sneakers",
            "Sandals",
            "Other dress Shoes"},{"Cap",
            "Beanies",
            "Hat"}};
    public static ArrayList<Item> items = new ArrayList<Item>();

public void showItem(Item item){

    System.out.println();
    System.out.println("Name: "+item.getNombre());
    System.out.println("Type: "+item.getTipo());
    System.out.println("Ocassion:");
    for(int i=0;i<item.getOcasion().length;i++){
        if(item.getOcasion()[i]!=null){
            System.out.print(item.getOcasion()[i]+ " | ");
        }
    }
    System.out.println();
    System.out.println("Material: "+item.getMaterial());
    System.out.println("Color Pallette: " +item.getPaletaColores().values());
    System.out.println("Image location: "+item.getUbicacion());
}
    public boolean isNameInItems(String name){
        for(Item it:items){
            if(it.getNombre().equals(name)){
                System.out.println("\nItem found!");
                showItem(it);
                return true;
            }
        }
        return false;

    }
    public void searchItemF(Stage stage, VBox root, Scene MainScene){
         root.getChildren().clear();
        Button searchItemB= new Button("search"); //Add Item function
        ComboBox searchCrit = new ComboBox();
        Label labelSearch=new Label("Search by: ");
        searchCrit.getItems().addAll("Nombre", "Número");
        TextField searchCritTF = new TextField();
        Button seeItemsB= new Button("See Item");
        //
       //Button searchItemB= new Button("search"); //Add Item function
        seeItemsB.setOnAction(e -> {
                    showItems();
                }
        );

        root.getChildren().addAll(labelSearch,searchCrit,searchCritTF,searchItemB);

        searchItemB.setOnAction(e -> {
            //Search item
            String searchValue= searchCritTF.getText();
            int searchCritInt=searchCrit.getSelectionModel().getSelectedIndex();

            if(searchCritInt==0){
                    if(!isNameInItems(searchValue)){
                    System.out.println();
                    System.out.println("\nItem not found");

            }
                stage.setScene(MainScene);

            } else if (searchCritInt==1){
                int searchedInt = Integer.parseInt(searchValue);

                if(items.size()>searchedInt){
                    showItem(items.get(searchedInt));
                }
else {System.out.println("\nInvalid Number");}
                stage.setScene(MainScene);

            }



        });

    }
public void showItems(){
int itemcounter=0;
    for(Item it:items){
        itemcounter++;
        System.out.println("\n\nItem # "+ itemcounter);
        showItem(it);

    }
}


    public void setFormLay(int ind1,int ind2,FlowPane pane,Stage stage, Scene nextScene){
        pane.setVgap(15);
        pane.setHgap(10);
        pane.setPadding(new Insets(30,15,15,30));

        //pane.setColumnHalignment(HPos.CENTER);
        Label labelName=new Label("Name: *");
        TextField nameT = new TextField();

        Label labelItem=new Label("Type: "+itemCat[ind1][ind2]);
        Label labelColors=new Label("Select "+itemCat[ind1][ind2]+ " colors: ");
        ColorPicker cp1 = new ColorPicker(Color.BLUE);
        ColorPicker cp2 = new ColorPicker(Color.RED);
        ColorPicker cp3 = new ColorPicker(Color.BLACK);
        ComboBox materialC = new ComboBox();
        Label labelOc=new Label("Occasion: ");
        CheckBox oc1 = new CheckBox("Formal");
        CheckBox oc2 = new CheckBox("Casual");
        CheckBox oc3 = new CheckBox("Sport");
        CheckBox oc4 = new CheckBox("Beach/ Pool");
        Label labelMat=new Label("Material: ");
        materialC.getItems().addAll("Leather", "Cotton", "Denim", "Silk", "Satin", "Polyester", "Other");
        Label labelLoc=new Label("Location: ");
        TextField locationT = new TextField();
        Button saveNewIt = new Button("Save");
        Button cancelNewIt = new Button("Cancel");




        pane.getChildren().addAll(labelItem,labelColors,cp1,cp2,cp3,labelOc,oc1,oc2,oc3,oc4,labelMat,materialC,labelLoc,locationT,labelName,nameT);

        pane.getChildren().addAll(saveNewIt,cancelNewIt);
        saveNewIt.setOnAction(action -> {
            Color color1=cp1.getValue();
            String sCol1=color1.toString();
            Color color2=cp2.getValue();
            String sCol2=color2.toString();
            Color color3=cp3.getValue();
            String sCol3=color3.toString();
            String type=itemCat[ind1][ind2];
            String name=nameT.getText();
            String material=(String) materialC.getValue();
            String location=locationT.getText();
            String[] ocasions= new String[4];
            if(oc1.isSelected()){ System.out.println(ocasions.length);ocasions[0] =oc1.getText();}
            if(oc2.isSelected() ){ ocasions[1] =oc2.getText();}
            if(oc3.isSelected() ){ocasions[2] =oc3.getText();}
            if(oc4.isSelected() ){ ocasions[3] =oc4.getText();}
            HashMap<String, Color> paletaCol= new HashMap<String,Color>();
            paletaCol.put(sCol1,color1);
            paletaCol.put(sCol2,color2);
            paletaCol.put(sCol3,color3);
items.add(new Item(name, material,paletaCol,ocasions,type,location));
            stage.setScene(nextScene);
            pane.getChildren().clear();

        });


        cancelNewIt.setOnAction(action -> {
            stage.setScene(nextScene);
            pane.getChildren().clear();

        });


    }
    @Override

    public void start(Stage stage) throws IOException {
        /*
        * Scene searchCrite
        *
        * */
        VBox searchCLayout= new VBox(20);

        Scene searchScene= new Scene(searchCLayout,500,400);

        /*
        * new Item form Scene
        * Asks for item info depending on the previous selected options
        * FlowPane layout
        * */

        FlowPane paneForm = new FlowPane();
        /*
        //root.setVgap(3);
        //root.setHgap(5);
        root.setPrefWrapLength(250);
        root.getChildren().add(new Button("Start"));
        root.getChildren().add(new Button("Stop"));
        root.getChildren().add(new Button("Reset"));
        */
        Scene formScene = new Scene(paneForm,500,400);





    /*
        // new item Scene 2 (select item type)
    */


        Scene newItScene2= new Scene(new Group(), 500, 400);

        ComboBox addItemC2 = new ComboBox();


        Button prevSceneB= new Button("Go back");

        GridPane grid2 = new GridPane();
        grid2.setVgap(4);
        grid2.setHgap(10);
        grid2.setPadding(new Insets(5, 5, 5, 5));
        grid2.add(new Label("What kind of item is it?"), 0, 0);
        grid2.add(addItemC2, 1, 0);
        grid2.add(prevSceneB, 1, 1);




        //Scene graph
        Group root2 = (Group)newItScene2.getRoot();
        root2.getChildren().add(grid2);

        /*
        // New item Scene 1 (select category of the item: body part)
        */

        Scene newItScene1= new Scene(new Group(), 500, 400);

        ComboBox addItemC1 = new ComboBox();  // Dropdown menu for selecting item category

        addItemC1.getItems().addAll(
                "Upper Body (Tops, coats/jackets, etc.)",
                "Lower Body (Trousers, skirts, etc.)",
                "Upper & lower body (dress, jumpsuit, etc.)",
                "Footwear",
                "Headwear"
          //     ,"Accesory"
        );

        addItemC1.setOnAction((event) -> { //When selected an option, goes to new item Scene 2

            int selectedIndex1 = addItemC1.getSelectionModel().getSelectedIndex();
            addItemC2.getItems().clear();
            Object selectedItem = addItemC1.getSelectionModel().getSelectedItem();
            System.out.println("Selection made: [" + selectedIndex1 + "] " + selectedItem);
            System.out.println("   ComboBox.getValue(): " + addItemC1.getValue());
            switch (selectedIndex1){
                case 0:
                    for(int i=0;i<6;i++){

                        addItemC2.getItems().add(itemCat[0][i]);
                    }

            break;

            case 1:
                for(int i=0;i<5;i++){

                    addItemC2.getItems().add(itemCat[1][i]);
                }
                    break;


                case 2:
                    for(int i=0;i<4;i++){

                        addItemC2.getItems().add(itemCat[2][i]);
                    }
                    break;

                case 3:
                    for(int i=0;i<4;i++){

                        addItemC2.getItems().add(itemCat[3][i]);
                    }
                    break;
                case 4:
                    for(int i=0;i<3;i++){

                        addItemC2.getItems().add(itemCat[4][i]);
                    }
                    break;
            }

           stage.setScene(newItScene2);
       });



       // When pressed in new item Scene 2, redirects to this scene (new item scene 1)

        EventHandler<ActionEvent> gobackEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                addItemC2.getItems().clear();
                stage.setScene(newItScene1);

            }
        };
        prevSceneB.setOnAction(gobackEvent);

        //Display of scene 1
        GridPane grid1 = new GridPane();
        grid1.setVgap(4);
        grid1.setHgap(10);
        grid1.setPadding(new Insets(5, 5, 5, 5));
        grid1.add(new Label("What kind of item is it?"), 0, 0);
        grid1.add(addItemC1, 1, 0);
        Group root1 = (Group)newItScene1.getRoot();
        root1.getChildren().add(grid1);

        /*
        // .....
        // Main Scene
        // Main User Interface
        // Functionalities: Add new item, see list of items, search items based on criteria.
        */

        Button addItemB= new Button("Add Item"); //Add Item function
        addItemB.setOnAction(e -> {stage.setScene(newItScene1);
            //addItemC2.getItems().clear();

        });

        Button seeItemsB= new Button("See Item");
        seeItemsB.setOnAction(e -> {
                    showItems();
                }
                );

        Button searchB= new Button("Search");

           // Scene graph
        VBox mainLayout= new VBox(20);
        mainLayout.getChildren().addAll(addItemB,seeItemsB,searchB);
        Scene mainScene= new Scene(mainLayout,500,400);
        searchB.setOnAction(e -> {
                    stage.setScene(searchScene);
                    searchItemF(stage, searchCLayout, mainScene );
                }
        );
        addItemC2.setOnAction((event) -> { //When selected an option, goes to new form Scene
            int selectedIndex1= addItemC1.getSelectionModel().getSelectedIndex();
            int selectedIndex2 = addItemC2.getSelectionModel().getSelectedIndex();
            Object selectedItem2 = addItemC2.getSelectionModel().getSelectedItem();

            stage.setScene(formScene);

            setFormLay(selectedIndex1,selectedIndex2,paneForm,stage, mainScene);


        });
        /*
        //Stage definition (only one stage)
        */
        stage.setTitle("EfDress");
        stage.setScene(mainScene);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}
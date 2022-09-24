/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package namemaintenance;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;



/**
 *
 * @author thoma
 */
public class FXMLDocumentController implements Initializable {
    
   @FXML
    private Button resetBtn;

    @FXML
    private Button loadBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private Button selectBtn;

    @FXML
    private TextField nameEntryField;

    @FXML
    private ListView<String> nameDisplayBox;
   

    @FXML
    private Button addBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameDisplayBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    } 

    @FXML
    void handleLoad(ActionEvent event) {
        
        try(Scanner input = new Scanner(Paths.get("names.txt"))){
            ArrayList<String> list = new ArrayList<String>(); 
            while (input.hasNext()){
                nameDisplayBox.getItems().add(input.next());
            }
    }
        catch (IOException | NoSuchElementException | IllegalStateException e){
            e.printStackTrace();
        }
        
        
    }

    @FXML
    void handleAddName(ActionEvent event) {
        nameDisplayBox.getItems().add(nameEntryField.getText());

    }

    @FXML
    void handleRemoveName(ActionEvent event) {
        final int selectedName = nameDisplayBox.getSelectionModel().getSelectedIndex();
        if (selectedName != -1){
            String itemToRemove = nameDisplayBox.getSelectionModel().getSelectedItem();
            
            final int newSelectedName = 
                    (selectedName == nameDisplayBox.getItems().size()-1)
                        ? selectedName -1
                        : selectedName;
            
            nameDisplayBox.getItems().remove(selectedName);
            nameDisplayBox.getSelectionModel().select(newSelectedName);
        }

    }

    @FXML
    void handleSelectName(ActionEvent event) {
        
        String selectedItem = nameDisplayBox.getSelectionModel().getSelectedItem();
        Dialog d = new Alert(Alert.AlertType.INFORMATION,selectedItem);
        d.show();

    }

    @FXML
    void resetNameList(ActionEvent event) {
        nameDisplayBox.getItems().clear();
        try(Scanner input = new Scanner(Paths.get("names.txt"))){
            ArrayList<String> list = new ArrayList<String>(); 
            while (input.hasNext()){
                nameDisplayBox.getItems().add(input.next());
            }
    }
        catch (IOException | NoSuchElementException | IllegalStateException e){
            e.printStackTrace();
        }

    }

    @FXML
    void handleExitProgram(ActionEvent event) {
        Platform.exit();

    }
    
       
    
}

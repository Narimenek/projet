package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class OptionVoitureController {
    @FXML
    private Parent fxml;

    @FXML
    private AnchorPane root;

    @FXML
    private Button addButton;

    @FXML
    private Button rechercheBoutton;

    @FXML
    private Button deleteButton;

  

   

    @FXML
    void addVoiture(ActionEvent event) {
    	try {
    
        fxml = FXMLLoader.load(getClass().getResource("/interfaces/Voiture.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxml);
    } catch (IOException e) {
        e.printStackTrace();
    }}

    @FXML
    void deleteVoiture(ActionEvent event) {
    	 try {
             fxml = FXMLLoader.load(getClass().getResource("/interfaces/suppression.fxml"));
             root.getChildren().removeAll();
             root.getChildren().setAll(fxml);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    @FXML
    void rechercheVoiture(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/RechercheClient.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent loadedFXML = loader.load();
            root.getChildren().setAll(loadedFXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
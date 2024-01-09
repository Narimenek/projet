package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;



public class LocataireController {
	
	@FXML
    private Parent fxml;

    @FXML
    private AnchorPane root;




    @FXML
    private Button addbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button rechercheButton;

    @FXML
    void addClient(ActionEvent event) {
    	try {
    	    
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/addAdmin.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deleteClient(ActionEvent event) {
    	try {
    	    
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/DeleteAdmin.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void rechercheClient(ActionEvent event) {
    	try {
    	    
            fxml = FXMLLoader.load(getClass().getResource("/interfaces/RechercheAdmin.fxml"));
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


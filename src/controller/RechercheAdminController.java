package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class RechercheAdminController {
	@FXML
    private Parent fxml;

    @FXML
    private AnchorPane root;
    
    
    @FXML
    private void retourner(ActionEvent event) {
    	   try {
               fxml = FXMLLoader.load(getClass().getResource("/interfaces/Locataire.fxml"));
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

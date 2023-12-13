module Projet1 {
	requires javafx.controls;
	
		
		requires javafx.fxml;
		requires javafx.base;
		
	    requires javafx.graphics;
		
		
	
		requires java.sql;
	    exports interfaces;
		exports images;
		exports controller;
		opens controller to javafx.graphics, javafx.fxml;
		opens application to javafx.graphics, javafx.fxml;
		opens images to javafx.graphics, javafx.fxml;
		opens interfaces to javafx.graphics, javafx.fxml;
	
	

	}

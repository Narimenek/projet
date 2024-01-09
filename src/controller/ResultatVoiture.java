package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ResultatVoiture {
	
	    private String marque;
	    private String couleur;
	    private double prix;
	    private byte[] imageBytes;

	    public ResultatVoiture(String marque, String couleur, double prix, byte[] imageBytes) {
	        this.marque = marque;
	        this.couleur = couleur;
	        this.prix = prix;
	        this.imageBytes = imageBytes;
	    }

	    // Ajoutez les getters et les setters pour le nouveau champ imageBytes
	    public byte[] getImageBytes() {
	        return imageBytes;
	    }

	    public void setImageBytes(byte[] imageBytes) {
	        this.imageBytes = imageBytes;
	    }

		public String getMarque() {
			return marque;
		}

		public void setMarque(String marque) {
			this.marque = marque;
		}

		public String getCouleur() {
			return couleur;
		}

		public void setCouleur(String couleur) {
			this.couleur = couleur;
		}

		public double getPrix() {
			return prix;
		}

		public void setPrix(double prix) {
			this.prix = prix;
		}
}


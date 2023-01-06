package main.java.joueur;

import java.util.Scanner;

public interface IJoueur {

	//Renvoi une coordonnee 
	String jouerCoup(int taille, Scanner sc);
	
	String getNom();
}

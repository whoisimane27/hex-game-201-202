package main.java.joueur;

import java.util.Random;
import java.util.Scanner;


public class JoueurMachine implements IJoueur {

	private String nom;
	
	public JoueurMachine(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public String jouerCoup(int taille, Scanner sc) {
		Random rand = new Random();
		char col = (char)(rand.nextInt(taille) + 'A');
		Random rand2 = new Random();
		int lig= rand2.nextInt(taille) + 1;
		String coord = col+""+lig;
		return coord;
	}

}

package main.java.joueur;

import java.util.Scanner;


public class JoueurHumain implements IJoueur {
	
	private String nom;
	
	public JoueurHumain(String nom) {
		this.nom = nom;
	}

	@Override
	public String jouerCoup(int taille, Scanner sc) {
		String coup;
		coup= sc.next();
		return coup;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

}

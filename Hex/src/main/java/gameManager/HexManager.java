package main.java.gameManager;

import java.util.ArrayList;
import java.util.Scanner;

import main.java.hex.IPlateau;
import main.java.joueur.IJoueur;

public class HexManager {
	
	private IJoueur[] joueurs;
	private IPlateau plateau;
	private int joueur = 0;
	private ArrayList<String> coupsJoues;
	private Scanner sc;
	
	
	public HexManager(IJoueur j1, IJoueur j2, IPlateau p, Scanner sc) {
		joueurs = new IJoueur[2];
		joueurs[0] = j1;
		joueurs[1]= j2;
		this.plateau = p;
		coupsJoues = new ArrayList<>();
		this.sc = sc;
	}
	
	public void jouerTour() {
		System.out.println( joueurs[joueur].getNom() + " a toi de jouer  !");
		String coord;
		while(true) {
			coord = joueurs[joueur].jouerCoup(plateau.taille(), sc);
			if(plateau.estValide(coord) && !coupsJoues.contains(coord)) break;
			else {
				if(!plateau.estValide(coord)) System.out.println("Ce coup est invalide !" );
				if(coupsJoues.contains(coord)) System.out.println("Ce coup a deja ete jouee!"  );
			}
		}
		plateau.jouer(coord);
		coupsJoues.add(coord);
		System.out.println( joueurs[joueur].getNom() + " a jouer le coup : " +coord);
		suivant();
	}
	
	private void suivant() {
		joueur = (joueur +1 )%2;
	}
	
	public int jouerPartie() {
		int statutPartie = 0;
		while (true) {
			System.out.println("This is the game : ");
			System.out.println(plateau.toString());
			// joueur 1
			jouerTour();
			statutPartie = plateau.VerifPartie();
			if(statutPartie !=0) {
				break;
			}
			// joueur 2
			jouerTour();
			statutPartie = plateau.VerifPartie();
			if(statutPartie !=0) {
				break;
			}
		}
		return statutPartie;

	}
	
	public String GameOver (int statutPartie) {
		String str = "\nLa partie est terminee !\n";
		if (statutPartie == 1) {
			str += "Le gagnant est : "+ joueurs[0].getNom()+  " (CROIX) !\n\n";
		}
		if (statutPartie == 2) {
			str += "Le gagnant est : "+ joueurs[1].getNom()+  " (ROND) !\n \n";
		}
		str+= "Le plateau :\n" + plateau.toString();
		return str;
	}
	
	
}

package main.java.appli;

import java.util.Scanner;

import main.java.gameManager.HexManager;
import main.java.hex.IPlateau;
import main.java.hex.Plateau;
import main.java.joueur.IJoueur;
import main.java.joueur.JoueurHumain;
import main.java.joueur.JoueurMachine;

public class Main {
	
	private final static int TAILLE_MAX = 26;
	private final static int NB_MODES = 3;
	
	public static void main (String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez choisir la taille du plateau.");
		int taille = choisirNbEntre(1, TAILLE_MAX, sc , "La taille n'est pas valide !");
		
		System.out.println("Choix du mode de jeu \n 1. Joueur contre Joueur \n 2. Joueur contre Ordinateur \n 3. Ordinateur contre Ordinateur \n");
		int modeDeJeu = choisirNbEntre(1, NB_MODES, sc, "Ce mode n'existe pas !");
		IPlateau p = new Plateau(taille);
		IJoueur j1 = modeDeJeu != 3 ? new JoueurHumain("Humain 1 (X)") : new JoueurMachine("Machine 1 (X)"); 
		IJoueur j2 = modeDeJeu == 1 ? new JoueurHumain("Humain 2 (O)"): new JoueurMachine("Machine 2 (O)");
		HexManager gameManager = new HexManager(j1, j2, p, sc);
		int resultatPartie = gameManager.jouerPartie();
		
		System.out.println(gameManager.GameOver(resultatPartie));
		 
	}
	
	
	
	public static int choisirNbEntre(int min, int max, Scanner sc, String messageErreure) {
		int nb;
		while(true) {
			while(true) {
				try {
					nb = Integer.parseInt(sc.next());
					break;
				}catch(Exception e) {
					System.out.println("Vous devez entrer un nombre !");
				}
			}
			if (nb>= min && nb<=max) break;
			System.out.println(messageErreure);
		}
		
		return nb;
	}
}
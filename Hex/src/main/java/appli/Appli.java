package main.java.appli;
//-------------
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import main.java.hex.Plateau;

public class Appli {
	
	private final static int TAILLE_MAX = 26;
	
	public static void main(String args[]) {
		ArrayList<String> coupsJouees = new ArrayList<>();
		
		Scanner s = new Scanner(System.in);
		System.out.println("Veuillez choisir la taille du plateau.");
		int taille = choisirNbEntre(1, TAILLE_MAX, s , "La taille n'est pas valide !");
		
		
		Plateau p = new Plateau(taille);
		int statutPartie;
		System.out.println("Choix du mode de jeu \n 1. Joueur contre Joueur \n 2. Joueur contre Ordinateur \n 3. Ordinateur contre Ordinateur \n");
		int ModeDeJeu =  choisirNbEntre(1, 3, s , "Ce mode de jeu n'existe Pas ! ");
		if(ModeDeJeu==1){
			System.out.println("Je rappelle les regles, le Joueur 1 (Croix) a pour objectif de relier Est et Ouest tandis que le Joueur 2 (Rond) a pour objectif de relier Nord et Sud");
			Scanner sc = new Scanner(System.in);
			while(true) {

				System.out.println("This is the game : ");
				System.out.println(p.toString());
				jouerCoup(1, sc, p, coupsJouees);
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
					
				jouerCoup(2, sc, p, coupsJouees);
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
			}
				
			GameOver(statutPartie,ModeDeJeu);
			System.out.println(p.toString());
		}
		else if(ModeDeJeu==2){
			System.out.println("Je rappelle les regles, le Joueur (Croix) a pour objectif de relier Est et Ouest tandis que l'Ordinateur (Rond) a pour objectif de relier Nord et Sud");
			Scanner sc = new Scanner(System.in);
			while(true) {

				System.out.println("This is the game : ");
				System.out.println(p.toString());
				jouerCoup(1, sc, p, coupsJouees);;
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
					
				System.out.println("Ordinateur (ROND) c'est a toi !");
				coupAleatoire(p, taille, coupsJouees);
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
			}
				
			GameOver(statutPartie,ModeDeJeu);
			System.out.println(p.toString());
		}
		else{
			System.out.println("Ca promet d'être rapide !");
			System.out.println("Je rappelle les regles, l'Ordinateur 1 (Croix) a pour objectif de relier Est et Ouest tandis que l'Ordinateur 2 (Rond) a pour objectif de relier Nord et Sud");
			while(true) {

				System.out.println("This is the game : ");
				System.out.println(p.toString());
				System.out.println("Ordinateur 1 (CROIX) c'est a toi !");
				coupAleatoire(p, taille, coupsJouees);
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
					
				System.out.println("Ordinateur 2 (ROND) c'est a toi !");
				coupAleatoire(p, taille, coupsJouees);
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
			}
				
			GameOver(statutPartie,ModeDeJeu);
			System.out.println(p.toString());
		}
	}
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	    
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
	
	public static void jouerCoup(int joueur, Scanner sc, Plateau p, ArrayList<String> coupsJouees) {
		if(joueur == 1) System.out.println("Joueur 1 (CROIX) c'est a toi !");
		if(joueur == 2) System.out.println("Joueur 2 (ROND) c'est a toi !");
		String coup;
		while(true) {
			coup= sc.next();
			if(p.estValide(coup) && !coupsJouees.contains(coup)) break;
			else {
				if(!p.estValide(coup)) System.out.println("Ce coup est invalide !");
				if(coupsJouees.contains(coup)) System.out.println("Ce coup a deja ete joue !");
			}
		}
		coupsJouees.add(coup);
		p.jouer(coup);
	}
	
	public static void coupAleatoire(Plateau p, int taillePlateau, ArrayList<String> coupsJoues) {
		Random rand = new Random();
		char col = (char)(rand.nextInt(taillePlateau) + 'A');
		Random rand2 = new Random();
		int lig= rand2.nextInt(taillePlateau+1);
		String coord = col+""+lig;
		System.out.println(coord);
		if(p.estValide(coord) && !coupsJoues.contains(coord)){
			p.jouer(coord);
			coupsJoues.add(coord);
		}
		else{
			coupAleatoire(p, taillePlateau, coupsJoues);
		}
}
	
	public static void GameOver (int statutPartie, int ModeDeJeu) {
		if(ModeDeJeu==1){
			if (statutPartie == 1) {
				System.out.println("Le gagnant est le Joueur 1 : CROIX !");
			}
			if (statutPartie == 2) {
				System.out.println("Le gagnant est le Joueur 2 : ROND !");
			}
		}
		else if(ModeDeJeu==2){
			if (statutPartie == 1) {
				System.out.println("Le gagnant est le Joueur : CROIX !");
			}
			if (statutPartie == 2) {
				System.out.println("Le gagnant est l'Ordinateur 2 : ROND !");
			}
		}
		else{
			if (statutPartie == 1) {
				System.out.println("Le gagnant est l'Ordinateur 1 : CROIX !");
			}
			if (statutPartie == 2) {
				System.out.println("Le gagnant est l'Ordinateur' 2 : ROND !");
			}
		}
	}
}

	
		


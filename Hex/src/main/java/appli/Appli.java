package main.java.appli;
//--------------
import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.hex.Plateau;

public class Appli {
	
	private final static int TAILLE_MAX = 26;
	private final static int NB_JOUEURS = 2;
	private final static int PREMIERE_COLONNE = 'A';
	private final static int PREMIERE_LIGNE = 1;
	
	public static void main(String args[]) {
		ArrayList<String> coupsJoues = new ArrayList<>();
		Scanner s = new Scanner(System.in);
		int taille = taillePleau(s) ;
		Plateau p = new Plateau(taille);
		
		int statutPartie;
		
		int ModeDeJeu = modeDeJeu(s); 
		
		if(ModeDeJeu==1){
			rappelRegles();
			while(true) {

				afficherPartie(p);
				
				p.jouer(demanderJouer(1, s, p, coupsJoues));
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
					
				p.jouer(demanderJouer(2, s, p, coupsJoues));
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
			}
				
			GameOver(statutPartie,ModeDeJeu);
			System.out.println(p.toString());
		}
		else if(ModeDeJeu==2){
			rappelRegles();
			while(true) {
				afficherPartie(p);

				p.jouer(demanderJouer(1, s, p, coupsJoues));
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) { 
					break;
				}
					
				System.out.println("Ordinateur (ROND) c'est a toi !");
				p.jouerAléatoirement();
					
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
			rappelRegles();
			while(true) {

				afficherPartie(p);
				
				System.out.println("Ordinateur 1 (CROIX) c'est a toi !");
				p.jouerAléatoirement();
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
					
				System.out.println("Ordinateur 2 (ROND) c'est a toi !");
				p.jouerAléatoirement();
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
			}
				
			GameOver(statutPartie,ModeDeJeu);
			System.out.println(p.toString());
		}
	}
	private static void afficherPartie(Plateau p) {
		System.out.println("This is the game : ");
		System.out.println(p.toString());
	}
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  
	
	public static String demanderJouer(int joueur, Scanner sc, Plateau p, ArrayList<String> coupsJoues) {
		if (joueur == 1) {
			System.out.println("Joueur 1 (CROIX) c'est a toi !");
		}
		else {
			System.out.println("Joueur 2 (ROND) c'est a toi !");
		}
		String coup = sc.next();
		while(!p.estValide(coup) || coupsJoues.contains(coup)) {
			if (!p.estValide(coup)) System.out.println("Mauvais coup reesaye !"); 
			if(coupsJoues.contains(coup)) System.out.println("Ce coup a deja ete joue !"); 
			coup = sc.next();
		}
		coupsJoues.add(coup);
		return coup;
	}
	
	public static int taillePleau(Scanner sc) {
		System.out.println("Veuillez choisir la taille du plateau.");
		int taille= 9;
		while(true) {
			while(true) {
				try {
					taille = Integer.parseInt(sc.next());
					break;
				}catch(Exception e){
					System.out.println("Vous devez entrer un nombre !");
				}
			}
			if (taille > 0 && taille<= TAILLE_MAX) break;
			else System.out.println("La taille est incorrecte");
		}


		return taille;

	}
	
	public static int modeDeJeu(Scanner sc) {
		System.out.println("Choix du mode de jeu \n 1. Joueur contre Joueur \n 2. Joueur contre Ordinateur \n 3. Ordinateur contre Ordinateur \n");
		System.out.println("Choisissez 1, 2 ou 3.");
		int modeDeJeu;
		while(true) {
			while(true) {
				try {
					modeDeJeu = Integer.parseInt(sc.next());
					break;
				}catch(Exception e){
					System.out.println("Vous devez entrer un nombre !");
				}
			}
			if (modeDeJeu > 0 && modeDeJeu<= 3) break;
			else System.out.println("Ce mode n'existe pas");
		}
		return modeDeJeu;
	}
	
	public static void rappelRegles(){
		System.out.println("Je rappelle les regles, le Joueur (Croix) a pour objectif de relier Est et Ouest tandis que l'Ordinateur (Rond) a pour objectif de relier Nord et Sud");
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

	
		


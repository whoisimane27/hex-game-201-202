package main.java.appli;

import java.io.ObjectInputFilter.Status;
import java.util.Scanner;

import main.java.hex.Plateau;

public class Appli {
	public static void main(String args[]) {
		int ModeDeJeu;
		Scanner s = new Scanner(System.in);
		System.out.println("Veuillez choisir la taille du plateau.");
		int taille = s.nextInt();
		Plateau p = new Plateau(taille);
		int statutPartie;
		System.out.println("Choix du mode de jeu \n 1. Joueur contre Joueur \n 2. Joueur contre Ordinateur \n 3. Ordinateur contre Ordinateur \n");
		do {
			System.out.println("Choisissez 1, 2 ou 3.");
			ModeDeJeu = s.nextInt();
			if(ModeDeJeu<0 && ModeDeJeu >4){
				System.out.println("Choix invalide.");
			}
		} while (ModeDeJeu<0 && ModeDeJeu >4);
		if(ModeDeJeu==1){
			System.out.println("Je rappelle les regles, le Joueur 1 (Croix) a pour objectif de relier Est et Ouest tandis que le Joueur 2 (Rond) a pour objectif de relier Nord et Sud");
			Scanner sc = new Scanner(System.in);
			while(true) {

				System.out.println("This is the game : ");
				System.out.println(p.toString());
				System.out.println("Joueur 1 (CROIX) c'est a toi !");
				String p1= sc.nextLine();
				p.jouer(p1);
					
				statutPartie = p.VerifPartie();
				if(statutPartie != 0 ) {
					break;
				}
					
				System.out.println("Joueur 2 (ROND) c'est a toi !");
				String p2= sc.nextLine();
				p.jouer(p2);
					
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
				System.out.println("Joueur (CROIX) c'est a toi !");
				String p1= sc.nextLine();
				p.jouer(p1);
					
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
			System.out.println("Je rappelle les regles, l'Ordinateur 1 (Croix) a pour objectif de relier Est et Ouest tandis que l'Ordinateur 2 (Rond) a pour objectif de relier Nord et Sud");
			while(true) {

				System.out.println("This is the game : ");
				System.out.println(p.toString());
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
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
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

	
		


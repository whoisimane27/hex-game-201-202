package main.java.appli;

import java.io.ObjectInputFilter.Status;
import java.util.Scanner;

import main.java.hex.Plateau;

public class Appli {
	public static void main(String args[]) {
		int taille = 3;
		Plateau p = new Plateau(taille);
		Scanner sc = new Scanner(System.in);
		int statutPartie;
		System.out.println("Je rappelle les regles,le Joueur 1 (Rond) a pour objectif de relier Nord et SUD tandis que le Joueur 2 (Croix) a pour objectif de relier Est et Ouest");
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
		
		GameOver(statutPartie);
		
	}
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  
	
	public static void GameOver (int statutPartie) {
		if (statutPartie == 1) {
			System.out.println("Le gagnant est le joueur 1 : CROIX !");
		}
		if (statutPartie == 2) {
			System.out.println("Le gagnant est le joueur 2 : ROND !");
		}
	}
}

	
		


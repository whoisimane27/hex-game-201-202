package main.java.hex;

import java.util.ArrayList;
import java.util.Random;

public class Plateau {
	private final static int TAILLE_MAX = 26;
	private final static int NB_JOUEURS = 2;
	private final static int PREMIERE_COLONNE = 'A';
	private final static int PREMIERE_LIGNE = 1;
	
	private Pion[][] t;
	private int joueur= 0;
	
	public void suivant() {
		joueur = (joueur + 1) % NB_JOUEURS;
	}
	
	public Plateau(int taille) {
		assert taille > 0 && taille <= TAILLE_MAX;
		t = new Pion [taille][taille];
		
		for (int lig = 0; lig < taille(); ++lig)
			for (int col = 0; col < taille(); ++col)
				t[col][lig] = Pion.Vide;
	}
	
	//renvoie la longueur du plateau
	public int taille() {
		return t.length;
	} 
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < taille(); ++i)
			s+=" "+(char)( 'A' + i);
		s+='\n';
		for (int lig = 0; lig < taille(); ++lig) {
			s+= lig+1 + espaces (lig);
			for (int col = 0; col < taille(); ++col)
				s+= " "+ t[col][lig];
			s+='\n';
		}
		return s;
	}
	
	//Cree des n espaces et les renvoie
	private String espaces(int n) {
		String s = "";
		for(int i = 0; i < n; ++i)
			s+= " ";
		return s;
	}
	
	
	public void jouer(String coord) {
		assert estValide(coord);
		assert getCase(coord) == Pion.Vide;
		Pion pion = Pion.values()[joueur];
		int col = getColonne (coord);
		int lig = getLigne(coord);
		t[col][lig] = pion; 
		suivant();
	}

	public void jouerAléatoirement() {
			Random rand = new Random();
			char col = (char)(rand.nextInt(taille()) + 'A');
			Random rand2 = new Random();
			int lig= rand2.nextInt(taille()+1);
			String coord = col+""+lig;
			System.out.println(coord);
			if(estValide(coord) && getCase(coord)==Pion.Vide){
				jouer(coord);
			}
			else{
				jouerAléatoirement();
			}
	}
	
	private int getColonne(String coord) {
		return coord.charAt(0) - PREMIERE_COLONNE; // ex 'B' -'A' == 1
	}
	
	private int getLigne(String coord) {
		return Integer.parseInt(coord.substring(1))-PREMIERE_LIGNE; // ex '2' - '1' == 1
	}
	
	public boolean estValide(String coord) {
		int col, lig;
		try {
			col = getColonne (coord);
			lig = getLigne(coord); 
		}catch(Exception e) {
			return false;
		}
		if (col <0 || col >= taille())
			return false;
		if (lig <0 || lig >= taille())
			return false;
		return true;
	}
	
	public Pion getCase(String coord) {
		assert estValide(coord);
		int col = getColonne (coord);
		int lig = getLigne(coord);
		return t[col][lig];
	} 
	
	//renvoi la coordonnee en dessous de celle mise en parametre
	public String coordEnDessous(String coord) {
		assert estValide(coord);
		String newCoord =""+  coord.charAt(0) + (getLigne(coord)+ PREMIERE_LIGNE + 1);
		return newCoord; 
		
	} 
	//renvoi la coordonnee au dessus de celle mise en parametre
	public String coordAuDessus(String coord) {
		assert estValide(coord);
		String newCoord =""+  coord.charAt(0) + (getLigne(coord) + PREMIERE_LIGNE - 1);  
		return newCoord;
		
	}
	
	//renvoi la coordonnee a droite de celle mise en parametre
	public String coordAdroite(String coord) {
		assert estValide(coord);
		char c = coord.charAt(0);
		c= (char)((int)c +1);
		String newCoord= c + coord.substring(1);
		return newCoord;
	}
	
	//renvoi la coordonnee a gauche de celle mise en parametre
	public String coordAgauche(String coord) {
		char c = coord.charAt(0); 
		c= (char)((int)c -1);  
		String newCoord= c + coord.substring(1);
		return newCoord;
	}
	
	
	//Verifie si la partie est fini et qu'il ya un gagnant
	public int VerifPartie() { 
		int gagnant = 0; 

		String coord = "" + (char)PREMIERE_COLONNE + PREMIERE_LIGNE;
		//Verifie si un chemin de Nord a Sud avec les pions O existe
		for(int i = 0; i<taille();i ++) {
			if (getCase(coord) == Pion.Rond && estCheminVers(coord, null, 'S'))
				return gagnant = 2;
			coord = coordAdroite(coord);
		}
		//Verifie si un chemin d'Est a Ouest avec les pions X existe
		coord = "" + (char)PREMIERE_COLONNE + PREMIERE_LIGNE;
		for(int i = 0; i<taille();i ++) {
			if (getCase(coord) == Pion.Croix && estCheminVers(coord, null, 'O'))
				return gagnant = 1;
			coord = coordEnDessous(coord);
		} 
		return gagnant;

	}
	
	//renvoi toutes les coordonnee valides autour de celle passee en parametre 
	public ArrayList<String> coordAutour(String coord) {
		assert estValide(coord);
		ArrayList<String> coords = new ArrayList<>();
		String bas = coordEnDessous(coord);
		if(estValide(bas)) coords.add(bas); 
		
		String haut = coordAuDessus(coord);
		if(estValide(haut)) coords.add(haut);
		
		String droite = coordAdroite(coord);
		if(estValide(droite)) coords.add(droite); 
		
		String gauche= coordAgauche(coord);
		if(estValide(gauche)) coords.add(gauche); 
		
		String hautDroite= coordAuDessus(coordAdroite(coord));
		if(estValide(hautDroite)) coords.add(hautDroite); 
		
		String basGauche = coordAgauche(coordEnDessous(coord));
		if(estValide(basGauche)) coords.add(basGauche);
		
		return coords;
	}
	
	
	
	//renvoie si un chemin de la (coord) vers la (direction) existe
	//Direction peut prendre deux valeurs 'S'(Sud) 'O'(Ouest), on  peut facilement ajouter les autres directions mais ce n'est pas utile dans ce cas
	public boolean estCheminVers (String coord,  ArrayList<String> array, char direction) {
		boolean estChemin= false;
		if(array == null) array = new ArrayList<String>();
		array.add(coord);
		if(getLigne(coord) + 1== taille() && direction == 'S') {return true;}
		if(getColonne(coord) + 1== taille() && direction == 'O') {return true;}
		ArrayList<String> coordsAutour = coordAutour(coord);
		for (String s : coordsAutour) {
			if(!array.contains(s) && getCase(coord) == getCase(s)) {
				estChemin= estChemin || estCheminVers(s, array, direction);
			}
		}
		return estChemin; 
	} 

}

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
		int col = getColonne (coord);
		int lig = getLigne(coord); 
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
	
	//renvoie la coordonne de la case en dessous 
	public String coordEnDessous(String coord) {
		assert estValide(coord);
		String newCoord =""+  coord.charAt(0) + (getLigne(coord)+ PREMIERE_LIGNE + 1);
		return newCoord; 
		
	} 
	
	//a supprimer peut etre
	public String coordAuDessus(String coord) {
		assert estValide(coord);
		String newCoord =""+  coord.charAt(0) + (getLigne(coord) + PREMIERE_LIGNE - 1);  
		return newCoord;
		
	}
	
	public String coordAdroite(String coord) {
		assert estValide(coord);
		char c = coord.charAt(0);
		c= (char)((int)c +1);
		String newCoord= c + coord.substring(1);
		return newCoord;
	}
	public String coordAgauche(String coord) {
		assert estValide(coord);
		char c = coord.charAt(0); 
		c= (char)((int)c -1);  
		String newCoord= c + coord.substring(1);
		return newCoord;
	}
	
	// renvoi une array List des coordonnes qui sont en dessous de coord si elles sont valides 
	public ArrayList<String> AllCoordsEnDessous(String coord) {
		assert estValide(coord);
		ArrayList<String> coords = new ArrayList<>();
		String bas = coordEnDessous(coord);
		if(estValide(bas)) coords.add(bas); 
		String basGauche = coordAgauche(coordEnDessous(coord));
		if(estValide(basGauche)) coords.add(basGauche);
		String basDroite = coordAdroite(coordEnDessous(coord)); 
		if(estValide(basDroite)) coords.add(basDroite);
		
		return coords;
	}
	
	public ArrayList<String> AllCoordsADroite(String coord) {
		assert estValide(coord);
		ArrayList<String> coords = new ArrayList<>();
		String droite = coordAdroite(coord);
		if(estValide(droite)) coords.add(droite); 
		String hautDroit = coordAuDessus(coordAdroite(coord));
		if(estValide(hautDroit)) coords.add(hautDroit);
		String basDroite = coordAdroite(coordEnDessous(coord)); 
		if(estValide(basDroite)) coords.add(basDroite);
		return coords;
	}
	
	public boolean estCheminVersBas(String coord) {
		boolean estChemin = false;
		assert estValide(coord);
		if(getCase(coord)!= Pion.Rond) return false;
		if(getLigne(coord) + 1== taille()) {return true;};
		ArrayList<String> coordsEnBas = AllCoordsEnDessous(coord);
		for (String s : coordsEnBas) {
			estChemin= estChemin || estCheminVersBas(s);
		}
		return estChemin;
	}
	
	public boolean estCheminVersDroite(String coord) {
		boolean estChemin = false;
		assert estValide(coord);
		if(getCase(coord)!= Pion.Croix) return false;
		if(getColonne(coord)+1== taille()) {return true;};
		ArrayList<String> coordsAdroite = AllCoordsADroite(coord);
		for (String s : coordsAdroite) {
			estChemin= estChemin || estCheminVersDroite(s);
		}
		return estChemin;
	}
	
	
	public int VerifPartie() { 
		int gagnant = 0; 

		String coord = "" + (char)PREMIERE_COLONNE + PREMIERE_LIGNE;
		//Verifie si un chemin de Nord a Sud avec les pions O existe
		for(int i = 0; i<taille();i ++) {
			if (estCheminVersBas(coord))
				return gagnant = 2;
			coord = coordAdroite(coord);
		}
		coord = "" + (char)PREMIERE_COLONNE + PREMIERE_LIGNE;
		for(int i = 0; i<taille();i ++) {
			if (estCheminVersDroite(coord)) return gagnant = 1;
			coord = coordEnDessous(coord);
		}
		return gagnant;

	}

}

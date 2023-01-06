package main.java.hex;

public interface IPlateau {
	boolean estValide(String coord);
	int taille();
	void jouer(String coord);
	int VerifPartie();
}

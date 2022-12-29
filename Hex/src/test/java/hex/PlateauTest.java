package test.java.hex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.java.hex.Pion;
import main.java.hex.Plateau;

class PlateauTest {

	@Test
	void test() {
		int taille = 4;
		Plateau p = new Plateau(taille);
		assertEquals(taille, p.taille());
		assertEquals(
				" A B C D\n" +  
				"1 . . . .\n" + 
				"2  . . . .\n" + 
				"3   . . . .\n" + 
				"4    . . . .\n", p.toString()); 
		p.jouer("B2"); 
		assertEquals(Pion.Croix, p.getCase("B2"));
		p.jouer("C2");   
		assertEquals(Pion.Rond, p.getCase("C2")); 
		
		String coordEnDessous = p.coordEnDessous("B2");
		assertEquals(coordEnDessous, "B3"); 
		String coordAuDessus = p.coordAuDessus("B2");  
		assertEquals(coordAuDessus, "B1");
		String coordAdroite = p.coordAdroite("B2");
		assertEquals(coordAdroite, "C2");
		String coordAgauche = p.coordAgauche("B2"); 
		assertEquals(coordAgauche, "A2");
		
		ArrayList<String> coordsEnDessous = p.AllCoordsEnDessous("A1");
		ArrayList<String> coordsEnDessousVerif = new ArrayList<>();
		coordsEnDessousVerif.add("A2");
		coordsEnDessousVerif.add("B2");
		assertEquals(coordsEnDessous, coordsEnDessousVerif);
		
		//Dans la partie qui suit, le Rond gagne
		taille = 3;
		Plateau partieRond = new Plateau(taille);
		

		partieRond.jouer("A1");
		partieRond.jouer("B1");
		partieRond.jouer("A2");
		partieRond.jouer("C2");
		partieRond.jouer("A3");
		partieRond.jouer("B3");;
		//il existe un chemin entre B1 et B3 donc Rond gagne

		assertEquals(partieRond.estCheminVersBas("B1"), true);  
		assertEquals(partieRond.estCheminVersBas("C1"), false);
		assertEquals(partieRond.VerifPartie(), 2); 
		
		
		//Dans la partie qui suit, la croix gagne
		taille = 3;
		Plateau partieCroix = new Plateau(taille);
				 

		partieCroix.jouer("A1");
		partieCroix.jouer("A2");
		partieCroix.jouer("B1");
		partieCroix.jouer("B2");
		partieCroix.jouer("C1");
		partieCroix.jouer("C2");
		System.out.println(partieCroix);
		//il existe un chemin entre A1 et B1 donc Rond gagne
		assertEquals(partieCroix.estCheminVersDroite("A1"), true);
		assertEquals(partieCroix.estCheminVersDroite("A3"), false);
		assertEquals(partieCroix.VerifPartie(), 1);

		




		
		
		
	}

}

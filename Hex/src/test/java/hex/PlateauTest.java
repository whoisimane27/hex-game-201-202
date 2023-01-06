package test.java.hex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

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
		
		ArrayList<String> coordsAutour = p.coordAutour("A1");
		ArrayList<String> coordsEAutourVerif = new ArrayList<>(Arrays.asList("A2", "B1"));
		assertEquals(coordsAutour, coordsEAutourVerif);
		
		//test de la fonction estChemin 
		System.out.println(p);
		assertEquals(p.estCheminVers("C4", null, 'S'), true);
		assertEquals(p.estCheminVers("B2", null, 'S'), false);
		assertEquals(p.estCheminVers("C2", null, 'O'), false); 
		assertEquals(p.estCheminVers("A3", null, 'O'), true);
		
	}

}

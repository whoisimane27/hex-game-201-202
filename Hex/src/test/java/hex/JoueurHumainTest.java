package test.java.hex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.joueur.JoueurHumain;

class JoueurHumainTest {

	@Test
	void test() {
		JoueurHumain joueur = new JoueurHumain("Humain");
		assertEquals("Humain", joueur.getNom() );	
	} 
	

		
	


}

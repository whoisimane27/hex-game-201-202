package test.java.hex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.joueur.JoueurMachine;

class JoueurMachineTest {

	@Test
	void test() {
		JoueurMachine joueur = new JoueurMachine("Machine");
		assertEquals("Machine", joueur.getNom() );	
	} 

}

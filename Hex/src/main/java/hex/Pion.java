package main.java.hex;

public enum Pion {
	Croix('X'), Rond('O'), Vide('.');
	private char symbole;
	
	Pion(char symbole) {
		this.symbole = symbole;
	}
	
	public String toString() {
		return ""+this.symbole;  
	}
}

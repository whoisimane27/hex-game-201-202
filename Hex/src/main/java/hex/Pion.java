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

	public static Pion get(char c) {
		for (Pion p : Pion.values())
			if (p.symbole == c) 
				return p;
		throw new IllegalArgumentException(
				"symbole inconnu " + c);
	}
}

package old;

/*
 * The class for each piece on the board
 * 
 * An important feature is the active attribute, which indicates whether this piece
 * is still in play or not.
 */
public class Piece {
	
	public int pieceType = 0;	// Type of each piece= P:1, N:2, B:3, R:4, Q:5, K:6
	public int location = -1;	// Location on the board
	public int value = 0;		// Value of the piece
	public boolean active = false;	// Whether or not piece is active
	
	public Piece () {}
	
	public Piece(int type, int location) {
		this.pieceType = type;
		this.location = location;
		this.active = true;
		
		/* Assigns the value based on the piece. These values are far from exact, 
		 * but at least we can handle all piece values from here.
		 * 
		 */
		if (type == 1) {
			value = 2;
		} else if (type == 2) {
			value = 12; 
		} else if (type == 3) {
			value = 14;
		} else if (type == 4) {
			value = 18;
		} else if (type == 5) {
			value = 60;
		} else if (type == 6) {
			value = 300;
		}
	}
	
	public Piece(int type, int location, boolean active) {
		this.pieceType = type;
		this.location = location;
		this.active = active;
		
		/* Assigns the value based on the piece. These values are far from exact, 
		 * but at least we can handle all piece values from here.
		 * 
		 */
		if (type == 1) {
			value = 4;
		} else if (type == 2) {
			value = 12; 
		} else if (type == 3) {
			value = 14;
		} else if (type == 4) {
			value = 18;
		} else if (type == 5) {
			value = 40;
		} else if (type == 6) {
			value = 300;
		}
	}
	
}

/*
 * The class for each piece on the board
 */
public class Piece {
	
	public int value = 0;	// Value of each piece= P:1, N:2, B:3, R:4, Q:5, K:6
	public int location = -1;	// Location on the board
	public boolean active = false;	// Whether or not piece is active
	
	public Piece () {}
	
	public Piece(int value, int location) {
		this.value = value;
		this.location = location;
		this.active = true;
	}
	
}

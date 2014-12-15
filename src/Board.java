import java.util.Arrays;

/*
 * So what I'm thinking for this is to have a board object that holds the 
 * current state of the board. The only flaw with this method is that I did something 
 * similar for my Rubik's cube project, and found that it was actually extremely difficult 
 * to copy the board, without copying the reference as well. For example, if you 
 * perform changes to a copy, those changes will be applied to the original as well.
 *
 *
 * We could try making a 2-D array to represent the board. I'm not sure how that would play out,
 * but it's simple if we just assign pieces to the board. We could do actual move calculation on the
 * pieces themselves.
 * 
 * What I was thinking, is that we just use the board for reference purposes only. We can pull locations from it
 * to test if moves by individual pieces would be valid or not.
 */




/*
 * Aha!
 * 
 * I found this excellent resource: https://cis.uab.edu/hyatt/boardrep.html
 * 
 * We're going to sorround the board with a double layer of 
 */

/*
 * The board:
 * 
 * 132 133 134 135 136 137 138 139 140 141 142 143
 * 120 121 122 123 124 125 126 127 128 129 130 131
 * 		  _________________________________
 * 108 109|110 111 112 113 114 115 116 117| 118 119
 * 96  97 |98  99  100 101 102 103 104 105| 106 107
 * 84  85 |86  87  88  89  90  91  92  93| 94 95
 * 72  73 |74  75  76  77  78  79  80  81| 82 83
 * 60  61 |62  63  64  65  66  67  68  69| 70 71
 * 48  49 |50  51  52  53  54  55  56  57| 58 59
 * 36  37 |38  39  40  41  42  43  44  45| 46 47
 * 24  25 |26  27  28  29  30  31  32  33| 34 35
 *         ______________________________
 * 12  13  14  15  16  17  18  19  20  21 22 23
 * 0   1   2   3   4   5   6   7   8   9  10 11 
 * 
 * There is an extra two rows/columns around the board, to aid in calculating what is off of the edge
 * 
 * A value of 99 on a space means unavailable, a value of 0 means available
 * Otherwise, it will hold positive array positions for white positions, and negative positions for black.
 * Each position value should be decremented by one (a value of 1 on the board will be 0 in the array), and 
 * negative values will also need to be changed to positive for black pieces.
 * 
 */

public class Board {
	
	// The location of pieces on the board. The order will be:
	// K Q R R B B N N P P P P P P P P 
	//
	// Value of each piece= P:1, N:2, B:3, R:4, Q:5, K:6
	public Piece[] whitePieces  = { new Piece(6, 30), new Piece(5, 29), new Piece(4, 26), new Piece(4, 33), new Piece(3, 28), 
			new Piece(3, 31), new Piece(2, 27), new Piece(2, 32), new Piece(1, 38), new Piece(1, 39), new Piece(1, 40), new Piece(1, 41), 
			new Piece(1, 42), new Piece(1, 43), new Piece(1, 44), new Piece(1, 45)};
	
	public Piece[] blackPieces  = { new Piece(6, 114), new Piece(5, 113), new Piece(4, 110), new Piece(4, 117), new Piece(3, 112), 
			new Piece(3, 115), new Piece(2, 111), new Piece(2, 116), new Piece(1, 98), new Piece(1, 99), new Piece(1, 100), new Piece(1, 101), 
			new Piece(1, 102), new Piece(1, 103), new Piece(1, 104), new Piece(1, 105)};
	
	// The board. Each individual value is the location of the piece in the pieces array
	public int[] state = new int[144];
	
	// Create the board
	public Board () {
		
		// Fill in the top and bottom border
		Arrays.fill(this.state, 0, 24, 99);
		Arrays.fill(this.state, 120, 144, 99);
		
		// Fill in the side borders
		for (int i = 24; i < 120; i += 12) {
			Arrays.fill(this.state, i, i + 2, 99);
			Arrays.fill(this.state, i + 10, i + 12, 99);
		}
		
		// Fill in the blank parts of the main board
		for (int i = 50; i < 98; i += 12) {
			Arrays.fill(this.state, i, i + 8, 0);
		}
		
		// Fill in white's pawns
		int index = 9;
		for (int i = 38; i < 46; i++) {
			state[i] = index;
			index++;
		}
		
		// Fill in black's pawns
		index = -9;
		for (int i = 98; i < 106; i++) {
			state[i] = index;
			index--;
		}
		
		// Fill in the rest
		state[30] = 1;
		state[29] = 2;
		state[26] = 3;
		state[33] = 4;
		state[28] = 5;
		state[31] = 6;
		state[27] = 7;
		state[32] = 8;
		
		state[114] = -1;
		state[113] = -2;
		state[110] = -3;
		state[117] = -4;
		state[112] = -5;
		state[115] = -6;
		state[111] = -7;
		state[116] = -8;
		
	}
	
	// Copy constructor
	public Board(Board aBoard) {
		int[] newState = new int[144];
		
		// Copy the board
		for (int i = 0; i < aBoard.state.length; i++) {
			newState[i] = aBoard.state[i];
		}
		
		Piece[] wPieces = new Piece[16];
		Piece[] bPieces = new Piece[16];
		
		// Copy the piece lists
		for (int i = 0; i < aBoard.whitePieces.length; i++) {
			Piece wPiece = new Piece(aBoard.whitePieces[i].pieceType, aBoard.whitePieces[i].location, aBoard.whitePieces[i].active);
			wPieces[i] = wPiece;
			Piece bPiece = new Piece(aBoard.whitePieces[i].pieceType, aBoard.whitePieces[i].location, aBoard.whitePieces[i].active);
			bPieces[i] = bPiece;
		}
		
		whitePieces = wPieces;
		blackPieces = bPieces;
		state = newState;
		
	}

}

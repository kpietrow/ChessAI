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
 * 108 109 110 111 112 113 114 115 116 117 118 119
 * 96 97 98 99 100 101 102 103 104 105 106 107
 * 84 85 86 87 88 89 90 91 92 93 94 95
 * 72 73 74 75 76 77 78 79 80 81 82 83
 * 60 61 62 63 64 65 66 67 68 69 70 71
 * 48 49 50 51 52 53 54 55 56 57 58 59
 * 36 37 38 39 40 41 42 43 44 45 46 47
 * 24 25 26 27 28 29 30 31 32 33 34 35
 * 12 13 14 15 16 17 18 19 20 21 22 23
 * 0 1 2 3 4 5 6 7 8 9 10 11 
 */

public class Board {
	
	// The location of pieces on the board. The order will be:
	// (White) K Q R B N P P P P P P P, then the same for black 
	public Piece[] pieces  = { new Piece(6, 5) };
	
	// The board. Each individual value is the location of the piece in the pieces array
	public int[] state = {};

}

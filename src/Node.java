import java.util.ArrayList;


public class Node {
	public Board board;
	public ArrayList<Node> children = new ArrayList<Node>();
	public String type;		// branch, leaf, root. Branches are assumed to be player moves,
							// while roots are assumed to be opponents moves
	public Node parent;		// Node or null
	public int evalValue = 0;	// The evaluation value
	public Node bestChild = null;	// Who the parent favors (who has the lowest evalValue)
	public String path = "";	// The move to get to this position
	
	// Root Node
	public Node(Board aBoard, String aType) {
		board = aBoard;
		type = aType;
		parent = null;
	}
	
	// Branch Node
	public Node(Board aBoard, String aType, Node aParent) {
		board = aBoard;
		type = aType;
		parent = aParent;
	}
	
	// Leaf Node
	public Node(Board aBoard, String aType, Node aParent, int eval) {
		board = aBoard;
		type = aType;
		parent = aParent;
		evalValue = eval;
	}
	
	// Creates the move path for the node
	public void createPath(int pieceType, int oldLocation, int newLocation) {
		if (pieceType == 1) {
			path += "P";
		} else if (pieceType == 2) {
			path += "N";
		} else if (pieceType == 3) {
			path += "B";
		} else if (pieceType == 4) {
			path += "R";
		} else if (pieceType == 5) {
			path += "Q";
		} else if (pieceType == 6) {
			path += "K";
		}
		
		int column = 0;
		
		// Determine old column
		for (int i = oldLocation; i > 25; i -= 12) {
			column = i;
		}
		
		// Determine old row
		int row = 0;
		for (int i = oldLocation; i > 25; i--) {
			if (board.state[i] != 99) {
				row = i;
			} else {
				break;
			}
		}
		
		// Designate old column
		if (column == 26) {
			path += "a";
		} else if (column == 27) {
			path += "b";
		} else if (column == 28) {
			path += "c";
		} else if (column == 29) {
			path += "d";
		} else if (column == 30) {
			path += "e";
		} else if (column == 31) {
			path += "f";
		} else if (column == 32) {
			path += "g";
		} else if (column == 33) {
			path += "h";
		}
		
		// Designate old row
		if (row == 26) {
			path += "1";
		} else if (row == 38) {
			path += "2";
		} else if (row == 50) {
			path += "3";
		} else if (row == 62) {
			path += "4";
		} else if (row == 74) {
			path += "5";
		} else if (row == 86) {
			path += "6";
		} else if (row == 98) {
			path += "7";
		} else if (row == 110) {
			path += "8";
		}
		
		column = 0;
		
		// Determine new column
		for (int i = newLocation; i > 25; i -= 12) {
			column = i;
		}
		
		// Determine new row
		row = 0;
		
		for (int i = newLocation; i > 25; i--) {
			if (board.state[i] != 99) {
				row = i;
			} else {
				break;
			}
		}
		
		// Designate old column
		if (column == 26) {
			path += "a";
		} else if (column == 27) {
			path += "b";
		} else if (column == 28) {
			path += "c";
		} else if (column == 29) {
			path += "d";
		} else if (column == 30) {
			path += "e";
		} else if (column == 31) {
			path += "f";
		} else if (column == 32) {
			path += "g";
		} else if (column == 33) {
			path += "h";
		}
		
		// Designate old row
		if (row == 26) {
			path += "1";
		} else if (row == 38) {
			path += "2";
		} else if (row == 50) {
			path += "3";
		} else if (row == 62) {
			path += "4";
		} else if (row == 74) {
			path += "5";
		} else if (row == 86) {
			path += "6";
		} else if (row == 98) {
			path += "7";
		} else if (row == 110) {
			path += "8";
		}
	}
}

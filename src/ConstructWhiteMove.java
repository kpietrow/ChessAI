import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * This class will handle the construction of the next move
 */
public class ConstructWhiteMove {
	
	public static void run(String gameID, Board board) {
		
		// Find the best move
		findMovesRoot(board);
		
		// Convert to appropriate URL
		//convertToURL()
		
		
	}
	
	// Root method of developing the potentential board moves
	public static void findMovesRoot(Board board) {
		
		// Create the root node
		Node root = new Node(new Board(board), "root");
		
		// Run through list of white moves
		for (int i = 0; i < root.board.whitePieces.length; i++) {
			if (root.board.whitePieces[i].pieceType == 6 && root.board.whitePieces[i].active) {
				findMovesWhiteKing(root);
			} else if (root.board.whitePieces[i].pieceType == 6 && root.board.whitePieces[i].active) {
				findMovesWhiteQueen(root);
			} else if (root.board.whitePieces[i].pieceType == 6 && root.board.whitePieces[i].active) {
				findMovesWhiteRook(root);
			} else if (root.board.whitePieces[i].pieceType == 6 && root.board.whitePieces[i].active) {
				findMovesWhiteBishop(root);
			} else if (root.board.whitePieces[i].pieceType == 6 && root.board.whitePieces[i].active) {
				findMovesWhiteKnight(root);
			} else if (root.board.whitePieces[i].pieceType == 6 && root.board.whitePieces[i].active) {
				findMovesWhitePawn(root);
			}
		}
		
	}
	
	// Examines all of the possible moves of a White King
	public static void findMovesWhiteKing(Node node) {
		
		// Get the location now
		int location = node.board.whitePieces[0].location;
		
		// Instantiate this now
		Node child;
		
		// This means a black piece is there, or the space is empty
		if (node.board.state[location - 13] <= 0) {
			if (node.board.state[location - 13] != 0) {
				node.board.blackPieces[node.board.state[location - 13] * (-1) - 1].active = false;
			}
			
			child = createBranchNode(node, 1, location, location - 13);
			
			findMovesBlack(child);
		} 
		
		if (node.board.state[location - 12] <= 0) {			
			if (node.board.state[location - 12] != 0) {
				node.board.blackPieces[node.board.state[location - 12] * (-1) - 1].active = false;
			}
			
			child = createBranchNode(node, 1, location, location - 12);
			
			findMovesBlack(child);			
		}
		
		if (node.board.state[location - 11] <= 0) {
			if (node.board.state[location - 11] != 0) {
				node.board.blackPieces[node.board.state[location - 11] * (-1) - 1].active = false;
			}
			
			child = createBranchNode(node, 1, location, location - 11);
			
			findMovesBlack(child);		
			
		}
		
		if (node.board.state[location - 1] <= 0) {
			if (node.board.state[location - 1] != 0) {
				node.board.blackPieces[node.board.state[location - 1] * (-1) - 1].active = false;
			}
			
			child = createBranchNode(node, 1, location, location - 1);
			
			findMovesBlack(child);		
			
		}
		
		if (node.board.state[location + 1] <= 0) {
			if (node.board.state[location + 1] != 0) {
				node.board.blackPieces[node.board.state[location + 1] * (-1) - 1].active = false;
			}
			
			child = createBranchNode(node, 1, location, location + 1);
			
			findMovesBlack(child);		
			
		}
		
		if (node.board.state[location + 11] <= 0) {
			if (node.board.state[location + 11] != 0) {
				node.board.blackPieces[node.board.state[location + 11] * (-1) - 1].active = false;
			}
			
			child = createBranchNode(node, 1, location, location + 11);

			findMovesBlack(child);		
			
		}
		
		if (node.board.state[location + 12] <= 0) {
			if (node.board.state[location + 12] != 0) {
				node.board.blackPieces[node.board.state[location + 12] * (-1) - 1].active = false;
			}
			
			child = createBranchNode(node, 1, location, location + 12);

			
			findMovesBlack(child);		
			
		}
		
		if (node.board.state[location + 13] <= 0) {
			if (node.board.state[location + 13] != 0) {
				node.board.blackPieces[node.board.state[location + 13] * (-1) - 1].active = false;
			}
			
			child = createBranchNode(node, 1, location, location + 13);
			
			findMovesBlack(child);		
		}
		
	}
	
	public static void findMovesWhiteQueen(Node root) {
		
		findMovesWhiteDiagonal(root, 2);
		findMovesWhiteHorizontal(root, 2);
		
	}
	
	public static void findMovesWhiteDiagonal(Node root, int whitePiecesArrayPos) {

		int location = root.board.whitePieces[whitePiecesArrayPos].location;
		
		// Create empty child node, used for return statements later
		Node child;
		
		// Up and to the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 13; i < 118; i += 13) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (root.board.state[i] < 0) {
				root.board.blackPieces[root.board.state[i] * (-1) - 1].active = false;
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				break;
			// Blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
			}
		}
		
		// Up and to the left 
		for (int i = location + 11; i < 118; i += 11) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (root.board.state[i] < 0) {
				root.board.blackPieces[root.board.state[i] * (-1) - 1].active = false;
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				break;
			// Blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
			}
		}
		
		// Down and to the right 
		// (Limit is 25, because 26 is the absolute lowest that the board goes)
		for (int i = location - 11; i > 25; i -= 11) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (root.board.state[i] < 0) {
				root.board.blackPieces[root.board.state[i] * (-1) - 1].active = false;
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				break;
			// Blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
			}
		}
		
		// Down and to the left 
		for (int i = location - 13; i > 25; i -= 13) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (root.board.state[i] < 0) {
				root.board.blackPieces[root.board.state[i] * (-1) - 1].active = false;
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				break;
			// Blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);
				
				findMovesBlack(child);
			}
		}
	}
	
	public static void findMovesWhiteHorizontal(Node root, int arrayPos) {
		
	}
	
	// Creates a White branch node
	public static Node createBranchNode(Node root, int whitePiecesArrayPos, int oldLocation, int newLocation) {
		
		root.board.whitePieces[whitePiecesArrayPos - 1].location = newLocation;
		root.board.state[oldLocation] = 0;
		root.board.state[newLocation] = whitePiecesArrayPos;
		Node child = new Node(new Board(root.board), "branch", root);
		root.children.add(child);
		
		return child;
	}
}

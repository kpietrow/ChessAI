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
	public static void findMovesWhiteKing(Node root) {
		
		// Get the location now
		int location = root.board.whitePieces[0].location;
		
		// Instantiate this now
		Node child;
		
		// Possible moves for King
		int[] moves = new int[] {-13, -12, -11, -1, 1, 11, 12, 13};
		
		for (int i = 0; i < moves.length; i++) {
			// Black or empty
			if (root.board.state[location + i] <= 0) {
				// If black, we have to deactivate piece sitting in new location
				if (root.board.state[location + moves[i]] != 0) {
					root.board.blackPieces[root.board.state[location + moves[i]] * (-1) - 1].active = false;
				}
				
				child = createBranchNode(root, 1, location, location + moves[i]);
				findMovesBlack(child);
			}
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

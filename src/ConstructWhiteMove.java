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
		Node root = findMovesRoot(board);
		
		// Convert to appropriate URL
		//convertToURL()
		
		System.out.println(root.children.size());
		System.out.println(root.type);
		
		for (int i = 0; i < root.children.size(); i++) {
			System.out.println("\t" + root.children.get(i).path);
			for (int x = 50; x < 58; x++) {
				System.out.println("\t\t" + root.children.get(i).board.state[x]);
			}
			
		}
		
		
		
	}
	
	// Root method of developing the potentential board moves
	public static Node findMovesRoot(Board board) {
		
		// Create the root node
		Node root = new Node(new Board(board), "root");
		
		// Run through list of white moves
		for (int i = 0; i < root.board.whitePieces.length; i++) {
			if (root.board.whitePieces[i].pieceType == 6 && root.board.whitePieces[i].active) {
				findMovesWhiteKing(root, i + 1);
			} else if (root.board.whitePieces[i].pieceType == 5 && root.board.whitePieces[i].active) {
				findMovesWhiteQueen(root, i + 1);
			} else if (root.board.whitePieces[i].pieceType == 4 && root.board.whitePieces[i].active) {
				findMovesWhiteRook(root, i + 1);
			} else if (root.board.whitePieces[i].pieceType == 3 && root.board.whitePieces[i].active) {
				findMovesWhiteBishop(root, i + 1);
			} else if (root.board.whitePieces[i].pieceType == 2 && root.board.whitePieces[i].active) {
				findMovesWhiteKnight(root, i + 1);
			} else if (root.board.whitePieces[i].pieceType == 1 && root.board.whitePieces[i].active) {
				findMovesWhitePawn(root, i + 1);
			}
		}
				
		return root;
		
	}
	
	// Examines all of the possible moves of a White King
	public static void findMovesWhiteKing(Node root, int whitePiecesArrayPos) {
		
		// Get the location now
		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
		
		// Instantiate this now
		Node child;
		
		// Possible moves for King
		int[] moves = new int[] {-13, -12, -11, -1, 1, 11, 12, 13};
		
		for (int i = 0; i < moves.length; i++) {
			// Black or empty
			if (root.board.state[location + moves[i]] <= 0) {
				
				child = createBranchNode(root, whitePiecesArrayPos, location, location + moves[i]);	
				findMovesBlack(child);
			}
		}
		
	}
	
	public static void findMovesWhiteQueen(Node root, int whitePiecesArrayPos) {
		
		findMovesWhiteDiagonal(root, whitePiecesArrayPos);
		findMovesWhiteHorizontal(root, whitePiecesArrayPos);
		
	}
	
	public static void findMovesWhiteRook(Node root, int whitePiecesArrayPos) {
		findMovesWhiteDiagonal(root, whitePiecesArrayPos);
	}
	
	public static void findMovesWhiteBishop(Node root, int whitePiecesArrayPos) {
		findMovesWhiteHorizontal(root, whitePiecesArrayPos);
	}
	
	public static void findMovesWhiteKnight(Node root, int whitePiecesArrayPos) {
		// Get the location now
		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
		
		// Instantiate this now
		Node child;
		
		// Possible moves for Knight
		int[] moves = new int[] {14, 10, 23, 25, -10, -14, -23, -25};
		
		for (int i = 0; i < moves.length; i++) {
			// Black or empty
			if (root.board.state[location + moves[i]] <= 0) {
				child = createBranchNode(root, whitePiecesArrayPos, location, location + moves[i]);
				findMovesBlack(child);
			}
		}
	}
	
	public static void findMovesWhitePawn(Node root, int whitePiecesArrayPos) {		
		
		// Get the location now
		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
		
		// Instantiate this now
		Node child;
				
		// Possible normal moves for Pawn
		int[] moves = new int[] {12};
		
		for (int i = 0; i < moves.length; i++) {
			// Black or empty
			if (root.board.state[location + moves[i]] >= 0) {
				child = createBranchNode(root, whitePiecesArrayPos, location, location + moves[i]);
				findMovesBlack(child);
			}
		}
		
		// Possible attacks
		int[] attacks = new int[] {11, 13};
		
		for (int i = 0; i < attacks.length; i++) {
			// Black piece
			if (root.board.state[location + attacks[i]] < 0) {	
				child = createBranchNode(root, whitePiecesArrayPos, location, location + attacks[i]);
				findMovesBlack(child);
			}
		}
	}
	
	
	
	public static void findMovesWhiteDiagonal(Node root, int whitePiecesArrayPos) {

		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
		
		// Create empty child node, used for return statements later
		Node child;
		
		// Up and to the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 13; i < 118; i += 13) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
		
		// Up and to the left 
		for (int i = location + 11; i < 118; i += 11) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
		
		// Down and to the right 
		// (Limit is 25, because 26 is the absolute lowest that the board goes)
		for (int i = location - 11; i > 25; i -= 11) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
		
		// Down and to the left 
		for (int i = location - 13; i > 25; i -= 13) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
	}
	
	public static void findMovesWhiteHorizontal(Node root, int whitePiecesArrayPos) {
		
		Node child;
		
		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
		
		
		// To the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 1; i < 118; i++) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
		
		// To the left 
		// (Limit is 25, because 26 is the absolute lowest that the board goes)
		for (int i = location - 1; i > 25; i--) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
		
		// Up 
		for (int i = location + 12; i < 118; i += 12) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
		
		// Down 
		for (int i = location - 12; i > 25; i -= 12) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
			// Black piece or blank space
			} else {
				child = createBranchNode(root, whitePiecesArrayPos, location, i);

				findMovesBlack(child);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
		
	}
	
	// Creates a White branch node
	public static Node createBranchNode(Node root, int whitePiecesArrayPos, int oldLocation, int newLocation) {
				
		Node child = new Node(new Board(root.board), "branch", root);
		child.createPath(root.board.whitePieces[whitePiecesArrayPos - 1].pieceType, oldLocation, newLocation);
		
		if (child.board.state[newLocation] < 0) {
			child.board.blackPieces[child.board.state[newLocation] * (-1) - 1].active = false;
		}
		
		child.board.whitePieces[whitePiecesArrayPos - 1].location = newLocation;
		child.board.state[oldLocation] = 0;
		child.board.state[newLocation] = whitePiecesArrayPos;
		
		root.children.add(child);
		
		return child;
	}
	
	/**********************************************************************************
	 * Here, we move into the generation of the Black leaf nodes
	 * 
	 */
	
	// Finds Black side moves to form leaf nodes
	public static void findMovesBlack(Node branch) {
		
		// Run through list of white moves
		for (int i = 0; i < branch.board.blackPieces.length; i++) {
			if (branch.board.blackPieces[i].pieceType == 6 && branch.board.blackPieces[i].active) {
				findMovesWhiteKing(branch, i + 1);
			} else if (branch.board.blackPieces[i].pieceType == 5 && branch.board.blackPieces[i].active) {
				findMovesWhiteQueen(branch, i + 1);
			} else if (branch.board.blackPieces[i].pieceType == 4 && branch.board.blackPieces[i].active) {
				findMovesWhiteRook(branch, i + 1);
			} else if (branch.board.blackPieces[i].pieceType == 3 && branch.board.blackPieces[i].active) {
				findMovesWhiteBishop(branch, i + 1);
			} else if (branch.board.blackPieces[i].pieceType == 2 && branch.board.blackPieces[i].active) {
				findMovesWhiteKnight(branch, i + 1);
			} else if (branch.board.blackPieces[i].pieceType == 1 && branch.board.blackPieces[i].active) {
				findMovesWhitePawn(branch, i + 1);
			}
		}
		return;
	}
	
	// Creates a Black leaf node
	public static Node createLeafNode(Node branch, int blackPiecesArrayPos, int oldLocation, int newLocation) {
				
		// Create the new node
		Node child = new Node(new Board(branch.board), "branch", branch);
		child.createPath(branch.board.blackPieces[blackPiecesArrayPos - 1].pieceType, oldLocation, newLocation);
		
		if (child.board.state[newLocation] < 0) {
			child.board.whitePieces[child.board.state[newLocation] - 1].active = false;
		}
		
		child.board.blackPieces[blackPiecesArrayPos - 1].location = newLocation;
		child.board.state[oldLocation] = 0;
		child.board.state[newLocation] = blackPiecesArrayPos;
		
		// Calculate and assign evaluation value
		int eval = Evaluation.eval(child.board, true);
		child.evalValue = eval;
		
		// See if it can become branch's new favorite node (for evalValue)
		if (branch.bestChild == null) {
			branch.bestChild = child;
			branch.evalValue = eval;
		} else {
			if (eval > branch.evalValue) {
				branch.evalValue = eval;
				branch.bestChild = child;
			}
		}
		
		branch.children.add(child);
		
		return child;
	}
}

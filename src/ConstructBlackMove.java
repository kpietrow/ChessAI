
/*
 * This class will handle the construction of the next move for a Black player
 */
public class ConstructBlackMove {
	
	public static Node run(Board board) {
		
		// Find the best move
		Node root = findBlackMoves(board);
		
		/*
		System.out.println("size: " + root.children.size() + ", evalValue: " + root.evalValue + ", best: " + root.bestChild.path);
		
		for (int i = 0; i < root.children.size(); i++) {
			System.out.println("\t" + root.children.get(i).path + " - " + root.children.get(i).evalValue);
			
			for (int x = 0; x < root.children.get(i).children.size(); x++) {
				System.out.println("\t\t" + root.children.get(i).children.get(x).path);
			}
			
		} */
		
		return root;
	}
	
	// Root method of developing the potential board moves
	public static void findWhiteMoves(Node branch) {
				
		// Run through list of white moves
		for (int i = 0; i < branch.board.whitePieces.length; i++) {
			if (branch.board.whitePieces[i].pieceType == 6 && branch.board.whitePieces[i].active) {
				findMovesWhiteKing(branch, i + 1);
			} else if (branch.board.whitePieces[i].pieceType == 5 && branch.board.whitePieces[i].active) {
				findMovesWhiteQueen(branch, i + 1);
			} else if (branch.board.whitePieces[i].pieceType == 4 && branch.board.whitePieces[i].active) {
				findMovesWhiteRook(branch, i + 1);
			} else if (branch.board.whitePieces[i].pieceType == 3 && branch.board.whitePieces[i].active) {
				findMovesWhiteBishop(branch, i + 1);
			} else if (branch.board.whitePieces[i].pieceType == 2 && branch.board.whitePieces[i].active) {
				findMovesWhiteKnight(branch, i + 1);
			} else if (branch.board.whitePieces[i].pieceType == 1 && branch.board.whitePieces[i].active) {
				findMovesWhitePawn(branch, i + 1);
			}
		
			/* Checks to see if we can continue with the branch
			   Both the root and the branch have to have an evaluation score if their fate is to be decided
			
			   This is because the root being null signifies that we're on the first branch (or the previous
			   branches have been abject failures. And the branch in question has to have something to be evaluated on.
			*/
			if (branch.parent.bestChild != null && branch.bestChild != null) {
				// If the current branch's evalValue is lower than the root's, cut off this branch now
				if (branch.parent.evalValue > branch.evalValue) {
					break;
				}
			}
		}
		
		
		// See if this new branch can be our champion!
		if (branch.parent.bestChild == null) {
			branch.parent.bestChild = branch;
			branch.parent.evalValue = branch.evalValue;
		} else if (branch.parent.evalValue < branch.evalValue){
			branch.parent.evalValue = branch.evalValue;
			branch.parent.bestChild = branch;
		}
		
	}
	
	// Examines all of the possible moves of a White King
	public static void findMovesWhiteKing(Node root, int whitePiecesArrayPos) {
		
		// Get the location now
		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
		
		// Possible moves for King
		int[] moves = new int[] {-13, -12, -11, -1, 1, 11, 12, 13};
		
		for (int i = 0; i < moves.length; i++) {
			// Black or empty
			if (root.board.state[location + moves[i]] <= 0) {
				createLeafNode(root, whitePiecesArrayPos, location, location + moves[i]);	
				
			}
		}
		
	}
	
	public static void findMovesWhiteQueen(Node root, int whitePiecesArrayPos) {
		
		findMovesWhiteDiagonal(root, whitePiecesArrayPos);
		findMovesWhiteHorizontal(root, whitePiecesArrayPos);
		
	}
	
	public static void findMovesWhiteRook(Node root, int whitePiecesArrayPos) {
		findMovesWhiteHorizontal(root, whitePiecesArrayPos);
	}
	
	public static void findMovesWhiteBishop(Node root, int whitePiecesArrayPos) {
		findMovesWhiteDiagonal(root, whitePiecesArrayPos);
	}
	
	public static void findMovesWhiteKnight(Node root, int whitePiecesArrayPos) {
		// Get the location now
		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
		
		// Possible moves for Knight
		int[] moves = new int[] {14, 10, 23, 25, -10, -14, -23, -25};
		
		for (int i = 0; i < moves.length; i++) {
			// Black or empty
			if (root.board.state[location + moves[i]] <= 0) {
				createLeafNode(root, whitePiecesArrayPos, location, location + moves[i]);
				
			}
		}
	}
	
	public static void findMovesWhitePawn(Node root, int whitePiecesArrayPos) {		
		
		// Get the location now
		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
	
		// Possible normal moves for Pawn
		int[] moves = new int[] {12};
		
		for (int i = 0; i < moves.length; i++) {
			// Empty
			if (root.board.state[location + moves[i]] == 0) {
				createLeafNode(root, whitePiecesArrayPos, location, location + moves[i]);
				
			}
		}
		
		// Possible attacks
		int[] attacks = new int[] {11, 13};
		
		for (int i = 0; i < attacks.length; i++) {
			// Black piece
			if (root.board.state[location + attacks[i]] < 0) {
				createLeafNode(root, whitePiecesArrayPos, location, location + attacks[i]);
				
			}
		}
	}
	
	
	
	public static void findMovesWhiteDiagonal(Node root, int whitePiecesArrayPos) {

		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
		
		// Up and to the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 13; i < 118; i += 13) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
				// Black piece or blank space
			} else {
				createLeafNode(root, whitePiecesArrayPos, location, i);
				
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
				createLeafNode(root, whitePiecesArrayPos, location, i);

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
				createLeafNode(root, whitePiecesArrayPos, location, i);
				
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
				createLeafNode(root, whitePiecesArrayPos, location, i);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
	}
	
	public static void findMovesWhiteHorizontal(Node root, int whitePiecesArrayPos) {
				
		int location = root.board.whitePieces[whitePiecesArrayPos - 1].location;
		
		
		// To the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 1; i < 118; i++) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] > 0) {
				break;
				// Black piece or blank space
			} else {
				createLeafNode(root, whitePiecesArrayPos, location, i);

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
				createLeafNode(root, whitePiecesArrayPos, location, i);
				
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
				createLeafNode(root, whitePiecesArrayPos, location, i);

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
				createLeafNode(root, whitePiecesArrayPos, location, i);
				
				// Break if Black piece
				if (root.board.state[i] < 0) {
					break;
				}
			}
		}
		
	}
	
	
	/**********************************************************************************
	 * Here, we move into the generation of the Black branch nodes
	 * 
	 */
	
	// Finds Black side moves to form leaf nodes
	public static Node findBlackMoves(Board board) {
		
		// Create the root node
		Node root = new Node(new Board(board), "root");
		
		// Run through list of white moves
		for (int i = 0; i < root.board.blackPieces.length; i++) {
			if (root.board.blackPieces[i].pieceType == 6 && root.board.blackPieces[i].active) {
				findMovesBlackKing(root, i + 1);
			} else if (root.board.blackPieces[i].pieceType == 5 && root.board.blackPieces[i].active) {
				findMovesBlackQueen(root, i + 1);
			} else if (root.board.blackPieces[i].pieceType == 4 && root.board.blackPieces[i].active) {
				findMovesBlackRook(root, i + 1);
			} else if (root.board.blackPieces[i].pieceType == 3 && root.board.blackPieces[i].active) {
				findMovesBlackBishop(root, i + 1);
			} else if (root.board.blackPieces[i].pieceType == 2 && root.board.blackPieces[i].active) {
				findMovesBlackKnight(root, i + 1);
			} else if (root.board.blackPieces[i].pieceType == 1 && root.board.blackPieces[i].active) {
				findMovesBlackPawn(root, i + 1);
			}			
		}
		
		return root;
	}
	
	// Examines all of the possible moves of a Black King
	public static void findMovesBlackKing(Node root, int blackPiecesArrayPos) {
		
		// Get the location now
		int location = root.board.blackPieces[blackPiecesArrayPos - 1].location;
		
		// Instantiate this now
		Node child;
		
		// Possible moves for King
		int[] moves = new int[] {-13, -12, -11, -1, 1, 11, 12, 13};
		
		for (int i = 0; i < moves.length; i++) {
			// White or empty
			if (root.board.state[location + moves[i]] >= 0 && root.board.state[location + moves[i]] != 99) {
				child = createBranchNode(root, blackPiecesArrayPos, location, location + moves[i]);	
				findWhiteMoves(child);
			}
		}
	}
	
	public static void findMovesBlackQueen(Node root, int blackPiecesArrayPos) {
		findMovesBlackDiagonal(root, blackPiecesArrayPos);
		findMovesBlackHorizontal(root, blackPiecesArrayPos);
	}
	
	public static void findMovesBlackRook(Node root, int blackPiecesArrayPos) {
		findMovesBlackHorizontal(root, blackPiecesArrayPos);
	}
	
	public static void findMovesBlackBishop(Node root, int blackPiecesArrayPos) {
		findMovesBlackDiagonal(root, blackPiecesArrayPos);
	}
	
	public static void findMovesBlackKnight(Node root, int blackPiecesArrayPos) {
		// Get the location now
		int location = root.board.blackPieces[blackPiecesArrayPos - 1].location;
		
		Node child;
		
		// Possible moves for Knight
		int[] moves = new int[] {14, 10, 23, 25, -10, -14, -23, -25};
		
		for (int i = 0; i < moves.length; i++) {
			// Black or empty
			if (root.board.state[location + moves[i]] >= 0 && root.board.state[location + moves[i]] != 99) {
				child = createBranchNode(root, blackPiecesArrayPos, location, location + moves[i]);	
				findWhiteMoves(child);
			}
		}
	}
	
	public static void findMovesBlackPawn(Node root, int blackPiecesArrayPos) {		
		
		// Get the location now
		int location = root.board.blackPieces[blackPiecesArrayPos - 1].location;
		
		Node child;
		
		// Possible normal moves for Pawn
		int[] moves = new int[] {-12};
		
		for (int i = 0; i < moves.length; i++) {
			// Empty
			if (root.board.state[location + moves[i]] == 0) {
				child = createBranchNode(root, blackPiecesArrayPos, location, location + moves[i]);	
				findWhiteMoves(child);
			}
		}
		
		// Possible attacks
		int[] attacks = new int[] {-11, -13};
		
		for (int i = 0; i < attacks.length; i++) {
			// White piece
			if (root.board.state[location + attacks[i]] > 0 && root.board.state[location + attacks[i]] != 99) {	
				child = createBranchNode(root, blackPiecesArrayPos, location, location + attacks[i]);	
				findWhiteMoves(child);
			}
		}
	}
	
	public static void findMovesBlackDiagonal(Node root, int blackPiecesArrayPos) {

		int location = root.board.blackPieces[blackPiecesArrayPos - 1].location;
		
		Node child;
		
		// Up and to the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 13; i < 118; i += 13) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] < 0 || root.board.state[i] == 99) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, blackPiecesArrayPos, location, i);	
				findWhiteMoves(child);
				
				// Break if White piece
				if (root.board.state[i] > 0) {
					break;
				}
			}
		}
		
		// Up and to the left 
		for (int i = location + 11; i < 118; i += 11) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] < 0 || root.board.state[i] == 99) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, blackPiecesArrayPos, location, i);	
				findWhiteMoves(child);
				
				// Break if White piece
				if (root.board.state[i] > 0) {
					break;
				}
			}
		}
		
		// Down and to the right 
		// (Limit is 25, because 26 is the absolute lowest that the board goes)
		for (int i = location - 11; i > 25; i -= 11) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] < 0 || root.board.state[i] == 99) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, blackPiecesArrayPos, location, i);	
				findWhiteMoves(child);
				
				// Break if White piece
				if (root.board.state[i] > 0) {
					break;
				}
			}
		}
		
		// Down and to the left 
		for (int i = location - 13; i > 25; i -= 13) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] < 0 || root.board.state[i] == 99) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, blackPiecesArrayPos, location, i);	
				findWhiteMoves(child);
				
				// Break if White piece
				if (root.board.state[i] > 0) {
					break;
				}
			}
		}
	}
	
	public static void findMovesBlackHorizontal(Node root, int blackPiecesArrayPos) {
		
		int location = root.board.blackPieces[blackPiecesArrayPos - 1].location;
		
		Node child;
		
		// To the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 1; i < 118; i++) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] < 0 || root.board.state[i] == 99) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, blackPiecesArrayPos, location, i);	
				findWhiteMoves(child);
				
				// Break if White piece
				if (root.board.state[i] > 0) {
					break;
				}
			}
		}
		
		// To the left 
		// (Limit is 25, because 26 is the absolute lowest that the board goes)
		for (int i = location - 1; i > 25; i--) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] < 0 || root.board.state[i] == 99) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, blackPiecesArrayPos, location, i);	
				findWhiteMoves(child);
				
				// Break if White piece
				if (root.board.state[i] > 0) {
					break;
				}
			}
		}
		
		// Up 
		for (int i = location + 12; i < 118; i += 12) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] < 0 || root.board.state[i] == 99) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, blackPiecesArrayPos, location, i);	
				findWhiteMoves(child);
				
				// Break if White piece
				if (root.board.state[i] > 0) {
					break;
				}
			}
		}
		
		// Down 
		for (int i = location - 12; i > 25; i -= 12) {
			// The jig is up! Friendly piece or boundary detected
			if (root.board.state[i] < 0 || root.board.state[i] == 99) {
				break;
				// Black piece or blank space
			} else {
				child = createBranchNode(root, blackPiecesArrayPos, location, i);	
				findWhiteMoves(child);
				
				// Break if White piece
				if (root.board.state[i] > 0) {
					break;
				}
			}
		}
		
	}
	
	// Creates a White leaf node
	public static void createLeafNode(Node branch, int whitePiecesArrayPos, int oldLocation, int newLocation) {
				
		// Create the new node
		Node child = new Node(new Board(branch.board), "branch", branch);
		child.createPath(child.board.whitePieces[whitePiecesArrayPos - 1].pieceType, oldLocation, newLocation);
		
		if (child.board.state[newLocation] < 0) {
			child.board.blackPieces[child.board.state[newLocation] * (-1) - 1].active = false;
		}
		
		child.board.whitePieces[whitePiecesArrayPos - 1].location = newLocation;
		child.board.state[oldLocation] = 0;
		child.board.state[newLocation] = whitePiecesArrayPos;
		
		// Calculate and assign evaluation value
		int eval = Evaluation.eval(child.board, false);
		child.evalValue = eval;
		
		// See if it can become branch's new favorite node (for evalValue)
		if (branch.bestChild == null) {
			branch.bestChild = child;
			branch.evalValue = eval;
		} else if (eval < branch.evalValue) {
			branch.evalValue = eval;
			branch.bestChild = child;
		}
		
		branch.children.add(child);
	}
	
	// Creates a Black branch node
	public static Node createBranchNode(Node root, int blackPiecesArrayPos, int oldLocation, int newLocation) {
				
		Node child = new Node(new Board(root.board), "branch", root);
		child.createPath(root.board.blackPieces[blackPiecesArrayPos - 1].pieceType, oldLocation, newLocation);
		
		if (child.board.state[newLocation] > 0 && child.board.state[newLocation] != 99) {
			child.board.whitePieces[child.board.state[newLocation] - 1].active = false;
		}
		
		child.board.blackPieces[blackPiecesArrayPos - 1].location = newLocation;
		child.board.state[oldLocation] = 0;
		child.board.state[newLocation] = -blackPiecesArrayPos;
		
		if (!Evaluation.checkKingStatus(child.board.whitePieces)) {
			// Set to, essentially, infinity
			child.evalValue = 99999;
			root.evalValue = child.evalValue;
			root.bestChild = child;
		}
		
		
		root.children.add(child);
		
		return child;
	}
}

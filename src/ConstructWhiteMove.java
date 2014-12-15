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
		
		int location = node.board.whitePieces[0].location;
		
		// This means a black piece is there, or the space is empty
		if (node.board.state[location - 13] <= 0) {
			if (node.board.state[location - 13] != 0) {
				node.board.blackPieces[node.board.state[location - 13] * -1].active = false;
			}
			
			node.board.state[location - 13] = 1;
		} 
		
		if (node.board.state[location - 12] <= 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 12] * -1].pieceType);
		}
		
		if (node.board.state[location - 11] <= 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 11] * -1].pieceType);
		}
		
		if (node.board.state[location - 1] <= 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 1] * -1].pieceType);
		}
		
		if (node.board.state[location + 1] <= 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 1] * -1].pieceType);
		}
		
		if (node.board.state[location + 11] <= 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 11] * -1].pieceType);
		}
		
		if (node.board.state[location + 12] <= 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 12] * -1].pieceType);
		}
		
		if (node.board.state[location + 13] <= 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 13] * -1].pieceType);
		}
		
	}
	
	
	
	
	public static void findMovesBranch(Node root) {
		
		
		
		
		
	}
}

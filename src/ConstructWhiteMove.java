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
		
		HttpURLConnection response;
		try {
			response = (HttpURLConnection) (new URL("http://www.bencarle.com/chess/move/" + gameID + "/209/fcbd8a97/Pa2a4/")).openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getInputStream()));
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("done!");
		
		
	}
	
	/*
	 * This is going to be difficult. This is where the evaluation function will go.
	 * 
	 * Going to do a simple one to start off. Each piece will be added up. 
	 * This class is dedicated to the white player, so only white's situation will
	 * be considered.
	 */
	public int eval(Board board) {
		
		int evalValue = 0;
		
		// Adds values of white pieces, subtracts that of black
		for (int i = 0; i < board.whitePieces.length; i++) {
			if (board.whitePieces[i].active) {
				evalValue += board.whitePieces[i].value;
			}
			
			if (board.blackPieces[i].active) {
				evalValue -= board.blackPieces[i].value;
			}
		}
		
		// Now let's add the white side's attacks
		for (int i = 0; i < board.whitePieces.length; i++) {
			if (board.whitePieces[i].active) {
				evalValue += calculateWhiteAttackValue(board.whitePieces[i], board);
			}
		}
		
		
		
		
		return evalValue;
	}
	
	// The hub for determining the potential value of a white piece's attacks
	public int calculateWhiteAttackValue(Piece piece, Board board) {
		
		// Get the location of the piece on the board
		int location = piece.location;
		
		int value = 0;
		
		// King
		if (piece.pieceType == 6) {
			value += calculateKingAttacks(location, board);
		
		// Queen
		} else if (piece.pieceType == 5) {
			value += calculateWhiteDiagonalAttacks(piece.location, board);
			value += calculateWhiteRowAttacks(piece.location, board);
		}
		
		
		
		return value;
	}
	
	public int calculateKingAttacks(int location, Board board) {
		int value = 0;
		
		// This means a black piece is there
		if (board.state[location - 13] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 13]].pieceType);
		} else if (board.state[location - 12] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 12]].pieceType);
		} else if (board.state[location - 11] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 11]].pieceType);
		} else if (board.state[location - 1] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 1]].pieceType);
		} else if (board.state[location + 1] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 1]].pieceType);
		} else if (board.state[location + 11] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 11]].pieceType);
		} else if (board.state[location + 12] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 12]].pieceType);
		} else if (board.state[location + 13] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 13]].pieceType);
		}
		
		return value;
	}
	
	public int calculateWhiteDiagonalAttacks(int location, Board board) {
		
		// Up and to the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 13; i < 118; i++) {
			// The game is up!
			if (board.state[i] == 99) {
				break;
			} else if (board.state[i] < 0) {
				
			}
		}
		
		return 0;
	}
	
	public int calculateWhiteRowAttacks(int location, Board board) {
		
		
		return 0;
	}
	
	
	public int calculateAttackValue(int pieceType) {
		if (pieceType == 1) {
			return 1;
		} else if (pieceType == 2) {
			return 3;
		} else if (pieceType == 3) {
			return 5;
		} else if (pieceType == 4) {
			return 7;
		} else if (pieceType == 5) {
			return 10;
		} else if (pieceType == 6) {
			return 15;
		}
		
		return 0;
	}
	
}

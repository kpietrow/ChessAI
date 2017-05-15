package old;

public class Evaluation {
	

	/*
	 * This is going to be difficult. This is where the evaluation function will go.
	 * 
	 * Evaluates both the white and black pieces/attacks. After the have been added/subtracted together, 
	 * they are returned (if it's a White player), or multiplied by -1 (if it's a Black player) and returned
	 */
	public static int eval(Board board, boolean white) {
		
		int evalValue = 0;
		int blackValue = 0;
		
		// Adds values of white pieces, subtracts that of black
		for (int i = 0; i < board.whitePieces.length; i++) {
			if (board.whitePieces[i].active) {
				evalValue += board.whitePieces[i].value;
			}
			
			if (board.blackPieces[i].active) {
				blackValue += board.blackPieces[i].value;

			}
		}
		
		evalValue -= blackValue;
		blackValue = 0;
		
		// Now let's add the white side's attacks
		for (int i = 0; i < board.whitePieces.length; i++) {
			if (board.whitePieces[i].active) {
				evalValue += calculateWhiteAttackValue(board.whitePieces[i], board);

			}
		}
		
		// Now let's add the black side's attacks
		for (int i = 0; i < board.blackPieces.length; i++) {
			if (board.blackPieces[i].active) {
				blackValue += calculateBlackAttackValue(board.blackPieces[i], board);
			}
		}
		
		evalValue -= blackValue;
					
		// If player is White, return as is. Else, return the result reversed
		if (white) {
			return evalValue;
		} else {
			return evalValue * -1;
		}
	}
	
	// The hub for determining the potential value of a white piece's attacks
	public static int calculateBlackAttackValue(Piece piece, Board board) {
		
		// Get the location of the piece on the board
		int location = piece.location;
		
		int value = 0;
		
		// King
		if (piece.pieceType == 6) {
			value += calculateBlackKingAttacks(location, board);
		
		// Queen
		} else if (piece.pieceType == 5) {
			value += calculateBlackQueensAttacks(location, board);
		
		// Rook
		} else if (piece.pieceType == 4) {
			value += calculateBlackRowAttacks(location, board);

		// Bishop
		} else if (piece.pieceType == 3) {
			value += calculateBlackDiagonalAttacks(location, board);
		
		
		// Knight
		} else if (piece.pieceType == 2) {
			value += calculateBlackKnightAttacks(location, board);
		
		// Pawn
		} else if (piece.pieceType == 1) {
			value += calculateBlackPawnAttacks(location, board);
		}

		return value;
	}
	
	// Calculates the value of a Black King's attacks
	public static int calculateBlackKingAttacks(int location, Board board) {
		int value = 0;
		
		// Check all of the potential attack locations
		
		// This means a white piece is there
		if (board.state[location - 13] > 0 && board.state[location - 13] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 13] - 1].pieceType);
		} 
		
		if (board.state[location - 12] > 0 && board.state[location - 12] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 12] - 1].pieceType);
		}
		
		if (board.state[location - 11] > 0 && board.state[location - 11] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 11] - 1].pieceType);
		}

		if (board.state[location - 1] > 0 && board.state[location - 1] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 1] - 1].pieceType);
		}
		
		if (board.state[location + 1] > 0 && board.state[location + 1] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location + 1] - 1].pieceType);
		}
		
		if (board.state[location + 11] > 0 && board.state[location + 11] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location + 11] - 1].pieceType);
		}
		
		if (board.state[location + 12] > 0 && board.state[location + 12] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location + 12] - 1].pieceType);
		}
		
		if (board.state[location + 13] > 0 && board.state[location + 13] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location + 13] - 1].pieceType);
		}

		return value;
	}
	
	// Calculates the attacks of a Black Queen
	public static int calculateBlackQueensAttacks(int location, Board board) {
		int value = 0;
		
		value += calculateBlackDiagonalAttacks(location, board);
		value += calculateBlackRowAttacks(location, board);
		
		return value;
	}
	
	public static int calculateBlackKnightAttacks(int location, Board board) {
		int value = 0;
		
		if (board.state[location + 14] > 0 && board.state[location + 14] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location + 14] - 1].pieceType);
		}
		
		if (board.state[location + 10] > 0 && board.state[location + 10] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location + 10] - 1].pieceType);
		}
		
		if (board.state[location + 23] > 0 && board.state[location + 23] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location + 23] - 1].pieceType);
		}
		
		if (board.state[location + 25] > 0 && board.state[location + 25] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location + 25] - 1].pieceType);
		}
		
		if (board.state[location - 10] > 0 && board.state[location - 10] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 10] - 1].pieceType);
		}
		
		if (board.state[location - 14] > 0 && board.state[location - 14] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 14] - 1].pieceType);
		}
		
		if (board.state[location - 23] > 0 && board.state[location - 23] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 23] - 1].pieceType);
		}
		
		if (board.state[location - 25] > 0 && board.state[location - 25] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 25] - 1].pieceType);
		}
		
		return value;
	}
	
	public static int calculateBlackPawnAttacks(int location, Board board) {
		int value = 0;
		
		if (board.state[location - 13] > 0 && board.state[location - 13] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 13] - 1].pieceType);
		}
		
		if (board.state[location - 11] > 0 && board.state[location - 11] != 99) {
			value += calculateAttackValue(board.whitePieces[board.state[location - 11] - 1].pieceType);
		}
		
		return value;
	}
	
	public static int calculateBlackDiagonalAttacks(int location, Board board) {
		
		int value = 0;
		
		// Up and to the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 13; i < 118; i += 13) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] < 0 || board.state[i] == 99) {
				break;
			// White piece detected
			} else if (board.state[i] > 0) {
				value += calculateAttackValue(board.whitePieces[board.state[i] - 1].pieceType);
				break;
			}
		}
		
		// Up and to the left 
		for (int i = location + 11; i < 118; i += 11) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] < 0 || board.state[i] == 99) {
				break;
			// White piece detected
			} else if (board.state[i] > 0) {
				value += calculateAttackValue(board.whitePieces[board.state[i] - 1].pieceType);
				break;
			}
		}
		
		// Down and to the right 
		// (Limit is 25, because 26 is the absolute lowest that the board goes)
		for (int i = location - 11; i > 25; i -= 11) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] < 0 || board.state[i] == 99) {
				break;
			// White piece detected
			} else if (board.state[i] > 0) {
				value += calculateAttackValue(board.whitePieces[board.state[i] - 1].pieceType);
				break;
			}
		}
		
		// Down and to the left 
		for (int i = location - 13; i > 25; i -= 13) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] < 0 || board.state[i] == 99) {
				break;
			// White piece detected
			} else if (board.state[i] > 0) {
				value += calculateAttackValue(board.whitePieces[board.state[i] - 1].pieceType);
				break;
			}
		}
		
		return value;
	}
	
	public static int calculateBlackRowAttacks(int location, Board board) {
		int value = 0;
		
		// To the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 1; i < 118; i++) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] < 0 || board.state[i] == 99) {
				break;
			// White piece detected
			} else if (board.state[i] > 0) {
				value += calculateAttackValue(board.whitePieces[board.state[i] - 1].pieceType);
				break;
			}
		}
		
		// To the left 
		// (Limit is 25, because 26 is the absolute lowest that the board goes)
		for (int i = location - 1; i > 25; i--) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] < 0 || board.state[i] == 99) {
				break;
			// White piece detected
			} else if (board.state[i] > 0) {
				value += calculateAttackValue(board.whitePieces[board.state[i] - 1].pieceType);
				break;
			}
		}
		
		// Up 
		for (int i = location + 12; i < 118; i += 12) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] < 0 || board.state[i] == 99) {
				break;
			// White piece detected
			} else if (board.state[i] > 0) {
				value += calculateAttackValue(board.whitePieces[board.state[i] - 1].pieceType);
				break;
			}
		}
		
		// Down 
		for (int i = location - 12; i > 25; i -= 12) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] < 0 || board.state[i] == 99) {
				break;
			// White piece detected
			} else if (board.state[i] > 0) {
				value += calculateAttackValue(board.whitePieces[board.state[i] - 1].pieceType);
				break;
			}
		}
		
		return value;
	}
	
	// The hub for determining the potential value of a white piece's attacks
	public static int calculateWhiteAttackValue(Piece piece, Board board) {
		
		// Get the location of the piece on the board
		int location = piece.location;
		
		int value = 0;
		
		// King
		if (piece.pieceType == 6) {
			value += calculateWhiteKingAttacks(location, board);
		
		// Queen
		} else if (piece.pieceType == 5) {
			value += calculateWhiteQueensAttacks(location, board);
		
		
		// Rook
		} else if (piece.pieceType == 4) {
			value += calculateWhiteRowAttacks(location, board);

		// Bishop
		} else if (piece.pieceType == 3) {
			value += calculateWhiteDiagonalAttacks(location, board);
		
		
		// Knight
		} else if (piece.pieceType == 2) {
			value += calculateWhiteKnightAttacks(location, board);
		
		// Pawn
		} else if (piece.pieceType == 1) {
			value += calculateWhitePawnAttacks(location, board);
		}
		
		return value;
	}
	
	// Calculates the value of a White King's attacks
	public static int calculateWhiteKingAttacks(int location, Board board) {
		int value = 0;
				
		// Check all of the potential attack locations
		
		// This means a black piece is there
		if (board.state[location - 13] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 13] * (-1) - 1].pieceType);
		} 
		
		if (board.state[location - 12] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 12] * (-1) - 1].pieceType);
		}
		
		if (board.state[location - 11] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 11] * (-1) - 1].pieceType);
		}

		if (board.state[location - 1] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 1] * (-1) - 1].pieceType);
		}
		
		if (board.state[location + 1] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 1] * (-1) - 1].pieceType);
		}
		
		if (board.state[location + 11] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 11] * (-1) - 1].pieceType);
		}
		
		if (board.state[location + 12] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 12] * (-1) - 1].pieceType);
		}
		
		if (board.state[location + 13] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 13] * (-1) - 1].pieceType);
		}
		
		
		return value;
	}
	
	// Calculates the attacks of a White Queen
	public static int calculateWhiteQueensAttacks(int location, Board board) {
		int value = 0;
		
		value += calculateWhiteDiagonalAttacks(location, board);
		value += calculateWhiteRowAttacks(location, board);
		
		return value;
	}
	
	public static int calculateWhiteKnightAttacks(int location, Board board) {
		int value = 0;
		
		if (board.state[location + 14] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 14] * (-1) - 1].pieceType);
		}
		
		if (board.state[location + 10] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 10] * (-1) - 1].pieceType);
		}
		
		if (board.state[location + 23] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 23] * (-1) - 1].pieceType);
		}
		
		if (board.state[location + 25] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 25] * (-1) - 1].pieceType);
		}
		
		if (board.state[location - 10] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 10] * (-1) - 1].pieceType);
		}
		
		if (board.state[location - 14] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 14] * (-1) - 1].pieceType);
		}
		
		if (board.state[location - 23] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 23] * (-1) - 1].pieceType);
		}
		
		if (board.state[location - 25] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location - 25] * (-1) - 1].pieceType);
		}
		
		return value;
	}
	
	public static int calculateWhitePawnAttacks(int location, Board board) {
		int value = 0;
		
		if (board.state[location + 13] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 13] * (-1) - 1].pieceType);
		}
		
		if (board.state[location + 11] < 0) {
			value += calculateAttackValue(board.blackPieces[board.state[location + 11] * (-1) - 1].pieceType);
		}
		
		return value;
	}
	
	public static int calculateWhiteDiagonalAttacks(int location, Board board) {
		
		int value = 0;
		
		// Up and to the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 13; i < 118; i += 13) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (board.state[i] < 0) {
				value += calculateAttackValue(board.blackPieces[board.state[i] * (-1) - 1].pieceType);
				break;
			}
		}
		
		// Up and to the left 
		for (int i = location + 11; i < 118; i += 11) {
			// The jig is up! Friendly piece or boundary detected
				if (board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (board.state[i] < 0) {
				value += calculateAttackValue(board.blackPieces[board.state[i] * (-1) - 1].pieceType);
				break;
			}
		}
		
		// Down and to the right 
		// (Limit is 25, because 26 is the absolute lowest that the board goes)
		for (int i = location - 11; i > 25; i -= 11) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (board.state[i] < 0) {
				value += calculateAttackValue(board.blackPieces[board.state[i] * (-1) - 1].pieceType);
				break;
			}
		}
		
		// Down and to the left 
		for (int i = location - 13; i > 25; i -= 13) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (board.state[i] < 0) {
				value += calculateAttackValue(board.blackPieces[board.state[i] * (-1) - 1].pieceType);
				break;
			}
		}
		
		return value;
	}
	
	public static int calculateWhiteRowAttacks(int location, Board board) {
		int value = 0;
		
		// To the right 
		// (Limit is 118, because 117 is the absolute highest that the board goes)
		for (int i = location + 1; i < 118; i++) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (board.state[i] < 0) {
				value += calculateAttackValue(board.blackPieces[board.state[i] * (-1) - 1].pieceType);
				break;
			}
		}
		
		// To the left 
		// (Limit is 25, because 26 is the absolute lowest that the board goes)
		for (int i = location - 1; i > 25; i--) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (board.state[i] < 0) {
				value += calculateAttackValue(board.blackPieces[board.state[i] * (-1) - 1].pieceType);
				break;
			}
		}
		
		// Up 
		for (int i = location + 12; i < 118; i += 12) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (board.state[i] < 0) {
				value += calculateAttackValue(board.blackPieces[board.state[i] * (-1) - 1].pieceType);
				break;
			}
		}
		
		// Down 
		for (int i = location - 12; i > 25; i -= 12) {
			// The jig is up! Friendly piece or boundary detected
			if (board.state[i] > 0) {
				break;
			// Black piece detected
			} else if (board.state[i] < 0) {
				value += calculateAttackValue(board.blackPieces[board.state[i] * (-1) - 1].pieceType);
				break;
			}
		}
		
		return value;
	}
	
	
	public static int calculateAttackValue(int pieceType) {
		if (pieceType == 1) {
			return 1;
		} else if (pieceType == 2) {
			return 4;
		} else if (pieceType == 3) {
			return 6;
		} else if (pieceType == 4) {
			return 8;
		} else if (pieceType == 5) {
			return 10;
		} else if (pieceType == 6) {
			return 15;
		}
		
		return 0;
	}
}

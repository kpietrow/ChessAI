/*
 * Interpret our opponent's wily moves
 */
public class InterpretMove {
	
	public static void interpret(String input, Board board) {
		
		String firstCol = input.substring(1, 2);
		int firstRow = Integer.parseInt(input.substring(2, 3));
		String secondCol = input.substring(3, 4);
		int secondRow = Integer.parseInt(input.substring(4, 5));
		
		int firstPos = tally(firstCol, firstRow);
		int secondPos = tally(secondCol, secondRow);
				
		adjustBoard(board, firstPos, secondPos);
		
		
		
		
	}
	
	public static int tally(String column, int row) {
		
		// Lower left hand corner of graph
		int value = 26;
		
		if (column.equals("b")) {
			value++;
		} else if (column.equals("c")) {
			value += 2;
		} else if (column.equals("d")) {
			value += 3;
		} else if (column.equals("e")) {
			value += 4;
		} else if (column.equals("f")) {
			value += 5;
		} else if (column.equals("g")) {
			value += 6;
		} else if (column.equals("h")) {
			value += 7;
		}
		
		value += ((row - 1) * 12);
		return value;
	}

	public static void adjustBoard(Board board, int firstPos, int secondPos) {
		
		// Piece moving to empty spot
		if (board.state[secondPos] == 0) {
			board.state[secondPos] = board.state[firstPos];
			if (board.state[firstPos] > 0) {
				board.whitePieces[board.state[firstPos] - 1].location = secondPos;
			} else {
				board.blackPieces[board.state[firstPos] * (-1) - 1].location = secondPos;
			}
			board.state[firstPos] = 0;
			
		// Black piece taken over
		} else if (board.state[secondPos] < 0) {
			board.blackPieces[board.state[secondPos] * (-1) - 1].active = false;
			board.state[secondPos] = board.state[firstPos];
			board.whitePieces[board.state[firstPos] - 1].location = secondPos;
			board.state[firstPos] = 0;
			
		// White piece taken over
		} else {
			board.whitePieces[board.state[secondPos] - 1].active = false;
			board.state[secondPos] = board.state[firstPos];
			board.blackPieces[board.state[firstPos] * (-1) - 1].location = secondPos;
			board.state[firstPos] = 0;
		}
		
		
	}
	
}

package board;

import java.util.Arrays;

import static board.Positions.*;

/**
 * Created by User on 5/14/17.
 */
public class Board {
    public Positions[] state = new Positions[144];

    public Board() {
        populateDefaultBoard(state);
    }

    private void populateDefaultBoard(Positions[] state) {
        populateBorders(state);
        populateEmptySpaces(state);
        populateWhitePieces(state);
        populateBlackPieces(state);
    }


    private void populateBorders(Positions[] state) {
        Arrays.fill(state, 0, 24, OUT_OF_BOUNDS);
        Arrays.fill(state, 120, 144, OUT_OF_BOUNDS);

        for (int position = 24; position < 120; position += 12) {
            Arrays.fill(state, position, position + 2, OUT_OF_BOUNDS);
            Arrays.fill(state, position + 10, position + 12, OUT_OF_BOUNDS);
        }
    }

    private void populateEmptySpaces(Positions[] state) {
        for (int position = 50; position < 98; position += 12) {
            Arrays.fill(state, position, position + 8, EMPTY);
        }
    }

    private void populateWhitePieces(Positions[] state) {
        for (int position = 38; position < 46; position++) {
            state[position] = W_PAWN;
        }

        state[26] = W_ROOK;
        state[27] = W_KNIGHT;
        state[28] = W_BISHOP;
        state[29] = W_QUEEN;
        state[30] = W_KING;
        state[31] = W_BISHOP;
        state[32] = W_KNIGHT;
        state[33] = W_ROOK;
    }

    private void populateBlackPieces(Positions[] state) {
        for (int position = 98; position < 106; position++) {
            state[position] = B_PAWN;
        }

        state[110] = B_ROOK;
        state[111] = B_KNIGHT;
        state[112] = B_BISHOP;
        state[113] = B_QUEEN;
        state[114] = B_KING;
        state[115] = B_BISHOP;
        state[116] = B_KNIGHT;
        state[117] = B_ROOK;
    }
}

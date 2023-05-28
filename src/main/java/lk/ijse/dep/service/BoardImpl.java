package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class BoardImpl implements Board {
    private Piece[][] pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];;
    private BoardUI boardUI;

    public BoardImpl(BoardController boardController) {
        this.boardUI = boardController;
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
    }

    @Override
    public BoardUI getBoardUi() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (this.pieces[col][i].equals(Piece.EMPTY)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j].equals(Piece.EMPTY)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)] = move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        for (int row = 0; row < NUM_OF_ROWS; row++) {
            for (int col = 0; col < pieces[0].length - 3; col++) {
                if (pieces[row][col].equals(Piece.BLUE) &&
                    pieces[row][col + 1].equals(Piece.BLUE) &&
                    pieces[row][col + 2].equals(Piece.BLUE) &&
                    pieces[row][col + 3].equals(Piece.BLUE)) {
                    return new Winner(Piece.BLUE, row, col, row, col + 3);
                } else if (pieces[row][col].equals(Piece.GREEN) &&
                           pieces[row][col + 1].equals(Piece.GREEN) &&
                           pieces[row][col + 2].equals(Piece.GREEN) &&
                           pieces[row][col + 3].equals(Piece.GREEN)) {
                    return new Winner(Piece.GREEN, row, col, row, col + 3);
                }
            }
        }

        for (int row = 0; row < NUM_OF_COLS - 3; row++) {
            for (int col = 0; col < pieces[0].length; col++) {
                if (pieces[row][col].equals(Piece.GREEN) &&
                    pieces[row + 1][col].equals(Piece.GREEN) &&
                    pieces[row + 2][col].equals(Piece.GREEN) &&
                    pieces[row + 3][col].equals(Piece.GREEN)) {
                    return new Winner(Piece.GREEN, row, col, row + 3, col);
                } else if (pieces[row][col].equals(Piece.BLUE) &&
                           pieces[row + 1][col].equals(Piece.BLUE) &&
                           pieces[row + 2][col].equals(Piece.BLUE) &&
                           pieces[row + 3][col].equals(Piece.BLUE)) {
                    return new Winner(Piece.BLUE, row, col, row + 3, col);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}
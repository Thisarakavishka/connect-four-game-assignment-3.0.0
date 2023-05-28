package lk.ijse.dep.service;

public class HumanPlayer extends Player{
    public HumanPlayer(Board newBoard) {
        super();
        this.board = newBoard;
    }

    @Override
    public void movePiece(int col) {
        super.movePiece(col);
        if(board.isLegalMove(col)){
            board.updateMove(col, Piece.BLUE);
            board.getBoardUi().update(col, true);
            if(board.findWinner().getWinningPiece().equals(Piece.BLUE)){
                board.getBoardUi().notifyWinner(board.findWinner());
            }else if(board.findWinner().getWinningPiece().equals(Piece.GREEN)){
                board.getBoardUi().notifyWinner(board.findWinner());
            }else if(!board.existLegalMoves()){
                board.getBoardUi().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}

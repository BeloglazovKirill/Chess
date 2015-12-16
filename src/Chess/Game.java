package Chess;

import Chess.Pieces.Piece;

import java.util.Set;


public class Game {
    private Board board;
    private boolean isWhiteTurn;

    public enum State {
        CHECKMATE, STALEMATE, CHECK, NOTHING
    }

    public enum PieceType {
        QUEEN, ROOK, BISHOP, KNIGHT
    }

    Game() {
        board = new Board();
        isWhiteTurn = true;
    }

    public Piece getPiece(Location loc) {
        return board.getPiece(loc);
    }

    public boolean isWhiteTurn(Location loc) {
        return (board.getColorPiece(loc) == isWhiteTurn);
    }

    public Set<Location> getMoves(Location loc) {
        return board.getMoves(loc);
    }

    public boolean isEmpty(Location loc) {
        return board.isEmpty(loc);
    }

    public boolean getColorPiece(Location loc) {
        return board.getColorPiece(loc);
    }

    public boolean pawnCheck(){
        return board.pawnCheck();
    }

    public void pawnChange(PieceType pt){
        board.pawnChange(pt);
    }

    public boolean motion(Location loc1, Location loc2) {
        if (board.isEmpty(loc1)) {
            return false;
        }
        Set<Location> possibleMoves = board.getMoves(loc1);
        if (possibleMoves != null && isWhiteTurn(loc1)) {
            for (Location temp : possibleMoves) {
                if (temp.equals(loc2)) {
//                    if (board.motion(loc1, loc2)){
                    board.motion(loc1, loc2);

                    isWhiteTurn = !isWhiteTurn;
                    return true;
//                    }
                }
            }
        }
        return false;
    }


    private boolean isCheck() {
        return !board.virtualCheck(isWhiteTurn);
    }


    public State getState(){
        if (!board.hasMoves(isWhiteTurn)){
            if (isCheck()){
                return State.CHECKMATE;
            } else {
                return State.STALEMATE;
            }
        }
        if (isCheck()) return State.CHECK;
        return State.NOTHING;
    }

    public boolean whoHasWon(){
        return isWhiteTurn;
    }
}

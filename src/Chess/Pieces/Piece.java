package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.List;

/**
 * Created by Kirill on 07.12.15.
 */
public abstract class Piece {
    private boolean isWhite;
    private Board boardState;
    protected Location loc;

    public Piece(boolean isWhite, Board boardState, Location loc){
        this.isWhite = isWhite;
        this.boardState = boardState;
        this.loc = loc;
    }

    public abstract List<Location> getMoves();

    public boolean isEmpty(Location loc){
        return boardState.isEmpty(loc);
    }

    public boolean isValid(Location loc){
        return boardState.isValid(loc);
    }

    public boolean isWhite(Location loc){
        return boardState.isWhite(loc);
    }

    protected boolean hasCastling(boolean isWhite, boolean isRight){
        return boardState.hasCastling(isWhite, isRight);
    }

    public boolean getWasMotion(){
        return true;
    }

    public void setLocation(Location loc){
        this.loc = loc;
    }

//    protected List<Location> cellUnderAttack(boolean color){
//        return boardState.cellUnderAttack(color);
//    }

    public boolean getIsCheck(){
        return false;
    }

    public boolean getColor(){
        return isWhite;
    }
}

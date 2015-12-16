package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.Iterator;
import java.util.Set;

public abstract class Piece {
    private final boolean isWhite;
    private final Board boardState;
    protected Location loc;

    public Piece(boolean isWhite, Board boardState, Location loc){
        this.isWhite = isWhite;
        this.boardState = boardState;
        this.loc = loc;
    }

    public abstract Set<Location> getMoves(boolean withVirtualMotion);

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

    public boolean getPawnCheck(){
        return false;
    }

    public void setPawnCheck(){
    }
    public boolean getWasMotion(){
        return true;
    }

    public void setLocation(Location loc){
        this.loc = loc;
    }

    public Location getLocation() {
        return loc;
    }

    public boolean getColor(){
        return isWhite;
    }

    protected void virtualMotion(Set<Location> possibleMoves){

        Iterator<Location> iter = possibleMoves.iterator();

        while(iter.hasNext()){
            Location locTemp = iter.next();
            if (!boardState.virtualMotion(loc, locTemp)){
                iter.remove();
            }
        }
    }

    @Override
    public int hashCode() {
        int result = (isWhite ? 1 : 0);
        return result;
    }

        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Piece)) return false;

        Piece piece = (Piece) o;
        return loc.equals(piece.loc);

    }

}

package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirill on 07.12.15.
 */
public class Rook extends Piece {
    private boolean wasMotion = false;
    public Rook(boolean isWhite, Board boardState, Location loc){
        super(isWhite, boardState, loc);
    }

    @Override
    public boolean getWasMotion() {
        return wasMotion;
    }

    @Override
    public void setLocation(Location loc) {
        super.setLocation(loc);
        wasMotion = true;
    }

    @Override
    public List<Location> getMoves() {
        ArrayList<Location> possibleMoves = new ArrayList<>();
        int x = this.loc.x;
        int y = this.loc.y;
        Location loc;
        while(true){
            y++;
            loc = new Location(x, y);
            if (isValid(loc)){
                if (isEmpty(loc)){
                    possibleMoves.add(loc);
                } else {
                    if (getColor() != isWhite(loc)){
                        possibleMoves.add(loc);
                        break;
                    } else break;
                }
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while(true){
            x++;
            loc = new Location(x, y);
            if (isValid(loc)){
                if (isEmpty(loc)){
                    possibleMoves.add(loc);
                } else {
                    if (getColor() != isWhite(loc)){
                        possibleMoves.add(loc);
                        break;
                    } else break;
                }
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while(true){
            y--;
            loc = new Location(x, y);
            if (isValid(loc)){
                if (isEmpty(loc)){
                    possibleMoves.add(loc);
                } else {
                    if (getColor() != isWhite(loc)){
                        possibleMoves.add(loc);
                        break;
                    } else break;
                }
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while(true){
            x--;
            loc = new Location(x, y);
            if (isValid(loc)){
                if (isEmpty(loc)){
                    possibleMoves.add(loc);
                } else {
                    if (getColor() != isWhite(loc)){
                        possibleMoves.add(loc);
                        break;
                    } else break;
                }
            } else break;
        }
        return possibleMoves;
    }
}

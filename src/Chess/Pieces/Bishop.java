package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.ArrayList;
import java.util.List;


public class Bishop extends Piece {
    public Bishop(boolean isWhite, Board boardState, Location loc){
        super(isWhite, boardState, loc);
    }

    @Override
    public List<Location> getMoves() {
        ArrayList<Location> possibleMoves = new ArrayList<>();
        int x =  this.loc.x;
        int y =  this.loc.y;
        Location loc;
        while(true){
            x--;
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
        x =  this.loc.x;
        y =  this.loc.y;
        while(true){
            x++;
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
        return possibleMoves;
    }
}

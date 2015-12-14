package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
    public Queen(boolean isWhite, Board boardState, Location loc){
        super(isWhite, boardState, loc);
    }

    @Override
    public Set<Location> getMoves() {
        Set<Location> possibleMoves = new HashSet<>();
        int x = this.loc.x;
        int y = this.loc.y;
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
        x = this.loc.x;
        y = this.loc.y;
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
        x = this.loc.x;
        y = this.loc.y;
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
        while(true) {
            x--;
            loc = new Location(x, y);
            if (isValid(loc)) {
                if (isEmpty(loc)) {
                    possibleMoves.add(loc);
                } else {
                    if (getColor() != isWhite(loc)) {
                        possibleMoves.add(loc);
                        break;
                    } else break;
                }
            } else break;
        }
//        virtualMotion(possibleMoves);
        return possibleMoves;
    }
}

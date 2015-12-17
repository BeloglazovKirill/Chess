package Chess.Pieces;

import Chess.GamingTools.Board;
import Chess.GamingTools.Location;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    public Pawn (boolean isWhite, Board boardState, Location loc){
        super(isWhite, boardState, loc);
    }

    private boolean hasMotion(Location loc, boolean color) {
        if (isValid(loc)){
            if (!isEmpty(loc)){
                if (color){
                    if (!isWhite(loc)){
                        return true;
                    }
                } else {
                    if (isWhite(loc)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hittenStep(Location loc1, Location loc2, Location loc3){
        if (isValid(loc1)){
            if (!isEmpty(loc1) && isEmpty(loc2) && isEmpty(loc3)){
                if (boardState.isPawn(loc1)){
                    if (isWhite(loc1) != getColor()){
                        if (boardState.getPawnDoubleStep(loc1)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Set<Location> getMoves(boolean withVirtualMotion) {
        Set<Location> possibleMoves = new HashSet<>();
        int x = loc.x;
        int y = loc.y;
        Location whiteUp = new Location(x, y + 1);
        Location white2Up = new Location(x, y + 2);
        Location whiteRight = new Location(x + 1, y + 1);
        Location whiteLeft = new Location(x - 1, y + 1);
        Location blackDown = new Location(x, y - 1);
        Location black2Down = new Location(x, y - 2);
        Location blackLeft = new Location(x - 1, y - 1);
        Location blackRight = new Location(x + 1, y - 1);
        if (getColor()){
            if (isValid(whiteUp)){
                if (isEmpty(whiteUp)){
                    possibleMoves.add(whiteUp);
                    if (y == 1){
                        if (isEmpty(white2Up)){
                            possibleMoves.add(white2Up);
                        }
                    }
                }
            }
            if (hasMotion(whiteLeft, true)) possibleMoves.add(whiteLeft);
            if (hasMotion(whiteRight, true)) possibleMoves.add(whiteRight);

            if (y == 4){
                Location tempLoc = new Location(x - 1, y);
                Location tempLoc2 = new Location(x - 1, y + 1);
                Location tempLoc3 = new Location(x - 1, y + 2);
                if (hittenStep(tempLoc, tempLoc2, tempLoc3)) possibleMoves.add(tempLoc2);
                tempLoc = new Location(x + 1, y);
                tempLoc2 = new Location(x + 1, y + 1);
                tempLoc3 = new Location(x + 1, y + 2);
                if (hittenStep(tempLoc, tempLoc2, tempLoc3)) possibleMoves.add(tempLoc2);
            }
        } else {
            if (isValid(blackDown)){
                if (isEmpty(blackDown)){
                    possibleMoves.add(blackDown);
                    if (y == 6){
                        if (isEmpty(black2Down)){
                            possibleMoves.add(black2Down);
                        }
                    }
                }
            }
            if (hasMotion(blackLeft, false)) possibleMoves.add(blackLeft);
            if (hasMotion(blackRight, false)) possibleMoves.add(blackRight);
            if (y == 3){
                Location tempLoc = new Location(x - 1, y);
                Location tempLoc2 = new Location(x - 1, y - 1);
                Location tempLoc3 = new Location(x - 1, y - 2);
                if (hittenStep(tempLoc, tempLoc2, tempLoc3)) possibleMoves.add(tempLoc2);
                tempLoc = new Location(x + 1, y);
                tempLoc2 = new Location(x + 1, y - 1);
                tempLoc3 = new Location(x + 1, y - 2);
                if (hittenStep(tempLoc, tempLoc2, tempLoc3)) possibleMoves.add(tempLoc2);
            }
        }
        if (withVirtualMotion) {
            virtualMotion(possibleMoves);
        }
        return possibleMoves;
    }
}

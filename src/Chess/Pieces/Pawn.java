package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    public Pawn (boolean isWhite, Board boardState, Location loc){
        super(isWhite, boardState, loc);
    }

    @Override
    public Set<Location> getMoves(boolean withVirtualMotion, boolean thisIsCheck) {
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
            if (isValid(whiteLeft)){
                if (!isEmpty(whiteLeft)){
                    if (!isWhite(whiteLeft)){
                        possibleMoves.add(whiteLeft);
                    }
                }
            }
            if (isValid(whiteRight)){
                if (!isEmpty(whiteRight)){
                    if (!isWhite(whiteRight)){
                        possibleMoves.add(whiteRight);
                    }
                }
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
            if (isValid(blackLeft)){
                if (!isEmpty(blackLeft)){
                    if (isWhite(blackLeft)){
                        possibleMoves.add(blackLeft);
                    }
                }
            }
            if (isValid(blackRight)){
                if (!isEmpty(blackRight)){
                    if (isWhite(blackRight)){
                        possibleMoves.add(blackRight);
                    }
                }
            }
        }
        if (withVirtualMotion) {
            virtualMotion(possibleMoves, thisIsCheck);
        }
        return possibleMoves;
    }
}

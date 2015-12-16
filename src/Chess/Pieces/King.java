package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.*;


public class King extends Piece {
    private boolean wasMotion = false;

    public King(boolean isWhite, Board boardState, Location loc) {
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
    public Set<Location> getMoves(boolean withVirtualMotion, boolean thisIsCheck) {
        Set<Location> possibleMoves = new HashSet<>();
        int x = this.loc.x;
        int y = this.loc.y;

        Location upLeft = new Location(x - 1, y + 1);
        Location up = new Location(x, y + 1);
        Location upRight = new Location(x + 1, y + 1);
        Location right = new Location(x + 1, y);
        Location downRight = new Location(x + 1, y - 1);
        Location down = new Location(x, y - 1);
        Location downLeft = new Location(x - 1, y - 1);
        Location left = new Location(x - 1, y);
        Location castlingRight = new Location(x + 2, y);
        Location castlingLeft = new Location(x - 2, y);

        if (hasMotion(upLeft)) possibleMoves.add(upLeft);
        if (hasMotion(up)) possibleMoves.add(up);
        if (hasMotion(upRight)) possibleMoves.add(upRight);
        if (hasMotion(downRight)) possibleMoves.add(downRight);
        if (hasMotion(down)) possibleMoves.add(down);
        if (hasMotion(downLeft)) possibleMoves.add(downLeft);
        if (hasMotion(right)) {
            possibleMoves.add(right);
            if (isEmpty(right)) {
                if (!wasMotion) {
                    if (isEmpty(castlingRight)) {
                        if (hasCastling(getColor(), true)) {
                            possibleMoves.add(castlingRight);
                        }
                    }
                }
            }
        }
        if (hasMotion(left)) {
            possibleMoves.add(left);
            if (isEmpty(left)) {
                if (!wasMotion) {
                    if (isEmpty(castlingLeft)) {
                        if (isEmpty(new Location(x - 3, y))) {
                            if (hasCastling(getColor(), false)) {
                                possibleMoves.add(castlingLeft);
                            }
                        }
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

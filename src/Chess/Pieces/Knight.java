package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    public Knight(boolean isWhite, Board boardState, Location loc) {
        super(isWhite, boardState, loc);
    }

    @Override
    public Set<Location> getMoves() {
        int x = loc.x;
        int y = loc.y;
        Set<Location> possibleMoves = new HashSet<>();
        Location[] moves = {
                new Location(x + 1, y + 2),
                new Location(x + 2, y + 1),
                new Location(x + 2, y - 1),
                new Location(x + 1, y - 2),
                new Location(x - 1, y + 2),
                new Location(x - 2, y + 1),
                new Location(x - 2, y - 1),
                new Location(x - 1, y - 2),
        };

        for (int i = 0; i < 8; i++) {
            if (hasMotion(moves[i])) possibleMoves.add(moves[i]);
        }
//        virtualMotion(possibleMoves);
        return possibleMoves;
    }

    private boolean hasMotion(Location loc) {
        if (isValid(loc)) {
            if (isEmpty(loc)) {
                return true;
            } else {
                if (getColor() != isWhite(loc)) {
                    return true;
                }
            }
        }
        return false;
    }
}

package Chess.Pieces;

import Chess.GamingTools.Board;
import Chess.GamingTools.Location;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    public Knight(boolean isWhite, Board boardState, Location loc) {
        super(isWhite, boardState, loc);
    }

    @Override
    public Set<Location> getMoves(boolean withVirtualMotion) {
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
        if (withVirtualMotion) {
            virtualMotion(possibleMoves);
        }
        return possibleMoves;
    }
}

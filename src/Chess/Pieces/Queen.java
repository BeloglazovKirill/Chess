package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
    public Queen(boolean isWhite, Board boardState, Location loc) {
        super(isWhite, boardState, loc);
    }

    @Override
    public Set<Location> getMoves(boolean withVirtualMotion, boolean thisIsCheck) {
        Set<Location> possibleMoves = new HashSet<>();
        int x = this.loc.x;
        int y = this.loc.y;
        Location loc;
        while (true) {
            x--;
            y++;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while (true) {
            x++;
            y++;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while (true) {
            x++;
            y--;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while (true) {
            x--;
            y--;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while (true) {
            y++;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while (true) {
            x++;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while (true) {
            y--;
            loc = new Location(x, y);

            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while (true) {
            x--;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        if (withVirtualMotion) {
            virtualMotion(possibleMoves, thisIsCheck);
        }
        return possibleMoves;
    }
}

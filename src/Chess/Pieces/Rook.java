package Chess.Pieces;

import Chess.GamingTools.Board;
import Chess.GamingTools.Location;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    private boolean wasMotion = false;
    public Rook(boolean isWhite, Board boardState, Location loc){
        super(isWhite, boardState, loc);
    }

    public boolean getWasMotion() {
        return wasMotion;
    }

    @Override
    public void step(Location loc) {
        super.step(loc);
        wasMotion = true;
    }

    @Override
    public Set<Location> getMoves(boolean withVirtualMotion) {
        Set<Location> possibleMoves = new HashSet<>();
        int x = this.loc.x;
        int y = this.loc.y;
        Location loc;
        while(true){
            y++;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while(true){
            x++;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while(true){
            y--;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        x = this.loc.x;
        y = this.loc.y;
        while(true){
            x--;
            loc = new Location(x, y);
            if (hasMotion(loc)) {
                possibleMoves.add(loc);
                if (!isEmpty(loc)) break;
            } else break;
        }
        if (withVirtualMotion) {
            virtualMotion(possibleMoves);
        }
        return possibleMoves;
    }
}

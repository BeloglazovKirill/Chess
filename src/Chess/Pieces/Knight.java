package Chess.Pieces;

import Chess.Board;
import Chess.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirill on 07.12.15.
 */
public class Knight extends Piece {
    public Knight(boolean isWhite, Board boardState, Location loc){
        super(isWhite, boardState, loc);
    }

    @Override
    public List<Location> getMoves() {
        return new ArrayList<>();
    }
}

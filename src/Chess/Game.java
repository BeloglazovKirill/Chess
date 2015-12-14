package Chess;

import Chess.Pieces.Piece;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Created by Kirill on 07.12.15.
 */
public class Game {
    private Board board;
    private boolean isWhiteTurn;

    Game(){
        board = new Board();
        isWhiteTurn = true;
    }

    public boolean isGameOver(){

        return false;
    }

    public Piece getPiece(Location loc){
        return board.getPiece(loc);
    }

//    public boolean isCheck(boolean isBlack){
//
//    }
    public boolean isWhiteTurn(Location loc){
        return (board.getColorPiece(loc) == isWhiteTurn);
    }

    public List<Location> getMoves(Location loc){
        return board.getMoves(loc);
    }

    public boolean isEmpty(Location loc){
        return board.isEmpty(loc);
    }
    public boolean getColorPiece(Location loc){
        return board.getColorPiece(loc);
    }

    public boolean motion(Location loc1, Location loc2) {
        if (board.isEmpty(loc1)) {
            return false;
        }
        List<Location> possibleMoves = board.getMoves(loc1);
        if (possibleMoves != null && isWhiteTurn(loc1)){
            for (Location temp: possibleMoves){
                if (temp.equals(loc2)){
                    if (board.motion(loc1, loc2)){
                        isWhiteTurn = !isWhiteTurn;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

package Chess;

import Chess.Pieces.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Board {
    private Piece[][] boardState = new Piece[8][8];
    Board(){
        arrange();
    }

    public Piece getPiece(Location loc){
        return boardState[loc.x][loc.y];
    }

    private Set<Location> cellUnderAttack(boolean color){
        Set<Location> set = new HashSet<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = boardState[x][y];
                if (piece != null){
                    if (piece.getColor() != color){
                        for (Location locs : piece.getMoves()){
                            set.add(locs);
                        }
                    }
                }
            }
        }
        return set;
    }
    
    public boolean isEmpty(Location loc){
        if (boardState[loc.x][loc.y] == null) return true;
        return false;
    }

    public boolean isValid(Location loc){
        if (loc.x >= 0 && loc.x <= 7 && loc.y >= 0 && loc.y <= 7) return true;
        return false;
    }

    public boolean isWhite(Location loc){
        if (boardState[loc.x][loc.y].getColor()) return true;
        return false;
    }

    public List<Location> getMoves(Location loc){
        if (boardState[loc.x][loc.y] == null){
            return null;
        } else {
            return boardState[loc.x][loc.y].getMoves();

        }
    }

    public boolean getColorPiece(Location loc){
        return (boardState[loc.x][loc.y].getColor());
    }

    public boolean motion(Location loc1, Location loc2){

        if(virtualMotion(loc1, loc2)){
            Piece piece1 = boardState[loc1.x][loc1.y];
            Piece piece2 = boardState[loc2.x][loc2.y];
            if (piece1 instanceof King){
                    if (loc1.x + 2 == loc2.x && loc1.y == loc2.y){
                        step(new Location(7, loc1.y), new Location(5, loc1.y));
                    }
                    if (loc1.x - 2 == loc2.x && loc1.y == loc2.y){
                        step(new Location(0, loc1.y), new Location(3, loc1.y));
                    }
            }
            step(loc1, loc2);
            return true;
        }
        return false;

    }

    private Piece step(Location loc1, Location loc2){
        return step(loc1, loc2, null);
    }

    private Piece step(Location loc1, Location loc2, Piece old) {
        boardState[loc1.x][loc1.y].setLocation(loc2);
        Piece piece = boardState[loc2.x][loc2.y];
        boardState[loc2.x][loc2.y] = boardState[loc1.x][loc1.y];
        boardState[loc1.x][loc1.y] = old;
        return piece;
    }



    private boolean virtualMotion(Location loc1, Location loc2){
        boolean color = getColorPiece(loc1);
        Piece piece = step(loc1, loc2);
        Set<Location> cellUnderAttack = cellUnderAttack(color);
        boolean result = true;

        main_loop:
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (boardState[x][y] instanceof King && (boardState[x][y].getColor() != color)){
                    if (cellUnderAttack.contains(new Location(x, y))) {
                        result = false;
                        break main_loop;
                    }
                }
            }
        }
        step(loc2, loc1, piece);
        return result;
    }

    public boolean hasCastling(boolean isWhite, boolean isRight){
        if (isWhite){
            if (isRight){
                Location loc = new Location(7, 0);
                if (isRook(loc)){
                    if (!wasMotion(loc)){
                        return true;
                    }
                }
            } else {
                Location loc = new Location(0, 0);
                if (isRook(loc)){
                    if (!wasMotion(loc)){
                        return true;
                    }
                }
            }
        } else {
            if (isRight){
                Location loc = new Location(7, 7);
                if (isRook(loc)) {
                    if (!wasMotion(loc)) {
                        return true;
                    }
                }
            } else {
                Location loc = new Location(0, 7);
                if (isRook(loc)) {
                    if (!wasMotion(loc)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isRook(Location loc){
        if (boardState[loc.x][loc.y] instanceof Rook){
            return true;
        }
        return false;
    }

    private boolean wasMotion(Location loc){
        return boardState[loc.x][loc.y].getWasMotion();
    }

    private void arrange(){
        for (int x = 0; x < boardState.length; x++) {
            for (int y = 0; y < boardState.length; y++) {
                switch (y){
                    case 0: {
                        switch (x){
                            case 0: boardState[x][y] = new Rook(true, this, new Location(x,y)); break;
                            case 1: boardState[x][y] = new Knight(true, this, new Location(x,y)); break;
                            case 2: boardState[x][y] = new Bishop(true, this, new Location(x,y)); break;
                            case 3: boardState[x][y] = new Queen(true, this, new Location(x,y)); break;
                            case 4: boardState[x][y] = new King(true, this, new Location(x,y)); break;
                            case 5: boardState[x][y] = new Bishop(true, this, new Location(x,y)); break;
                            case 6: boardState[x][y] = new Knight(true, this, new Location(x,y)); break;
                            case 7: boardState[x][y] = new Rook(true, this, new Location(x,y)); break;
                        }
                        break;
                    }
                    case 1: {
                        boardState[x][y] = new Pawn(true, this, new Location(x,y));
                        break;
                    }
                    case 6: {
                        boardState[x][y] = new Pawn(false, this, new Location(x,y));
                        break;
                    }
                    case 7: {
                        switch (x){
                            case 0: boardState[x][y] = new Rook(false, this, new Location(x,y)); break;
                            case 1: boardState[x][y] = new Knight(false, this, new Location(x,y)); break;
                            case 2: boardState[x][y] = new Bishop(false, this, new Location(x,y)); break;
                            case 3: boardState[x][y] = new Queen(false, this, new Location(x,y)); break;
                            case 4: boardState[x][y] = new King(false, this, new Location(x,y)); break;
                            case 5: boardState[x][y] = new Bishop(false, this, new Location(x,y)); break;
                            case 6: boardState[x][y] = new Knight(false, this, new Location(x,y)); break;
                            case 7: boardState[x][y] = new Rook(false, this, new Location(x,y)); break;
                        }
                        break;
                    }
                }
            }
        }
    }
}

package Chess;

import Chess.Pieces.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Board {
    private Set<Piece> pieceSet = new HashSet<>();
    private Piece[][] boardState = new Piece[8][8];

    public Board() {
        arrange();
    }

    public Piece getPiece(Location loc) {
        return boardState[loc.x][loc.y];
    }

    private Set<Location> cellUnderAttack(boolean color) {
        Set<Location> set = new HashSet<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = boardState[x][y];
                if (piece != null) {
                    if (piece.getColor() != color) {
                        for (Location locs : piece.getMoves(false)) {
                            set.add(locs);
                        }
                    }
                }
            }
        }
        return set;
    }

    public boolean isEmpty(Location loc) {
        if (boardState[loc.x][loc.y] == null) return true;
        return false;
    }

    public boolean isValid(Location loc) {
        if (loc.x >= 0 && loc.x <= 7 && loc.y >= 0 && loc.y <= 7) return true;
        return false;
    }

    public boolean isWhite(Location loc) {
        if (boardState[loc.x][loc.y].getColor()) return true;
        return false;
    }

    public Set<Location> getMoves(Location loc) {
        if (boardState[loc.x][loc.y] == null) {
            return null;
        } else {
            return boardState[loc.x][loc.y].getMoves(true);
        }
    }

    public boolean getColorPiece(Location loc) {
        return (boardState[loc.x][loc.y].getColor());
    }

    public void motion(Location loc1, Location loc2) {
        Piece piece1 = boardState[loc1.x][loc1.y];
        if (piece1 instanceof King) {
            if (loc1.x + 2 == loc2.x && loc1.y == loc2.y) {
                step(new Location(7, loc1.y), new Location(5, loc1.y), null);
            }
            if (loc1.x - 2 == loc2.x && loc1.y == loc2.y) {
                step(new Location(0, loc1.y), new Location(3, loc1.y), null);
            }
        }
        step(loc1, loc2);
    }

    public boolean pawnCheck() {
        for (Piece p : pieceSet) {
            if (p.getPawnCheck()) {
                return true;
            }
        }
        return false;
    }


    public void pawnChange(Game.PieceType pt) {
        Piece piece = null;
        Location mainLoc = null;
        for (Piece p : pieceSet) {
            if (p.getPawnCheck()) {
                piece = p;
                boolean color = p.getColor();
                Location loc = p.getLocation();
                mainLoc = loc;
                switch (pt) {
                    case QUEEN: {
                        boardState[loc.x][loc.y] = new Queen(color, this, loc);
                        break;
                    }
                    case ROOK: {
                        boardState[loc.x][loc.y] = new Rook(color, this, loc);
                        break;
                    }
                    case BISHOP: {
                        boardState[loc.x][loc.y] = new Bishop(color, this, loc);
                        break;
                    }
                    case KNIGHT: {
                        boardState[loc.x][loc.y] = new Knight(color, this, loc);
                        break;
                    }
                }
                break;
            }
        }
        pieceSet.remove(piece);
        pieceSet.add(boardState[mainLoc.x][mainLoc.y]);
    }

    private Piece step(Location loc1, Location loc2) {
        Piece piece = step(loc1, loc2, null);
        Piece tempPiece = boardState[loc2.x][loc2.y];
        if (tempPiece instanceof Pawn && (loc2.y == 0 || loc2.y == 7)) {
            tempPiece.setPawnCheck();
        }
        return piece;
    }

    private Piece step(Location loc1, Location loc2, Piece old) {
        boardState[loc1.x][loc1.y].setLocation(loc2);
        Piece piece = boardState[loc2.x][loc2.y];
        boardState[loc2.x][loc2.y] = boardState[loc1.x][loc1.y];
        boardState[loc1.x][loc1.y] = old;
        if (piece != null) {
            pieceSet.remove(piece);
        }
        if (old != null) {
            pieceSet.add(old);
        }
        return piece;
    }


    public boolean virtualMotion(Location loc1, Location loc2) {
        boolean color = getColorPiece(loc1);
        Piece piece = step(loc1, loc2, null);
        boolean result = virtualCheck(color);
        step(loc2, loc1, piece);
        return result;
    }

    public boolean virtualCheck(boolean isWhite) {
        boolean result = true;
        Set<Location> cellUnderAttack = cellUnderAttack(isWhite);
        for (Piece p : pieceSet) {
            if (p instanceof King && (p.getColor() == isWhite)) {
                if (cellUnderAttack.contains(p.getLocation())) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public boolean hasMoves(boolean isWhite) {
        for (Piece p : pieceSet) {
            if (p.getColor() == isWhite) {
                if (!p.getMoves(true).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean hasCastling(boolean isWhite, boolean isRight) {
        if (isWhite) {
            if (isRight) {
                Location loc = new Location(7, 0);
                if (isRook(loc)) {
                    if (!wasMotion(loc)) {
                        return true;
                    }
                }
            } else {
                Location loc = new Location(0, 0);
                if (isRook(loc)) {
                    if (!wasMotion(loc)) {
                        return true;
                    }
                }
            }
        } else {
            if (isRight) {
                Location loc = new Location(7, 7);
                if (isRook(loc)) {
                    if (!wasMotion(loc)) {
                        return true;
                    }
                }
            } else {
                Location loc = new Location(0, 7);
                if (isRook(loc)) {
                    if (!wasMotion(loc)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isRook(Location loc) {
        if (boardState[loc.x][loc.y] instanceof Rook) {
            return true;
        }
        return false;
    }

    private boolean wasMotion(Location loc) {
        return boardState[loc.x][loc.y].getWasMotion();
    }

    private void arrange() {
        for (int x = 0; x < boardState.length; x++) {
            for (int y = 0; y < boardState.length; y++) {
                switch (y) {
                    case 0: {
                        switch (x) {
                            case 0:
                                boardState[x][y] = new Rook(true, this, new Location(x, y));
                                break;
                            case 1:
                                boardState[x][y] = new Knight(true, this, new Location(x, y));
                                break;
                            case 2:
                                boardState[x][y] = new Bishop(true, this, new Location(x, y));
                                break;
                            case 3:
                                boardState[x][y] = new Queen(true, this, new Location(x, y));
                                break;
                            case 4:
                                boardState[x][y] = new King(true, this, new Location(x, y));
                                break;
                            case 5:
                                boardState[x][y] = new Bishop(true, this, new Location(x, y));
                                break;
                            case 6:
                                boardState[x][y] = new Knight(true, this, new Location(x, y));
                                break;
                            case 7:
                                boardState[x][y] = new Rook(true, this, new Location(x, y));
                                break;
                        }
                        break;
                    }
                    case 1: {
                        boardState[x][y] = new Pawn(true, this, new Location(x, y));
                        break;
                    }
//                    case 6: {
//                        boardState[x][y] = new Pawn(false, this, new Location(x, y));
//                        break;
//                    }
                    case 7: {
                        switch (x) {
                            case 0:
                                boardState[x][y] = new Rook(false, this, new Location(x, y));
                                boardState[2][6] = new Pawn(true, this, new Location(2, 6));
                                break;
                            case 1:
                                boardState[x][y] = new Knight(false, this, new Location(x, y));
                                break;
                            case 2:
//                                boardState[x][y] = new Bishop(false, this, new Location(x, y));
                                break;
                            case 3:
                                boardState[x][y] = new Queen(false, this, new Location(x, y));
                                break;
                            case 4:
                                boardState[x][y] = new King(false, this, new Location(x, y));
                                break;
                            case 5:
                                boardState[x][y] = new Bishop(false, this, new Location(x, y));
                                break;
                            case 6:
                                boardState[x][y] = new Knight(false, this, new Location(x, y));
                                break;
                            case 7:
                                boardState[x][y] = new Rook(false, this, new Location(x, y));
                                break;
                        }
                        break;
                    }
                }
                if (boardState[x][y] != null) {
                    pieceSet.add(boardState[x][y]);
                }

            }
        }
    }
}

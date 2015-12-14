package Chess;

import Chess.Pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.*;

/**
 * Created by Kirill on 09.12.15.
 */
public class ChessPanel extends JPanel {
    private Game game;
    private int dimension;
    private Location loc1, loc2;
    private List<Location> possibleMoves;
    private Point point = new Point();
    private Image blackBishop, blackKing, blackKnight, blackPawn, blackQueen, blackRook,
            whiteBishop, whiteKing, whiteKnight, whitePawn, whiteQueen, whiteRook;
    private Map<String, Image> images = new HashMap<>();

    public ChessPanel() {

        game = new Game();
        repaint();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int y = 7 - e.getY() / dimension;
                int x = e.getX() / dimension;
                if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
                    point.setLocation(x, y);
//                    System.out.println(point);
                    motion(point);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        try {
            String[] names = {"Bishop", "King", "Knight", "Pawn", "Queen", "Rook"};
            for (String name : names) {
                images.put("black" + name, ImageIO.read(new File("src/images/black" + name + ".png")));
                images.put("white" + name, ImageIO.read(new File("src/images/white" + name + ".png")));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private void miniFunc1(Location loc) {
        loc1 = new Location((int) point.getX(), (int) point.getY());
        if (!game.isEmpty(loc1)) {
            if (game.isWhiteTurn(loc1)) {
                possibleMoves = game.getMoves(loc1);
                repaint();
            }
        }
    }

    private void motion(Point point) {
        if (loc1 == null) {
            miniFunc1(loc1);
        } else {
            loc2 = new Location((int) point.getX(), (int) point.getY());
            if (!game.isEmpty(loc2)) {
                if (game.getColorPiece(loc1) == game.getColorPiece(loc2)) {
                    loc1 = loc2;
                    loc2 = null;
                    miniFunc1(loc1);
                    return;
                }
            }
            possibleMoves = null;
            repaint();
            if (game.motion(loc1, loc2)) {
                repaint();
            }
            loc1 = null;
            loc2 = null;
        }
//        if (game.isGameOver()){
//
//        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        dimension = getWidth() > getHeight() ? getHeight() / 8 : getWidth() / 8;
        g.setColor(Color.black);
        for (int i = 0; i < 9; i++) {
            g.drawLine(0, i * dimension, 8 * dimension, i * dimension);
            g.drawLine(i * dimension, 0, i * dimension, 8 * dimension);
        }
        if (possibleMoves != null) {
            g.setColor(Color.RED);
            for (Location temp : possibleMoves) {
                g.drawRect(temp.x * dimension, (7 - temp.y) * dimension, dimension, dimension);
            }
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = game.getPiece(new Location(x, 7 - y));
                if (piece != null) {
                    String name = (piece.getColor() ? "white" : "black") + piece.getClass().getSimpleName();
                    g.drawImage(images.get(name), x * dimension + 1, y * dimension + 1, dimension - 1, dimension - 1, null);
                }
            }
        }
    }
}

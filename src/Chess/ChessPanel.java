package Chess;

import Chess.GamingTools.Game;
import Chess.GamingTools.Location;
import Chess.Pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Set;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.*;

public class ChessPanel extends JPanel {
    private Game game;
    private int dimension;
    private Location loc1, loc2;
    private Set<Location> possibleMoves;
    private Point point = new Point();
    private Map<String, Image> images = new HashMap<>();
    private Image background;

    public ChessPanel() {

        game = new Game();
        repaint();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

                int y = 7 - e.getY() / dimension;
                int x = e.getX() / dimension;
                if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
                    point.setLocation(x, y);
                    motion(point);
                }
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
            background = ImageIO.read(new File("src/images/background.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private void chooseLoc() {
        loc1 = new Location((int) point.getX(), (int) point.getY());
        if (!game.isEmpty(loc1)) {
            if (game.isWhiteTurn(loc1)) {
                possibleMoves = game.getMoves(loc1);
                repaint();
                return;
            }
        }
        loc1 = null;
    }

    private void motion(Point point) {
        if (loc1 == null) {
            chooseLoc();
        } else {
            loc2 = new Location((int) point.getX(), (int) point.getY());
            if (!game.isEmpty(loc2)) {
                if (game.getColorPiece(loc1) == game.getColorPiece(loc2)) {
                    loc1 = loc2;
                    loc2 = null;
                    chooseLoc();
                    return;
                }
            }
            possibleMoves = null;
            repaint();
            if (game.motion(loc1, loc2)) {
                if (game.pawnCheck()){
                    JDialog pawnDialog = new JDialog();
                    pawnDialog.setTitle("Выберите фигуру");
                    pawnDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    pawnDialog.setSize(500,200);
                    pawnDialog.setResizable(false);
                    pawnDialog.setLocationRelativeTo(null);
                    GridBagLayout g = new GridBagLayout();
                    GridBagConstraints cq = new GridBagConstraints();
                    pawnDialog.setLayout(g);
                    pawnDialog.setAlwaysOnTop(true);
                    JButton[] jButtons = new JButton[4];
                    jButtons[0] = new JButton("КОРОЛЕВА");

                    jButtons[0].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.pawnChange(Game.PieceType.QUEEN);
                            repaint();
                            pawnDialog.dispose();
                        }
                    });
                    jButtons[1] = new JButton("СЛОН");
                    jButtons[1].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.pawnChange(Game.PieceType.BISHOP);
                            repaint();
                            pawnDialog.dispose();
                        }
                    });
                    jButtons[2] = new JButton("КОНЬ");
                    jButtons[2].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.pawnChange(Game.PieceType.KNIGHT);
                            repaint();
                            pawnDialog.dispose();
                        }
                    });
                    jButtons[3] = new JButton("ЛАДЬЯ");
                    jButtons[3].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.pawnChange(Game.PieceType.ROOK);
                            repaint();
                            pawnDialog.dispose();
                        }
                    });
                    for (int i = 0; i < 4; i++) {
                        jButtons[i].setFont(new Font("Verdana", Font.BOLD, 12));
                        jButtons[i].setSize(50,50);
                        pawnDialog.add(jButtons[i],cq);
                    }
                    pawnDialog.setVisible(true);
                    repaint();
                }
                Game.State state = game.getState();
                if (state != Game.State.NOTHING){
                    JDialog jDialog = new JDialog();
                    jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    jDialog.setSize(500, 100);
                    jDialog.setResizable(false);
                    JLabel jLabel = new JLabel();
                    jLabel.setFont(new Font("Verdana", Font.BOLD, 32));
                    jLabel.setHorizontalAlignment(jLabel.CENTER);
                    jLabel.setHorizontalAlignment(jLabel.CENTER);
                    if (state == Game.State.CHECKMATE){
                        jDialog.setTitle("ШАХ-И-МАТ");
                        if (!game.whoHasWon()){
                            jLabel.setText("Белые победили");
                        } else {
                            jLabel.setText("Чёрные победили");
                        }
                    }
                    if (state == Game.State.STALEMATE){
                        jDialog.setTitle("ПАТ");
                        jLabel.setText("Ничья");
                    }
                    if (state == Game.State.CHECK) {
                        jDialog.setTitle("ШАХ");
                        jLabel.setText("Шах");
                    }
                    jDialog.add(jLabel);
                    jDialog.setLocationRelativeTo(this);
                    jDialog.setVisible(true);
                    jDialog.setAlwaysOnTop(true);
                }
            }
            loc1 = null;
            loc2 = null;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        dimension = getWidth() > getHeight() ? getHeight() / 8 : getWidth() / 8;
        g.drawImage(background, 0, 0, 8 * dimension, 8 * dimension, null);
        g.setColor(Color.BLACK);
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

package Chess;

import javax.swing.*;

public class Main extends JFrame {

    private Main(){
        super("Шахматы");
        setSize(761, 800);
        add(new ChessPanel());
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}

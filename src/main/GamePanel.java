package main;

import piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 640;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    // Pieces
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    Piece activePiece;

    // Colors
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    // Movement
    boolean canMove;
    boolean validSquare;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.darkGray);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces, simPieces);
    }
    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPieces() {
    // White Pieces
        // Pawns
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(WHITE, i, 6));
        }

        // Other Pieces
        pieces.add(new Rook(WHITE, 0, 7));
        pieces.add(new Rook(WHITE, 7, 7));
        pieces.add(new Knight(WHITE, 1, 7));
        pieces.add(new Knight(WHITE, 6, 7));
        pieces.add(new Bishop(WHITE, 2, 7));
        pieces.add(new Bishop(WHITE, 5, 7));
        pieces.add(new Queen(WHITE, 3, 7));
        pieces.add(new King(WHITE, 4, 7));

    // Black Pieces
        // Pawns
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(BLACK, i, 1));
        }

        // Other Pieces
        pieces.add(new Rook(BLACK, 0, 0));
        pieces.add(new Rook(BLACK, 7, 0));
        pieces.add(new Knight(BLACK, 1, 0));
        pieces.add(new Knight(BLACK, 6, 0));
        pieces.add(new Bishop(BLACK, 2, 0));
        pieces.add(new Bishop(BLACK, 5, 0));
        pieces.add(new Queen(BLACK, 3, 0));
        pieces.add(new King(BLACK, 4, 0));
    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();

        target.addAll(source);
    }

    private void update() {
        if(mouse.pressed) {
            if (activePiece == null) {
                for (Piece piece : simPieces) {

                    if (piece.color == currentColor &&
                            piece.col == mouse.x / Board.SQUARE_SIZE &&
                            piece.row == mouse.y / Board.SQUARE_SIZE) {

                        activePiece = piece;
                    }
                }
            } else {
                simulate();
            }
        }
        if (!mouse.pressed) {
            if (activePiece != null) {
                if (validSquare) {

                    copyPieces(simPieces, pieces);
                    activePiece.updatePos();
                }
                else {
                    copyPieces(pieces, simPieces);
                    activePiece.resetPos();
                    activePiece = null;
                }
            }
        }
    }

    private void simulate() {

        canMove = false;
        validSquare = false;

        copyPieces(pieces, simPieces);

        activePiece.x = mouse.x - Board.HALF_SQUARE_SIZE;
        activePiece.y = mouse.y - Board.HALF_SQUARE_SIZE;
        activePiece.col = activePiece.getCol(activePiece.x);
        activePiece.row = activePiece.getRow(activePiece.y);

        if (activePiece.canMove(activePiece.col, activePiece.row)) {
            canMove = true;

            if (activePiece.hittingPiece != null) {
                simPieces.remove(activePiece.hittingPiece.getIndex());
            }

            validSquare = true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        // Board
        board.draw(g2);

        // Pieces
        for (Piece p : simPieces){
            p.draw(g2);
        }

        if (activePiece != null) {
            if (canMove) {
                g2.setColor(Color.white);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activePiece.col * Board.SQUARE_SIZE, activePiece.row * Board.SQUARE_SIZE,
                        Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }

            activePiece.draw(g2);
        }
    }

    @Override
    public void run() {
        // Game loop
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
}

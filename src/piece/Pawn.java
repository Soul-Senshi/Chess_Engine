package piece;

import main.GamePanel;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/White_Pawn");
        } else {
            image = getImage("/piece/Black_Pawn");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {


        return false;
    }
}

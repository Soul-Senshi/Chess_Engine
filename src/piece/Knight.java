package piece;

import main.GamePanel;

public class Knight extends Piece{
    public Knight(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/White_Knight");
        }
        else {
            image = getImage("/piece/Black_Knight");
        }
    }
    public boolean canMove(int targetCol, int targetRow){
        if (isInBoard(targetCol, targetRow)) {
            if (Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2) {
                if (isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }

}

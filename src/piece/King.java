package piece;

import main.GamePanel;

public class King extends Piece {
    public King(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/White_King");
        } else {
            image = getImage("/piece/Black_King");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        // Check if the positions chosen are valid.
        if (isInBoard(targetCol, targetRow)) {
            if (Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 ||
                    Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1){
                if (isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}

package piece;

import main.GamePanel;

public class Bishop extends Piece{
    public Bishop(int color, int col, int row) {
        super(color, col, row);

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/White_Bishop");
        }
        else {
            image = getImage("/piece/Black_Bishop");
        }
    }


    public boolean canMove(int targetCol, int targetRow) {
        if (isInBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                if (isValidSquare(targetCol, targetRow) && !isOnDiagonal(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}

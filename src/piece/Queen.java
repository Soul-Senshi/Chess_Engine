package piece;

import main.GamePanel;

public class Queen extends Piece{
    public Queen(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.WHITE) {
            image = getImage("/piece/White_Queen");
        }
        else {
            image = getImage("/piece/Black_Queen");
        }
    }
    public boolean canMove(int targetCol, int targetRow) {
        if (isInBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)){
            // Horizontal Movement
            if (targetCol == preCol || targetRow == preRow) {
                if (isValidSquare(targetCol, targetRow) && !isOnStraightLine(targetCol, targetRow)) {
                    return true;
                }
            }
            // Vertical Movement
            if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                if(isValidSquare(targetCol, targetRow) && !isOnDiagonal(targetCol, targetRow)){
                    return true;
                }
            }

        }
        return false;
    }

}

package piece;

import main.Board;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {
    public BufferedImage image;
    public int x;
    public int y;
    public int col, row, preCol, preRow;
    public int color;
    public Piece hittingPiece;
    public Piece(int color, int col, int row) {
        this.color = color;
        this.row = row;
        this.col = col;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }

    public BufferedImage getImage(String imagePath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath  + ".png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col) {
        return col * Board.SQUARE_SIZE;
    }
    public int getY(int row) {
        return row * Board.SQUARE_SIZE;
    }
    public int getCol(int x) {
        return (x + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }
    public int getRow(int y) {
        return (y + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }
    public int getIndex() {
        for (int index = 0; index < GamePanel.simPieces.size(); index++) {
            if (GamePanel.simPieces.get(index) == this) {
                return index;
            }
        }
        return 0;
    }


    public void updatePos() {
        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
    }

    public void resetPos() {
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

    public boolean canMove(int targetCol, int targetRow) {
        return false;
    }
    public boolean isInBoard(int targetCol, int targetRow){
        if (targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7) {
            return true;
        }
        return false;
    }

    public boolean isSameSquare(int targetCol, int targetRow) {
        if (targetCol == preCol && targetRow == preRow) {
            return true;
        }
        return false;
    }

    public Piece isHittingPiece(int targetCol, int targetRow) {
        for(Piece piece : GamePanel.simPieces) {
            if (piece.col == targetCol && piece.row == targetRow && piece != this) {
                return piece;
            }
        }
        return null;
    }

    public boolean isValidSquare(int targetCol, int targetRow) {
        hittingPiece = isHittingPiece(targetCol, targetRow);
        if (hittingPiece == null) {
            return true;
        }
        else {
            if (hittingPiece.color != this.color) {
                return true;
            }
            else {
                hittingPiece = null;
            }
        }

        return false;
    }

    public boolean isOnStraightLine(int targetCol, int targetRow) {

        // If the piece is moving to the left
        for (int c = preCol-1; c > targetCol; c--) {
            for (Piece piece : GamePanel.simPieces) {
                if (piece.col == c && piece.row == targetRow) {
                    hittingPiece = piece;
                    return true;

                }
            }
        }
        // If the piece is moving to the right
        for (int c = preCol+1; c < targetCol; c++) {
            for (Piece piece : GamePanel.simPieces) {
                if (piece.col == c && piece.row == targetRow) {
                    hittingPiece = piece;
                    return true;

                }
            }
        }
        // If the piece is moving up
        for (int r = preRow-1; r > targetRow; r--) {
            for (Piece piece : GamePanel.simPieces) {
                if (piece.row == r && piece.col == targetCol) {
                    hittingPiece = piece;
                    return true;

                }
            }
        }
        // If the piece is moving down
        for (int r = preRow+1; r < targetRow; r++) {
            for (Piece piece : GamePanel.simPieces) {
                if (piece.row == r && piece.col == targetCol) {
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        return false;
    }

    // Diagonal piece movement
    public boolean isOnDiagonal(int targetCol, int targetRow) {
        // Top movement
        if (targetRow < preRow) {
            // Top left
            for (int c = preCol-1; c > targetCol; c--) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == preRow - diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }

            // Top right
            for (int c = preCol+1; c < targetCol; c++) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == preRow - diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }

        // Bottom movement
        if ( targetRow > preRow) {
            // Bottom left
            for (int c = preCol-1; c > targetCol; c--) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == preRow + diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }

            // Bottom right
            for (int c = preCol+1; c < targetCol; c++) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == c && piece.row == preRow + diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }


    // Draw the piece
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
}

package com.chess.Engine.pieces;

import com.chess.Engine.Alliance;
import com.chess.Engine.board.Board;
import com.chess.Engine.board.Move;

import java.util.List;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    public abstract List<Move> calcLegalMoves(final Board board);

}

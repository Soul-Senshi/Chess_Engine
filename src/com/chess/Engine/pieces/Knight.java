package com.chess.Engine.pieces;

import com.chess.Engine.Alliance;
import com.chess.Engine.board.Board;
import com.chess.Engine.board.Move;
import com.chess.Engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private final int[] LEGAL_MOVE_CANDIDATES = {-17, -15, -10, -6, 6, 10, 15, 17}; int i = 0;
    Knight(final int piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calcLegalMoves(Board board) {

        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidate : LEGAL_MOVE_CANDIDATES) {
            candidateDestinationCoordinate = this.piecePosition + currentCandidate;

            if(true) {
                final Tile candidateDestionationTile = board.getTile(candidateDestinationCoordinate);

                if (!candidateDestionationTile.isTileOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestination = candidateDestionationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}

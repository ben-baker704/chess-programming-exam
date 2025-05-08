package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueenMoves implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        List<ChessMove> valid_moves = new ArrayList<>();

        int[][] directions = {
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1},
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };

        for (int[] direction : directions) {
            int row = myPosition.getRow();
            int col = myPosition.getColumn();

            while (true) {

                row += direction[0];
                col += direction[1];

                if (row < 1 || row > 8 || col < 1 || col > 8) {
                    break;
                }

                ChessPosition new_position = new ChessPosition(row, col);
                ChessPiece target = board.getPiece(new_position);

                if (target == null) {
                    valid_moves.add(new ChessMove(myPosition, new_position, null));
                }
                else {
                    ChessPiece my_piece = board.getPiece(myPosition);
                    ChessGame.TeamColor color = my_piece.getTeamColor();
                    if (color != target.getTeamColor()) {
                        valid_moves.add(new ChessMove(myPosition, new_position, null));
                    }
                    break;
                }
            }
        }

        return valid_moves;
    }
}

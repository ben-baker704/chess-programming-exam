package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KnightMoves implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        List<ChessMove> valid_moves = new ArrayList<>();

        int[][] directions = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] direction : directions) {
            int row = myPosition.getRow();
            int col = myPosition.getColumn();


            row += direction[0];
            col += direction[1];

            if (row < 1 || row > 8 || col < 1 || col > 8) {
                continue;
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
            }
        }

        return valid_moves;
    }
}

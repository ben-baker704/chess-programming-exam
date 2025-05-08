package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMoves implements PieceMovesCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        List<ChessMove> valid_moves = new ArrayList<>();

        int up;
        int up_2;
        int[][] diagonals;
        int starting_row;
        int promotion_row;

        ChessPiece pawn = board.getPiece(myPosition);
        ChessGame.TeamColor color = pawn.getTeamColor();

        if (color == ChessGame.TeamColor.WHITE) {
            up = 1;
            up_2 = 2;
            diagonals = new int[][]{
                    {1, 1}, {1, -1}
            };
            starting_row = 2;
            promotion_row = 8;
        }
        else {
            up = -1;
            up_2 = -2;
            diagonals = new int[][]{
                    {-1, 1}, {-1, -1}
            };
            starting_row = 7;
            promotion_row = 1;
        }

        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ChessPosition new_position = new ChessPosition(row + up, col);
        ChessPiece target = board.getPiece(new_position);
        if (row + up >= 1 && row + up <= 8 && target == null) {
            if (new_position.getRow() == promotion_row) {
                valid_moves.add(new ChessMove(myPosition, new_position, ChessPiece.PieceType.BISHOP));
                valid_moves.add(new ChessMove(myPosition, new_position, ChessPiece.PieceType.KNIGHT));
                valid_moves.add(new ChessMove(myPosition, new_position, ChessPiece.PieceType.QUEEN));
                valid_moves.add(new ChessMove(myPosition, new_position, ChessPiece.PieceType.ROOK));
            }
            else {
                valid_moves.add(new ChessMove(myPosition, new_position, null));
            }
        }

        if (row == starting_row) {
            ChessPosition new_position_2 = new ChessPosition(row + up_2, col);
            ChessPiece target_2 = board.getPiece(new_position_2);
            if (target == null && target_2 == null) {
                valid_moves.add(new ChessMove(myPosition, new_position_2, null));
            }
        }

        for (int diagonal[] : diagonals) {
            row = myPosition.getRow();
            col = myPosition.getColumn();

            row += diagonal[0];
            col += diagonal[1];

            if (row < 1 || row > 8 || col < 1 || col > 8) {
                continue;
            }
            new_position = new ChessPosition(row, col);
            target = board.getPiece(new_position);
            if (target != null && target.getTeamColor() != color) {
                if (new_position.getRow() == promotion_row) {
                    valid_moves.add(new ChessMove(myPosition, new_position, ChessPiece.PieceType.BISHOP));
                    valid_moves.add(new ChessMove(myPosition, new_position, ChessPiece.PieceType.KNIGHT));
                    valid_moves.add(new ChessMove(myPosition, new_position, ChessPiece.PieceType.QUEEN));
                    valid_moves.add(new ChessMove(myPosition, new_position, ChessPiece.PieceType.ROOK));
                }
                else {
                    valid_moves.add(new ChessMove(myPosition, new_position, null));
                }
            }
        }

        return valid_moves;
    }
}

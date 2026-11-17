package edu.sustech.xiangqi.model;

public class Cannon extends Piece{
    public Cannon(Boolean isRed) {
        super(isRed?"炮":"砲",isRed );
    }
    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        if (Math.abs(toRow - fromRow) * Math.abs(toCol - fromCol) != 0) {
            return false;
        }
        int num = 0;
        if (toRow == fromRow) {
            int step = (toCol - fromCol) / Math.abs(toCol - fromCol);
            for (int i = fromCol + step; i != toCol; i += step) {
                if (board.getPieceAt(fromRow, i) != null) {
                    num++;
                }
            }
        } else {
            int step = (toRow - fromRow) / Math.abs(toRow - fromRow);
            for (int i = fromRow + step; i != toRow; i += step) {
                if (board.getPieceAt(i, fromCol) != null) {
                    num++;
                }
            }
        }
        if (board.getPieceAt(toRow, toCol) == null) {
            return num == 0;
        } else {
            return num == 1&&board.getPieceAt(toRow, toCol).isRed()!=isRed();
        }
    }
}

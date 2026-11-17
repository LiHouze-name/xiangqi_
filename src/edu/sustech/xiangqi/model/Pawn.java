package edu.sustech.xiangqi.model;

public class Pawn extends Piece{//兵
    public Pawn(Boolean isRed) {
        super(isRed?"兵":"卒",isRed );
    }
    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        //只能向前，过河后可以左右走
        return (isRed() == false || (fromRow >= toRow && (fromRow < 5 || fromRow != toRow))) && (isRed() == true || (fromRow <= toRow && (fromRow > 4 || fromRow != toRow))) && Math.abs(toRow - fromRow) + Math.abs(toCol - fromCol) == 1;
    }
}

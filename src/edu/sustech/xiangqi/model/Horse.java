package edu.sustech.xiangqi.model;

public class Horse extends Piece{
    public Horse(Boolean isRed) {
        super(isRed?"傌":"馬",isRed );
    }
    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        // 日字形，加了拌脚
        if (Math.abs(toRow - fromRow)*Math.abs(toCol - fromCol) == 0||Math.abs(toRow - fromRow)+Math.abs(toCol - fromCol)!=3||board.getPieceAt((int)Math.round((double)fromRow+((double)toRow - (double)fromRow)/3), (int)Math.round((double)fromCol+((double)toCol - (double)fromCol)/3))!=null) {
            return false;
        }
        return true;
    }
}

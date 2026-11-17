package edu.sustech.xiangqi.model;

public class Elephant extends Piece{
    public Elephant(Boolean isRed) {
        super(isRed?"相":"象",isRed );
    }
    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        // 田字形，加了拌脚
        if (Math.abs(toRow - fromRow)!=2||Math.abs(toCol - fromCol) != 2||board.getPieceAt(fromRow+(toRow - fromRow)/2, fromCol+(toCol - fromCol)/2)!=null||fromRow+(toRow - fromRow)/2==4||fromRow+(toRow - fromRow)/2==5) {
            return false;
        }
        return true;
    }
}

package edu.sustech.xiangqi.model;

// 啥也没改，加个注释，我测试一下你你那边看不看得到

public class Car extends Piece {
    public Car(Boolean isRed) {
        super(isRed?"俥":"車",isRed );
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
        // 只能直走
        if (Math.abs(toRow - fromRow)*Math.abs(toCol - fromCol) != 0||Math.abs(toRow - fromRow)+Math.abs(toCol - fromCol)==0) {
            return false;
        }
        return true;
    }
}

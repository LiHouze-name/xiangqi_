package edu.sustech.xiangqi.model;

// 红帅

public class GeneralPiece extends Piece {
    public GeneralPiece(Boolean isRed) {
        super(isRed?"帥":"將",isRed );
    }

    @Override
    public boolean canMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {


        // 行和列必须都变化一格，不能原地停留或跨越多格
        // 实现帅的移动规则。
        if (Math.abs(toRow - fromRow)+Math.abs(toCol - fromCol) != 1) {
            return false;
        }

        // 列判断
        if (toCol < 3 || toCol > 5) {
            return false;
        }

        // 行判断
        return isRed()?toRow >= 7 && toRow <= 9:toRow >= 0 && toRow <= 2;


    }
}

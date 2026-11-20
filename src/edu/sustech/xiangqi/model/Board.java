package edu.sustech.xiangqi.model;

// 保存棋盘数据，并统一检查和执行移动

public class Board {
    // 定义棋盘大小
    public static final int ROWS = 10;
    public static final int COLS = 9;
    private boolean ISREDTURN=true;

    // 存储棋子位置
    private final Piece[][] pieces = new Piece[ROWS][COLS];

    public Board() {
        initializePieces();
    }

    private void initializePieces() {
        //红仕
        pieces[9][3] = new AdvisorPiece(true);
        pieces[9][5] = new AdvisorPiece(true);
        //黑士
        pieces[0][3] = new AdvisorPiece(false);
        pieces[0][5] = new AdvisorPiece(false);
        //帥
        pieces[9][4] = new GeneralPiece(true);
        //将
        pieces[0][4] = new GeneralPiece(false);
        //红俥
        pieces[9][0] = new Car(true);
        pieces[9][8] = new Car(true);
        //黑車
        pieces[0][0] = new Car(false);
        pieces[0][8] = new Car(false);
        //红傌
        pieces[9][1] = new Horse(true);
        pieces[9][7] = new Horse(true);
        //黑馬
        pieces[0][1] = new Horse(false);
        pieces[0][7] = new Horse(false);
        //红相
        pieces[9][2] = new Elephant(true);
        pieces[9][6] = new Elephant(true);
        //象
        pieces[0][2] = new Elephant(false);
        pieces[0][6] = new Elephant(false);
        //红炮
        pieces[7][1] = new Cannon(true);
        pieces[7][7] = new Cannon(true);
        //砲
        pieces[2][1] = new Cannon(false);
        pieces[2][7] = new Cannon(false);
        //红兵
        pieces[6][0] = new Pawn(true);
        pieces[6][2] = new Pawn(true);
        pieces[6][4] = new Pawn(true);
        pieces[6][6] = new Pawn(true);
        pieces[6][8] = new Pawn(true);
        //卒
        pieces[3][0] = new Pawn(false);
        pieces[3][2] = new Pawn(false);
        pieces[3][4] = new Pawn(false);
        pieces[3][6] = new Pawn(false);
        pieces[3][8] = new Pawn(false);
        //红	黑
        //帥	將
        //仕	士
        //相	象
        //俥	車
        //傌	馬
        //炮	砲
        //兵	卒
        //棋子已加完
    }

    public boolean isInside(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    // 判断传入的棋盘位置是否有棋子，有的话返回到底是什么棋子
    public Piece getPieceAt(int row, int col) {
        if (!isInside(row, col)) {
            return null;
        }
        return pieces[row][col];
    }

    public boolean getISREDTURN() {
        return ISREDTURN;
    }//加的ISREDTURN getter——李厚泽

    public boolean tryMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (!isInside(fromRow, fromCol) || !isInside(toRow, toCol)) {
            return false;
        }
        if (fromRow == toRow && fromCol == toCol) {
            return false;
        }

        Piece piece = getPieceAt(fromRow, fromCol);
        if (piece == null) {
            return false;
        }

        // 已加入回合检查，只允许当前一方移动自己的棋子——李厚泽
        if(getPieceAt(fromRow, fromCol).isRed()!=ISREDTURN){
            return false;
        }

        // 区分己方占用与敌方棋子，并实现吃子——李厚泽
        if (getPieceAt(toRow, toCol) != null&&(getPieceAt(toRow, toCol).isRed()==getPieceAt(fromRow, fromCol).isRed() )) {
            return false;
        }

        // 单独判断将帅照面
        if(getPieceAt(fromRow, fromCol) instanceof GeneralPiece&&getPieceAt(toRow, toCol) instanceof GeneralPiece){
            if(fromCol==toCol){
                for(int i=Math.min(fromRow,toRow)+1;i<Math.max(fromRow,toRow);i++){
                    if(getPieceAt(i,fromCol)!=null){
                        return false;
                    }
                }
                pieces[fromRow][fromCol] = null;
                pieces[toRow][toCol] = piece;
                return true;
            }else{
                return false;
            }
        }

        if (!piece.canMove(this, fromRow, fromCol, toRow, toCol)) {
            return false;//棋子自身行走规则
        }

        // TODO: 检查走完后己方将帅是否安全
        if (getPieceAt(fromRow, fromCol) instanceof GeneralPiece){
            // TODO: 把各种面板处理好后再写这个finish方法
        }

        pieces[fromRow][fromCol] = null;
        pieces[toRow][toCol] = piece;
        //移动被记录在二维数组了



        // TODO: 仅在成功移动后切换回合，并判断将军、胜负等状态
        ISREDTURN=!ISREDTURN;//已切换回合
        return true;
    }
}


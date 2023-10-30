package sesh.mood.chessGame.domain;

public class Piece {
    //eerste zet bepalen
    public Boolean initialMove = false;
    public int initialMovePos;

    //resterende zet
    public int MovePos;

    //bewegingsrichtingen
    public Boolean allowedFileUp = false;
    public Boolean allowedFileDown = false;
    public Boolean allowedDiagonalUp = false;
    public Boolean allowedDiagonalDown = false;
    public Boolean allowedRankLeft = false;
    public Boolean allowedRankRight = false;

    //aantal max toegelaten posities
    public int fileUp;
    public int fileDown;
    public int diagonalUp;
    public int diagonalDown;
    public int rankLeft;
    public int rankRight;

    //identifier
    public String name;
    public int id;
    public String unicode;

    public Piece(Boolean initialMove, int initialMovePos, int MovePos, Boolean allowedFileUp, Boolean allowedFileDown, Boolean allowedDiagonalUp, Boolean allowedDiagonalDown, Boolean allowedRankLeft, Boolean allowedRankRight, int fileUp, int fileDown, int diagonalUp, int diagonalDown, int rankLeft, int rankRight, String name, int id, String unicode) {
        this.initialMove = initialMove;
        this.initialMovePos = initialMovePos;
        this.MovePos = MovePos;
        this.allowedFileUp = allowedFileUp;
        this.allowedFileDown = allowedFileDown;
        this.allowedDiagonalUp = allowedDiagonalUp;
        this.allowedDiagonalDown = allowedDiagonalDown;
        this.allowedRankLeft = allowedRankLeft;
        this.allowedRankRight = allowedRankRight;
        this.fileUp = fileUp;
        this.fileDown = fileDown;
        this.diagonalUp = diagonalUp;
        this.diagonalDown = diagonalDown;
        this.rankLeft = rankLeft;
        this.rankRight = rankRight;
        this.name = name;
        this.id = id;
        this.unicode = unicode;
    }
}
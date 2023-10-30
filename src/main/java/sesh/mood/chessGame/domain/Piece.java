package sesh.mood.chessGame.domain;

public class Piece {
    //eerste zet bepalen
    private Boolean initialMove = false;
    private int initialMovePos;

    //resterende zet
    private int MovePos;

    //bewegingsrichtingen
    private Boolean allowedFileUp = false;
    private Boolean allowedFileDown = false;
    private Boolean allowedDiagonalUp = false;
    private Boolean allowedDiagonalDown = false;
    private Boolean allowedRankLeft = false;
    private Boolean allowedRankRight = false;

    //aantal max toegelaten posities
    private int fileUp;
    private int fileDown;
    private int diagonalUp;
    private int diagonalDown;
    private int rankLeft;
    private int rankRight;

    //identifier
    private String name;
    private int id;
    private String unicode;


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
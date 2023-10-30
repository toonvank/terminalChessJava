package sesh.mood.chessGame.services;

import org.springframework.stereotype.Service;

import sesh.mood.chessGame.domain.Piece;

@Service
public class Pieces {
    public Piece Pawn = new Piece(true, 2, 1, true, false, false, false, false, false, 2, 0, 0, 0, 0, 0, null, 0, "♟");
}
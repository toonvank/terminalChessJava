package sesh.mood.chessGame.services;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import sesh.mood.chessGame.domain.Piece;

@Service
public class Pieces {
    public Piece Pawn = new Piece(true, 2, 1, false, true, false, false, false, false, 0, 2, 0, 0, 0, 0, "Pawn", 0, "♟");
    public HashMap<Piece, String> Pieces = new HashMap<>();
    public Pieces() {
        Pieces.put(Pawn, "♟");
    }
}
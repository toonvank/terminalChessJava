package sesh.mood.chessGame;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import sesh.mood.chessGame.games.TerminalChess;

@SpringBootApplication
public class TerminalChessApplication {
	public static void main(String[] args) {
		TerminalChess chess = new TerminalChess();
		chess.StartGame();
	}
}

package sesh.mood.chessGame.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import sesh.mood.chessGame.domain.Game;
import sesh.mood.chessGame.services.ConsoleHelper;

@Service
public class TerminalChess implements Game {
    ConsoleHelper ch = new ConsoleHelper();
    ArrayList<String> rankArray = new ArrayList<>(Arrays.asList("♜", "♞","♝","♛","♚","♝","♞","♜"));
    ArrayList<String> secondRankArray = new ArrayList<>(Arrays.asList("♟", "♟","♟","♟","♟","♟","♟","♟"));
    ArrayList<ArrayList<String>> boardList = new ArrayList<>();
    HashMap<String, Integer> letterToNumberMap = new HashMap<>();
    public TerminalChess(ConsoleHelper ch) {
        this.ch = ch;
    }
    public TerminalChess() {
    }
    @Override
    public void StartGame() {
        GetLetterMap();
        GenerateChessBoard();
        ch.pr(BoardFromArray());
        //infinte loop
        while(true){
            ch.pr("Enter the current position of the piece you want to move: ");
            String currentPos = ch.getl();
            ch.pr("Enter the desired position of the piece you want to move: ");
            String desiredPos = ch.getl();
            MovePin(currentPos, desiredPos);
        }
    }
    public String GenerateChessBoard() {
        String board = "  a b c d e f g h\n";
        for (int i = 0; i < 8; i++) {
            board += (i + 1) + " ";
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                if (i == 0 || i == 7) {
                    board += rankArray.get(j);
                    row.add(rankArray.get(j));
                } else if (i == 1 || i == 6) {
                    board += secondRankArray.get(j);
                    row.add(secondRankArray.get(j));
                } else {
                    int index = (i + j) % 2 == 0 ? 0 : 1;
                    board += "•";
                    row.add("•");
                }
            }
            boardList.add(row);
            board += "\n";
        }
        return board;
    }
    public String BoardFromArray(){
        String board = "  a b c d e f g h\n";
        int i = 0;
        for (List<String> row : boardList) {
            board += (i+1) + " ";
            for (String element : row) {
                board+= element + " "; 
            }
            board += "\n";
            i++;
        }
        return board;
    }
    public void MovePin(String currentPos, String desiredPos){
        int desiredX = Integer.parseInt(String.valueOf(desiredPos.charAt(0))) - 1;
        int desiredY = letterToNumberMap.get(String.valueOf(desiredPos.charAt(1)));

        int currentX = Integer.parseInt(String.valueOf(currentPos.charAt(0))) - 1;
        int currentY = letterToNumberMap.get(String.valueOf(currentPos.charAt(1)));

        boardList.get(desiredX).set(desiredY, boardList.get(currentX).get(currentY));

        boardList.get(currentX).set(currentY, "•");

        ch.pr(BoardFromArray());
    }
    public void GetLetterMap(){
        for (char letter = 'a'; letter <= 'h'; letter++) {
            String letterString = String.valueOf(letter);
            int number = letter - 'a'; // Calculate the corresponding number
            letterToNumberMap.put(letterString, number);
        }
    }
}
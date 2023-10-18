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
    List<String> rankArray = Arrays.asList("♜", "♞","♝","♛","♚","♝","♞","♜");
    List<String> secondRankArray = Arrays.asList("♟", "♟","♟","♟","♟","♟","♟","♟");
    List<List<String>> boardList = new ArrayList<>();
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
    public String GenerateChessBoard(){
        String board = "  a b c d e f g h\n";
        String rank = "♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜ ";
        String secondRank = "♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ ";
        for(int i = 0; i < 8; i++){
            board += (i+1) + " ";
            List<String> row1 = new ArrayList<>();
            if(i < 2){
                if(i%2 == 0){
                    board += rank;
                    boardList.add(rankArray);
                }
                else{
                    board += secondRank;
                    boardList.add(secondRankArray);
                }
            }
            else if(i>=6){
                if(i%2 == 0){
                    board += secondRank;
                     boardList.add(secondRankArray);
                }
                else{
                    board += rank;
                    boardList.add(rankArray);
                }
            }
            else{
                //normally it should be possible just using board += "•" but for some reason it doesn't work so i had to keep the same code as before
                if (i%2 == 0){
                    for(int j = 0; j < 8; j++){
                        if (j%2 == 0){
                            board += "•";
                            row1.add("•");
                        } else{
                            board += "•";
                            row1.add("•");
                        }
                    }
                }
                else{
                    for(int j = 0; j < 8; j++){
                    if (j%2 == 0){
                        board += "•";
                        row1.add("•");
                    } else{
                        board += "•";
                        row1.add("•");
                    }
                }
            }
            boardList.add(row1);
            }
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
                if(i < 2){
                   board+= element + " "; 
                }
                else if(i>=6){
                    board+= element + " ";
                }
                else{
                    board+= element + " ";
                }
            }
            board += "\n";
            i++;
        }
        return board;
    }
    public void MovePin(String currentPos, String desiredPos){
        int desiredX = Integer.parseInt(String.valueOf(desiredPos.charAt(0))) - 1;
        Integer desiredY = letterToNumberMap.get(String.valueOf(desiredPos.charAt(1)));

        int currentX = Integer.parseInt(String.valueOf(currentPos.charAt(0))) - 1;
        Integer currentY = letterToNumberMap.get(String.valueOf(currentPos.charAt(1)));

        boardList.get(desiredX).set(desiredY, boardList.get(currentX).get(currentY));

        //get currentpost in letternumbermap and replace with black

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
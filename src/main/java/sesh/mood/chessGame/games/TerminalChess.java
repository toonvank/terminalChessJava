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
    static int ROWS = 8;
    ConsoleHelper ch = new ConsoleHelper();
    List<String> rankArray = Arrays.asList("♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜");
    List<String> secondRankArray = Arrays.asList("♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟");
    List<String> emptyRankArray = Arrays.asList("•", "•", "•", "•", "•", "•", "•", "•");
    // boardlist should be a 2d char array. if not why?
    List<List<String>> boardList = new ArrayList<>();
    // waarom + 1, omdat de a b c d e... er ook bij moet en + 2 omdat de a b c d
    // e... een padding heeft van 2 chars
    char[][] boardArray = new char[ROWS + 1][ROWS + 2];
    HashMap<String, Integer> letterToNumberMap = new HashMap<>();

    public TerminalChess(ConsoleHelper ch) {
        this.ch = ch;
    }

    public TerminalChess() {
    }

    @Override
    public void StartGame() {
        GetLetterMap();
        BoardToArray(GenerateChessBoardV2());
        PrintBoard();
        GameLoop();
        // ch.pr(BoardFromArray());
        // GameLoop();
    }

    public void GameLoop() {
        // infinte loop
        while (true) {
            ch.pr("Enter the current position of the piece you want to move: ");
            String currentPos = ch.getl();
            ch.pr("Enter the desired position of the piece you want to move: ");
            String desiredPos = ch.getl();
            MoveSelectedPin(StringToPos(desiredPos), SelectPinV2(StringToPos(currentPos)));
            PrintBoard();
        }
    }

    public int[] StringToPos(String pos) {
        int[] coordinate = new int[2];
        var sva = String.valueOf(pos.charAt(1));
        coordinate[0] = Integer.parseInt(sva);
        coordinate[1] = letterToNumberMap.get(String.valueOf(pos.charAt(0))) + 1;
        return coordinate;
    }

    public String GenerateChessBoardV2() {
        int rows = 8;
        String board = "  a b c d e f g h\n";
        String rank = "♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜ ";
        String secondRank = "♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ ";
        String emptyRow = "• • • • • • • • ";

        for (int i = 0; i < rows; i++) {
            board += (i + 1) + " ";
            if (i == 0 || i == rows - 1) {
                board += rank;
                boardList.add(rankArray);
            } else if (i == 1 || i == rows - 2) {
                board += secondRank;
                boardList.add(secondRankArray);
            } else {
                board += emptyRow;
                boardList.add(emptyRankArray);
            }
            board += "\n";
        }

        return board;
    }

    public void BoardToArray(String boardString) {
        var boardStringRows = boardString.split("\n");
        for (int i = 0; i < boardStringRows.length; i++) {
            var charArray = boardStringRows[i].split(" ");
            for (int j = 0; j < charArray.length; j++) {
                if (charArray[j].length() > 0) {
                    var charAtPos = charArray[j].charAt(0);
                    boardArray[i][j] = charAtPos;
                }
            }
        }
    }

    public void PrintBoard() {
        for (int i = 0; i < boardArray.length; i++) {
            var row = boardArray[i];
            for (int j = 0; j < row.length; j++) {
                System.out.print(boardArray[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public String GenerateChessBoard() {
        // too many magic numbers 8, 6, 2 ???
        String board = "  a b c d e f g h\n";
        String rank = "♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜ ";
        String secondRank = "♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ ";
        for (int i = 0; i < 8; i++) {
            board += (i + 1) + " ";
            List<String> row1 = new ArrayList<>();
            if (i < 2) {
                if (i % 2 == 0) {
                    board += rank;
                    boardList.add(rankArray);
                } else {
                    board += secondRank;
                    boardList.add(secondRankArray);
                }
            } else if (i >= 6) {
                if (i % 2 == 0) {
                    board += secondRank;
                    boardList.add(secondRankArray);
                } else {
                    board += rank;
                    boardList.add(rankArray);
                }
            } else {
                // normally it should be possible just using board += "•" but for some reason it
                // doesn't work so i had to keep the same code as before
                if (i % 2 == 0) {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 == 0) {
                            board += "•";
                            row1.add("•");
                        } else {
                            board += "•";
                            row1.add("•");
                        }
                    }
                } else {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 == 0) {
                            board += "•";
                            row1.add("•");
                        } else {
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

    public String BoardFromArray() {
        String board = "  a b c d e f g h\n";
        int i = 0;
        for (List<String> row : boardList) {
            board += (i + 1) + " ";
            for (String element : row) {
                if (i < 2) {
                    board += element + " ";
                } else if (i >= 6) {
                    board += element + " ";
                } else {
                    board += element + " ";
                }
            }
            board += "\n";
            i++;
        }
        return board;
    }

    public void MovePin(String currentPos, String desiredPos) {
        // doesn't work for some reason
        int desiredX = Integer.parseInt(String.valueOf(desiredPos.charAt(0))) - 1;
        Integer desiredY = letterToNumberMap.get(String.valueOf(desiredPos.charAt(1)));
        // no check if desiredX and desiredY are in range
        // no check if desiredX and desiredY are empty

        int currentX = Integer.parseInt(String.valueOf(currentPos.charAt(0))) - 1;
        Integer currentY = letterToNumberMap.get(String.valueOf(currentPos.charAt(1)));
        // no check if currentX and currentY are in range
        // no check if currentX and currentY are empty

        boardList.get(desiredX).set(desiredY, boardList.get(currentX).get(currentY));

        // get currentpost in letternumbermap and replace with black

        boardList.get(currentX).set(currentY, "•");

        ch.pr(BoardFromArray());
    }

    public char SelectPinV2(int[] coordinate) {
        int x = coordinate[0];
        int y = coordinate[1];

        if (x < 0 || x > ROWS || y < 0 || y > ROWS) {
            ch.pr("Invalid coordinate");
            return ' ';
        }

        if (boardArray[x][y] == '•') {
            ch.pr("No pin at this coordinate");
            return ' ';
        }

        var pin = boardArray[x][y];
        boardArray[x][y] = '•';
        return pin;
    }

    public void MoveSelectedPin(int[] coordinate, char pin) {
        int x = coordinate[0];
        int y = coordinate[1];

        if (x < 0 || x > ROWS || y < 0 || y > ROWS) {
            ch.pr("Invalid coordinate");
            return;
        }

        if (pin == ' ')
            return;

        if (boardArray[x][y] != '•') {
            ch.pr("pin at this coordinate");
            return;
        }

        // get currentpost in letternumbermap and replace with black

        boardArray[x][y] = pin;
    }

    public void GetLetterMap() {
        for (char letter = 'a'; letter <= 'h'; letter++) {
            String letterString = String.valueOf(letter);
            int number = letter - 'a'; // Calculate the corresponding number
            letterToNumberMap.put(letterString, number);
        }
    }

    public void MirrorBoard() {
        // player 2 needs a mirrored board
    }
}
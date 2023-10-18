package sesh.mood.chessGame.services;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleHelper {
    private Scanner scanner = new Scanner(System.in);
    public void enterToContinue(){
        System.out.print("↩");
        scanner.nextLine();
    }
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void pr(String message){
        System.out.print(message);
    }
    public void prl(String message){
        System.out.println(message);
    }
    public void prent(String message){
        System.out.print(message);
        enterToContinue();
    }
    public void prlent(String message){
        System.out.println(message);
        enterToContinue();
    }
    public String getl(){
        return scanner.nextLine();
    }
}

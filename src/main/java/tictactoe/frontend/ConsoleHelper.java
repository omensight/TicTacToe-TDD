package tictactoe.frontend;

import tictactoe.frontend.util.AnsiColors;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleHelper {
    private Scanner sc;

    public ConsoleHelper() {
        sc = null;
    }
    // Input Number Console
    public int enterNumber(int max, String message){
        sc = null;
        int number = 0;
        do{
            try {
                sc = new Scanner(System.in);
                number = sc.nextInt();
                while (number < 1 || number > max) {
                    System.out.println(AnsiColors.RED + "*** " + message + " not valid, enter another" + AnsiColors.RESET);
                    System.out.print("- enter the " + message + ": ");
                    number = sc.nextInt();
                }
            } catch (InputMismatchException ime) {
                System.out.println(AnsiColors.RED + "***Watch out! You can only insert numbers" + AnsiColors.RESET);
                System.out.print("- enter the " + message + ": ");
                assert sc != null;
                sc.next();
            }finally {
                if (sc == null){
                    sc.close();
                }
            }
        }while (number < 1 || number > max);
        return number;
    }



    //Message Game
    public String messageWinnerGame(String winner){
        return "The winner is " + winner + ", Congratulations!";
    }

    public String messageDrawGame(){
        return "It's a Draw :'v";
    }

    public String messageFinishGame(){
        return "Thanks for Play!!! :)";
    }
}

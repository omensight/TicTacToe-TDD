package tictactoe.frontend;

public class InputHelper {
    public int parseInt(String line){
        int number = -1;
        try{
            number = Integer.parseInt(line);
        }catch (NumberFormatException exception){
            exception.printStackTrace();
        }
        return number;
    }
}

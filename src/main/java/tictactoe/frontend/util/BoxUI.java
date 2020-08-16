package tictactoe.frontend.util;

import java.awt.*;

public class BoxUI implements IBoxUI {
    private String color;
    private char symbol;
    private String symbolColor;

    public BoxUI(String color, char symbol, String symbolColor) {
        this.color = color;
        this.symbol = symbol;
        this.symbolColor = symbolColor;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getBoxColor() {
        return color;
    }

    @Override
    public String getSymbolColor() {
        return symbolColor;
    }
}

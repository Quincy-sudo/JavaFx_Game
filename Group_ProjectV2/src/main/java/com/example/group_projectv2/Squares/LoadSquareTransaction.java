package com.example.group_projectv2.Squares;


import java.util.ArrayList;
import java.util.List;

public class LoadSquareTransaction {

    public static List<Square> gameTiles = new ArrayList<>();

    public List<Square> loadSquare() {
        for (int i = 0; i <= 14; i++) {
            String name = i == 0 ? "Square 0" : "Square" + i;
            SquareType type;
            int price;

            if (i == 0) {
                type = SquareType.START;
                price = 50;
            } else if (i == 1 || i >= 11) {
                type = SquareType.SPECIAL;
                price = 100;
            } else {
                type = SquareType.NORMAL;
                price = i == 2 ? 111 : 100;
            }

            Square Square = new Square(name, i, type, price);
            gameTiles.add(Square);
        }

        return gameTiles;
    }
}
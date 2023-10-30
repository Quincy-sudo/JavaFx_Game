package com.example.group_projectv2.Squares;

import com.example.group_projectv2.Player;

public class Square {
    private final int position;
    private final SquareType type;
    private final int price;
    private Player owner;
    private String Name;
    private boolean isOffered = false;


    public Square(String Name, int position, SquareType type, int price) {
        this.position = position;
        this.type = type;
        this.price = price;
        this.Name = Name;
    }

    public int getPosition() {
        return position;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public SquareType getType() {
        return type;
    }
    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public boolean isOffered() {
        return isOffered;
    }

    public void setOffered(boolean offered) {
        isOffered = offered;
    }
}

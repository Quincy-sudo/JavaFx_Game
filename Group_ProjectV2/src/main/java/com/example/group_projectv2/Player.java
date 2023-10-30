package com.example.group_projectv2;
import java.util.ArrayList;
import java.util.List;

import com.example.group_projectv2.Squares.Square;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {
    private String name;
    private int score;
    private int position;
    private IntegerProperty resources;
    private List<Square> SquaresOwned = new ArrayList<>();
    private Square currentSquare;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.resources = new SimpleIntegerProperty(1500);
        this.position = 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }
    public Square getCurrentSquare() {
        return this.currentSquare;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getPosition() {
        return position;
    }
    public int getResources() {
        return this.resources.get();
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public void setResources(int resources) {
        this.resources.set(resources);
    }
    public IntegerProperty resourcesProperty() {
        return resources;
    }
    public List<Square> getSquaresOwned() {
        return SquaresOwned;
    }
    public void setCurrentSquare(Square square) {
        this.currentSquare = square;
    }
    public void buySquare(Square square) {
        if (resources.get() >= square.getPrice()) {
            resources.set(resources.get() - square.getPrice());
            square.setOwner(this);
            SquaresOwned.add(square);
        } else {
            System.out.println("You do not have enough resources to buy this tile.");
        }
    }

    public void tradeTile(Square square, Player tradePartner) {
        if (SquaresOwned.contains(square)) {
            SquaresOwned.remove(square);
            tradePartner.getSquaresOwned().add(square);
            square.setOwner(tradePartner);
        } else {
            System.out.println("Trade failed. You do not own this tile.");
        }
    }

    public void increaseScore(int amount) {
        score += amount;
    }
}
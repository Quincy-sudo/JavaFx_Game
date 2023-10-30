package com.example.group_projectv2;
import javafx.scene.control.TextArea;

import com.example.group_projectv2.Player;
import com.example.group_projectv2.Squares.Square;


import java.util.List;

public class ActionButtonHandler {
    final List<Player> players;
    final List<Square> gameBoard;

    public ActionButtonHandler(List<Player> players, List<Square> gameBoard) {
        this.players = players;
        this.gameBoard = gameBoard;
    }

    public static int handleRollAction(TextArea console, List<Player> players, int currentPlayer, List<Square> gameBoard) {
        console.clear();
        // Generate random numbers between 1 and 6 for two dice
        int dice1 = (int) (Math.random() * 6) + 1;
        int dice2 = (int) (Math.random() * 6) + 1;

        // Calculate the sum of the two dice rolls
        int sum = dice1 + dice2;

        // Display the dice roll result in the console
        console.appendText(players.get(currentPlayer).getName() + " rolled a " + sum + "\n");
        int currentPosition = players.get(currentPlayer).getPosition();
        int newPosition = currentPosition + sum;
        // Check if the new position exceeds the maximum position on the game board
        if (newPosition >= gameBoard.size()) {
            newPosition = newPosition % gameBoard.size();
            // Add resources to the player when they pass the start tile
            players.get(currentPlayer).setResources(players.get(currentPlayer).getResources() + 250); // Add 200 resources
            console.appendText(players.get(currentPlayer).getName() + "'s resources increased to " + players.get(currentPlayer).getResources()+ "\n");
        }

        players.get(currentPlayer).setPosition(newPosition);
        // Find the tile corresponding to the new position
        Square currentTile = gameBoard.get(newPosition);

        // Output the tile information
        console.appendText(players.get(currentPlayer).getName() + " landed on " + currentTile.getName() + " - " + currentTile.getType()+ "\n");
        return currentPlayer;

    }

    public static int handleEndTurnAction(TextArea console, List<Player> players, int currentPlayer) {
        console.clear();
        console.appendText(players.get(currentPlayer).getName() + " ended their turn\n");
        currentPlayer = ((currentPlayer) + 1) % players.size();
        return currentPlayer;
    }
    public static void handleBuySquareAction(TextArea console, List<Player> players, int currentPlayer, List<Square> gameBoard) {
        Player player = players.get(currentPlayer);
        Square currentSquare = gameBoard.get(player.getPosition());

        if (currentSquare.getOwner() != null) {
            console.appendText("This square is already owned by " + currentSquare.getOwner().getName() + "\n");
            return;
        }

        if (player.getResources() < currentSquare.getPrice()) {
            console.appendText(player.getName() + " does not have enough resources to buy this square\n");
            return;
        }

        player.setResources(player.getResources() - currentSquare.getPrice());
        currentSquare.setOwner(player);

        console.appendText(player.getName() + " bought " + currentSquare.getName() + " for " + currentSquare.getPrice() + " resources\n");
    }
}

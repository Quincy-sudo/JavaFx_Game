package com.example.group_projectv2;
import com.example.group_projectv2.Squares.Square;
import com.example.group_projectv2.Squares.SquareType;

import java.util.List;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.beans.binding.Bindings;

public class Game extends Application {

    // Define the TextArea as a field to access it later
    private TextArea console;
    private final List<Player> players;
    private int currentplayer = 0;
    private final List<Square> gameBoard = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public Game(List<Player> players) {
        this.players = players;
        // Fill up gameboard with squares
        for (int i = 0; i < 40; i++) {
            Square square = new Square("Square " + i, i, SquareType.NORMAL, 100);
            gameBoard.add(square);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Console");

        // Create a StackPane to overlay background and content
        StackPane root = new StackPane();

        // Load the background image
        Image backgroundImage = new Image("file:Images/homeScreenImage.jpg");

        // Create an ImageView for the background image
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Apply a blur effect to the background image
        GaussianBlur blur = new GaussianBlur(10); // Adjust the radius for desired blur intensity
        backgroundImageView.setEffect(blur);

        // Create a GridPane to hold the main components
        GridPane content = new GridPane();
        content.setAlignment(Pos.CENTER);
        content.setHgap(20);

        // Create column constraints for the GridPane to control column widths
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();

        // Set the preferred column widths
        column1.setPercentWidth(25); // Left column (player names)
        column2.setPercentWidth(50); // Middle column (console)
        column3.setPercentWidth(25); // Right column (buttons)

        content.getColumnConstraints().addAll(column1, column2, column3);

        // Create a TextArea for the game console
        console = new TextArea();
        console.setEditable(false);
        console.setPrefColumnCount(40);
        console.setPrefRowCount(20);

        VBox leftColumn = new VBox(10);
        leftColumn.setAlignment(Pos.CENTER_LEFT);
        for (Player player : players) {
            Button playerButton = createPlayerButton(player.getName(), player);
            leftColumn.getChildren().addAll(playerButton);
        }

        VBox rightColumn = new VBox(10);
        rightColumn.setAlignment(Pos.CENTER_RIGHT);
        rightColumn.getChildren().addAll(
                createActionButton("Roll"),
                createActionButton("End Turn"),
                createActionButton("Action 2"),
                createActionButton("Action 3")

        );

        content.add(leftColumn, 0, 0);
        content.add(console, 1, 0);
        content.add(rightColumn, 2, 0);

        root.getChildren().addAll(backgroundImageView, content);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // You can use this line to add text to the console
        console.appendText("Hello, this is the console!\n");
    }

    private Button createActionButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(100);

        switch (text) {
            case "Roll":
                button.setOnAction(event -> {
                    currentplayer = ActionButtonHandler.handleRollAction(console,players,currentplayer,gameBoard);
                });
                break;
            case "End Turn":
                button.setOnAction(event -> {
                    currentplayer = ActionButtonHandler.handleEndTurnAction(console,players,currentplayer);
                });
                break;
            case "Action 2":
                button.setOnAction(event -> {
                    ActionButtonHandler.handleBuySquareAction(console, players, currentplayer, gameBoard);
                });
                break;
            case "Action 3":
                button.setOnAction(event -> {
                    currentplayer = ActionButtonHandler.handleEndTurnAction(console,players,currentplayer);
                });
                break;
            default:
                // Handle the default case if needed
                break;
        }

        return button;
    }

    private Button createPlayerButton(String name, Player player) {
        Button button = new Button(name);
        button.setStyle("-fx-font-size: 18px");
        button.setOnAction(event -> showPlayerInfo(player.getName() + "'s resources: " + player.getResources()));
        return button;
    }

    private void showPlayerInfo(String info) {
        console.clear();
        console.appendText(info);
    }

}

package com.example.group_projectv2;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelectPlayers extends Application {

    private VBox root;
    private Text instructionText;
    private HBox playerButtons;
    private List<Player> players = new ArrayList<>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set the title for the player selection menu
        primaryStage.setTitle("Select Number of Players");

        // Set the fixed size of the game window
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);

        // Disable window resizing
        primaryStage.setResizable(false);

        // Load the background image
        Image backgroundImage = new Image("file:Images/homeScreenImage.jpg");

        // Create an ImageView for the background image
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Apply a blur effect to the background image
        GaussianBlur blur = new GaussianBlur(10); // Adjust the radius for desired blur intensity
        backgroundImageView.setEffect(blur);

        // Create a separate Pane for the background image
        Pane backgroundPane = new Pane(backgroundImageView);

        // Create a dark transparent rectangle for the elements
        Rectangle darkRectangle = new Rectangle(1280, 720);
        darkRectangle.setFill(Color.rgb(0, 0, 0, 0.5)); // Dark transparent color

        root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        // Set the text element to instruct the user
        instructionText = new Text("Select the number of players (1-4):");
        instructionText.setFill(Color.WHITE); // Set text color to white
        instructionText.setStyle("-fx-font-size: 20px"); // Increase font size
        root.getChildren().add(instructionText);

        // Set buttons to select the number of players
        playerButtons = new HBox(10); // Horizontally align buttons
        playerButtons.setAlignment(Pos.CENTER); // Center-align buttons
        for (int i = 1; i <= 4; i++) {
            Button playerButton = new Button(Integer.toString(i));
            int finalI = i;
            playerButton.setStyle("-fx-font-size: 20px"); // Set the button style
            playerButton.setOnAction(event -> {
                // Handle player selection, e.g., show name input fields
                createNameInputFields(primaryStage, finalI);
            });
            playerButtons.getChildren().add(playerButton);
        }
        root.getChildren().add(playerButtons);

        // Create a "Start Game" button (disabled initially)
        Button startGameButton = new Button("Start Game");
        startGameButton.setDisable(true);
        startGameButton.setStyle("-fx-font-size: 20px"); // Increase font size
        startGameButton.setOnAction(event -> {
            // Handle starting the game with selected players and names
            // You'll implement this part
        });
        root.getChildren().add(startGameButton);

        // Create the scene
        Scene scene = new Scene(new StackPane(backgroundPane, darkRectangle, root), 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create name input fields based on the selected number of players
    private void createNameInputFields(Stage primaryStage, int numPlayers) {
        root.getChildren().clear(); // Clear the root layout
        root.getChildren().add(instructionText); // Re-add the instruction text
        root.getChildren().add(playerButtons); // Re-add the player buttons

        HBox nameInputFields = new HBox(10);
        nameInputFields.setAlignment(Pos.CENTER);

        // Create text fields for player names
        List<TextField> nameFields = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            TextField nameField = new TextField();
            nameField.setPromptText("Player " + i + " Name");
            nameField.setStyle("-fx-font-size: 14px; -fx-pref-width: 150px;"); // Smaller width
            nameInputFields.getChildren().add(nameField);
            nameFields.add(nameField);
        }

        // Enable the "Start Game" button when names are entered
        Button startGameButton = new Button("Start Game");
        startGameButton.setStyle("-fx-font-size: 20px"); // Increase font size
        startGameButton.setOnAction(event -> {
            boolean allNamesEntered = true;
            for (TextField nameField : nameFields) {
                String playerName = nameField.getText();
                if (playerName.isEmpty()) {
                    allNamesEntered = false;
                    break;
                }
                Player player = new Player(playerName);
                players.add(player);
            }
            if (allNamesEntered) {
                Game game = new Game(players); // Pass the player objects to the Game constructor
                game.start(primaryStage);
            } else {
                // Handle the case when not all names are entered
                // Display an error message or take appropriate action
            }
        });
    

        nameInputFields.getChildren().add(startGameButton);
        root.getChildren().addAll(nameInputFields);
    }

}

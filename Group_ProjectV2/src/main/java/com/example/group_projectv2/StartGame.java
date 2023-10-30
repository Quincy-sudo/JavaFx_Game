package com.example.group_projectv2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartGame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set game title
        primaryStage.setTitle("Making It Better");

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

        // Create a new VBox to stack the title, paragraph, and button
        VBox vbox = new VBox(10); // 10px spacing between children
        vbox.setAlignment(Pos.CENTER);

        // text for the "Making It Better" title with a bigger font size
        Text titleLabel = new Text("Making It Better");
        titleLabel.setFill(Color.WHITE);
        titleLabel.setStyle("-fx-font-size: 48px"); // Increase the font size

        // paragraph text below the title
        Text paragraphLabel = new Text("A board game set to inspire those to solve the challenges of the Pu Ngaol.");
        paragraphLabel.setFill(Color.WHITE);
        paragraphLabel.setStyle("-fx-font-size: 14px"); // Set the font size for the paragraph

        // Start Game button
        Button startGameButton = new Button("Start Game");
        startGameButton.setStyle("-fx-font-size: 18px");

        // Create and show the SelectPlayers menu
        startGameButton.setOnAction(event -> {
            SelectPlayers selectPlayersMenu = new SelectPlayers();
            selectPlayersMenu.start(primaryStage);
        });

        // Add title, paragraph, and button to the VBox
        vbox.getChildren().addAll(titleLabel, paragraphLabel, startGameButton);

        // Create a StackPane to overlay the background and other elements
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundPane, darkRectangle, vbox);

        // Create a new Scene and add the StackPane to it
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

package server;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TitleScreen {

  private static BorderPane titleLayout = new BorderPane();
  private static VBox titleSelect = new VBox();
  private static Scene titleScreen = new Scene(titleLayout, 900, 600);
  private static Label title = new Label();
  private static Button take = new Button("Take Quiz");
  private static Button create = new Button("Create");
  private static Button settings = new Button("Settings");
  private static Button exit = new Button("Exit");

  public static Scene createTitleScreen() {

    title.setText("Quiz Game");
    title.setFont(new Font(48));
    titleLayout.setTop(title);
    titleLayout.setCenter(titleSelect);
    titleSelect.getChildren().addAll(take, create, settings, exit);
    titleLayout.setAlignment(title, Pos.CENTER);

    // UI Formatting
    titleSelect.setAlignment(Pos.CENTER);
    titleSelect.setSpacing(10);
    take.setMinSize(20, 40);
    create.setMinSize(20, 40);
    settings.setMinSize(20, 40);
    exit.setMinSize(20, 40);

    return titleScreen;
  }

  public static void setButtons(Stage primaryStage) {
    take.setOnAction(e -> startTest(primaryStage));
    create.setOnAction(e -> startQCreate(primaryStage));
  }

  public static void startTest(Stage primaryStage) {

    primaryStage.setScene(MainWindow.createMainWindow());
    UserInputs.getNextQuestion();
  }

  public static void startQCreate(Stage primaryStage) {
    primaryStage.setScene(QuizCreation.createQCreateScreen());
  }
}

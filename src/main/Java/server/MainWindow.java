package server;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MainWindow {

  private static BorderPane mainLayout = new BorderPane();
  private static HBox topWindow = new HBox();
  private static Scene mainScene = new Scene(mainLayout, 900, 600);
  private static Text questionOutput = new Text();
  private static Text correctIncorrect = new Text();
  private static HBox textBox = new HBox();
  private static Label scoreBoard = new Label();

  public static void createMainWindow() {
    mainLayout.setTop(topWindow);
    mainLayout.setCenter(textBox);
    mainLayout.setRight(scoreBoard);

    textBox.getChildren().addAll(questionOutput);
    topWindow.getChildren().addAll(correctIncorrect);

    // UI formatting
    questionOutput.setFont(new Font(20));
    questionOutput.setWrappingWidth(620);
    textBox.setAlignment(Pos.CENTER);
    topWindow.setAlignment(Pos.CENTER);
    questionOutput.setTextAlignment(TextAlignment.CENTER);
    scoreBoard.setFont(new Font(30));

    // binds Label text
    scoreBoard.textProperty().bind(ScoreBoard.getScoreBoard());
    questionOutput.textProperty().bind(QuestionOutput.getQuestionOutputText());
  }

  public static void setCorrect() {
    correctIncorrect.setFill(Color.GREEN);
    correctIncorrect.setText("Correct");
  }

  public static void setIncorrect() {
    correctIncorrect.setFill(Color.RED);
    correctIncorrect.setText("Incorrect");
  }

  public static BorderPane getGameWindow() {
    return mainLayout;
  }

  public static Scene getMainScene() {
    return mainScene;
  }
}

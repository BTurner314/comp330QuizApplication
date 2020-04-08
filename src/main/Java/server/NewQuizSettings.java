package server;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class NewQuizSettings {

  private static BorderPane border = new BorderPane();
  private static HBox bottomBox = new HBox();
  private static VBox first = new VBox();
  private static VBox second = new VBox();
  private static VBox third = new VBox();
  private static Label fileName = new Label();
  private static Label rounds = new Label();
  private static Label questions = new Label();
  private static Label quizTitle = new Label();
  private static Label categoryTitle = new Label();
  private static TextField fileNameInput = new TextField();
  private static TextField roundsInput = new TextField();
  private static TextField questionsInput = new TextField();
  private static TextField categoryTitleInput = new TextField();
  private static TextField questionStatementInput = new TextField();
  private static Button next = new Button("Next");
  private static Scene newQuizSettings = new Scene(border, 900, 600);
  private static Insets inset = new Insets(20);

  private static int roundsAmount = 0;
  private static int roundCounter = 0;
  private static int questionsPerRound = 0;
  private static int questionCounter = 0;
  private static String roundType = new String();

  private static ComboBox<String> questionType = new ComboBox<String>();

  public static void createNewQuizSettings() {
    fileName.setText("Name of Quiz:");
    rounds.setText("# of rounds:");
    questions.setText("# of questions per round:");
    first.getChildren().addAll(fileName, fileNameInput);
    second.getChildren().addAll(rounds, roundsInput);
    third.getChildren().addAll(questions, questionsInput);
    bottomBox.getChildren().addAll(second, third, next);
    border.setBottom(bottomBox);
    border.setCenter(first);

    quizTitle.setFont(new Font(36));
    categoryTitle.setFont(new Font(36));
    bottomBox.setAlignment(Pos.CENTER);
    bottomBox.setSpacing(10);
    first.setAlignment(Pos.CENTER);
    second.setAlignment(Pos.CENTER);
    third.setAlignment(Pos.CENTER);
    border.setPadding(inset);

    next.setOnAction(e -> settingsInput());
    questionType.getItems().addAll("True or False", "Matching", "Multiple Choice");
  }

  public static Scene getNewQuizSettings() {
    return newQuizSettings;
  }

  public static void settingsInput() {
    roundsAmount = Integer.valueOf(roundsInput.getText());
    questionsPerRound = Integer.valueOf(questionsInput.getText());
    quizTitle.setText(fileNameInput.getText());
    // TODO Store quizTitle in JSON
    openCategoryInput();
  }

  // open the category creation window
  public static void openCategoryInput() {
    roundCounter++;
    Label categoryTitle = new Label();
    Label questionTitle = new Label();
    Button nextInput = new Button("Next");
    VBox temp = new VBox();

    temp.getChildren()
        .addAll(quizTitle, categoryTitle, categoryTitleInput, questionTitle, questionType);
    temp.setSpacing(10);
    temp.setAlignment(Pos.CENTER);
    border.getChildren().clear();
    border.setCenter(temp);
    border.setBottom(nextInput);

    questionTitle.setText("What is the question type for the round?");
    categoryTitle.setText("Round " + roundCounter + " Category");
    nextInput.setOnAction(e -> nextCategoryStart());
  }

  public static void nextCategoryStart() {

    categoryTitle.setText(categoryTitleInput.getText());
    roundType = questionType.getValue();

    if (roundType.equalsIgnoreCase("True or False")) {
      createTFInput();
    } else if (roundType.equalsIgnoreCase("Matching")) {
      createMatchingInput();
    } else if (roundType.equalsIgnoreCase("Multiple Choice")) {
      createMultipleChoiceInput();
    }
  }

  public static void nextQuestionInputCheck() {
    if (questionCounter <= questionsPerRound) {
      nextCategoryStart();
    } else {
      if (roundCounter < roundsAmount) {
        openCategoryInput();
      } else if (roundCounter >= roundsAmount) {
        // exit method
      }
    }
  }

  public static void createTFInput() {
    questionCounter++;

    VBox temp = new VBox();
    Button next = new Button("Next");
    Label tempLabel = new Label();
    ComboBox<String> choice = new ComboBox<String>();

    border.getChildren().clear();
    border.setCenter(temp);
    choice.getItems().addAll("True", "False");
    tempLabel.setText("Question " + questionCounter);
    temp.setSpacing(10);
    temp.setAlignment(Pos.CENTER);
    temp.getChildren().addAll(categoryTitle, tempLabel, questionStatementInput, choice, next);

    next.setOnAction(e -> writeAnswers());
  }

  public static void writeAnswers() {
    // TODO write to JSON
    questionStatementInput.clear();
    nextQuestionInputCheck();
  }

  public static void createMatchingInput() {}

  public static void createMultipleChoiceInput() {}
}

package server;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

  // sets how many rounds and questions per round the quiz will be as well as the name of the quiz
  public static void settingsInput() {
    // TODO add checking for non integer values
    roundsAmount = Integer.valueOf(roundsInput.getText());
    questionsPerRound = Integer.valueOf(questionsInput.getText());
    quizTitle.setText(fileNameInput.getText().trim());
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

  // depending on the type of question directs the ui creation to the right methods
  public static void nextCategoryStart() {

    categoryTitle.setText(categoryTitleInput.getText().trim());
    roundType = questionType.getValue();

    if (roundType.equalsIgnoreCase("True or False")) {
      createTFInput();
    } else if (roundType.equalsIgnoreCase("Matching")) {
      createMatchingInput();
    } else if (roundType.equalsIgnoreCase("Multiple Choice")) {
      createMultipleChoiceInput();
    }
  }

  // keeps track of how many question and rounds have been entered to keep the right amount
  public static void nextQuestionInputCheck() {
    if (questionCounter <= questionsPerRound) {
      nextCategoryStart();
    } else {
      if (roundCounter < roundsAmount) {
        openCategoryInput();
      } else if (roundCounter >= roundsAmount) {
        // TODO exit method
      }
    }
  }

  // create the ui for a True false question input
  public static void createTFInput() {
    questionCounter++;

    VBox temp = new VBox();
    Button next = new Button("Next");
    Label tempLabel = new Label();
    ComboBox<String> choice = new ComboBox<String>();
    TextField questionStatementInput = new TextField();

    border.getChildren().clear();
    border.setCenter(temp);
    choice.getItems().addAll("True", "False");
    tempLabel.setText("Question " + questionCounter);
    temp.setSpacing(10);
    temp.setAlignment(Pos.CENTER);
    temp.getChildren().addAll(categoryTitle, tempLabel, questionStatementInput, choice, next);

    next.setOnAction(
        e -> {
          // TODO write to JSON
          nextQuestionInputCheck();
        });
  }

  // create the ui for a matching question input
  public static void createMatchingInput() {
    questionCounter++;

    VBox tempV = new VBox();
    HBox tempH = new HBox();
    VBox list1 = new VBox();
    VBox list2 = new VBox();
    Label question = new Label();
    Label match1 = new Label();
    Label match2 = new Label();
    ListView<String> matchingASet = new ListView<String>();
    ListView<String> matchingBSet = new ListView<String>();
    TextField matchingA = new TextField();
    TextField matchingB = new TextField();
    TextField questionStatementInput = new TextField();
    Button next = new Button("Next");

    tempV.setSpacing(10);
    tempV.setAlignment(Pos.CENTER);
    question.setText("Question " + questionCounter);
    match1.setText("Matching Set A:");
    match2.setText("Matching Set B:");

    matchingA.setOnAction(
        e -> {
          if (!matchingA.getText().trim().isEmpty()) {
            matchingASet.getItems().add(matchingA.getText().trim());
            matchingA.clear();
          }
        });

    matchingB.setOnAction(
        e -> {
          if (!matchingB.getText().trim().isEmpty()) {
            matchingBSet.getItems().add(matchingB.getText().trim());
            matchingB.clear();
          }
        });

    tempH.getChildren().addAll(list1, list2);
    list1.getChildren().addAll(match1, matchingA, matchingASet);
    list2.getChildren().addAll(match2, matchingB, matchingBSet);
    tempV.getChildren().addAll(question, questionStatementInput, tempH, next);

    border.getChildren().clear();
    border.setCenter(tempV);

    next.setOnAction(
        e -> {
          if (matchingASet.getItems().size() != matchingBSet.getItems().size()) {
            Alert alert =
                new Alert(
                    Alert.AlertType.ERROR,
                    "Must have the same amount of matching statements in both lists.",
                    ButtonType.OK);
            alert.show();
          } else {
            // TODO write to JSON
            nextQuestionInputCheck();
          }
        });
  }

  // create the ui for a multiple choice question input
  public static void createMultipleChoiceInput() {
    questionCounter++;
    VBox tempV = new VBox();
    Label question = new Label();
    Label answers = new Label();
    ListView<String> mcSet = new ListView<String>();
    TextField questionStatementInput = new TextField();
    TextField mcInput = new TextField();
    Button next = new Button("Next");

    mcInput.setOnAction(
        e -> {
          if (!mcInput.getText().trim().isEmpty()) {
            mcSet.getItems().add(mcInput.getText().trim());
            mcInput.clear();
          }
        });

    tempV.setSpacing(10);
    tempV.setAlignment(Pos.CENTER);
    question.setText("Question " + questionCounter);
    answers.setText("Input Multiple Choice Options:");
    tempV.getChildren().addAll(question, questionStatementInput, answers, mcInput, mcSet, next);

    border.getChildren().clear();
    border.setCenter(tempV);

    next.setOnAction(
        e -> {
          // TODO write to JSON
          nextQuestionInputCheck();
        });
  }
}

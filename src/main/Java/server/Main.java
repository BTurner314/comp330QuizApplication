package server;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private static Stage primary;

  public void start(Stage primaryStage) throws Exception {
    primary = primaryStage;
    QProcessor tester = new QProcessor("TestQProcessor.json");

    TitleScreen.createTitleScreen();
    QuizCreation.createQCreateScreen();
    MainWindow.createMainWindow();
    NewQuizSettings.createNewQuizSettings();

    // Quiz Elements
    primaryStage.setTitle("Quiz Game");
    primaryStage.setScene(TitleScreen.getTitleScreen());
    primaryStage.show();
  }

  public static Stage getPrimaryStage() {
    return primary;
  }
}

package server;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) throws Exception {

    QProcessor tester = new QProcessor("TestQProcessor.json");

    // Quiz Elements
    primaryStage.setTitle("Quiz Game");
    primaryStage.setScene(TitleScreen.createTitleScreen());

    // Button Functions
    TitleScreen.setButtons(primaryStage);
    primaryStage.show();
  }
}

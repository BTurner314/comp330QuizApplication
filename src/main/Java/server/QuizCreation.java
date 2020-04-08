package server;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuizCreation {

  private static BorderPane mainLayout = new BorderPane();
  private static VBox qSelect = new VBox();
  private static Label title = new Label();
  private static Button upload = new Button("Upload");
  private static Button create = new Button("Create New Quiz");
  private static Button download = new Button("Download");
  private static Button exit = new Button("Exit");
  private static Scene qCreation = new Scene(mainLayout, 900, 600);

  public static void createQCreateScreen() {

    title.setText("Quiz Creation");
    title.setFont(new Font(48));
    mainLayout.setTop(title);
    mainLayout.setCenter(qSelect);
    qSelect.getChildren().addAll(create, upload, download, exit);
    mainLayout.setAlignment(title, Pos.CENTER);

    // UI Formatting
    qSelect.setAlignment(Pos.CENTER);
    qSelect.setSpacing(10);
    upload.setMinSize(20, 40);
    create.setMinSize(20, 40);
    download.setMinSize(20, 40);
    exit.setMinSize(20, 40);

    // Sets buttons functions
    exit.setOnAction(e -> TitleScreen.changeScene(TitleScreen.getTitleScreen()));
    create.setOnAction(e -> TitleScreen.changeScene(NewQuizSettings.getNewQuizSettings()));
  }

  public static Scene getQCreationScene() {
    return qCreation;
  }
}

package sample;/**
 * Created by User on 17.11.2016.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WordRoom extends Application {
    private Label enWord;
    private TextField enText;
    private Button enBut;

    private Label translate;
    private TextField trText;
    private Button trBut;


    private Label wordInfo;
    private TextArea wiText;
    private TextArea trArea;

    private Pane base;
    private Scene mScene;
    private double borderX = 600.0;
    private double borderY = 400.0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        base = new Pane();
        base.maxWidth(borderX);
        base.maxHeight(borderY);
        base.minWidth(borderX);
        base.minHeight(borderY);
        base.prefWidth(borderX);
        base.prefHeight(borderY);

        enWord = new Label("English word");
        enWord.setLayoutX(37.0);
        enWord.setLayoutY(97.0);

        enText = new TextField();
        enText.setLayoutX(120.0);
        enText.setLayoutY(92.0);

        enBut = new Button("Confirm");
        enBut.setLayoutX(286.0);
        enBut.setLayoutY(92.0);


        translate = new Label("Translation");
        translate.setLayoutX(41.0);
        translate.setLayoutY(137.0);

        trText = new TextField();
        trText.setLayoutX(120.0);
        trText.setLayoutY(133.0);

        trBut = new Button("Add");
        trBut.setLayoutX(286.0);
        trBut.setLayoutY(133.0);
        trBut.setPrefSize(58.0, 25.0);

        wordInfo = new Label("Word info");
        wordInfo.setAlignment(Pos.CENTER);
        wordInfo.setLayoutX(424.0);
        wordInfo.setLayoutY(58.0);
        wordInfo.setContentDisplay(ContentDisplay.CENTER);
        wordInfo.setPrefSize(95.0, 17.0);

        wiText = new TextArea();
        wiText.setLayoutX(371.0);
        wiText.setLayoutY(86.0);
        wiText.setMaxSize(200,20);


        trArea = new TextArea();
        trArea.setLayoutX(371.0);
        trArea.setLayoutY(133.0);
        trArea.setMaxSize(200,200);



        base.getChildren().addAll(enWord, enText, enBut, translate, trText, trBut, wordInfo, wiText, trArea);
        mScene = new Scene(base, borderX, borderY);
        primaryStage.setScene(mScene);
        primaryStage.show();
    }

}


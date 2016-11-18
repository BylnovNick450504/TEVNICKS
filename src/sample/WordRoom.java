package sample;/**
 * Created by Nickolay on 15.11.2016.
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class WordRoom extends Application {
    private Label enWord;
    private TextField enText;
    private Button enBut;
    private Label enEr;


    private Label translate;
    private TextField trText;
    private Button trBut;


    private Label wordInfo;
    private TextField wiText;
    private ListView trArea;
    private Button addW;

    private Pane base;
    private Scene mScene;

    private double borderX = 600.0;
    private double borderY = 400.0;

    public static void main(String[] args) {
        launch(args);
    }

    private void setEnGUI() {
        enWord = new Label("English word");
        enWord.setLayoutX(37.0);
        enWord.setLayoutY(97.0);


        enText = new TextField();
        enText.setLayoutX(120.0);
        enText.setLayoutY(92.0);

        enBut = new Button("Confirm");
        enBut.setLayoutX(286.0);
        enBut.setLayoutY(92.0);
        enBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enErDown();
                String temp = enText.getText();
                enText.clear();
                if(isEnValid(temp)) {
                    wiText.setText(temp);
                } else {
                    enErUp("Use a-z");
                }
            }
        });


        enEr = new Label("Use a-z");
        enEr.setLayoutX(120.0);
        enEr.setLayoutY(67.0);
        enEr.setPrefSize(58.0, 17.0);
        enEr.setMaxWidth(80);
        enEr.setTextFill(Color.RED);
        enEr.setVisible(false);
    }

    private void setTrGUI() {
        translate = new Label("Translation");
        translate.setLayoutX(41.0);
        translate.setLayoutY(137.0);

        trText = new TextField();
        trText.setLayoutX(120.0);
        trText.setLayoutY(133.0);

        trBut = new Button("Add");
        trBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enErDown();
                String temp = trText.getText();
                trText.clear();
                if(isRusValid(temp)) {
                    trArea.getItems().add(temp);
                } else {

                    enErUp("Use а-я");
                }
            }
        });



        trBut.setLayoutX(286.0);
        trBut.setLayoutY(133.0);
        trBut.setPrefSize(58.0, 25.0);
    }

    private void setWiGUI() {
        wordInfo = new Label("Word info");
        wordInfo.setAlignment(Pos.CENTER);
        wordInfo.setLayoutX(424.0);
        wordInfo.setLayoutY(58.0);
        wordInfo.setContentDisplay(ContentDisplay.CENTER);
        wordInfo.setPrefSize(95.0, 17.0);

        wiText = new TextField();
        wiText.setLayoutX(371.0);
        wiText.setLayoutY(86.0);
        wiText.setMaxSize(200,20);
        wiText.setPrefSize(200,30);
        wiText.setEditable(false);

        trArea = new ListView();
        trArea.setLayoutX(371.0);
        trArea.setLayoutY(133.0);
        trArea.setMaxSize(200,200);

        addW = new Button("Add word to dictionary");
        addW.setLayoutX(402.0);
        addW.setLayoutY(348.0);
    }

    private void drawAll() {
        base.getChildren().addAll(enWord, enText, enBut, translate, trText, trBut,
                                  wordInfo, wiText, trArea, addW, enEr);
        mScene = new Scene(base, borderX, borderY);

    }

    private boolean isEnValid(String temp) {

        if(temp.equals("")){
            return false;
        }
        char[] test = temp.toCharArray();
        for (char x : test) {
            if(x  < 'a' || x > 'z') {
                return false;
            }
        }
        return true;
    }

    private boolean isRusValid(String temp) {

        if(temp.equals("")){
            return false;
        }
        char[] test = temp.toCharArray();
        for (char x : test) {
            if(x  < 'а' || x > 'я') {
                return false;
            }
        }
        return true;
    }

    private void enErUp(String value) {
        enEr.setText(value);
        enEr.setVisible(true);
    }

    private void enErDown() {
        enEr.setVisible(false);
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

        setEnGUI();
        setTrGUI();
        setWiGUI();
        drawAll();

        primaryStage.setScene(mScene);
        primaryStage.show();

    }

}


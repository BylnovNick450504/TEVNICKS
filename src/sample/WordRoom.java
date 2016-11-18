package sample;/**
 * Created by Nickolay on 15.11.2016.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.TreeSet;

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

    private Word curWord;

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

        setTranslateList();
        addWord();

    }

    private void drawAll() {
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

    private void setTranslateList() {
        trArea = new ListView();
        trArea.setLayoutX(371.0);
        trArea.setLayoutY(133.0);
        trArea.setMaxSize(200,200);
        trArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (trArea.getItems().isEmpty() == false) {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        int value = trArea.getSelectionModel().getSelectedIndex();
                        try {
                            trArea.getItems().remove(value);
                        } catch(ArrayIndexOutOfBoundsException ex) {
                        }
                    }
                }
            }
        });

    }

    private void addWord() {
        addW = new Button("Add word to dictionary");
        addW.setLayoutX(402.0);
        addW.setLayoutY(348.0);
        addW.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enErDown();
                boolean flagEnWord = true;
                boolean flagTranslation = true;

                if(isEnWordValid()) {
                    enErUp("Enter word");
                    flagEnWord = false;
                }
                if(flagEnWord) {
                    if (!isTranslationValid()) {
                        enErUp("Enter translation");
                        flagTranslation = false;
                    }
                }
                if(flagEnWord && flagTranslation) {
                    String tempWord = wiText.getText();
                    Object[] tempTr =  trArea.getItems().toArray();
                    String[] strings = Arrays.stream(tempTr).toArray(String[]::new);
                    TreeSet<String> listTr = new TreeSet<String>(Arrays.asList(strings));
                    curWord = new Word(tempWord, listTr);
                    System.out.println(curWord);
                    DataBase db = DataBase.getInstance();
                    db.writeFile(curWord);
                    db.show();
                    wiText.clear();
                    trArea.getItems().clear();
                }
            }
        });
    }

    private  boolean isEnWordValid() {
        return wiText.getText().equals("");
    }

    private  boolean isTranslationValid() {
        return !trArea.getItems().isEmpty();
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

        //drawAll();

        RunTest test = new RunTest();
        //primaryStage.setScene(mScene);
        primaryStage.setScene(test.getRunTest());
        primaryStage.show();


    }

}


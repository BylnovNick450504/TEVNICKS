package sample;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Pair;

/**
 * Created by User on 17.11.2016.
 */
public class RunTest {
    private int testSize = 5;
    private LinkedList<Pair<Word,String>> test;
    private DataBase db;

    private Label enWord;
    private TextField enTF;
    private Button enAns;
    private ProgressBar progress;
    private Pane base;
    private Scene mScene;
    private int count = 0;
    private double borderX = 600.0;
    private double borderY = 400.0;





    private void setElements() {
        enWord = new Label(test.get(count).getKey().getWord());
        enWord.setLayoutX(111.0);
        enWord.setLayoutY(147.0);
        enWord.setFont(Font.font(18));

        enTF = new TextField();
        enTF.setLayoutX(111.0);
        enTF.setLayoutY(200.0);

        enAns = new Button("Answer");
        enAns.setLayoutX(281.0);
        enAns.setLayoutY(200.0);
        enAns.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(count == testSize) {
                    refresh();
                    return;
                } else {
                    String temp = enTF.getText();
                    Pair<Word, String> x = test.get(count);
                    Word chword = x.getKey();
                    test.set(count++, new Pair<>(chword, temp));
                    System.out.println(count);
                    enWord.setText(test.get(count).getKey().getWord());
                    enTF.clear();
                }
            }
        });

        progress = new ProgressBar();
        progress.setPrefSize(59.0, 18.0);
        progress.setLayoutX(166.0);
        progress.setLayoutY(59.0);
    }
    private void refresh() {
        drawAll();
    }
    private int checkResult() {
        int i = 0;
        for (Pair<Word, String> x : test) {
            if(x.getKey().checkAnswer(x.getValue())) {
                i++;
            }
        }
        return i;
    }

    public RunTest() {
        test = new LinkedList<>();
        drawAll();
    }

    public void drawAll() {

        base = new Pane();
        createTest();
        base.setPrefSize(borderX, borderY);
        setElements();
        base.getChildren().addAll(enWord, enTF, enAns, progress);
        mScene = new Scene(base, borderX, borderY);

    }

    public Scene getRunTest() {
        return mScene;
    }

    private void createTest() {
        db = DataBase.getInstance();
        count = 0;
        test.clear();
        LinkedList<Word> all = db.getInfo();

        Random rand = new Random(all.size());
        for(int i = 0; i < testSize; i++) {
            test.add(new Pair<>(all.get(rand.nextInt(all.size())),new String("")));
        }

    }
}

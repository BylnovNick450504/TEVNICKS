package sample;

import java.io.*;
import java.util.TreeSet;

/**
 * Created by Nickolay on 15.11.2016.
 */
public class Word implements Serializable{
    private String enWord;
    private TreeSet<String> tr;

    public Word() {
        this.enWord = new String("");
        tr = new TreeSet<>();

    }

    public Word(String word, TreeSet<String> translation) {
        enWord = word;
        tr = translation;
    }

    void setWord(String word) {
        enWord = word;
    }

    public String getWord() {
        return enWord;
    }

    public void addTranslation(String word) {
        tr.add(word);
    }

    public boolean checkAnswer(String answer) {
        for (String x : tr) {
            if(x.equals(answer)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return new String(enWord + ": " + tr.toString());
    }


}

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

    void setWord(String word) {
        enWord = word;
    }

    public String getWord() {
        return enWord;
    }

    public void addTranslation(String word) {
        tr.add(word);
    }



    @Override
    public String toString() {
        return new String(enWord + ": " + tr.toString());
    }


}

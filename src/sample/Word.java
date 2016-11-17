package sample;

import java.io.*;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by User on 18.11.2016.
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

    public void writeFile() {
        try(FileOutputStream fos = new FileOutputStream("BD.out")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Word readFile() {
        Word temp;
        temp = new Word();
        try (FileInputStream fis = new FileInputStream("BD.out")) {
            ObjectInputStream oin = new ObjectInputStream(fis);
            try {
                temp = (Word) oin.readObject();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return temp;
    }

    @Override
    public String toString() {
        return new String(enWord + ": " + tr.toString());
    }


}

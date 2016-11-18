package sample;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by Nickolay on 15.11.2016.
 */
public class DataBase implements Serializable {
    private final String source = "DB.txt";
    private final static DataBase inst = new DataBase();
    private LinkedList<Word> dictionary;

    private DataBase() {
        dictionary = new LinkedList<>();
    }

    private void addWord(Word x) {
        dictionary.add(x);
    }
    public static DataBase getInstance() {
        return inst;
    }

    public void writeFile(Word temp) {
        readFile();
        addWord(temp);
        try(FileOutputStream fos = new FileOutputStream(source)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dictionary);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readFile() {
        try (FileInputStream fis = new FileInputStream(source)) {
            ObjectInputStream oin = new ObjectInputStream(fis);
            try {
                dictionary = (LinkedList<Word>) oin.readObject();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        readFile();
        for (Word x : dictionary) {
            System.out.println(x);
        }
    }
}

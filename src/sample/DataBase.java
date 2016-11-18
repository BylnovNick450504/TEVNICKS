package sample;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;

/**
 * Created by Nickolay on 15.11.2016.
 */
public class DataBase implements Serializable {
    private final String source = "DB.txt";
    private final static DataBase inst = new DataBase();
    private LinkedList<Word> dictionary;
    private static final long serialVersionUID = 6529685098267757690L;

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
            Word[] array = new Word[dictionary.size()];
            dictionary.toArray(array);
            oos.writeObject(array);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readFile() {
        try (FileInputStream fis = new FileInputStream(source)) {
            ObjectInputStream oin = new ObjectInputStream(fis);
            try {

                Word[] x = (Word[]) oin.readObject();
                LinkedList<Word> list = new LinkedList<>(Arrays.asList(x));
                dictionary = list;

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            catch(InvalidClassException z) {
                z.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Word> getInfo() {
        readFile();
        return dictionary;
    }
    public void show() {
        readFile();
        for (Word x : dictionary) {
            System.out.println(x);
        }
    }
}

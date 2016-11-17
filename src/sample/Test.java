package sample;

import java.util.LinkedList;

/**
 * Created by User on 18.11.2016.
 */
public class Test {
    public static void main(String args[]) {
        Word cat = new Word();
        cat.setWord("cat");
        cat.addTranslation("кот");
        cat.addTranslation("кот");
        cat.addTranslation("котик");
        cat.writeFile();        
        System.out.println( Word.readFile());
    }
}

package sample;

import java.util.LinkedList;

/**
 * Created by Nickolay on 15.11.2016.
 */
public class Test {
    public static void main(String args[]) {
        DataBase db;
        db = DataBase.getInstance();
        Word cat = new Word();
        Word dog = new Word();
        cat.setWord("cat");
        dog.setWord("dog");
        cat.addTranslation("кот");
        cat.addTranslation("кот");
        cat.addTranslation("котик");
        dog.addTranslation("собака");
        dog.addTranslation("пес");
        //System.out.println(dog);
        //db.writeFile(cat);
        //db.writeFile(dog);
        db.show();
        //cat.writeFile();
        //System.out.println( Word.readFile());
    }
}

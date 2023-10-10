
package code;

import java.util.LinkedList;

/**
 * The Category class represents a single category and holds its questions.
 */
public class Category {
    private final String NAME;
    private final LinkedList<String> QUESTIONS;


    public Category(String name) {
        this.NAME = name;
        this.QUESTIONS = new LinkedList<>();

        for (int i = 0; i < 50; i++) {
            this.QUESTIONS.addLast(name + " Question " + i);
        }
    }


    public String getName() {
        return this.NAME;
    }

    public String getQuestion() {
        return this.QUESTIONS.removeFirst();
    }
}

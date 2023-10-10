
package code;

import java.util.HashMap;
import java.util.Map;

/**
 * The QuestionManager class creates and holds Category objects.
 */
public class QuestionManager {
    private final Map<String, Category> CATEGORIES;


    public QuestionManager() {
        this.CATEGORIES = new HashMap<>();
        CATEGORIES.put("Pop", new Category("Pop"));
        CATEGORIES.put("Science", new Category("Science"));
        CATEGORIES.put("Sports", new Category("Sports"));
        CATEGORIES.put("Rock", new Category("Rock"));
    }


    public String getQuestion(String category) {
        return CATEGORIES.get(category).getQuestion();
    }
}

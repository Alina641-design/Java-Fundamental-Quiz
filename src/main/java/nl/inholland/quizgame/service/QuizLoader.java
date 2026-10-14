package nl.inholland.quizgame.service;
import com.fasterxml.jackson.databind.JsonNode;
import nl.inholland.quizgame.model.Question;
import nl.inholland.quizgame.model.Quiz;
import nl.inholland.quizgame.model.QuizPage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class QuizLoader {
    private final QuizJsonReader reader = new QuizJsonReader();
    private final QuestionJsonMapper questionMapper = new QuestionJsonMapper();
    private int skippedQuestions;

    public Quiz loadQuiz(File file) throws IOException {
        skippedQuestions = 0;
        JsonNode root = reader.readFile(file);
        List<QuizPage> pages = readPages(root.path("pages"));
        if (pages.isEmpty()) {
            throw new IOException("The quiz does not contain any questions. ");
        }
        return new Quiz(root.path("quizId").asText(),
                root.path("title").asText(),
                root.path("description").asText(""), pages);
    }

    private List<QuizPage> readPages(JsonNode nodes) throws IOException {
        List<QuizPage> pages = new ArrayList<>();
        for (JsonNode node : nodes) {
            QuizPage page = readPage(node);
            if (page != null) {
                pages.add(page);
            }
        }
            return pages;
        }
        private QuizPage readPage (JsonNode node) throws IOException {
            JsonNode elements = node.path("elements");
            if (!node.isObject() || !elements.isArray() || elements.size() != 1) {
                throw new IOException("The quiz does not contain any questions. ");
            }
            Question question = questionMapper.mapQuestion(elements.get(0));
            if (question == null) {
                skippedQuestions++;
                return null;
            }
            return new QuizPage(readTimeLimit(node), question);
        }
        private int readTimeLimit (JsonNode node) throws IOException {
            JsonNode value = node.path("timeLimit");
            if (!value.isIntegralNumber() || !value.canConvertToInt() || value.intValue() <= 0) {
                throw new IOException("The time limit must be a positive integer.");
            }
            return value.intValue();
        }
        public int getSkippedQuestions () {
            return skippedQuestions;
        }
    }


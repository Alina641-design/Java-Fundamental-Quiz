package nl.inholland.quizgame.service;
import com.fasterxml.jackson.databind.JsonNode;
import nl.inholland.quizgame.model.BooleanQuestion;
import nl.inholland.quizgame.model.MultipleChoiceQuestion;
import nl.inholland.quizgame.model.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class QuestionJsonMapper {
    public Question mapQuestion(JsonNode node) throws IOException{
        if (node == null || !node.isObject()) {
            throw new IOException ("A questionis invalid.");
        }
        String type = readRequiredText(node,"type");
        if (type.equals("radiogroup")) {
            return mapMultipleChoice(node);
        }
        if (type.equals("boolean")) {
            return mapBoolean(node);
        }
        return null;
    }
    private MultipleChoiceQuestion mapMultipleChoice(JsonNode node) throws IOException {
        List<String> choices = readChoices(node);
        String answer = readRequiredText(node, "correctAnswer");
        if(!choices.contains(answer)) {
            throw new IOException ("A correct answer is missing from its choices.");
        }
        return new MultipleChoiceQuestion(
                readRequiredText(node,"name"),
                readRequiredText(node,"title"),
                readIsRequired(node), choices,
                readOptionalText(node,"choicesOrder","none"), answer);
    }
    private BooleanQuestion mapBoolean(JsonNode node) throws IOException {
        JsonNode answer = node.path("correctAnswer");
        if (!answer.isBoolean()) {
            throw new IOException ("A true/false question needs a boolean correct answer.");
        }
        return new BooleanQuestion(
                readRequiredText(node,"name"),
                readRequiredText(node,"title"),
                readIsRequired(node),
                readOptionalText(node,"labelTrue", "True"),
                readOptionalText(node,"labelFalse", "False"),
                answer.booleanValue());
    }
    private List<String> readChoices(JsonNode node) throws IOException {
        JsonNode values = node.path("choices");
        if (!values.isArray() || values.size() == 0) {
            throw new IOException ("A multiplr choice quiestion needs answer options.");
        }
        List<String> choices = new ArrayList<>();
        for (JsonNode value : values) {
            if(!value.isTextual() || value.asText().isBlank()) {
                throw new IOException("Answer options must contain text.");
            }
            choices.add(value.asText());
        }
        return choices;
    }
    private String readRequiredText(JsonNode node, String field) throws IOException {
        JsonNode value = node.path(field);
        if(!value.isTextual() || value.asText().isBlank()) {
            throw new IOException("Question field" + field + "must contain text.");

        }
        return value.asText();
    }
    private String readOptionalText(JsonNode node, String field,
                                    String fallback) throws IOException {
        JsonNode value = node.path(field);
        if (value.isMissingNode() || value.isNull()) {
            return fallback;
        }
        return readRequiredText(node, field);
    }
    private boolean readIsRequired(JsonNode node) throws IOException {
        JsonNode value = node.path("isRequired");
        if (value.isMissingNode() || value.isNull()) {
            return false;
        }
        if (!value.isBoolean()) {
            throw new IOException("isRequired must be true or false.");
        }
        return value.booleanValue();
    }
}

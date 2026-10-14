package nl.inholland.quizgame.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class QuizJsonReader {
    private final ObjectMapper mapper = new ObjectMapper();
    public JsonNode readFile(File file) throws IOException {
        validateFile(file);
        JsonNode root = readJson(file);
        if(!isQuiz(root)){
            throw new IOException("File is not a Quiz File");

        }
        return root;
    }
    private void validateFile(File file) throws IOException {
        if (file == null || !file.isFile() || !file.canRead()){
            throw new IOException("Selected file is not readable.");
        }
        if(file.length() == 0){
            throw new IOException("Selected file is empty.");
        }
    }
    private  JsonNode readJson (File file) throws IOException {
        try {
            return mapper.readTree(file);
        }catch  (JsonProcessingException e){
            throw new IOException("The selected file is not valid.", e);
        }catch  (IOException e){
            throw new IOException("The file is not readable. Choose the correct file.", e);
        }
    }
    private boolean isQuiz(JsonNode root){
        if (root== null || !root.isObject()){
            return false;
        }
        return root.path("quizId").isTextual()
                && !root.path("quizId").asText().isBlank()
                && root.path("title").isTextual()
                && !root.path("title").asText().isBlank()
                && root.path("pages").isArray()
                && root.path("pages").size() > 0;
    }
}
